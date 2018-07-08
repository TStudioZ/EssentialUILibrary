package com.tstudioz.essentialuilibrary.ui

import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import com.tstudioz.essentialuilibrary.dagger.Injectable
import javax.inject.Inject

abstract class BaseFragment : Fragment(), Injectable {

    @Inject
    @JvmField
    protected var viewModelFactory: ViewModelProvider.Factory? = null
}