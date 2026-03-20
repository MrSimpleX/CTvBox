package com.simplez.ctvbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.simplez.ctvbox.feature.home.presentation.HomeRoute
import com.simplez.ctvbox.ui.theme.CTvBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CTvBoxTheme {
                HomeRoute()
            }
        }
    }
}
