package com.alibaba.basedemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.alibaba.basedemo.utils.DataBindingConfig

/**
 * 数据绑定Activity基类，
 *
 * Created by Rick on 2022/6/15 12:12.
 * God bless my code!
 */
abstract class DataBindingActivity : AppCompatActivity() {

    private lateinit var mBinding: ViewDataBinding

    /**
     * 初始化ViewModel
     */
    protected abstract fun initViewModel()

    /**
     * 提供一个DataBindingConfig实例自动完成绑定
     * @see DataBindingConfig
     */
    protected abstract fun getDataBindingConfig(): DataBindingConfig

    /**
     * @param T 当前布局的Binding类类
     * @return 返回当前布局的binding对象
     */
    protected fun <T : ViewDataBinding> getBinding(): T {
        return mBinding as T
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initViewModel()
        this.getDataBindingConfig().run {
            mBinding = DataBindingUtil.setContentView(this@DataBindingActivity, getLayout())
            mBinding.lifecycleOwner = this@DataBindingActivity
            getViewModelId()?.let { mBinding.setVariable(it, getViewModel()) }
            val bindingParams = getBindingParams()
            for (bindingParam in bindingParams) {
                mBinding.setVariable(bindingParam.key, bindingParam.value)
            }
            mBinding.executePendingBindings()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }

}
