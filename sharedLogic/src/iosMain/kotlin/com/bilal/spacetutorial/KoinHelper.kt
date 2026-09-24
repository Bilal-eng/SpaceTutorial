package com.bilal.spacetutorial

import com.bilal.spacetutorial.cache.IOSDatabaseDriverFactory
import com.bilal.spacetutorial.entity.RocketLaunch
import com.bilal.spacetutorial.network.SpaceApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinHelper : KoinComponent {
    private val sdk: SpaceSDK by inject<SpaceSDK>()

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        return sdk.getLaunches(forceReload = forceReload)
    }
}

fun initKoin() {
    startKoin {
        modules(module {
            single<SpaceApi> { SpaceApi() }
            single<SpaceSDK> {
                SpaceSDK(
                    databaseDriverFactory = IOSDatabaseDriverFactory(), api = get()
                )
            }
        })
    }
}