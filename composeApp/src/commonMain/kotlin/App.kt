
import androidx.compose.runtime.*
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
// Add Retrofit analog for compose
// Implement Dark theme (Theme.kt)
// Add Splash screen
// Add login screen
// Profile screen
// Main Screen: State, Refresh the page, scroll to top button, menu
// Add lints, tests

// support desktop version

enum class Screen(val route: String) {
    Main(route = "main_screen"),
    Detail(route = "detail_screen")
}

@Composable
fun App() {
    CoinTrackerAppTheme {
        val navController = rememberNavController()
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
                CoinDetailsScreen(requireNotNull(coinId))
            }
        }
    }
}
