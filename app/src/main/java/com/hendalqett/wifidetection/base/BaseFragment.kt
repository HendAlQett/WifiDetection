package com.hend.airlines.ui.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hendalqett.wifidetection.utils.ErrorMessagesUtils

abstract class BaseFragment<P : BaseContract.Presenter> : Fragment(), BaseContract.View<Fragment> {

    protected lateinit var rootView: View

    @get:LayoutRes
    protected abstract val layoutResource: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(layoutResource, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        afterInflation(rootView, savedInstanceState)

        if (setToolbarTitle() != 0) {

            (activity as AppCompatActivity).supportActionBar!!.setTitle(setToolbarTitle())
        }
        if (viewBackButton()) {
            (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }


    @StringRes
    protected open fun setToolbarTitle(): Int {
        return 0
    }

    protected open fun viewBackButton(): Boolean {
        return false
    }


    abstract fun afterInflation(resultView: View, savedInstanceState: Bundle?)

    override fun showErrorMessage(errorRes: String) {
        ErrorMessagesUtils.showMessageShortToast(activity, errorRes)
    }

}
