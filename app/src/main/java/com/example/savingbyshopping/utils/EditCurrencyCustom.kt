package com.example.savingbyshopping.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet

class EditCurrencyCustom(context: Context, attrs: AttributeSet? = null) :
    EditTextCustom(context, attrs) {

    private var isEditing = false

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

                val cleanString =
                    original.replace("Rp", "").replace(".", "").replace("\\s".toRegex(), "")

                if (cleanString.isNotEmpty()) {
                    try {
                        val parsed = cleanString.toLong()
                        val formatted = parsed.toRupiah()
                        setText(formatted)
                        setSelection(formatted.length)
                    } catch (e: NumberFormatException) {
                        // jika input tidak valid, biarkan kosong atau abaikan
                    }
                }

                isEditing = false
            }

        })
    }
}