package com.thaer.core.repository

import com.thaer.core.BaseUseCase
import java.lang.reflect.ParameterizedType

interface RepositoryBinding<BR: BaseRepository> {
    val repository: BR
    fun BaseUseCase<BR>.bindRepository()
}

class RepositoryBindingDelegate<BR: BaseRepository> : RepositoryBinding<BR> {

    private var _repository: BR? = null

    override val repository: BR
        get() = requireNotNull(_repository) { "The property of repository has been destroyed." }

    override fun BaseUseCase<BR>.bindRepository() {
        if (_repository == null) {
            val repositoryClass: Class<BR> = getGenericRepository(this) as Class<BR>
            _repository = repositoryClass.newInstance()
        }
    }

    private fun getGenericRepository(genericOwner: Any): Class<BaseRepository>? {
        val genericSuperclass = genericOwner.javaClass.genericSuperclass
        val superclass = genericOwner.javaClass.superclass
        if (superclass != null) {
            if (genericSuperclass is ParameterizedType) {
                for (argument in genericSuperclass.actualTypeArguments) {
                    if (((argument) as Class<*>).superclass.equals(BaseRepository::class.java)) {
                        return ((argument) as Class<BaseRepository>)
                    }
                }
            }
        }
        return null
    }

}
