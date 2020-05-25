package com.moizest89.mobile_gl_latam.framework

import android.app.Application

class GlobalLogicTest() : Application(){

    //Manual DI for inject components
    val appContainer : GlobalLogicContainer by lazy { GlobalLogicContainer( this ) }

    override fun onCreate() {
        super.onCreate()
    }
}