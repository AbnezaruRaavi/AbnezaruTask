package com.app.abnezarutask.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.abnezarutask.R
import com.app.abnezarutask.models.Location
import com.squareup.picasso.Picasso

@BindingAdapter("app:address")
fun setAddress(textView: TextView, location: Location){
    textView.text = "${location.street.number}, ${location.street.name}, ${location.city}, ${location.state}, ${location.country}, ${location.postcode}"
}

@BindingAdapter("app:loadImage")
fun loadImage(imageView: ImageView, url : String){
    Picasso.get().load(url).error(R.drawable.ic_user).into(imageView)
}