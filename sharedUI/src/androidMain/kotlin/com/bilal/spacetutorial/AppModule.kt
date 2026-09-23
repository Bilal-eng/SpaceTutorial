package com.bilal.spacetutorial

import com.bilal.spacetutorial.cache.AndroidDatabaseDriverFactory
import com.bilal.spacetutorial.network.SpaceApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<SpaceApi> { SpaceApi() }
    single<SpaceSDK> {
        SpaceSDK(
            databaseDriverFactory = AndroidDatabaseDriverFactory(androidContext()), api = get()
        )
    }
    viewModel { RocketLaunchViewModel(sdk = get()) }
}