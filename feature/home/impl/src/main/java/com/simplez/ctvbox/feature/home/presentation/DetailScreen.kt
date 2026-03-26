package com.simplez.ctvbox.feature.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * @Title: CTvBox
 * @Package com.simplez.ctvbox.feature.home.presentation
 * @Description:
 * @author SimpleZ
 * @date 2026/3/26 15:38
 * @version V1.0
 */
@Composable
fun DetailScreen() {
    Scaffold() {
        Box(modifier = Modifier.padding(it)) {
            Text(text = "详细")
        }
    }
}