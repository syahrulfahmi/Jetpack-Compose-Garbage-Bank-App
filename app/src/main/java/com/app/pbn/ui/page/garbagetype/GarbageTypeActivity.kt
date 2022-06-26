package com.app.pbn.ui.page.garbagetype

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.app.pbn.constant.EXTRA
import com.app.pbn.ui.theme.BankSampahPalembonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GarbageTypeActivity : ComponentActivity() {

    private val viewModel: GarbageTypeViewModel by viewModels()
    private var isAdmin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getIntentExtras()

        setContent {
            BankSampahPalembonTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    GarbageTypePage(isAdmin, viewModel,
                    doOnBackPressed = {
                        onBackPressed()
                    },
                    doOnAddData =  {
                        val intent = Intent(this, InputGarbageTypeActivity::class.java)
                        startActivity(intent)
                    })
                }
            }
        }
    }

    override fun onResume() {
        viewModel.getTrashType()
        super.onResume()
    }

    private fun getIntentExtras() {
        isAdmin = intent.getBooleanExtra(EXTRA.DATA, false)
    }
}