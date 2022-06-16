package com.alibaba.basedemo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.alibaba.basedemo.utils.App

/**
 * Fragment基类，封装部分方法
 * Created by Rick on 2022/6/16 15:42.
 * God bless my code!
 */
abstract class BaseFragment : DataBindingFragment() {

    /**
     * ViewModel范围提供者
     */
    private lateinit var mFragmentProvider: ViewModelProvider
    private lateinit var mActivityProvider: ViewModelProvider
    private lateinit var mApplicationProvider: ViewModelProvider

    /**
     * @return 获取NavController
     */
    protected fun nav() = NavHostFragment.findNavController(this)

    /**
     * 获取applicationContext
     */
    protected fun getApplicationContext() = mActivity.applicationContext

    /**
     * 传入ViewModel泛型,返回Fragment级的作用域的ViewModel.
     * @sample 请在initViewModel时调用或使用延迟初始化
     * @param vm ViewModel类型
     * @return 返回Activity级的作用域的ViewModel.
     */
    protected fun <T : ViewModel> getFragmentScopeViewModel(vm: Class<T>): T {
        if (!this::mFragmentProvider.isInitialized) {
            mFragmentProvider = ViewModelProvider(this)
        }
        return mFragmentProvider.get(vm)
    }

    /**
     * 传入ViewModel泛型,返回Activity级的作用域的ViewModel.
     * @sample 请在initViewModel时调用或使用延迟初始化
     * @param vm ViewModel类型
     * @return 返回Activity级的作用域的ViewModel.
     */
    protected fun <T : ViewModel> getActivityScopeViewModel(vm: Class<T>): T {
        if (!this::mActivityProvider.isInitialized) {
            mActivityProvider = ViewModelProvider(mActivity)
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
            mApplicationProvider = ViewModelProvider(mActivity.applicationContext as App)
        }
        return mApplicationProvider.get(vm)
    }
}