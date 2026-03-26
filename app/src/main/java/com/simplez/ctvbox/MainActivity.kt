package com.simplez.ctvbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.simplez.api.HomeNavKey
import com.simplez.ctvbox.feature.home.presentation.HomeRoute
import com.simplez.ctvbox.navigation.NAV_ITEMS
import com.simplez.designui.theme.CTvBoxTheme
import com.simplez.navigation.rememberNavigationState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navigationState = rememberNavigationState(HomeNavKey, NAV_ITEMS.keys)

            CTvBoxTheme {
                CtvBoxApp(navigationState)
            }
        }
    }
}
