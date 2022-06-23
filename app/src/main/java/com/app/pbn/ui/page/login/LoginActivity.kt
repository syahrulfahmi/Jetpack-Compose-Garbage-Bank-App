package com.app.pbn.ui.page.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.app.pbn.ui.page.home.HomeActivity
import com.app.pbn.ui.page.register.RegisterActivity
import com.app.pbn.ui.theme.BankSampahPalembonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankSampahPalembonTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    LoginPage(
                        viewModel = viewModel,
                        doOnLoginClick = {
                            viewModel.loginAccount {
                                val intent = Intent(this, HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        },
                        doOnRegisterClick = {
                            val intent = Intent(this, RegisterActivity::class.java)
                            startActivity(intent)
                        })
                }
            }
        }
    }
}
