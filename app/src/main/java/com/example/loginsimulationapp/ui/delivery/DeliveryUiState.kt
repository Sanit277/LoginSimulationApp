package com.example.loginsimulationapp.ui.delivery

data class DeliveryItem(
    val orderId: String,
    val customerName: String,
    val address: String,
    val product: String,
    val status: String
)

data class DeliveryUiState(
    val agentName: String = "Ram Delivery",
    val activeDeliveries: Int = 3,
    val completedToday: Int = 7,
    val totalEarnings: Double = 1250.0,
    val deliveries: List<DeliveryItem> = listOf(
        DeliveryItem("D-001", "Aarav Sharma", "Kathmandu, Baneshwor", "Laptop Pro 15", "Picked Up"),
        DeliveryItem("D-002", "Sita Rai", "Lalitpur, Pulchowk", "Smart Watch Z", "Pending"),
        DeliveryItem("D-003", "Bikash Thapa", "Bhaktapur, Suryabinayak", "Running Shoes X", "Pending"),
        DeliveryItem("D-004", "Priya Karki", "Kathmandu, Thamel", "Wireless Earbuds", "Delivered"),
        DeliveryItem("D-005", "Raj Adhikari", "Lalitpur, Jawlakhel", "Smart Watch Z", "Delivered"),
    ),
    val pickedUpIds: Set<String> = emptySet(),
    val deliveredIds: Set<String> = emptySet()
)
