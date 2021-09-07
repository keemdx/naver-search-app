package com.dani.naversearch.util

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager

class KeyboardUtil(
    context: Context
) {
    private val imm: InputMethodManager =
        context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

    fun hideKeyboard(target: View) {
        imm.hideSoftInputFromWindow(target.windowToken, 0)
    }
}