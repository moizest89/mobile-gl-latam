package com.moizest89.mobile_gl_latam.data

import android.content.Context
import android.util.Log
import com.moizest89.mobile_gl_latam.common.ApiClient
import com.moizest89.mobile_gl_latam.domain.DataModelItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemRepository( val context: Context ) : IItemImpl {

    override fun getItemList( onSuccess: (MutableList<DataModelItem>) -> Unit, onError: (Throwable?) -> Unit) {
        ApiClient.getService().getListItems().enqueue( object : Callback< MutableList<DataModelItem >>{
            override fun onResponse( call: Call<MutableList<DataModelItem>>, response: Response<MutableList<DataModelItem>>) {
                if( response.isSuccessful ){
                    onSuccess.invoke( response.body()!! )
                }else{
                    onError.invoke( null )
                }
            }
            override fun onFailure(call: Call<MutableList<DataModelItem>>, t: Throwable) {
                onError.invoke( t )
            }
        })
    }

}