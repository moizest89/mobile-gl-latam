package com.moizest89.mobile_gl_latam.common

import android.animation.Animator
import android.view.View

fun View.onAlphaAnimation(alpha : Float,
                          duration : Long = 800,
                          onAnimationEnd : ((View, Animator? ) -> Unit )? = null,
                          onAnimationStart : (( View , Animator? ) -> Unit)? = null ) : View{

    this.animate().alpha( alpha ).setDuration( duration ).setListener( object  : Animator.AnimatorListener{
        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationEnd(p0: Animator?) {
            onAnimationEnd?.invoke( this@onAlphaAnimation , p0 )
        }
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {
            onAnimationStart?.invoke( this@onAlphaAnimation , p0 )
        }
    })

    return this
}