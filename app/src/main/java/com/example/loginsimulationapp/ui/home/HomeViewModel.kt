package com.example.loginsimulationapp.ui.home

import androidx.lifecycle.ViewModel
import com.example.loginsimulationapp.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeUiState(
            categories = sampleCategories,
            products = sampleProducts
        )
    )
    val uiState: StateFlow<HomeUiState> = _uiState

    // ✅ Tabs are controlled by Dashboard now, so no selectedTab / onTabSelected here.
}

/* -------------------- SAMPLE DATA -------------------- */

private val sampleCategories = listOf(
    Category(1, "Grocery", R.drawable.grocery),
    Category(2, "Electronics", R.drawable.electronics),
    Category(3, "Accessories", R.drawable.accessories),
    Category(4, "Clothing", R.drawable.clothing),
    Category(5, "Pharmacy", R.drawable.pharmacy)
)

private val sampleProducts = listOf(
    Product(1, "Smart TV 43\"", "$299.99", R.drawable.tv),
    Product(2, "Organic Apples 1kg", "$3.49", R.drawable.apples),
    Product(3, "Bluetooth Headphones", "$49.99", R.drawable.headphones),
    Product(4, "Vacuum Cleaner", "$89.99", R.drawable.vaccum),
    Product(5, "Men's T-Shirt", "$12.99", R.drawable.tshirt),
    Product(6, "Dish Soap", "$2.99", R.drawable.dishsoap),
    Product(7, "Thermos", "$1.99", R.drawable.thermos),
    Product(8, "Banana", "$0.99", R.drawable.banana),
    Product(9, "Milk", "$2.49", R.drawable.milk),
    Product(10, "Bread", "$1.49", R.drawable.bread),
    Product(11, "Coffee", "$3.99", R.drawable.coffee),
    Product(12, "Trimmer", "$2.99", R.drawable.trimmer),
)
