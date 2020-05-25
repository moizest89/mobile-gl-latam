package com.moizest89.mobile_gl_latam.framework

import android.app.Application
import com.moizest89.mobile_gl_latam.data.ItemRepository

class GlobalLogicContainer( private val application: Application ) {

    val itemtRepository = ItemRepository( application.baseContext )
}