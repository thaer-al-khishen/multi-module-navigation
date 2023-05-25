package com.thaer.core.fragment_input_data_utils

import android.os.Build
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import com.thaer.core.error_handling.GenericErrorHandlingController

inline fun <reified T : DataInputClass> Fragment.getInputData(): T? {
    arguments?.let {
        val navKey = T::class.java.newInstance().getNavKey()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return it.getSerializable(navKey, T::class.java)
        } else {
            return it.getSerializable(navKey) as? T
        }
    } ?: run {
        return null
    }
}

fun <T : DataInputClass> Fragment.getInputData(dataClass: T): T? {
    arguments?.let {
        val navKey = dataClass.getNavKey()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return it.getSerializable(navKey, dataClass.javaClass)
        } else {
            return it.getSerializable(navKey) as? T
        }
    } ?: run {
        return null
    }
}

data class GenericDataInput<T>(
    private val data: Array<out T>
): java.io.Serializable {
    fun printAllData() {
        for (item in data) {
            println(item)
        }
    }

    companion object {
        fun <T> create(vararg data: T): GenericDataInput<T> {
            return GenericDataInput(data)
        }
    }
}

fun main() {
    val dataInput = GenericDataInput.create(1, "Two", 3, 4, 5)
    val jsonObj = constructJsonObject(
        Pair("name", "Thaer"), Pair("Age", 26), Pair("DataClass", GenericDataInput.create("Attempt"))
    )

    println(jsonObj.extract("DataClass"))
}

fun <T> constructJsonObject(vararg data: Pair<String, T>): JsonObject {
    val jo = JsonObject()
    val gson = Gson()
    jo.apply {
        for (item in data) {
            when(item.second) {
                is Number -> {
                    addProperty(item.first, item.second as Number)
                }
                is String -> {
                    addProperty(item.first, item.second as String)
                }
                is Char -> {
                    addProperty(item.first, item.second as Char)
                }
                is Boolean -> {
                    addProperty(item.first, item.second as Boolean)
                }
                is java.io.Serializable -> {
                    add(item.first, gson.toJsonTree(item.second))
                }
                else -> {
                    GenericErrorHandlingController.getInterface()?.onGenericError("Json Object error", "This type can't be handled by a jsonObject: ${item.first}: ${item.second}")
                }
            }
        }
    }
    return jo
}

fun JsonObject.extract(key: String): JsonElement? {
    if (this.get(key) != null) {
        return this.get(key)
    } else {
        GenericErrorHandlingController.getInterface()?.onGenericError("Json Object error", "Failed to extract value from incorrect key: $key")
        return null
    }
}

fun JsonObject.extractString(key: String): String? {
    return try {
        this.get(key)?.asString
    } catch (e: ClassCastException) {
        GenericErrorHandlingController.getInterface()?.onGenericError("Json Object error", "Failed to extract a string value from key: $key")
        null
    }
}

fun JsonObject.extractNumber(key: String): Number? {
    return try {
        this.get(key)?.asNumber
    } catch (e: ClassCastException) {
        GenericErrorHandlingController.getInterface()?.onGenericError("Json Object error", "Failed to extract a number value from key: $key")
        null
    }
}

fun JsonObject.extractBoolean(key: String): Boolean? {
    return try {
        this.get(key)?.asBoolean
    } catch (e: ClassCastException) {
        GenericErrorHandlingController.getInterface()?.onGenericError("Json Object error", "Failed to extract a boolean value from key: $key")
        null
    }
}

inline fun <reified T: java.io.Serializable>JsonObject.extractSerializable(key: String): T? {
    val gson = Gson()
    return try {
        this.get(key)?.let { gson.fromJson(it, T::class.java) }
    } catch (e: Exception) {
        when (e) {
            is ClassCastException, is JsonSyntaxException -> {
                GenericErrorHandlingController.getInterface()?.onGenericError("Json Object JsonSyntaxException error", "Failed to extract a serializable value from key: $key")
                null
            }
            else -> {
                GenericErrorHandlingController.getInterface()?.onGenericError("Json Object error", "Failed to extract a serializable value from key: $key")
                null
            }
        }
    }
}
