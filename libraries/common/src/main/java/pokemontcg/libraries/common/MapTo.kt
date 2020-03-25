package pokemontcg.libraries.common

interface MapTo<T> {
    fun mapTo(): T
}

fun <T> Iterable<MapTo<T>>.mapTo() = this.map { it.mapTo() }