package me.neo.synapser.server

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import me.neo.synapser.server.utils.Logger
class Synapser(private val ip: String, private val port: Int) {
    private var serverBossGroup: EventLoopGroup = NioEventLoopGroup()
    private var serverWorkerGroup: EventLoopGroup = NioEventLoopGroup()

    init {
        Logger.log("Starting synapser server on $ip:$port")
    }

    fun start() {
        try {
            val server = ServerBootstrap()
            server.group(serverBossGroup, serverWorkerGroup)
                .channel(NioServerSocketChannel::class.java)
                .childHandler(object : ChannelInitializer<SocketChannel>() {
                    override fun initChannel(channel: SocketChannel) {
                        channel.pipeline().addLast()
                    }
                })
                .childOption(ChannelOption.SO_KEEPALIVE, true)
            val future = server.bind(ip, port)
            Logger.log("Server has started.")
            future.channel().closeFuture().sync()
        } finally {
            Logger.event("Server Shutdown", "Stopping server.")
            serverWorkerGroup.shutdownGracefully()
            serverBossGroup.shutdownGracefully()
        }
    }
}