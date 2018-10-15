package com.hend.airlines.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.hendalqett.wifidetection.utils.ErrorMessagesHandler


abstract class BaseActivity<P : BaseContract.Presenter> : AppCompatActivity(), BaseContract.View<AppCompatActivity> {

    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        afterInflation(savedInstanceState)

        if (supportActionBar != null) {
            if (setActionbarTitle() != 0) {
                supportActionBar!!.setTitle(setActionbarTitle())
            } else if (setActionbarTitleString() != null) {
                supportActionBar!!.title = setActionbarTitleString()
            }
            if (viewBackButton()) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            }
        }

    }

    protected abstract fun afterInflation(savedInstanceState: Bundle?)

    protected open fun setActionbarTitle(): Int {
        return 0
    }

    protected open fun setActionbarTitleString(): String? {
        return null
    }

    protected abstract fun viewBackButton(): Boolean

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun showErrorMessage(errorRes: String) {
        ErrorMessagesHandler.showMessageShortToast(this, errorRes)
    }

}
