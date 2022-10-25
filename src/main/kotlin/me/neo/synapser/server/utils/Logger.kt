package me.neo.synapser.server.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Logger {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    private fun internalOut(msg: String, type: String, vararg input: String) {
        var msg = "(Synapser $type) -> $msg"
        for (input_ in input) {
            msg += input_ + " "
        }
        val time = formatter.format(LocalDateTime.now()).toString()
        println("[$time] $msg")
    }
    fun debug(msg: String, vararg input: String) {
        internalOut(msg, "Debug", *input)
    }

    fun log(msg: String, vararg input: String) {
        internalOut(msg, "Log", *input)
    }

    fun warn(msg: String, vararg input: String) {
        internalOut(msg, "Warn", *input)
    }

    fun error(msg: String, vararg input: String) {
        internalOut(msg, "Error", *input)
    }

    fun event(event: String, msg: String, vararg input: String) {
        internalOut(msg, "Event | $event", *input)
    }

    fun out(type: String, msg: String, vararg input: String) {
        internalOut(msg, type, *input)
    }
}