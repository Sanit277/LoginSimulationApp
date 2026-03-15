package com.example.loginsimulationapp.ui.login

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state defaults to Customer role with correct credentials`() {
        val state = viewModel.state.value
        assertEquals("Customer", state.selectedRole)
        assertEquals("customer@hamrostore.com", state.identifier)
        assertEquals("customer123", state.password)
    }

    @Test
    fun `selecting Admin role fills admin credentials`() {
        viewModel.onRoleSelected("Admin")
        val state = viewModel.state.value
        assertEquals("Admin", state.selectedRole)
        assertEquals("admin@hamrostore.com", state.identifier)
        assertEquals("admin123", state.password)
    }

    @Test
    fun `selecting Vendor role fills vendor credentials`() {
        viewModel.onRoleSelected("Vendor")
        val state = viewModel.state.value
        assertEquals("Vendor", state.selectedRole)
        assertEquals("vendor@hamrostore.com", state.identifier)
        assertEquals("vendor123", state.password)
    }

    @Test
    fun `login with blank identifier shows error message`() {
        viewModel.onIdentifierChange("")
        viewModel.onPasswordChange("password123")
        viewModel.onLoginClicked()
        val state = viewModel.state.value
        assertEquals("Email or phone is required", state.errorMessage)
        assertFalse(state.isLoggedIn)
    }

    @Test
    fun `login with invalid email format shows error message`() {
        viewModel.onIdentifierChange("invalid-email")
        viewModel.onPasswordChange("password123")
        viewModel.onLoginClicked()
        val state = viewModel.state.value
        assertEquals("Please enter a valid email address", state.errorMessage)
    }

    @Test
    fun `login with short password shows error message`() {
        viewModel.onIdentifierChange("admin@hamrostore.com")
        viewModel.onPasswordChange("123")
        viewModel.onLoginClicked()
        val state = viewModel.state.value
        assertEquals("Password must be at least 6 characters", state.errorMessage)
    }

    @Test
    fun `login with wrong password for role shows error message`() = runTest {
        viewModel.onRoleSelected("Admin")
        viewModel.onPasswordChange("wrongpassword")
        viewModel.onLoginClicked()
        advanceUntilIdle()
        val state = viewModel.state.value
        assertEquals("Incorrect password for selected role", state.errorMessage)
        assertFalse(state.isLoggedIn)
    }

    @Test
    fun `successful login sets isLoggedIn true with correct role`() = runTest {
        viewModel.onRoleSelected("Admin")
        viewModel.onLoginClicked()
        advanceUntilIdle()
        val state = viewModel.state.value
        assertTrue(state.isLoggedIn)
        assertEquals("ADMIN", state.userRole)
        assertNull(state.errorMessage)
    }

    @Test
    fun `successful Customer login maps identifier to CUSTOMER role`() = runTest {
        viewModel.onRoleSelected("Customer")
        viewModel.onLoginClicked()
        advanceUntilIdle()
        val state = viewModel.state.value
        assertTrue(state.isLoggedIn)
        assertEquals("CUSTOMER", state.userRole)
    }
}
