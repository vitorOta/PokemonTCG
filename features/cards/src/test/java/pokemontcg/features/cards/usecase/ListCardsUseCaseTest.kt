package pokemontcg.features.cards.usecase

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import pokemontcg.features.cards.data.CardsRepository
import pokemontcg.features.cards.mockCard
import pokemontcg.libraries.network.exceptions.MyNetworkException

class ListCardsUseCaseTest {

    @MockK
    private lateinit var repo: CardsRepository

    private lateinit var useCase: ListCardsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = ListCardsUseCase(repo)
    }

    @Test
    fun `executa com sucesso`() {
        runBlocking {
            //arrange
            val expectedResponse = listOf(mockCard())
            coEvery { repo.listCards() } returns expectedResponse

            //act
            val response = useCase.execute(null)

            //assert
            assertEquals(expectedResponse, response)
        }
    }

    @Test(expected = MyNetworkException::class)
    fun `executa com erro lanca excecao do repositorio MyNetworkException`() {
        runBlocking {
            //arrange
            coEvery { repo.listCards() } throws MyNetworkException()

            //act
            val response = useCase.execute(null)

            //assert
            fail()
        }
    }
}