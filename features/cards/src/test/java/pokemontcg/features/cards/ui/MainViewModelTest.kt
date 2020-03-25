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
    fun `init with success and fill cards correctly`() {
        //arrange
        val cards = listOf(mockCard())
        coEvery { useCase.execute(null) } returns cards

        //act
        viewModel.init()

        //assert
        assertEquals(cards, viewModel.cards.value)
    }
}