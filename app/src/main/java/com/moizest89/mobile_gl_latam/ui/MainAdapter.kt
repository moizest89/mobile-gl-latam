package com.moizest89.mobile_gl_latam.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moizest89.mobile_gl_latam.R

class MainAdapter( private val mOrientation : Int ) : RecyclerView.Adapter< MainAdapter.Holder >(){


    private val ORIENTATION_LANDSCAPE = 2
    private val ORIENTATION_PORTRAIT = 1

    //True for landscape and false for portrait
    private val orientationLand : Boolean = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return when(mOrientation){
            ORIENTATION_LANDSCAPE -> Holder( LayoutInflater.from( parent.context ).inflate( R.layout.item_main_list_row_land , parent, false ) )
            //ORIENTATION_PORTRAIT
            else ->{ Holder( LayoutInflater.from( parent.context ).inflate( R.layout.item_main_list_row_port , parent, false ) )}
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class Holder( itemView: View ): RecyclerView.ViewHolder( itemView ){
        var imageView = itemView.findViewById< ImageView >( R.id.imageView )
        var textViewTitle = itemView.findViewById< ImageView >( R.id.textViewTitle )
        var textViewDescription = itemView.findViewById< TextView >( R.id.textViewDescription )
    }

}