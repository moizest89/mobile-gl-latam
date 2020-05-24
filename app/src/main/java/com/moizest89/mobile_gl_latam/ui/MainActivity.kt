package com.moizest89.mobile_gl_latam.ui

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.moizest89.mobile_gl_latam.R
import com.moizest89.mobile_gl_latam.common.onAlphaAnimation
import com.moizest89.mobile_gl_latam.data.DataModelItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() , MainAdapter.OnItemClickListener{

    private val viewModel : MainViewModel by lazy { ViewModelProvider( this ).get( MainViewModel::class.java ) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //resources.configuration.orientation returns 1 for Portrait and 2 for landscape
        val mAdapter = MainAdapter( resources.configuration.orientation )
        this.recyclerViewMainData.layoutManager = GridLayoutManager( this, getGridSpanByOrientation( resources.configuration.orientation ) )
        this.recyclerViewMainData.adapter = mAdapter

        viewModel.dataItems.observe( this , Observer {
            it?.let {
                mAdapter.list = it
                mAdapter.notifyDataSetChanged()

                swipeRefreshMainList.onAlphaAnimation( 1.0f )
                progressBar.onAlphaAnimation( 0.0f )

                if(swipeRefreshMainList.isRefreshing) swipeRefreshMainList.isRefreshing = false
            }
        })

        viewModel.loadDataItems()

        swipeRefreshMainList.setOnRefreshListener {
            viewModel.loadDataItems( true )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.menu_action_about_project -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getGridSpanByOrientation( orientation : Int ) : Int {
        return when( orientation ){
            2 -> 3
            else -> orientation
        }
    }

    override fun itemClickListener(position: Int, dataItem: DataModelItem) {

    }
}
