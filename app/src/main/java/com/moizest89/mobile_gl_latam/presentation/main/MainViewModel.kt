package com.moizest89.mobile_gl_latam.presentation.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moizest89.mobile_gl_latam.common.LiveDataResource
import com.moizest89.mobile_gl_latam.data.ItemRepository
import com.moizest89.mobile_gl_latam.domain.DataModelItem
import com.moizest89.mobile_gl_latam.framework.GlobalLogicTest

class MainViewModel( private val mItemRepository: ItemRepository) : ViewModel() {

    val items = MutableLiveData< LiveDataResource< MutableList<DataModelItem>>>()

    fun getDataItems( reloadData : Boolean = false ){
        if( reloadData) {
            getDataItemsFromServer()
        }else{
            items.value?.extractData?.let {
                items.value = LiveDataResource.Success( it )
            }?:run{
                items.value = LiveDataResource.Loading()
                getDataItemsFromServer()
            }
        }
    }

    private fun getDataItemsFromServer(){
        mItemRepository.getItemList({
            items.value = LiveDataResource.Success( it )
        },{
            items.value = LiveDataResource.Failure( it )
        })
    }

}