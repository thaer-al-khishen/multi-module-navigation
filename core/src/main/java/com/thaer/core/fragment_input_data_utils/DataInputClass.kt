package com.thaer.core.fragment_input_data_utils

abstract class DataInputClass: java.io.Serializable {
    fun getNavKey(): String = javaClass.name
}
