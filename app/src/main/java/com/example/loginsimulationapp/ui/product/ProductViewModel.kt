package com.example.loginsimulationapp.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginsimulationapp.data.remote.dto.ProductDto
import com.example.loginsimulationapp.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository = ProductRepository()
) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductDto>>(emptyList())
    val products: StateFlow<List<ProductDto>> = _products.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun fetchProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                _products.value = repository.getAllProducts()
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load products"
            } finally {
                _isLoading.value = false
            }
        }
    }
}