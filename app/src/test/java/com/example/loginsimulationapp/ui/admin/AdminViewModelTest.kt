package com.example.loginsimulationapp.ui.admin

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AdminViewModelTest {

    private lateinit var viewModel: AdminViewModel

    @Before
    fun setUp() {
        viewModel = AdminViewModel()
    }

    @Test
    fun `initial state has correct default stats`() {
        val state = viewModel.uiState.value
        assertEquals(1240, state.totalUsers)
        assertEquals(548, state.totalOrders)
        assertEquals(3, state.pendingVendorApprovals)
        assertTrue(state.approvedVendorIds.isEmpty())
        assertTrue(state.rejectedVendorIds.isEmpty())
    }

    @Test
    fun `approving a vendor adds it to approvedVendorIds`() {
        viewModel.approveVendor("V-01")
        val state = viewModel.uiState.value
        assertTrue("V-01" in state.approvedVendorIds)
        assertFalse("V-01" in state.rejectedVendorIds)
    }

    @Test
    fun `approving a vendor decrements pending approvals`() {
        val before = viewModel.uiState.value.pendingVendorApprovals
        viewModel.approveVendor("V-01")
        val after = viewModel.uiState.value.pendingVendorApprovals
        assertEquals(before - 1, after)
    }

    @Test
    fun `rejecting a vendor adds it to rejectedVendorIds`() {
        viewModel.rejectVendor("V-02")
        val state = viewModel.uiState.value
        assertTrue("V-02" in state.rejectedVendorIds)
        assertFalse("V-02" in state.approvedVendorIds)
    }

    @Test
    fun `pending approvals cannot go below zero`() {
        // Reject more vendors than there are
        repeat(10) { i -> viewModel.rejectVendor("V-$i") }
        val state = viewModel.uiState.value
        assertTrue(state.pendingVendorApprovals >= 0)
    }
}
