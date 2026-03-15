package com.example.loginsimulationapp.ui.vendor

data class VendorProductItem(
    val id: String,
    val name: String,
    val price: Double,
    val stock: Int
)

data class VendorOrderItem(
    val orderId: String,
    val product: String,
    val qty: Int,
    val status: String
)

data class VendorUiState(
    val storeName: String = "My Hamro Shop",
    val vendorName: String = "Suresh Vendor",
    val totalProducts: Int = 34,
    val pendingOrders: Int = 5,
    val totalSales: Double = 42300.0,
    val products: List<VendorProductItem> = listOf(
        VendorProductItem("P-01", "Laptop Pro 15", 85000.0, 12),
        VendorProductItem("P-02", "Running Shoes X", 3500.0, 45),
        VendorProductItem("P-03", "Smart Watch Z", 12000.0, 8),
        VendorProductItem("P-04", "Wireless Earbuds", 5500.0, 20),
    ),
    val orders: List<VendorOrderItem> = listOf(
        VendorOrderItem("O-101", "Laptop Pro 15", 1, "Pending"),
        VendorOrderItem("O-102", "Running Shoes X", 2, "Processing"),
        VendorOrderItem("O-103", "Smart Watch Z", 1, "Ready to Ship"),
        VendorOrderItem("O-104", "Wireless Earbuds", 3, "Pending"),
        VendorOrderItem("O-105", "Laptop Pro 15", 1, "Shipped"),
    ),
    val fulfilledOrderIds: Set<String> = emptySet(),
    val removedProductIds: Set<String> = emptySet()
)
