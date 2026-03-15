# HamroStore — Multivendor App Simulation 🛍️

A **Jetpack Compose** Android application simulating a multivendor e-commerce platform with role-based access control (Admin, Vendor, Delivery Agent, Customer). Built as a portfolio project demonstrating **MVVM architecture**, **Jetpack Navigation**, and **clean Kotlin code**.

---

## 📸 Screenshots

> _Add screenshots here after recording demo_

---

## 🏗️ Architecture

```
MVVM (Model-View-ViewModel)
├── UI Layer      → Composable Screens + Route composables
├── ViewModel     → Business logic + StateFlow state
└── Data Layer    → Repositories (local simulation)
```

---

## ⚙️ Tech Stack

| Layer | Library |
|---|---|
| Language | Kotlin |
| UI Framework | Jetpack Compose |
| State Management | `StateFlow` + `collectAsStateWithLifecycle` |
| Navigation | Jetpack Navigation Compose |
| Architecture | MVVM |
| Testing | JUnit 4, `kotlinx-coroutines-test` |
| Build | Gradle (KTS) |

---

## 🔐 Role-Based Login

The login screen includes a dropdown to select a user role. Each role auto-fills its credentials:

| Role | Email | Password |
|---|---|---|
| Admin | admin@hamrostore.com | admin123 |
| Customer | customer@hamrostore.com | customer123 |
| Vendor | vendor@hamrostore.com | vendor123 |
| Delivery Agent | delivery@hamrostore.com | delivery123 |

---

## 📦 Features by Role

### 👤 Customer
- Browse product grid with search and category filter
- Product detail screen
- Add to cart with badge counter
- Checkout and order success flow
- Profile screen

### 👨‍💼 Admin
- Dashboard stats (users, orders, revenue, pending approvals)
- Approve or reject vendor applications
- View recent orders with status

### 🏪 Vendor
- Store stats (products, pending orders, total sales)
- Product list with remove action
- Order fulfillment management

### 🚴 Delivery Agent
- Active delivery list with addresses and product info
- Mark as **Picked Up** → **Delivered** state transitions (animated)
- Daily completion counter and earnings tracker

---

## 🗺️ Navigation Flow

```
Splash → Login
           ├── Admin      → AdminDashboard
           ├── Vendor     → VendorDashboard
           ├── Delivery Agent → DeliveryDashboard
           └── Customer   → CustomerDashboard
                               ├── Home (Products)
                               ├── Cart → Checkout → OrderSuccess
                               └── Profile
```

---

## 🧪 Testing

Unit tests cover each ViewModel using JUnit 4 and `kotlinx-coroutines-test`:

```
test/
└── LoginViewModelTest.kt
└── AdminViewModelTest.kt
└── VendorViewModelTest.kt
└── DeliveryViewModelTest.kt
```

Run tests:
```bash
./gradlew test
```

---

## 📂 Project Structure

```
app/src/main/java/com/example/loginsimulationapp/
├── data/
│   └── repository/       # AuthRepository, ProductRepository
├── ui/
│   ├── admin/            # AdminUiState, AdminViewModel, AdminScreen, AdminRoute
│   ├── vendor/           # VendorUiState, VendorViewModel, VendorScreen, VendorRoute
│   ├── delivery/         # DeliveryUiState, DeliveryViewModel, DeliveryScreen, DeliveryRoute
│   ├── dashboard/        # Customer dashboard with bottom tabs
│   │   └── tabs/         # ProfileScreen, ProfileViewModel, ProfileUiState
│   ├── home/             # HomeScreen (product grid)
│   ├── product/          # ProductDetailScreen, ProductItem
│   ├── cart/             # CartScreen, CartViewModel
│   ├── checkout/         # CheckoutScreen, OrderSuccessScreen
│   ├── login/            # LoginScreen, LoginViewModel, LoginState, LoginRoute
│   ├── signup/           # SignUpRoute
│   ├── navigation/       # AppRoute, AppNavigation, AppNavGraph
│   ├── splash/           # SplashScreen
│   └── theme/            # Color.kt, Theme.kt
```

---

## 🚀 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/YOUR_USERNAME/LoginSimulationApp.git
   ```
2. Open in **Android Studio Hedgehog** or later.
3. Run on a device or emulator (minSdk 24, targetSdk 36).

---

## 📝 Notes

- This is a **simulation** — no real backend or API. All data is local/dummy.
- Credentials are pre-filled based on selected role for demo convenience.
- The app uses a **Black & White** design theme with role-colored status indicators.

---

## 🤝 Contributing

Pull requests are welcome. For major changes, please open an issue first.

---

## 📄 License

[MIT](LICENSE)
