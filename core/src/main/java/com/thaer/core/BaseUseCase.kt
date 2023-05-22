package com.thaer.core

import com.thaer.core.repository.BaseRepository
import com.thaer.core.repository.RepositoryBinding
import com.thaer.core.repository.RepositoryBindingDelegate

abstract class BaseUseCase<BR: BaseRepository>: RepositoryBinding<BR> by RepositoryBindingDelegate() {

    private val bindRepository by lazy {
        bindRepository()
    }

    init {
        bindRepository
    }
}