package pokemontcg.features.cards

import java.io.File

fun loadJsonFromResources(fileName: String): String {
    val url = ClassLoader.getSystemClassLoader().getResource(fileName)
    val file = File(url.path)
    return file.readText()
}

