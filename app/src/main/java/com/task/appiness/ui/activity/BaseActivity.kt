package com.task.appiness.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {

    protected abstract fun initViewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        initViewBinding()
        initView()
        initListener()
    }

    protected abstract fun initView()
    protected abstract fun initListener()
    protected abstract fun observeViewModel()
}