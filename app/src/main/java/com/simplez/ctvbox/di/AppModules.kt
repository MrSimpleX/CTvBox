package com.simplez.ctvbox.di

import com.simplez.ctvbox.core.di.coreNetworkModule
import com.simplez.ctvbox.feature.home.di.homeModule
import org.koin.core.module.Module

val appModules: List<Module> = listOf(
    coreNetworkModule,
    homeModule
)
