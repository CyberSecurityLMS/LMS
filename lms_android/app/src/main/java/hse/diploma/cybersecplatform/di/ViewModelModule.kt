package hse.diploma.cybersecplatform.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import hse.diploma.cybersecplatform.di.vm.ViewModelFactory
import hse.diploma.cybersecplatform.di.vm.ViewModelKey
import hse.diploma.cybersecplatform.ui.screens.auth.AuthStateViewModel
import hse.diploma.cybersecplatform.ui.screens.home.HomeScreenViewModel

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AuthStateViewModel::class)
    abstract fun provideAuthStateViewModel(viewModel: AuthStateViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeScreenViewModel::class)
    abstract fun provideHomeScreenViewModel(viewModel: HomeScreenViewModel): ViewModel
}
