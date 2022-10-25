package me.neo.synapser.server.player

import io.netty.channel.ChannelHandlerContext

class PlayerSocket(private val ctx: ChannelHandlerContext) {

    fun disconnect() {

    }
    fun close() {
        ctx.close()
    }
}