package com.alibaba.basedemo

import android.os.Bundle
import android.util.Log
import com.alibaba.basedemo.base.BaseActivity
import com.alibaba.basedemo.utils.DataBindingConfig
import com.alibaba.basedemo.databinding.ActivitySampleBinding
import com.alibaba.basedemo.vm.SampleMessage
import com.alibaba.basedemo.vm.SampleState

class SampleActivity : BaseActivity() {

    private lateinit var mState: SampleState
    private lateinit var mMessage: SampleMessage
    private val binding by lazy { getBinding<ActivitySampleBinding>() }

    override fun initViewModel() {
        mState = getActivityScopeViewModel(SampleState::class.java)
        mMessage = getApplicationScopeViewModel(SampleMessage::class.java)
    }

    override fun init(savedInstanceState: Bundle?) {
        mState.clickCount.observe(this) {
            Log.d("TAG", "init: $it")
        }
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_sample, BR.vm, mState)
            .addBindingParam(BR.click, SampleClickProxy())
    }

    inner class SampleClickProxy {

        /**
         * do something.
         */
        fun sampleBtnClick() {
            mState.updateCount()
        }
    }

}