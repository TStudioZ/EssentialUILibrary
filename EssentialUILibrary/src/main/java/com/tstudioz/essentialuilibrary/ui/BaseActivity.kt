package com.tstudioz.essentialuilibrary.ui

import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    @JvmField
    var viewModelFactory: ViewModelProvider.Factory? = null;

    @Inject
    @JvmField
    var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>? = null;

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector!!;
    }
}