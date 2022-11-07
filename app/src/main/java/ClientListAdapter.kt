//package com.shutterlux.onsite.ui.client
//
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.shutterlux.onsite.databinding.ClientGridViewItemBinding
//import com.shutterlux.onsite.domain.Client
//
//
//
//class ClientListAdapter :
//    ListAdapter<Client, ClientListAdapter.ClientsViewHolder>(DiffCallback) {
//
//
////    class ClientsViewHolder(
////        private var binding: ClientGridViewItemBinding
////    ) : RecyclerView.ViewHolder(binding.root) {
////        fun bind(client: Client) {
////            binding.client = client
////            binding.executePendingBindings()
////        }
////    }
////
////    companion object DiffCallback : DiffUtil.ItemCallback<Client>() {
////        override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean {
////            return oldItem.name == newItem.name
////        }
////
////        override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean {
////            return oldItem.name == newItem.name
////        }
////    }
////
////    override fun onCreateViewHolder(
////        parent: ViewGroup,
////        viewType: Int
////    ): ClientsViewHolder {
////        return ClientsViewHolder(
////            ClientGridViewItemBinding.inflate(LayoutInflater.from(parent.context))
////        )
////    }
////
////    override fun onBindViewHolder(holder: ClientsViewHolder, position: Int) {
////        val client = getItem(position)
////        holder.bind(client)
////    }
//}
