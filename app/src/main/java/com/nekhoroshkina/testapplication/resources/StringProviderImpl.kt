package com.example.androidtutorial2.resources

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StringProviderImpl @Inject constructor(@ApplicationContext private val context: Context) :
    StringProvider {

    override fun getString(resId: Int): String {
        return context.getString(resId)
    }
}
