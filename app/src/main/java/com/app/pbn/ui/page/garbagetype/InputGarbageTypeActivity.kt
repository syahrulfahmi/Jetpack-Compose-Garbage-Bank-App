package com.app.pbn.ui.page.garbagetype

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
class InputGarbageTypeActivity : ComponentActivity() {

    private val viewModel: GarbageTypeViewModel by viewModels()
    private var isAdmin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getIntentExtras()

        setContent {
            BankSampahPalembonTheme {

                viewModel.getTrashType()

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    InputGarbageTypePage(viewModel,
                        doOnInputClick = {
                            viewModel.saveTrashType {
                                finish()
                            }
                        },
                        doOnBackPressed = {
                            onBackPressed()
                        })
                }
            }
        }
    }

    private fun getIntentExtras() {
        isAdmin = intent.getBooleanExtra(EXTRA.DATA, false)
    }
}