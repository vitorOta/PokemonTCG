package pokemontcg.libraries.ui_components

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.spyk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import pokemontcg.libraries.testutils.CoroutineTestRule

class BaseViewModelTest {
    @Rule
    @JvmField
    val coroutineTestRule = CoroutineTestRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel = spyk(ViewModelTest(), recordPrivateCalls = true)

    @Test
    fun `callSuccess execute all steps`() {
        //arrange
        val work = { }

        //act
        viewModel.callSuccess(work)

        //assert
        verifyOrder {
            viewModel.setLoading(true)
            work()
            viewModel.setLoading(false)
        }

        verify(exactly = 0) {
            viewModel.setError(any())
        }
    }

    @Test
    fun `callError should execute steps and set showError`() {
        //arrange
        val errorMessageExpected = "AAA"

        //act
        viewModel.callError(errorMessageExpected)

        //assert
        verifyOrder {
            viewModel.setLoading(true)
            viewModel.setError(errorMessageExpected)
            viewModel.setLoading(false)
        }

        assertEquals(errorMessageExpected, viewModel.showError.value)
    }
}
