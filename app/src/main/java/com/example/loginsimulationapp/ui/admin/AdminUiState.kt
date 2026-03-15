package com.example.loginsimulationapp.ui.admin

data class AdminOrderItem(
    val id: String,
    val customer: String,
    val amount: Double,
    val status: String
)

data class PendingVendor(
    val id: String,
    val name: String,
    val storeName: String
)

data class AdminUiState(
    val adminName: String = "Super Admin",
    val totalUsers: Int = 1240,
    val totalOrders: Int = 548,
    val totalRevenue: Double = 154200.0,
    val pendingVendorApprovals: Int = 3,
    val recentOrders: List<AdminOrderItem> = listOf(
        AdminOrderItem("ORD-001", "Aarav Sharma", 2500.0, "Delivered"),
        AdminOrderItem("ORD-002", "Sita Rai", 1200.0, "Processing"),
        AdminOrderItem("ORD-003", "Bikash Thapa", 4750.0, "Shipped"),
        AdminOrderItem("ORD-004", "Priya Karki", 890.0, "Pending"),
        AdminOrderItem("ORD-005", "Raj Adhikari", 3300.0, "Cancelled"),
    ),
    val pendingVendors: List<PendingVendor> = listOf(
        PendingVendor("V-01", "Mohan Electronics", "TechHub Store"),
        PendingVendor("V-02", "Laxmi Textiles", "FashionNepal"),
        PendingVendor("V-03", "Ganesh Foods", "FreshBazar"),
    ),
    val approvedVendorIds: Set<String> = emptySet(),
    val rejectedVendorIds: Set<String> = emptySet()
)
