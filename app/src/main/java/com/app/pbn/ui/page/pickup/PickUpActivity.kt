package com.app.pbn.ui.page.pickup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.app.pbn.ui.theme.BankSampahPalembonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PickUpActivity : ComponentActivity() {

    private val viewModel: PickUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankSampahPalembonTheme {
                // A surface container using the 'background' color from the theme
                viewModel.getTrashType()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    PickUpPage(
                        viewModel,
                        doOnBack = {
                            onBackPressed()
                        },
                        doOnSave = {
                            viewModel.savePickUpRequest {
                                finish()
                            }
                        }
                    )
                }
            }
        }
    }
}