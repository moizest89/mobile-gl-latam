package com.moizest89.mobile_gl_latam.presentation.main

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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

    /*
    * (this.applicationContext as GlobalLogicTest) get component injected via manual DI
    * */
    private val viewModel : MainViewModel by lazy {
        ViewModelProvider( this , MainViewModelFactory( (this.applicationContext as GlobalLogicTest).appContainer.itemtRepository )).get( MainViewModel::class.java )
    }

    private val mAdapter by lazy {
        MainAdapter( resources.configuration.orientation , this@MainActivity )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.toolbar.title = ""
        this.title = ""
        setSupportActionBar(toolbar)

        //resources.configuration.orientation returns 1 for Portrait and 2 for landscape
        this.recyclerViewMainData.layoutManager = GridLayoutManager( this, getGridSpanByOrientation( resources.configuration.orientation ) )
        this.recyclerViewMainData.adapter = mAdapter

        //Observe data Model when data changed
        this.viewModel.items.observe( this, Observer { dataResource ->
            dataResource?.onSuccess { items ->
                swipeRefreshMainList.isRefreshing = false
                mAdapter.list = items
                mAdapter.notifyDataSetChanged()
                swipeRefreshMainList.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }?.onLoading {
                swipeRefreshMainList.isRefreshing = true
                if(!swipeRefreshMainList.isVisible) swipeRefreshMainList.visibility = View.GONE
                if( progressBar.isVisible ) progressBar.visibility = View.VISIBLE
                linearLayoutFailureMessage.visibility = View.GONE
            }?.onFailure {
                swipeRefreshMainList.isRefreshing = false
                swipeRefreshMainList.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                linearLayoutFailureMessage.visibility = View.VISIBLE
            }
        })

        //Get init data if is necessary or get data model prev loaded
        this.viewModel.getDataItems( )

        //Refresh data for get new items from API service
        swipeRefreshMainList.setOnRefreshListener {
            this.viewModel.getDataItems( true )
        }

        //If request has error you can the option to try to get data again
        this.materialButtonErrorData.setOnClickListener {
            this.viewModel.getDataItems( true )
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.menu_action_about_project -> {
                aboutProjectDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Determine specific span grid from orientation screen value
     */
    private fun getGridSpanByOrientation( orientation : Int ) : Int {
        return when( orientation ){
            2 -> 3
            else -> orientation
        }
    }

    /*
    * Callback from MainAdapter when user touch to a specific item on the list
    * */
    override fun itemClickListener(position: Int, dataItem: DataModelItem) {
        // Load details view when user selects item form list
        Intent( this@MainActivity , DetailsActivity::class.java ).also {
            it.putExtra( Config.INTENT_MAIN_DATA , dataItem )
            startActivity( it )
        }
    }

    private fun aboutProjectDialog(){
        val dialog = AlertDialog.Builder( this )
            .setMessage( R.string.message_about_project )
            .setPositiveButton( android.R.string.ok ){ dialog,_ ->
                dialog.dismiss()
            }.show()
        dialog.findViewById<TextView>(android.R.id.message)?.movementMethod = LinkMovementMethod.getInstance()
    }
}
