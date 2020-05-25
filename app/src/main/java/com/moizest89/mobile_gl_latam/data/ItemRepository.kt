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
        Log.e("ItemRepository" ,"getItemList()")
        ApiClient.getService().getListItems().enqueue( object : Callback< MutableList<DataModelItem >>{
            override fun onResponse( call: Call<MutableList<DataModelItem>>, response: Response<MutableList<DataModelItem>>) {
                if( response.isSuccessful ){
                    onSuccess.invoke( response.body()!! )
                }else{
                    onError.invoke( null )
                }
                Log.e("ItemRepository" ,"response ${response.toString()}")
            }
            override fun onFailure(call: Call<MutableList<DataModelItem>>, t: Throwable) {
                Log.e("ItemRepository" ,"onFailure ${t}")
                onError.invoke( t )
            }
        })
    }

}