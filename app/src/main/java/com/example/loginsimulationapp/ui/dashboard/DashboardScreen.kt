package com.example.loginsimulationapp.ui.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.loginsimulationapp.data.remote.dto.ProductDto
import com.example.loginsimulationapp.ui.cart.CartScreen
import com.example.loginsimulationapp.ui.cart.CartViewModel
import com.example.loginsimulationapp.ui.dashboard.tabs.ProfileScreen
import com.example.loginsimulationapp.ui.home.HomeScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    uiState: DashboardUiState,
    onLogout: () -> Unit,
    onTabRouteChanged: (String) -> Unit
) {
    val tabNavController = rememberNavController()

    // ✅ Shared VM across Home + Cart tab (Dashboard scope)
    val cartViewModel: CartViewModel = viewModel()
    val cartState by cartViewModel.uiState.collectAsState()

    // Snackbar for “Added to cart”
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()



    LaunchedEffect(tabNavController) {
        tabNavController.currentBackStackEntryFlow.collect { entry ->
            val route = entry.destination.route ?: return@collect
            onTabRouteChanged(route)
        }
    }

    val currentTabRoute = uiState.currentTab.route

    val topTitle = when (currentTabRoute) {
        DashboardTab.Home.route -> "HamroStore"
        DashboardTab.Profile.route -> "Profile"
        DashboardTab.Cart.route -> "Cart"
        else -> "HamroStore"
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(topTitle) },
                actions = { TextButton(onClick = onLogout) { Text("Logout") } }
            )
        },
        bottomBar = {
            NavigationBar {
                dashboardTabs.forEach { tab ->
                    val selected = currentTabRoute == tab.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            tabNavController.navigate(tab.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(tabNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                            }
                        },
                        icon = {
                            if (tab is DashboardTab.Cart && cartState.totalItems > 0) {
                                BadgedBox(
                                    badge = { Badge { Text("${cartState.totalItems}") } }
                                ) {
                                    Icon(tab.icon, contentDescription = tab.label)
                                }
                            } else {
                                Icon(tab.icon, contentDescription = tab.label)
                            }
                        },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { innerPadding: PaddingValues ->
        NavHost(
            navController = tabNavController,
            startDestination = DashboardTab.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(DashboardTab.Home.route) {
                HomeScreen(
                    onAddToCart = { productDto ->
                        cartViewModel.addToCart(productDto)

                        scope.launch {
                            snackbarHostState.currentSnackbarData?.dismiss()

                            snackbarHostState.showSnackbar(
                                "${productDto.productName} added to cart 🛒"
                            )
                        }
                    }
                )
            }


            composable(DashboardTab.Profile.route) {
                ProfileScreen()
            }

            composable(DashboardTab.Cart.route) {
                var showCheckout by remember { mutableStateOf(false) }

                CartScreen(
                    uiState = cartState,
                    onPlus = { cartViewModel.addToCart(it.product) },
                    onMinus = { cartViewModel.removeOne(it.product.id) },
                    onDelete = { cartViewModel.deleteItem(it.product.id) },
                    onClear = { cartViewModel.clearCart() },
                    onCheckout = { showCheckout = true }
                )

                if (showCheckout) {
                    AlertDialog(
                        onDismissRequest = { showCheckout = false },
                        title = { Text("Order placed ✅") },
                        text = { Text("Your checkout was successful!") },
                        confirmButton = {
                            TextButton(onClick = {
                                showCheckout = false
                                cartViewModel.clearCart()
                            }) { Text("OK") }
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DashboardScreenPreview(
){
    DashboardScreen(
        uiState = DashboardUiState(),
        onLogout = {},
        onTabRouteChanged = {}
    )
}
