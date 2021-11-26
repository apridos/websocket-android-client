package id.infinion.websocketchat

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.infinion.websocketchat.databinding.ActivityMainBinding
import io.socket.emitter.Emitter

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var chatAdapter : ChatItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WebSocketClient.setSocket()
        WebSocketClient.startSocket()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setupChatView()
        setupListeners()
        setContentView(binding.root)
    }

    private fun setupChatView(){
        val rvChats = binding.rvChats
        chatAdapter = ChatItemAdapter()
        rvChats.adapter = chatAdapter
        rvChats.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)

    }

    private fun setupListeners(){
        binding.btnSendMessage.setOnClickListener{
            WebSocketClient.get().emit("message", binding.etSendMessage.text.toString())
            binding.etSendMessage.text = null
        }

        WebSocketClient.get().on("new message", onNewMessage)
    }

    private val onNewMessage = Emitter.Listener { args ->
        this.runOnUiThread {
            chatAdapter.chats.add(args[0].toString())
            chatAdapter.notifyDataSetChanged()
        }
    }
}