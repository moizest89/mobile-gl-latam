package com.moizest89.mobile_gl_latam.common

import android.animation.Animator
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.moizest89.mobile_gl_latam.R

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

fun ImageView.setImageFromUrl( imageUrl : String? , placeHolder : Int = R.drawable.preview_img ) : ImageView {
    val imageData = imageUrl?.let { imageUrl }?: ""
    Glide.with(this.context)
        .load(imageData)
        .placeholder( placeHolder )
        .crossFade()
        .centerCrop()
        .error(placeHolder)
        .centerCrop()
        .into(this)

    return this
}