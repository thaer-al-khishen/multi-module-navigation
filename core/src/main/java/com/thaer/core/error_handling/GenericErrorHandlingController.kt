package com.thaer.core.error_handling

import com.thaer.core.navigation.BaseNavigationController

object GenericErrorHandlingController: BaseNavigationController<GenericErrorHandlingController.IErrorHandling>() {
    interface IErrorHandling {
        fun onGenericError(title: String, message: String)
    }
}
