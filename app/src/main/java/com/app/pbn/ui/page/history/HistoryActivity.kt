package com.app.pbn.ui.page.history

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.app.pbn.constant.EXTRA
import com.app.pbn.ui.theme.BankSampahPalembonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : ComponentActivity() {

    private val viewModel: HistoryViewModel by viewModels()
    private var isAdmin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getUserInfo()
        getIntentExtras()
        if (isAdmin) {
            viewModel.getRequestPickUp()
        } else {
            viewModel.getHistoryByEmail()
        }

        setContent {
            BankSampahPalembonTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val userState by viewModel.userState
                    if (isAdmin) viewModel.getHistoryPickUp(userState)
                    if (isAdmin) {
                        HistoryAdminPage(
                            viewModel = viewModel,
                            isAdmin = isAdmin,
                            doOnBackPressed = {
                                onBackPressed()
                            },
                            doOnUpdate = { id, status, item ->
                                viewModel.updateRequestStatus(id, status) {
                                    viewModel.addNewItem(item, status)
                                    viewModel.removeItem(item)
                                    viewModel.getHistoryPickUp(userState)
                                }
                            })
                    } else {
                        HistoryPageUser(
                            viewModel,
                            doOnBackPressed = {
                                onBackPressed()
                            },
                            doOnDeleted = { item ->
                                viewModel.deleteHistory(item.id) {
                                    viewModel.isShowDialog.value = true
                                }
                            }
                        )
                    }
                }
            }
        }
    }


    private fun getIntentExtras() {
        isAdmin = intent.getBooleanExtra(EXTRA.DATA, false)
    }
}