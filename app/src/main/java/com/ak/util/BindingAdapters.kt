package com.ak.util

import android.graphics.Paint
import android.widget.ImageView
import android.widget.TextView

import androidx.databinding.BindingAdapter


@BindingAdapter("imageResource")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}


@BindingAdapter("strikeThrough")
fun strikeThrough(view: TextView, strikeThrough: Boolean) {
    if (strikeThrough) {
        view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        view.paintFlags = view.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}