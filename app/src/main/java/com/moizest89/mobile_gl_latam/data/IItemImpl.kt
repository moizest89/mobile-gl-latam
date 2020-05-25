package com.moizest89.mobile_gl_latam.data

import com.moizest89.mobile_gl_latam.domain.DataModelItem

interface IItemImpl {
    fun getItemList( onSuccess : ( MutableList<DataModelItem >) -> Unit , onError : ( Throwable?) -> Unit )
}