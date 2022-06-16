package com.alibaba.basedemo.utils

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * 实现application级作用域的ViewModelStore
 * Created by Rick on 2022/6/16 10:11.
 * God bless my code!
 */
class App : Application(), ViewModelStoreOwner {

    private val mAppViewModelStore = ViewModelStore()

    override fun onCreate() {
        super.onCreate()
    }

    /**
     * @return {mAppViewModelStore} application级作用域的
     */
    override fun getViewModelStore() = mAppViewModelStore
}