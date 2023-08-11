package com.example.notes.domain.extensions

import android.view.View

fun View.disable() {
    this.isEnabled = false
    this.alpha = .4f
}

fun View.enable() {
    this.isEnabled = true
    this.alpha = 1f
}
