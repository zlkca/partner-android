//package com.shutterlux.onsite
//
//
//import android.view.View
//import android.widget.ImageView
//import androidx.core.net.toUri
//import androidx.databinding.BindingAdapter
//import androidx.recyclerview.widget.RecyclerView
//import coil.load
//import com.shutterlux.onsite.ui.client.ApiStatus
//import com.shutterlux.onsite.domain.Client
//import com.shutterlux.onsite.ui.client.ClientListAdapter
////import com.example.android.Clients.overview.ApiStatus
//
///**
// * Updates the data shown in the [RecyclerView].
// */
//@BindingAdapter("app:listData")
//fun bindRecyclerView(recyclerView: RecyclerView, data: List<Client>?) {
//    val adapter = recyclerView.adapter as ClientListAdapter
//    adapter.submitList(data)
//}
//
///**
// * Uses the Coil library to load an image by URL into an [ImageView]
// */
//@BindingAdapter("app:imageUrl")
//fun bindImage(imgView: ImageView, imgUrl: String?) {
//    imgUrl?.let {
//        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
//        imgView.load(imgUri) {
//            placeholder(R.drawable.loading_animation)
//            error(R.drawable.ic_broken)
//        }
//    }
//}
//
///**
// * This binding adapter displays the [ApiStatus] of the network request in an image view.  When
// * the request is loading, it displays a loading_animation.  If the request has an error, it
// * displays a broken image to reflect the connection error.  When the request is finished, it
// * hides the image view.
// */
//@BindingAdapter("ApiStatus")
//fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
//    when (status) {
//        ApiStatus.LOADING -> {
//            statusImageView.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.loading_animation)
//        }
//        ApiStatus.ERROR -> {
//            statusImageView.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.ic_connection_error)
//        }
//        ApiStatus.DONE -> {
//            statusImageView.visibility = View.GONE
//        }
//        null -> TODO()
//    }
//}
