package com.example.loginsimulationapp.ui.home


data class HomeUiState(
    val categories: List<Category> = emptyList(),
    val products: List<Product> = emptyList()
)


data class Category(
    val id: Int,
    val title: String,
    val iconRes: Int
)

data class Product(
    val id: Int,
    val title: String,
    val price: String,
    val imageRes: Int
)