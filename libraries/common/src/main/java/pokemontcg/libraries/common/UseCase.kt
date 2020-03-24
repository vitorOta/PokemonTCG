package pokemontcg.libraries.common

interface UseCase<Param, Return> {
    suspend fun execute(param: Param): Return
}