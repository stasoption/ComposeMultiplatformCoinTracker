
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
//      4-menu,
//      5-pagination (https://github.com/Ahmad-Hamwi/lazy-pagination-compose),
//      6-Search panel (done)
//      7-Freezes
//      8-filters
//      9-shimmer effect (done)
// Implement Dark theme (Theme.kt)
// Add login screen
// Profile screen
// Add lints, tests
// support desktop version
// Shimmer background effect

// https://api.coinpaprika.com/v1/coins

enum class Screen(val route: String) {
    Main(route = "main_screen"),
    Detail(route = "detail_screen")
}

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
                CoinListScreen(navController)
            }

            /*
            * Coin details screen. It represents the details of a specific coin.
            * */
            composable(
                route = "${Screen.Detail.route}/{${Constants.PARAM_COIN_ID}}",
                arguments = listOf(navArgument(Constants.PARAM_COIN_ID) { type = NavType.StringType })
            ) { backStackEntry ->
                val coinId = backStackEntry.arguments?.getString(Constants.PARAM_COIN_ID)
                CoinDetailsScreen(requireNotNull(coinId), navController)
            }
        }
    }
}
