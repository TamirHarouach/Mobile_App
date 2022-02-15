package com.example.cirestechnologiesmobilechallenge.core.util

import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> ComponentActivity.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
        lifecycleScope.launch {
            flow.collectLatest(collect)
        }
    }

    fun <T> ComponentActivity.collectLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
        lifecycleScope.launch {
            flow.collect(collect)
        }
    }

    //Loading Image URL into ImageView Using Glide Library
    fun ImageView.loadImageWithGlide(imgUrl: String) {
        Glide.with(this).load(imgUrl)
            .placeholder(0)
            .into(this)
    }