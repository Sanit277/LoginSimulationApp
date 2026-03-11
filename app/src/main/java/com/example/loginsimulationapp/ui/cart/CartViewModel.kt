package com.example.loginsimulationapp.ui.cart

import androidx.lifecycle.ViewModel
import com.example.loginsimulationapp.data.remote.dto.ProductDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CartViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState

    fun addToCart(product: ProductDto) {
        _uiState.update { state ->
            val existing = state.items.find { it.product.id == product.id }
            val newItems = if (existing == null) {
                state.items + CartItem(product = product, quantity = 1)
            } else {
                state.items.map {
                    if (it.product.id == product.id) it.copy(quantity = it.quantity + 1) else it
                }
            }
            state.copy(items = newItems)
        }
    }

    fun removeOne(productId: Long) {
        _uiState.update { state ->
            val newItems = state.items.mapNotNull { item ->
                if (item.product.id != productId) return@mapNotNull item
                val newQty = item.quantity - 1
                if (newQty <= 0) null else item.copy(quantity = newQty)
            }
            state.copy(items = newItems)
        }
    }

    fun deleteItem(productId: Long) {
        _uiState.update { state ->
            state.copy(items = state.items.filterNot { it.product.id == productId })
        }
    }

    fun clearCart() {
        _uiState.update { it.copy(items = emptyList()) }
    }
}
