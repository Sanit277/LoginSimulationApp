package com.example.loginsimulationapp.ui.vendor

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class VendorViewModelTest {

    private lateinit var viewModel: VendorViewModel

    @Before
    fun setUp() {
        viewModel = VendorViewModel()
    }

    @Test
    fun `initial state contains products and orders`() {
        val state = viewModel.uiState.value
        assertTrue(state.products.isNotEmpty())
        assertTrue(state.orders.isNotEmpty())
        assertEquals(34, state.totalProducts)
        assertEquals(5, state.pendingOrders)
    }

    @Test
    fun `removing a product adds it to removedProductIds`() {
        val productId = viewModel.uiState.value.products.first().id
        viewModel.removeProduct(productId)
        val state = viewModel.uiState.value
        assertTrue(productId in state.removedProductIds)
    }

    @Test
    fun `removing a product decrements totalProducts`() {
        val before = viewModel.uiState.value.totalProducts
        viewModel.removeProduct(viewModel.uiState.value.products.first().id)
        val after = viewModel.uiState.value.totalProducts
        assertEquals(before - 1, after)
    }

    @Test
    fun `fulfilling an order adds it to fulfilledOrderIds`() {
        val orderId = viewModel.uiState.value.orders.first().orderId
        viewModel.markOrderFulfilled(orderId)
        val state = viewModel.uiState.value
        assertTrue(orderId in state.fulfilledOrderIds)
    }

    @Test
    fun `fulfilling an order decrements pendingOrders`() {
        val before = viewModel.uiState.value.pendingOrders
        viewModel.markOrderFulfilled(viewModel.uiState.value.orders.first().orderId)
        val after = viewModel.uiState.value.pendingOrders
        assertEquals(before - 1, after)
    }
}
