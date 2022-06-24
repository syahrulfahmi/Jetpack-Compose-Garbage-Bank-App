package com.app.pbn.ui.page.garbagetype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.app.pbn.ui.theme.BankSampahPalembonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GarbageTypeActivity : ComponentActivity() {

    private val viewModel: GarbageTypeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankSampahPalembonTheme {
                viewModel.getTrashType()
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    GarbageTypePage(viewModel) {
                        onBackPressed()
                    }
                }
            }
        }
    }
}