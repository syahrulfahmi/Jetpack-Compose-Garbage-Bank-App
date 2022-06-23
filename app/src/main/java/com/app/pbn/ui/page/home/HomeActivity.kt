package com.app.pbn.ui.page.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.pbn.ui.page.pickup.PickUpActivity
import com.app.pbn.ui.theme.BankSampahPalembonTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankSampahPalembonTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    HomePage(
                        doOnPickUpCLick = {
                            val intent = Intent(this, PickUpActivity::class.java)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BankSampahPalembonTheme {
        HomePage {

        }
    }
}