package com.moizest89.mobile_gl_latam.ui.details

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
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
        setSupportActionBar(toolbar)

        intent?.getParcelableExtra< DataModelItem >( Config.INTENT_MAIN_DATA)?.let { dataModelItem ->
            imageViewHeader.setImageFromUrl( dataModelItem.image )
            textViewTitle.text = dataModelItem.title
            textViewDescription.text = dataModelItem.description
        }

    }
}
