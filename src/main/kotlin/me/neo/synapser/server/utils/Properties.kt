package me.neo.synapser.server.utils

import java.io.File
import java.io.FileWriter
import java.util.*
import kotlin.collections.HashMap

class Properties(filename: String, defaults: Map<String, String>) {
    private var file: File = File(filename)
    private var properties: HashMap<String, String> = HashMap()
    init {
        if (!file.exists()) file.createNewFile()
        val reader = Scanner(file)
        while (reader.hasNextLine()) {
            val line = reader.nextLine()
            if (!line.contains("=") || line.startsWith("#")) continue
            val parts = line.split("=")
            properties[parts[0]] = if (parts.size == 2) parts[1] else ""
        }
        defaults.forEach {
            if (getProperty(it.key) == null) {
                setProperty(it.key, it.value)
            }
        }
    }

    fun getProperty(key: String): String? {
        return properties[key]
    }

    fun setProperty(key: String, value: String) {
        properties[key] = value
        write()
    }

    fun write(vararg topText: String) {
        val writer = FileWriter(file)
        var output = topText.joinToString("\n") + "\n"
        properties.forEach {
            output += "${it.key}=${it.value}\n"
        }
        writer.write(output)
        writer.close()
    }
}