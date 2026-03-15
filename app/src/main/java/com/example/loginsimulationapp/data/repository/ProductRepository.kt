package com.example.loginsimulationapp.data.repository

import com.example.loginsimulationapp.R
import com.example.loginsimulationapp.data.remote.dto.ProductDto
import kotlinx.coroutines.delay

class ProductRepository {

    private val dummyProducts = listOf(
        ProductDto(
            id = 1,
            storeId = 101,
            storeName = "Astro Electronics",
            categoryId = 1,
            categoryName = "Tech",
            productName = "Astro Book Pro 14\"",
            productDescription = "A powerful laptop for professionals.",
            price = 1299.99,
            stockQuantity = 50,
            brand = "Astro",
            imageRes = R.drawable.product_laptop,
            status = "AVAILABLE"
        ),
        ProductDto(
            id = 2,
            storeId = 104,
            storeName = "Fresh Mart",
            categoryId = 4,
            categoryName = "Grocery",
            productName = "Fresh Red Apples",
            productDescription = "Crispy and sweet organic apples.",
            price = 4.99,
            stockQuantity = 100,
            brand = "Nature's Own",
            imageRes = R.drawable.apples,
            status = "AVAILABLE"
        ),
        ProductDto(
            id = 3,
            storeId = 105,
            storeName = "Home Essentials",
            categoryId = 5,
            categoryName = "Home",
            productName = "Insulated Thermos",
            productDescription = "Keeps your drinks hot or cold for 24 hours.",
            price = 24.99,
            stockQuantity = 30,
            brand = "Hydra",
            imageRes = R.drawable.thermos,
            status = "AVAILABLE"
        ),
        ProductDto(
            id = 4,
            storeId = 101,
            storeName = "Astro Electronics",
            categoryId = 1,
            categoryName = "Tech",
            productName = "Smart LED TV 55\"",
            productDescription = "4K Ultra HD resolution with smart features.",
            price = 599.00,
            stockQuantity = 20,
            brand = "Vision",
            imageRes = R.drawable.tv,
            status = "AVAILABLE"
        ),
        ProductDto(
            id = 5,
            storeId = 104,
            storeName = "Fresh Mart",
            categoryId = 4,
            categoryName = "Grocery",
            productName = "Whole Grain Bread",
            productDescription = "Freshly baked healthy bread.",
            price = 3.49,
            stockQuantity = 45,
            brand = "Baker's Choice",
            imageRes = R.drawable.bread,
            status = "AVAILABLE"
        ),
        ProductDto(
            id = 6,
            storeId = 101,
            storeName = "Astro Electronics",
            categoryId = 1,
            categoryName = "Tech",
            productName = "Wireless Headphones",
            productDescription = "Noise cancelling over-ear headphones.",
            price = 199.99,
            stockQuantity = 60,
            brand = "Sonic",
            imageRes = R.drawable.headphones,
            status = "AVAILABLE"
        ),
        ProductDto(
            id = 7,
            storeId = 104,
            storeName = "Fresh Mart",
            categoryId = 4,
            categoryName = "Grocery",
            productName = "Organic Bananas",
            productDescription = "Fresh bundle of organic bananas.",
            price = 2.99,
            stockQuantity = 120,
            brand = "Nature's Own",
            imageRes = R.drawable.banana,
            status = "AVAILABLE"
        ),
        ProductDto(
            id = 8,
            storeId = 106,
            storeName = "Lifestyle Hub",
            categoryId = 6,
            categoryName = "Grooming",
            productName = "Precision Trimmer",
            productDescription = "Professional waterproof beard trimmer.",
            price = 45.00,
            stockQuantity = 25,
            brand = "GroomPro",
            imageRes = R.drawable.trimmer,
            status = "AVAILABLE"
        ),
        ProductDto(
            id = 9,
            storeId = 104,
            storeName = "Fresh Mart",
            categoryId = 4,
            categoryName = "Grocery",
            productName = "Full Cream Milk",
            productDescription = "Rich and creamy 1L milk carton.",
            price = 1.99,
            stockQuantity = 80,
            brand = "Dairy Fresh",
            imageRes = R.drawable.milk,
            status = "AVAILABLE"
        ),
        ProductDto(
            id = 10,
            storeId = 107,
            storeName = "Urban Wear",
            categoryId = 7,
            categoryName = "Clothing",
            productName = "Classic White T-Shirt",
            productDescription = "100% cotton premium white t-shirt.",
            price = 19.99,
            stockQuantity = 150,
            brand = "Urban",
            imageRes = R.drawable.tshirt,
            status = "AVAILABLE"
        ),
        ProductDto(
            id = 11,
            storeId = 105,
            storeName = "Home Essentials",
            categoryId = 5,
            categoryName = "Home",
            productName = "Robot Vacuum",
            productDescription = "Smart vacuum cleaner for automatic home cleaning.",
            price = 299.00,
            stockQuantity = 12,
            brand = "CleanBot",
            imageRes = R.drawable.vaccum,
            status = "AVAILABLE"
        ),
        ProductDto(
            id = 12,
            storeId = 104,
            storeName = "Fresh Mart",
            categoryId = 4,
            categoryName = "Grocery",
            productName = "Roasted Coffee Beans",
            productDescription = "Premium 500g bag of Arabica coffee beans.",
            price = 14.50,
            stockQuantity = 40,
            brand = "Morning Brew",
            imageRes = R.drawable.coffee,
            status = "AVAILABLE"
        ),
        ProductDto(
            id = 13,
            storeId = 105,
            storeName = "Home Essentials",
            categoryId = 5,
            categoryName = "Home",
            productName = "Dish Soap",
            productDescription = "Tough on grease, gentle on hands.",
            price = 5.99,
            stockQuantity = 90,
            brand = "Sparkle",
            imageRes = R.drawable.dishsoap,
            status = "AVAILABLE"
        ),
        ProductDto(
            id = 14,
            storeId = 103,
            storeName = "Elegance Timepieces",
            categoryId = 3,
            categoryName = "Accessories",
            productName = "Titanium Chrono Watch",
            productDescription = "Luxury smartwatch with titanium build.",
            price = 399.99,
            stockQuantity = 15,
            brand = "Elegance",
            imageRes = R.drawable.product_watch,
            status = "AVAILABLE"
        ),
        ProductDto(
            id = 15,
            storeId = 102,
            storeName = "Velocity Sports",
            categoryId = 2,
            categoryName = "Footwear",
            productName = "Velocity Boost X",
            productDescription = "Premium running shoes.",
            price = 149.99,
            stockQuantity = 200,
            brand = "Velocity",
            imageRes = R.drawable.product_shoe,
            status = "AVAILABLE"
        )
    )

    suspend fun getAllProducts(): List<ProductDto> {
        delay(500)
        return dummyProducts
    }

    suspend fun getProductById(id: Long): ProductDto {
        delay(500)
        return dummyProducts.find { it.id == id } 
            ?: throw Exception("Product not found")
    }
}
