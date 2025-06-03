package com.example.savingbyshopping.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.example.savingbyshopping.R
import com.google.android.material.textfield.TextInputLayout

class EmailCustomText(context: Context, attrs: AttributeSet? = null) :
    EditTextCustom(context, attrs) {

    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                validateEmail()
            }

        })
    }

    fun validateEmail() : Boolean {
        val emailText = text?.toString()?.trim()
        val layout = parent?.parent
        if (emailText.isNullOrEmpty()) return false

        val regex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (layout is TextInputLayout) {
           return if (!regex.matches(emailText)) {
                layout.error = context.getString(R.string.error_formatEmail)
                layout.isErrorEnabled = true
                false
            } else {
                layout.error = null
                layout.isErrorEnabled = false
               true
            }
        }
        return false
    }

}