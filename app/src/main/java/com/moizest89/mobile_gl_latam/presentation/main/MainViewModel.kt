package com.moizest89.mobile_gl_latam.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moizest89.mobile_gl_latam.common.LiveDataResource
import com.moizest89.mobile_gl_latam.data.ItemRepository
import com.moizest89.mobile_gl_latam.domain.DataModelItem

class MainViewModel( private val mItemRepository: ItemRepository) : ViewModel() {

    val items = MutableLiveData< LiveDataResource< MutableList<DataModelItem>>>()

    /***
     * The following method determines if data was loaded or need to get data from repository.
     * However, has the option to force to get data from repository
     */
    fun getDataItems( reloadData : Boolean = false ){
        if( reloadData) {
            getDataItemsFromServer()
        }else{
            items.value?.extractData?.let {
                items.value = LiveDataResource.Success( it )
            }?:run{
                getDataItemsFromServer()
            }
        }
    }

    /***
     * Private fun for get data from repository
     */
    private fun getDataItemsFromServer(){
        items.value = LiveDataResource.Loading()
        mItemRepository.getItemList({
            items.value = LiveDataResource.Success( it )
        },{
            items.value = LiveDataResource.Failure( it )
        })
    }

}