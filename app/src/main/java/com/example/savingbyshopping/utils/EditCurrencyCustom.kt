package com.example.savingbyshopping.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout

class EditCurrencyCustom(context: Context, attrs: AttributeSet? = null) :
    EditTextCustom(context, attrs) {

    private var isEditing = false
    private var maxValue: Long? = null
    private var messageError: String? = null

    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (isEditing) return
                isEditing = true

                val original = s.toString()

                if (original == "Rp " || original.isBlank()) {
                    setText("")
                    setSelection(0)
                    isEditing = false
                    return
                }

                val parsed = original.fromRupiah()
                val formatted = parsed.toRupiah()
                setText(formatted)
                setSelection(formatted.length)

                notBiggerValue(parsed)
                isEditing = false
            }

        })
    }

    private fun notBiggerValue(currentValue: Long) {
        val max = maxValue ?: return
        val parentLayout = parent?.parent

        if (parentLayout is TextInputLayout) {
            if (currentValue > max) {
                parentLayout.error = messageError
                parentLayout.isErrorEnabled = true
            } else {
                parentLayout.error = null
                parentLayout.isErrorEnabled = false
            }
        }
    }

    fun setMaxValueError(value: String, message: String) {
        maxValue = value.fromRupiah()
        messageError = message
        notBiggerValue(text?.toString()?.fromRupiah() ?: 0)
    }
}