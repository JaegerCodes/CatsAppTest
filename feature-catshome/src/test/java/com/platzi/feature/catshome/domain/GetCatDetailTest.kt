package com.platzi.feature.catshome.domain

import com.platzi.feature.catshome.domain.model.CatDetail
import com.platzi.feature.catshome.domain.repository.CatDetailRepository
import com.platzi.feature.catshome.domain.usecase.GetCatDetail
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCatDetailTest {

    @Test
    fun `test invoke with success response`() = runTest {
        // Given
        val mockCatId = "32a"
        val expectedCatDetail = CatDetail(
            "32a",
            "https://cdn2.thecatapi.com/images/32a.jpg",
            listOf()
        )
        val mockRepository = mockk<CatDetailRepository>()
        val useCase = GetCatDetail(repository = mockRepository)

        // Mock the repository response
        coEvery { mockRepository.getCatDetail(mockCatId) } returns Result.success(expectedCatDetail)

        // When
        val resultFlow: Flow<GetCatDetail.Result> = useCase(mockCatId)

        // Then
        resultFlow.collect { result ->
            assertTrue(result is GetCatDetail.Result.Success)
            assertEquals(expectedCatDetail, (result as GetCatDetail.Result.Success).detail)
        }
    }

    @Test
    fun `test invoke with error response`() = runTest {
        // Given
        val mockCatId = "32a"
        val mockRepository = mockk<CatDetailRepository>()
        val useCase = GetCatDetail(repository = mockRepository)

        // Mock the repository response with an error
        coEvery { mockRepository.getCatDetail(mockCatId) } returns Result.failure(mockk(relaxed = true))

        // When
        val resultFlow: Flow<GetCatDetail.Result> = useCase(mockCatId)

        // Then
        resultFlow.collect { result ->
            assertTrue(result is GetCatDetail.Result.Error)
        }
    }
}
