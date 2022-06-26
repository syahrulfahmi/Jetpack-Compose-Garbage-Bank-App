package com.app.pbn.ui.page.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.app.pbn.constant.EXTRA
import com.app.pbn.ui.page.home.HomeActivity
import com.app.pbn.ui.page.login.LoginActivity
import com.app.pbn.ui.theme.BankSampahPalembonTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : ComponentActivity() {
    private val viewModel: SplashScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getUserInfo()

        setContent {
            BankSampahPalembonTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    SplashScreenPage()
                    lifecycleScope.launchWhenCreated {
                        delay(2000)
                        viewModel.checkUserIfLogin {
                            if (it) {
                                val intent = Intent(this@SplashScreenActivity, HomeActivity::class.java).apply {
                                    putExtra(EXTRA.DATA, viewModel.uiState.value.isAdmin)
                                }
                                startActivity(intent)
                            } else {
                                val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                                startActivity(intent)
                            }
                            finish()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    BankSampahPalembonTheme {
        SplashScreenPage()
    }
}