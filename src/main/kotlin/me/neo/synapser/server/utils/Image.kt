package me.neo.synapser.server.utils

import java.io.File
import java.util.Base64

object Image {
    /**
     * Loads PNG file as base64
     * @param filePath The path of the PNG file
     * @return Returns null if file not present. Otherwise, a base64 string
     */
    fun load(filePath: String): String? {
        val file = File(filePath)
        if (!file.exists()) return null
        val base64 = Base64.getEncoder().encodeToString(file.readBytes())
        return "data:image/png;base64,$base64"
    }
}