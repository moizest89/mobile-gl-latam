package com.moizest89.mobile_gl_latam.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moizest89.mobile_gl_latam.R
import com.moizest89.mobile_gl_latam.common.setImageFromUrl
import com.moizest89.mobile_gl_latam.data.DataModelItem

class MainAdapter( private val mOrientation : Int ) : RecyclerView.Adapter<MainAdapter.Holder>(){

    var list : MutableList<DataModelItem> = mutableListOf()
    private val ORIENTATION_LANDSCAPE = 2
    private val ORIENTATION_PORTRAIT = 1
    var mListener : OnItemClickListener? = null

    //True for landscape and false for portrait
    private val orientationLand : Boolean = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return when(mOrientation){
            ORIENTATION_LANDSCAPE -> Holder( LayoutInflater.from( parent.context ).inflate( R.layout.item_main_list_row_land , parent, false ) )
            //ORIENTATION_PORTRAIT
            else ->{ Holder( LayoutInflater.from( parent.context ).inflate( R.layout.item_main_list_row_port , parent, false ) )}
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val itemList = list[ position ]
        holder.textViewTitle.text = itemList.title
        holder.textViewDescription.text = itemList.description
        holder.imageView.setImageFromUrl( itemList.image )

        holder.itemView.setOnClickListener {
            mListener?.itemClickListener( position , itemList )
        }
    }

    inner class Holder( itemView: View ): RecyclerView.ViewHolder( itemView ){
        var imageView = itemView.findViewById< ImageView >( R.id.imageView )
        var textViewTitle = itemView.findViewById< TextView >( R.id.textViewTitle )
        var textViewDescription = itemView.findViewById< TextView >( R.id.textViewDescription )
    }

    interface OnItemClickListener{
        fun itemClickListener( position: Int , dataItem : DataModelItem )
    }

}