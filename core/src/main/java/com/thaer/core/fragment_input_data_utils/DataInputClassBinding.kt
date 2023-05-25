package com.thaer.core.fragment_input_data_utils

import androidx.fragment.app.Fragment
import java.lang.reflect.ParameterizedType

interface DataInputClassBinding<DIC: DataInputClass> {
    val dataInputs: DIC
    fun Fragment.createDataInputClass()
}

class DataInputClassBindingDelegate<DIC: DataInputClass> : DataInputClassBinding<DIC> {

    private var _dic: DIC? = null

    override val dataInputs: DIC
        get() = requireNotNull(_dic) { "The property of dataInputClass has been destroyed." }

    override fun Fragment.createDataInputClass() {
        if (_dic == null) {
            val dataInputClass: Class<DIC> = getGenericDataInputClass(this) as Class<DIC>
            _dic = getInputData(dataInputClass.newInstance())
        }
    }

    private fun getGenericDataInputClass(genericOwner: Any): Class<DataInputClass>? {
        val genericSuperclass = genericOwner.javaClass.genericSuperclass
        val superclass = genericOwner.javaClass.superclass
        if (superclass != null) {
            if (genericSuperclass is ParameterizedType) {
                for (argument in genericSuperclass.actualTypeArguments) {
                    if (((argument) as Class<*>).superclass.equals(DataInputClass::class.java)) {
                        return ((argument) as Class<DataInputClass>)
                    }
                }
            }
        }
        return null
    }

}
