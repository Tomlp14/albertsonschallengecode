package com.tommyappdevs.albertsonsapp.view

import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.tommyappdevs.albertsonsapp.R

fun FragmentActivity.showError(errorMessage: String){
    Snackbar.make(
        findViewById(R.id.container),
        errorMessage,
        Snackbar.LENGTH_INDEFINITE
    ).setAction(
        "Dismiss"
    ){ }
}

fun FragmentActivity.showLoading(isLoading: Boolean){
    if (isLoading)
        findViewById<ProgressBar>(R.id.loading).visibility = View.VISIBLE
    else
        findViewById<ProgressBar>(R.id.loading).visibility = View.INVISIBLE
}