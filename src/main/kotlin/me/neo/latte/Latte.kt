package me.neo.latte

import me.neo.synapser.server.utils.Image
import me.neo.synapser.server.utils.Properties

object Latte {
    val favicon: String? = Image.load("icon.png")

    val eula = Properties("eula.txt", mapOf(Pair("eula", "false")))
    val serverProperties = Properties("server.properties", mapOf(
        Pair("ip-address", ""),
        Pair("server-port", "25565"),
        Pair("motd", "A Minecraft Server. Powered by Synapser."),
        Pair("disconnect-message", "Disconnected."),
        Pair("max-players", "20"),
        Pair("default-gamemode", "survival"),
        Pair("difficulty", "easy")
    ))
}