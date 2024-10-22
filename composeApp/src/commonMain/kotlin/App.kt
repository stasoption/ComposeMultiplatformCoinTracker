
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import domain.Constants
import ui.screens.details.CoinDetailsScreen
import ui.screens.favorites.FavoritesScreen
import ui.theme.CoinTrackerAppTheme
import ui.screens.list.CoinListScreen
import ui.screens.more.MoreScreen
import ui.theme.ColorAccent
import ui.theme.TextPrimary

// TODO List:
// Main Screen:
//      1-Store Screen State, (done)
//      2-Refresh the page (swipe or so),
//      3-scroll to top button, (done)
//      4-menu, (done)
//      5-pagination (https://github.com/Ahmad-Hamwi/lazy-pagination-compose),
//      6-Search panel (done)
//      7-Freezes
//      8-filters
//      9-shimmer effect (done)
//      10-top tabs (coins, categories(coin,token))
//  Details Screen:
//      1-Fake chart
//      2-Shimmer placeholder (done)
// Improve navigation (Graph flow + tabs + callbacks)
// Implement Dark theme (Theme.kt)
// Add login screen
// Add About screen (tab)
// Profile screen
// Add lints, tests
// Support desktop version

// API: https://api.coinpaprika.com/#tag/Coins


private val topLevelRoutes = listOf(
    TopLevelRoute(Tab.Main, Icons.Filled.Home),
    TopLevelRoute(Tab.Favorites, Icons.Filled.Favorite),
    TopLevelRoute(Tab.More, Icons.Filled.MoreVert),
)

@Composable
fun App() {
    CoinTrackerAppTheme {
        val navController: NavHostController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    topLevelRoutes.forEach { topLevelRoute ->
                        BottomNavigationItem(
                            icon = { Icon(topLevelRoute.icon, contentDescription = topLevelRoute.route.name) },
                            selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route.id, null) } == true,
                            selectedContentColor = ColorAccent,
                            unselectedContentColor = TextPrimary,
                            onClick = {
                                navController.navigate(topLevelRoute.route.id) {
                                    popUpTo(navController.graph.findStartDestination().route.orEmpty()) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Tab.Main.id,
                modifier = Modifier.padding(innerPadding)
            ) {
                /*
                * Tab Main
                * */
                navigation(startDestination = Screen.List.id, route = Tab.Main.id) {
                    composable(route = Screen.List.id) {
                        CoinListScreen(onNavigateNext = { coinId ->
                            navController.navigateToDetailsScreen(coinId)
                        })
                    }
                    composable(
                        route = "${Screen.Detail.id}/{${Constants.PARAM_COIN_ID}}",
                        arguments = listOf(navArgument(Constants.PARAM_COIN_ID) { type = NavType.StringType })
                    ) { backStackEntry ->
                        val coinId = backStackEntry.arguments?.getString(Constants.PARAM_COIN_ID)
                        CoinDetailsScreen(
                            coinId = requireNotNull(coinId),
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                }

                /*
                * Tab Favorites
                * */
                navigation(startDestination = Screen.Favorites.id, route = Tab.Favorites.id) {
                    composable(route = Screen.Favorites.id) {
                        FavoritesScreen()
                    }
                }

                /*
                * Tab More
                * */
                navigation(startDestination = Screen.More.id, route = Tab.More.id) {
                    composable(route = Screen.More.id) {
                        MoreScreen()
                    }
                }
            }

        }
    }
}

fun NavHostController.navigateToDetailsScreen(coinId: String) = this.navigate("${Screen.Detail.id}/${coinId}")

enum class Tab(val id: String) {
    Main(id = "main_tab"),
    Favorites(id = "favorites_tab"),
    More(id = "more_tab")
}

enum class Screen(val id: String) {
    List(id = "list_screen"),
    Detail(id = "detail_screen"),
    Favorites(id = "favorites_screen"),
    More(id = "more_screen"),
}

data class TopLevelRoute<T : Any>(val route: T, val icon: ImageVector)