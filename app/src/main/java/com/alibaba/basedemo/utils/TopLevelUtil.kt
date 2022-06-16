package com.alibaba.basedemo.utils

import android.content.Context
import android.content.Intent

/**
 * 顶层函数工具类
 * Created by Rick on 2022/6/15 10:23.
 * God bless my code!
 */

/**
 * 在context作用域下启动一个新的Activity.
 *
 * @sample :
 * mContext.goActivity<TestActivity> {
 *    putExtra("param1", "data")
 *    putExtra("param2", 100)
 * }
 *
 * @param A 要跳转的Activity泛型.
 * @param block 在块作用域内以this作为intent值
 */
inline fun <reified A> Context.startActivity(noinline block: (Intent.() -> Unit)? = null) {
    Intent(this, A::class.java).run {
        startActivity(this.apply { block?.invoke(this) })
    }
}

