package com.scb.countriesmvvm.di

import com.scb.countriesmvvm.model.CountriesService
import com.scb.countriesmvvm.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)

    fun inject(viewModel: ListViewModel)
}