package com.app.pbn.ui.page.home

import androidx.compose.runtime.Composable
import com.app.pbn.model.UserModel

@Composable
fun HomePage(isAdmin: Boolean, userData: UserModel, doOnPickUpCLick: () -> Unit, doOnGarbageTypeClick: () -> Unit, doOnHistoryClick: () -> Unit, doOnLogOut: () -> Unit) {
    if (!isAdmin) {
        HomePageUser(userData, doOnPickUpCLick::invoke, doOnGarbageTypeClick::invoke, doOnHistoryClick::invoke, doOnLogOut::invoke)
    } else {
        HomePageAdmin(userData, doOnGarbageTypeClick::invoke, doOnHistoryClick::invoke, doOnLogOut::invoke)
    }
}