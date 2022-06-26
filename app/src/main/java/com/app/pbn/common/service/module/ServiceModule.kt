package com.app.pbn.common.service.module

import com.app.pbn.common.service.AccountService
import com.app.pbn.common.service.StorageService
import com.app.pbn.common.service.serviceimpl.AccountServiceImpl
import com.app.pbn.common.service.serviceimpl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService
}