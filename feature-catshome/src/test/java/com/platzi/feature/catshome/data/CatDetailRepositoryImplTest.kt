package com.platzi.feature.catshome.data

import com.platzi.feature.catshome.data.mapper.toCatDetail
import com.platzi.feature.catshome.data.remote.CatsApi
import com.platzi.feature.catshome.data.remote.dto.CatDetailDto
import com.platzi.feature.catshome.data.repository.CatDetailRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class CatDetailRepositoryImplTest {

    @Test
    fun `test getCatDetail with success response`() = runTest {
        // Given
        val mockCatId = "32a"
        val expectedCatDetailDto = CatDetailDto(
            "32a",
            "https://cdn2.thecatapi.com/images/32a.jpg",
            listOf(),
            1600,
            1200
        )
        val api = mockk<CatsApi>()
        val repository = CatDetailRepositoryImpl(api)

        // Mock the CatsApi response
        coEvery { api.getCatDetail(mockCatId) } returns expectedCatDetailDto

        // When
        val result = repository.getCatDetail(mockCatId)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedCatDetailDto.toCatDetail(), result.getOrNull())
    }

    @Test
    fun `test getCatDetail with error response`() = runTest {
        // Given
        val mockCatId = "cat123"
        val errorException = Exception("Network Error")
        val api = mockk<CatsApi>()
        val repository = CatDetailRepositoryImpl(api)

        // Mock the CatsApi response with an exception
        coEvery { api.getCatDetail(mockCatId) } throws errorException

        // When
        val result = repository.getCatDetail(mockCatId)

        // Then
        assertTrue(result.isFailure)
        assertEquals(errorException, result.exceptionOrNull())
    }
}