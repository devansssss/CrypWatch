package com.example.crypwatch

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.crypwatch.api.CrypWatchAPI
import com.example.crypwatch.screens.CoinDetailScreen
import com.example.crypwatch.screens.CoinListScreen
import com.example.crypwatch.screens.HomeScreen
import com.example.crypwatch.ui.theme.CrypWatchTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrypWatchTheme {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x33,0x33,0x33))){
                    App()
                }
            }
        }
    }
}


@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "coins"){
        composable(route = "home"){
            HomeScreen()
        }
        composable(route = "coins"){
            CoinListScreen{
                navController.navigate("detail/${it}")
            }
        }
        composable(route="detail/{coins}", arguments = listOf(
            navArgument("coins"){
                type = NavType.StringType
            }
        )
        ){
            CoinDetailScreen()
        }
    }
}
