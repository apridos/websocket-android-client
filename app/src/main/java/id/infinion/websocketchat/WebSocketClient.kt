package id.infinion.websocketchat

import io.socket.client.IO
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


object WebSocketClient {

    lateinit var socket : io.socket.client.Socket

    @Synchronized
    fun setSocket(){
        val options = IO.Options()
        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(0, TimeUnit.MILLISECONDS)
            .readTimeout(0, TimeUnit.MILLISECONDS)
            .writeTimeout(0, TimeUnit.MILLISECONDS)

        options.callFactory = clientBuilder.build()

        try {
            socket = IO.socket("https://go-websocket.aprido.my.id", options)
        }catch (e : Exception){

        }
    }

    @Synchronized
    fun get() = socket

    @Synchronized
    fun startSocket(){
        socket.connect()
    }

    @Synchronized
    fun disconnectSocket(){
        socket.disconnect()
    }

}