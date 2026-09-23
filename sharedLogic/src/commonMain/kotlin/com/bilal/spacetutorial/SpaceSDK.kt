package com.bilal.spacetutorial

import com.bilal.spacetutorial.cache.Database
import com.bilal.spacetutorial.cache.DatabaseDriverFactory
import com.bilal.spacetutorial.entity.RocketLaunch
import com.bilal.spacetutorial.network.SpaceApi

class SpaceSDK(databaseDriverFactory: DatabaseDriverFactory, val api: SpaceApi) {
    private val database = Database(databaseDriverFactory)

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearAndCreateLaunches(it)
            }
        }
    }
}