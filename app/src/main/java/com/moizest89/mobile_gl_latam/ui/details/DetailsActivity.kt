package com.moizest89.mobile_gl_latam.ui.details

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.moizest89.mobile_gl_latam.R
import com.moizest89.mobile_gl_latam.common.Config
import com.moizest89.mobile_gl_latam.common.setImageFromUrl
import com.moizest89.mobile_gl_latam.data.DataModelItem
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        toolbar.navigationIcon = ContextCompat.getDrawable( this  , R.drawable.ic_arrow_back_white )
        this.title = ""
        this.toolbar.title = ""
        setSupportActionBar(toolbar)



        intent?.getParcelableExtra< DataModelItem >( Config.INTENT_MAIN_DATA)?.let { dataModelItem ->
            imageViewHeader.setImageFromUrl( dataModelItem.image )
            textViewTitle.text = dataModelItem.title
            textViewDescription.text = dataModelItem.description
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
