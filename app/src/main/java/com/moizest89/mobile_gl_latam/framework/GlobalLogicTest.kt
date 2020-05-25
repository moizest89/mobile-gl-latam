package com.moizest89.mobile_gl_latam.framework

import android.app.Application

class GlobalLogicTest() : Application(){

    val appContainer : GlobalLogicContainer by lazy { GlobalLogicContainer( this ) }

    override fun onCreate() {
        super.onCreate()
    }
}