package pokemontcg.features.cards.data

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import pokemontcg.features.cards.data.network.CardsApi
import pokemontcg.features.cards.data.network.CardsNetworkRepository
import pokemontcg.features.cards.data.network.schemas.ListCardResponse
import pokemontcg.libraries.common.mapTo
import pokemontcg.libraries.network.ApiClientBuilder
import pokemontcg.libraries.network.exceptions.ServerErrorException
import pokemontcg.libraries.network.fromJson
import pokemontcg.libraries.network.gsonDefault
import pokemontcg.libraries.testutils.enqueueResponse

class CardsNetworkRepositoryTest {

    private val server = MockWebServer()

    private lateinit var api: CardsApi

    private lateinit var repo: CardsNetworkRepository

    @Before
    fun setup() {
        server.start()
        val url = server.url("").toString()

        api = ApiClientBuilder.createServiceApi(CardsApi::class.java, url)
        repo = CardsNetworkRepository(api)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `listCards com sucesso`() = runBlocking {
        //assert
        val jsonResponse =
            pokemontcg.libraries.testutils.loadJsonFromResources("listCards.json")
        server.enqueueResponse(200, jsonResponse)

        //act
        val response = repo.listCards()

        //assert
        val expectedResponse = gsonDefault.fromJson<ListCardResponse>(jsonResponse).cards.mapTo()
        assertEquals(expectedResponse, response)
    }

    @Test(expected = ServerErrorException::class)
    fun `api retorna 500 lanca ServerErrorException`() = runBlocking {
        server.enqueueResponse(500, "")
        repo.listCards()
        fail()
    }
}