package com.moizest89.mobile_gl_latam.common

import com.moizest89.mobile_gl_latam.domain.DataModelItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiClientInterface {

    @GET("/list")
    fun getListItems( ) : Call< MutableList< DataModelItem> >
}