package com.calyrsoft.ucbp1.features.profile.domain.usecase

import com.calyrsoft.ucbp1.features.profile.domain.model.ProfileModel
import com.calyrsoft.ucbp1.features.profile.domain.repository.IProfileRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetProfileUseCaseTest {

    @Mock
    private lateinit var mockRepository: IProfileRepository

    private lateinit var getProfileUseCase: GetProfileUseCase

    // Datos de prueba
    private val mockProfile = ProfileModel(
        id = "1",
        name = "Homero Simpson",
        email = "homero@springfield.com",
        phone = "+59177788899",
        avatarUrl = "https://example.com/avatar.png",
        summary = "Inspector nuclear"
    )

    @Before
    fun setUp() {
        getProfileUseCase = GetProfileUseCase(mockRepository)
    }

    @Test
    fun `invoke should return success result when repository call is successful`() = runTest {
        // Given
        val expectedResult = Result.success(mockProfile)
        whenever(mockRepository.fetchData()).thenReturn(expectedResult)

        // When
        val result = getProfileUseCase()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockProfile, result.getOrNull())
        verify(mockRepository).fetchData()
    }

    @Test
    fun `invoke should return failure result when repository call fails`() = runTest {
        // Given
        val expectedException = Exception("Error de red")
        val expectedResult = Result.failure<ProfileModel>(expectedException)
        whenever(mockRepository.fetchData()).thenReturn(expectedResult)

        // When
        val result = getProfileUseCase()

        // Then
        assertTrue(result.isFailure)
        assertEquals(expectedException, result.exceptionOrNull())
        verify(mockRepository).fetchData()
    }

    @Test
    fun `invoke should call repository exactly once`() = runTest {
        // Given
        whenever(mockRepository.fetchData()).thenReturn(Result.success(mockProfile))

        // When
        getProfileUseCase()

        // Then
        verify(mockRepository).fetchData()
        verifyNoMoreInteractions(mockRepository)
    }

    @Test
    fun `invoke should delay for approximately 3 seconds`() = runTest {
        // Given
        whenever(mockRepository.fetchData()).thenReturn(Result.success(mockProfile))
        val startTime = System.currentTimeMillis()

        // When
        getProfileUseCase()

        // Then
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime

        // Verificar que el tiempo transcurrido es aproximadamente 3000ms
        // (con un margen de tolerancia para evitar falsos negativos)
        assertTrue("Elapsed time should be close to 3000ms", elapsedTime in 2900L..3100L)
        verify(mockRepository).fetchData()
    }

    @Test
    fun `invoke should propagate repository success result correctly`() = runTest {
        // Given
        val customProfile = ProfileModel(
            id = "2",
            name = "Marge Simpson",
            email = "marge@springfield.com",
            phone = "+59177788800",
            avatarUrl = "https://example.com/marge.png",
            summary = "Ama de casa"
        )
        val expectedResult = Result.success(customProfile)
        whenever(mockRepository.fetchData()).thenReturn(expectedResult)

        // When
        val result = getProfileUseCase()

        // Then
        assertEquals(expectedResult, result)
        assertEquals(customProfile, result.getOrNull())
        verify(mockRepository).fetchData()
    }

    @Test
    fun `invoke should propagate repository failure result correctly`() = runTest {
        // Given
        val networkException = RuntimeException("Network unavailable")
        val expectedResult = Result.failure<ProfileModel>(networkException)
        whenever(mockRepository.fetchData()).thenReturn(expectedResult)

        // When
        val result = getProfileUseCase()

        // Then
        assertEquals(expectedResult, result)
        assertTrue(result.isFailure)
        assertEquals("Network unavailable", result.exceptionOrNull()?.message)
        verify(mockRepository).fetchData()
    }
}