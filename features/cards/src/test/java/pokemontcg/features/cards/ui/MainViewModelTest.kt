package pokemontcg.features.cards.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pokemontcg.features.cards.mockCard
import pokemontcg.features.cards.ui.main.MainViewModel
import pokemontcg.features.cards.usecase.ListCardsUseCase
import pokemontcg.libraries.network.exceptions.ServerErrorException
import pokemontcg.libraries.testutils.CoroutineTestRule

class MainViewModelTest {

    @Rule
    @JvmField
    val coroutineTestRule = CoroutineTestRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val useCase: ListCardsUseCase = spyk(ListCardsUseCase(mockk()))

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(useCase)
    }

    @Test
    fun `init with success, set initialized true and fill cards correctly`() {
        //arrange
        val cards = listOf(mockCard())
        coEvery { useCase.execute(null) } returns cards

        //act
        viewModel.init()

        //assert
        assert(viewModel.isInitialized)
        assertEquals(cards, viewModel.cards.value)
    }

    @Test
    fun `init with error from usecase, let initialized as false and NOT fill cards`() {
        //arrange
        coEvery { useCase.execute(null) } throws ServerErrorException()

        //act
        viewModel.init()

        //assert
        assertEquals(false, viewModel.isInitialized)
        assert(viewModel.cards.value.isNullOrEmpty())
    }
}