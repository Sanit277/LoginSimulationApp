package com.example.loginsimulationapp.ui.delivery

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DeliveryViewModelTest {

    private lateinit var viewModel: DeliveryViewModel

    @Before
    fun setUp() {
        viewModel = DeliveryViewModel()
    }

    @Test
    fun `initial state has correct active deliveries`() {
        val state = viewModel.uiState.value
        assertEquals(3, state.activeDeliveries)
        assertEquals(7, state.completedToday)
        assertTrue(state.pickedUpIds.isEmpty())
        assertTrue(state.deliveredIds.isEmpty())
    }

    @Test
    fun `marking picked up adds orderId to pickedUpIds`() {
        viewModel.markPickedUp("D-002")
        val state = viewModel.uiState.value
        assertTrue("D-002" in state.pickedUpIds)
    }

    @Test
    fun `marking delivered adds orderId to deliveredIds and updates counters`() {
        val before = viewModel.uiState.value.completedToday
        val earningsBefore = viewModel.uiState.value.totalEarnings
        viewModel.markPickedUp("D-002")
        viewModel.markDelivered("D-002")
        val state = viewModel.uiState.value
        assertTrue("D-002" in state.deliveredIds)
        assertEquals(before + 1, state.completedToday)
        assertEquals(earningsBefore + 150.0, state.totalEarnings, 0.01)
    }

    @Test
    fun `active deliveries decrement on delivery`() {
        val before = viewModel.uiState.value.activeDeliveries
        viewModel.markPickedUp("D-002")
        viewModel.markDelivered("D-002")
        val after = viewModel.uiState.value.activeDeliveries
        assertEquals(before - 1, after)
    }

    @Test
    fun `active deliveries cannot go below zero`() {
        repeat(20) { i -> viewModel.markDelivered("D-$i") }
        assertTrue(viewModel.uiState.value.activeDeliveries >= 0)
    }
}
