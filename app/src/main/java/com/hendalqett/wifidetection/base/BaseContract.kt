package com.hend.airlines.ui.base

interface BaseContract {

    interface View<C> {
        fun showErrorMessage(errorRes: String)
    }

    interface Presenter
}
