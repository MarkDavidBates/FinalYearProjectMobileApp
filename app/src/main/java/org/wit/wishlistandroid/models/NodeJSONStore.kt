package org.wit.wishlistandroid.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.wishlistandroid.main.MainApp
import org.wit.wishlistandroid.main.exists
import org.wit.wishlistandroid.main.read
import org.wit.wishlistandroid.main.write
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "nodes.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<NodeModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class NodesJSONStore(private val context: Context) : NodeStore {

    var nodes = mutableListOf<NodeModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<NodeModel> {
        logAll()
        return nodes
    }

    override fun create(node: NodeModel) {
        node.id = generateRandomId()
        nodes.add(node)
        serialize()
    }


    override fun update(node: NodeModel) {
        val nodesList = findAll() as ArrayList<NodeModel>
        var foundNodelist: NodeModel? = nodesList.find { p -> p.id == node.id }
        if (foundNodelist != null) {
            foundNodelist.title = node.title
            foundNodelist.penID = node.penID
            foundNodelist.temperature = node.temperature
            foundNodelist.humidity = node.temperature
            foundNodelist.moisture = node.moisture
        }
        serialize()
    }

    override fun delete(node: NodeModel) {
        nodes.remove(node)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(nodes, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        nodes = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        nodes.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}