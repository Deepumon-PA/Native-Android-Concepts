package com.deepu.myandroidapp.common

import android.content.Context

class SampleAndroidComponent {

    fun checkIfTheStringIsSameAsTheOneWithResourceId(context: Context, resId: Int, originalString: String): Boolean{
        return context.getString(resId) == originalString
    }
}