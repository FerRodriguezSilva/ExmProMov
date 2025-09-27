package com.calyrsoft.ucbp1.features.profile.domain.model

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.Mockito.mock

@RunWith(MockitoJUnitRunner::class)
class ProfileValueObjectsTest {

    @Test
    fun `crear ProfileName válido`() {
        // Given
        val nombreValido = "Homero Simpson"

        // When
        val name = ProfileName(nombreValido)

        // Then
        assertEquals("Homero Simpson", name.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `crear ProfileName vacío lanza excepción`() {
        ProfileName("")
    }

    @Test
    fun `crear ProfileEmail válido`() {
        // Given
        val emailValido = "homero@springfield.com"

        // When
        val email = ProfileEmail(emailValido)

        // Then
        assertEquals("homero@springfield.com", email.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `crear ProfileEmail inválido lanza excepción`() {
        ProfileEmail("correo_invalido")
    }

    @Test
    fun `crear ProfileCellphone válido`() {
        // Given
        val telefonoValido = "+59177788899"

        // When
        val phone = ProfileCellphone(telefonoValido)

        // Then
        assertEquals("+59177788899", phone.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `crear ProfileCellphone vacío lanza excepción`() {
        ProfileCellphone("")
    }

    @Test
    fun `crear ProfileUrl válido`() {
        // Given
        val urlValida = "https://example.com/avatar.png"

        // When
        val url = ProfileUrl(urlValida)

        // Then
        assertTrue(url.value.startsWith("http"))
        assertEquals(urlValida, url.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `crear ProfileUrl inválido lanza excepción`() {
        ProfileUrl("ftp://example.com")
    }

    @Test
    fun `crear ProfileSummary válido`() {
        // Given
        val resumenValido = "Inspector nuclear"

        // When
        val summary = ProfileSummary(resumenValido)

        // Then
        assertEquals("Inspector nuclear", summary.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `crear ProfileSummary vacío lanza excepción`() {
        ProfileSummary("")
    }

    // Ejemplo de test usando Mockito (aunque no es necesario para value objects)
    @Test
    fun `ProfileName no debería interactuar con dependencias externas`() {
        // Given
        val dependenciaMockeada = mock(Any::class.java)
        val nombreValido = "Homero Simpson"

        // When
        val name = ProfileName(nombreValido)

        // Then - Verificar que no hay interacción con mocks
        verifyNoInteractions(dependenciaMockeada)
        assertEquals(nombreValido, name.value)
    }
}