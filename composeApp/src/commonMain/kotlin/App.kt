
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import domain.Constants
import ui.screens.details.CoinDetailsScreen
import ui.theme.CoinTrackerAppTheme
import ui.screens.list.CoinListScreen

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

@Composable
fun App() {
    CoinTrackerAppTheme {
        val navController: NavHostController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.Main.route
        ) {
            /*
            * The main screen of the app. It represents the list of coins.
            * */
            composable(
                route = Screen.Main.route
            ) {
                CoinListScreen(onNavigateNext = { coinId ->
                    navController.navigateToDetailsScreen(coinId)
                })
            }

            /*
            * Coin details screen. It represents the details of a specific coin.
            * */
            composable(
                route = "${Screen.Detail.route}/{${Constants.PARAM_COIN_ID}}",
                arguments = listOf(navArgument(Constants.PARAM_COIN_ID) { type = NavType.StringType })
            ) { backStackEntry ->
                val coinId = backStackEntry.arguments?.getString(Constants.PARAM_COIN_ID)
                CoinDetailsScreen(
                    coinId = requireNotNull(coinId),
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}

fun NavHostController.navigateToDetailsScreen(coinId: String) = this.navigate("${Screen.Detail.route}/${coinId}")

enum class Screen(val route: String) {
    Main(route = "main_screen"),
    Detail(route = "detail_screen")
}
