package com.example.loginsimulationapp.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.loginsimulationapp.R
import com.example.loginsimulationapp.data.remote.dto.ProductDto
import com.example.loginsimulationapp.ui.cart.CartScreen
import com.example.loginsimulationapp.ui.cart.CartViewModel
import com.example.loginsimulationapp.ui.dashboard.tabs.ProfileScreen
import com.example.loginsimulationapp.ui.home.HomeScreen
import com.example.loginsimulationapp.ui.navigation.AppRoute
import com.example.loginsimulationapp.ui.product.ProductDetailScreen
import com.example.loginsimulationapp.ui.product.ProductViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    uiState: DashboardUiState,
    onLogout: () -> Unit,
    onTabRouteChanged: (String) -> Unit,
    onCheckoutClick: () -> Unit // Added for Step 4
) {
    val tabNavController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()
    val productViewModel: ProductViewModel = viewModel()
    val cartState by cartViewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(tabNavController) {
        tabNavController.currentBackStackEntryFlow.collect { entry ->
            val route = entry.destination.route ?: return@collect
            if (route.startsWith("product_detail")) {
                onTabRouteChanged(DashboardTab.Home.route)
            } else {
                onTabRouteChanged(route)
            }
        }
    }

    val currentBackStackEntry by tabNavController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    
    val showBottomBar = currentRoute?.startsWith("product_detail") != true

    val topTitle = when {
        currentRoute == DashboardTab.Home.route -> "HamroStore"
        currentRoute == DashboardTab.Profile.route -> "Profile"
        currentRoute == DashboardTab.Cart.route -> "Cart"
        currentRoute?.startsWith("product_detail") == true -> "Product Details"
        else -> "HamroStore"
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    if (currentRoute?.startsWith("product_detail") == true) {
                        Text(topTitle, color = Color.Black)
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.hamrostorelogo),
                            contentDescription = "HamroStore Logo",
                            modifier = Modifier
                        )
                    }
                },
                navigationIcon = {
                    if (currentRoute?.startsWith("product_detail") == true) {
                        IconButton(onClick = { tabNavController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.Black
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Logout",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFAD9696)
                )
            )
        },
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = Color(0xFFAD9696),
                    tonalElevation = 0.dp
                ) {
                    dashboardTabs.forEach { tab ->
                        val selected = currentRoute == tab.route
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
                            label = { Text(tab.label) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Black,
                                selectedTextColor = Color.Black,
                                indicatorColor = Color(0xFFF2F2F2)
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = tabNavController,
            startDestination = DashboardTab.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(DashboardTab.Home.route) {
                HomeScreen(
                    onProductClick = { product ->
                        tabNavController.navigate(AppRoute.ProductDetail.createRoute(product.id))
                    },
                    onAddToCart = { productDto ->
                        cartViewModel.addToCart(productDto)
                        scope.launch {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(
                                "${productDto.productName} added to cart 🛒"
                            )
                        }
                    },
                    productViewModel = productViewModel
                )
            }

            composable(
                route = AppRoute.ProductDetail.route,
                arguments = listOf(navArgument("productId") { type = NavType.LongType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getLong("productId") ?: 0L
                ProductDetailScreen(
                    productId = productId,
                    viewModel = productViewModel,
                    onBack = { tabNavController.popBackStack() },
                    onAddToCart = { product ->
                        cartViewModel.addToCart(product)
                        scope.launch {
                            snackbarHostState.showSnackbar("${product.productName} added to cart")
                        }
                    }
                )
            }

            composable(DashboardTab.Profile.route) {
                ProfileScreen()
            }

            composable(DashboardTab.Cart.route) {
                CartScreen(
                    uiState = cartState,
                    onPlus = { cartViewModel.addToCart(it.product) },
                    onMinus = { cartViewModel.removeOne(it.product.id) },
                    onDelete = { cartViewModel.deleteItem(it.product.id) },
                    onClear = { cartViewModel.clearCart() },
                    onCheckout = onCheckoutClick // Trigger step 4 navigation
                )
            }
        }
    }
}

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(
        uiState = DashboardUiState(),
        onLogout = {},
        onTabRouteChanged = {},
        onCheckoutClick = {}
    )
}
