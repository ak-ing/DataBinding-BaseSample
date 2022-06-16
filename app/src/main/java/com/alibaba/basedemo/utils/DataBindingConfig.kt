package com.alibaba.basedemo.utils

import androidx.lifecycle.ViewModel

/**
 * DataBinding配置类
 *
 * @param layout 视图xml的Id，即layoutResId--必填
 * @param viewModelId DataBinding中xml的ViewModel变量生成的BR值--可选
 * @param vm 需绑定到xml视图的ViewModel实例--可选
 *
 * Created by Rick on 2022/6/15 14:59.
 * God bless my code!
 */
class DataBindingConfig(
    private val layout: Int,
    private val viewModelId: Int? = null,
    private val vm: ViewModel? = null,
) {
    private val bindParams: HashMap<Int, Any> = HashMap()

    public fun getLayout() = layout

    public fun getViewModelId() = viewModelId

    public fun getViewModel() = vm

    public fun getBindingParams() = bindParams

    public fun addBindingParam(variableId: Int, variable: Any) = apply {
        if (!bindParams.containsKey(variableId)) {
            bindParams[variableId] = variable
        }
    }

}