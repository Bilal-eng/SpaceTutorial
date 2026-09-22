package com.bilal.spacetutorial

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform