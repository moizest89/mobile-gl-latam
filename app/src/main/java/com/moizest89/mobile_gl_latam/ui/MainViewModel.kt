package com.moizest89.mobile_gl_latam.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moizest89.mobile_gl_latam.common.LiveDataResource
import com.moizest89.mobile_gl_latam.data.DataModelItem

class MainViewModel : ViewModel() {

    val dataItems = MutableLiveData< MutableList< DataModelItem > >()

    fun loadDataItems(){
        Log.e("MainViewModel", "dataItems.value ${dataItems.value}")
        dataItems.value?.let {
            if( it.isEmpty() ){
                dataItems.value = getDataDummy()
            }
        }?: kotlin.run {
            dataItems.value = getDataDummy()
        }
    }

    private fun getDataDummy() : MutableList< DataModelItem >{
        val dummyData = mutableListOf< DataModelItem >()
        repeat( 20 ){
            dummyData.add( DataModelItem(
                title = "Title No $it" ,
                description = "In hac habitasse platea dictumst. Aliquam mi erat, fermentum non nisi non, congue dictum velit. Suspendisse hendrerit velit at vulputate suscipit. Nunc in erat vestibulum, lacinia neque vel, molestie arcu. Proin vestibulum sagittis mauris, eu consequat neque imperdiet non. Donec feugiat viverra quam, sit amet pulvinar justo accumsan sed. Praesent gravida eros in libero facilisis, quis molestie nisl interdum. Ut gravida vel felis quis rutrum. Mauris accumsan lacus et sem efficitur, in mattis sem lacinia")
            )
        }
        return dummyData;
    }
}