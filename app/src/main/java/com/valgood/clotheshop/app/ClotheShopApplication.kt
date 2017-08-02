package com.valgood.clotheshop.app

import android.app.Application
import com.backendless.Backendless

/**
 * Application class to initialize Backendless
 */
class ClotheShopApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Backendless.initApp(this, "34256D93-0146-611D-FFD9-7273F6981800",
                "277450B3-02DC-ED4A-FF26-BCC83921B900")
    }
}
