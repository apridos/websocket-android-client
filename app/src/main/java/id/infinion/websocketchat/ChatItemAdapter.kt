package id.infinion.websocketchat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.infinion.websocketchat.databinding.ItemChatBinding

class ChatItemAdapter : RecyclerView.Adapter<ChatItemAdapter.ChatViewHolder>() {
    var chats = arrayListOf<String>()

    inner class ChatViewHolder(private val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(msg : String){
            binding.tvChatItem.text = msg
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder =
        ChatViewHolder(ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chats[position])
    }

    override fun getItemCount(): Int = chats.size
}