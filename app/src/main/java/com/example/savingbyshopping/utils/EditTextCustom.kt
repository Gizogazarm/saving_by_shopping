package com.example.savingbyshopping.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

open class EditTextCustom @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : TextInputEditText(context, attrs) {

    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val parentLayout = parent?.parent
                if (parentLayout is TextInputLayout) {
                    parentLayout.error = null
                    parentLayout.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    fun isValid(message: String): Boolean {
        val textStr = text?.toString()?.trim()
        val parentLayout = parent?.parent
        return if (textStr.isNullOrBlank()) {
            if (parentLayout is TextInputLayout) {
                parentLayout.error = message
                parentLayout.isErrorEnabled = true
            }
            false
        } else {
            if (parentLayout is TextInputLayout) {
                parentLayout.error = null
                parentLayout.isErrorEnabled = false
            }
            true
        }
    }
}