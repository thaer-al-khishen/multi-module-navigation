package com.thaer.home

import com.thaer.core.fragment_input_data_utils.DataInputClass

data class HomeFragmentInput(
    var name: String = "",
    var message: String = "",
    var description: String = "",
    var homeInputSource: HomeInputSource = HomeInputSource.NONE
): DataInputClass()

enum class HomeInputSource {
    FROM_TEST_1, FROM_TEST_2, NONE
}