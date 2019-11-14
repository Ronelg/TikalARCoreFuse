package com.android.tikalarcorefuse.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory private constructor() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GameViewModel() as T
    }


    private object HOLDER {
        val INSTANCE = ViewModelFactory()
    }

    companion object {
        val instance: ViewModelFactory by lazy { HOLDER.INSTANCE }
    }
}