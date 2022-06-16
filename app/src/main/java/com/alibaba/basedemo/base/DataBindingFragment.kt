package com.alibaba.basedemo.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.alibaba.basedemo.utils.DataBindingConfig

/**
 * 数据绑定Fragment基类，
 *
 * Created by Rick on 2022/6/16 15:17.
 * God bless my code!
 */
abstract class DataBindingFragment : Fragment() {

    protected lateinit var mActivity: AppCompatActivity
    private var _mBinding: ViewDataBinding? = null
    private val mBinding get() = _mBinding!!

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.getDataBindingConfig().run {
            _mBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
            mBinding.lifecycleOwner = viewLifecycleOwner
            getViewModelId()?.let { mBinding.setVariable(it, getViewModel()) }
            val bindingParams = getBindingParams()
            for (bindingParam in bindingParams) {
                mBinding.setVariable(bindingParam.key, bindingParam.value)
            }
            mBinding.executePendingBindings()
        }
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._mBinding?.unbind()
        this._mBinding = null
    }

}