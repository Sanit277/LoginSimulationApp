package com.example.loginsimulationapp.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    onAddToCart: (Product) -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    HomeScreenContent(uiState = uiState, onAddToCart = onAddToCart)
}

/*
   UI CONTENT (NO Scaffold / NO BottomNav / NO TopBar)
   DashboardScreen provides them.
*/
@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onAddToCart: (Product) -> Unit
) {
    var query by remember { mutableStateOf("") }

    val filteredProducts = remember(uiState.products, query) {
        uiState.products.filter { p ->
            query.isBlank() || p.title.contains(query, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 🔍 Search (WOW + useful)
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Search products") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Categories",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow {
            items(uiState.categories) { category ->
                CategoryItem(category)
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Products",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ✨ Animated empty state
        AnimatedVisibility(
            visible = filteredProducts.isEmpty(),
            enter = fadeIn() + slideInVertically(initialOffsetY = { it / 3 }),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 3 })
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("No products found 😕", style = MaterialTheme.typography.titleMedium)
            }
        }

        if (filteredProducts.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredProducts) { product ->
                    ProductCard(product = product, onAddToCart = { onAddToCart(product) })
                }
            }
        }
    }
}

/*
   COMPONENTS
 */

@Composable
fun CategoryItem(category: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(end = 16.dp)
            .clickable { /* later: filter */ }
    ) {
        Image(
            painter = painterResource(id = category.iconRes),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Text(text = category.title)
    }
}

@Composable
fun ProductCard(
    product: Product,
    onAddToCart: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(product.title, fontWeight = FontWeight.SemiBold, softWrap = true, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(product.price)

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = onAddToCart,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add")
            }
        }
    }
}

/*
   PREVIEW
 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(
        uiState = HomeUiState(
            categories = listOf(Category(1, "Preview", android.R.drawable.ic_menu_gallery)),
            products = listOf(Product(1, "Preview Item", "$9.99", android.R.drawable.ic_menu_gallery))
        ),
        onAddToCart = {}
    )
}
