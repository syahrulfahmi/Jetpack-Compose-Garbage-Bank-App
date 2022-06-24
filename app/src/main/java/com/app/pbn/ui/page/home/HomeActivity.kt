package com.app.pbn.ui.page.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.pbn.model.UserModel
import com.app.pbn.ui.page.garbagetype.GarbageTypeActivity
import com.app.pbn.ui.page.history.HistoryActivity
import com.app.pbn.ui.page.login.LoginActivity
import com.app.pbn.ui.page.pickup.PickUpActivity
import com.app.pbn.ui.theme.BankSampahPalembonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getUserInfo()

        setContent {
            BankSampahPalembonTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val uiState by viewModel.uiState
                    HomePage(
                        uiState,
                        doOnPickUpCLick = {
                            val intent = Intent(this, PickUpActivity::class.java)
                            startActivity(intent)
                        },
                        doOnGarbageTypeClick = {
                            val intent = Intent(this, GarbageTypeActivity::class.java)
                            startActivity(intent)
                        },
                        doOnHistoryClick = {
                            val intent = Intent(this, HistoryActivity::class.java)
                            startActivity(intent)
                        },
                        doOnLogOut = {
                            viewModel.logOut {
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
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
        HomePage(
            UserModel(),
            {},
            {},
            {},
            {}
        )
    }
}