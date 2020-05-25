package com.moizest89.mobile_gl_latam.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moizest89.mobile_gl_latam.data.ItemRepository

class MainViewModelFactory( private val mItemRepository: ItemRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel( mItemRepository ) as T
    }
}