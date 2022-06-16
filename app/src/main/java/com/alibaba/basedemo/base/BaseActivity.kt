package com.alibaba.basedemo.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alibaba.basedemo.utils.App

/**
 * Activity基类，封装部分方法
 * Created by Rick on 2022/6/15 17:57.
 * God bless my code!
 */
abstract class BaseActivity : DataBindingActivity() {

    /**
     * ViewModel范围提供者
     */
    private lateinit var mActivityProvider: ViewModelProvider
    private lateinit var mApplicationProvider: ViewModelProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)
    }

    /**
     * 初始化
     */
    abstract fun init(savedInstanceState: Bundle?)

    /**
     * 传入ViewModel泛型,返回Activity级的作用域的ViewModel.
     * @sample 请在initViewModel时调用或使用延迟初始化
     * @param vm ViewModel类型
     * @return 返回Activity级的作用域的ViewModel.
     */
    protected fun <T : ViewModel> getActivityScopeViewModel(vm: Class<T>): T {
        if (!this::mActivityProvider.isInitialized) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider.get(vm)
    }

    /**
     * 传入ViewModel泛型,返回Application级的作用域的ViewModel.
     * @sample 请在initViewModel时调用或使用延迟初始化
     * @param vm ViewModel类型
     * @return 返回Application级的作用域的ViewModel.
     */
    protected fun <T : ViewModel> getApplicationScopeViewModel(vm: Class<T>): T {
        if (!this::mApplicationProvider.isInitialized) {
            mApplicationProvider = ViewModelProvider(applicationContext as App)
        }
        return mApplicationProvider.get(vm)
    }

}