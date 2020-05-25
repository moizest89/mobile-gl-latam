package com.moizest89.mobile_gl_latam.presentation.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.moizest89.mobile_gl_latam.R
import com.moizest89.mobile_gl_latam.common.Config
import com.moizest89.mobile_gl_latam.common.onAlphaAnimation
import com.moizest89.mobile_gl_latam.domain.DataModelItem
import com.moizest89.mobile_gl_latam.framework.GlobalLogicTest
import com.moizest89.mobile_gl_latam.presentation.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() , MainAdapter.OnItemClickListener {

    private val viewModel : MainViewModel by lazy {
        ViewModelProvider( this , MainViewModelFactory( (this.applicationContext as GlobalLogicTest).appContainer.itemtRepository )).get( MainViewModel::class.java )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.toolbar.title = ""
        this.title = ""
        setSupportActionBar(toolbar)

        //resources.configuration.orientation returns 1 for Portrait and 2 for landscape
        val mAdapter = MainAdapter(resources.configuration.orientation)
        mAdapter.mListener = this@MainActivity
        this.recyclerViewMainData.layoutManager = GridLayoutManager( this, getGridSpanByOrientation( resources.configuration.orientation ) )
        this.recyclerViewMainData.adapter = mAdapter

        viewModel.items.observe( this, Observer { dataResource ->
            dataResource?.onSuccess { items ->
                Log.e("MainActivity" , "onSuccess $items")
                swipeRefreshMainList.isRefreshing = false
                mAdapter.list = items
                mAdapter.notifyDataSetChanged()
                swipeRefreshMainList.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }?.onLoading {
                swipeRefreshMainList.isRefreshing = true
                swipeRefreshMainList.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }?.onFailure {
                swipeRefreshMainList.isRefreshing = false
                swipeRefreshMainList.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
        })

        viewModel.getDataItems( )
        swipeRefreshMainList.setOnRefreshListener {
            viewModel.getDataItems( true )
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

        Intent( this@MainActivity , DetailsActivity::class.java ).also {
            it.putExtra( Config.INTENT_MAIN_DATA , dataItem )
            startActivity( it )
        }
    }
}
