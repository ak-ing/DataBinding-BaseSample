package com.alibaba.basedemo.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Rick on 2022/6/15 12:13.
 * God bless my code!
 */
class SampleState : ViewModel() {

    private val _clickCount = MutableLiveData(0)

    val clickCount: LiveData<Int> get() = _clickCount

    fun updateCount() {
        _clickCount.value = _clickCount.value?.plus(1)
    }

}