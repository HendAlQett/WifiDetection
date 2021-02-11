package com.hend.airlines.ui.base

import java.lang.ref.WeakReference

abstract class BasePresenter<V : BaseContract.View<*>>(mView: V) : BaseContract.Presenter {

    protected var mView: WeakReference<V> = WeakReference(mView)

}
