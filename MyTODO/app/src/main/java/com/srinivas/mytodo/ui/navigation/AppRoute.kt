package com.srinivas.mytodo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.srinivas.mytodo.presentation.screens.DetailsScreen
import com.srinivas.mytodo.presentation.screens.MainScreen
import com.srinivas.mytodo.presentation.viewmodels.TodoViewModel


const val MAIN = "main"
const val DETAILS = "details"
@Composable
fun TodoApp() {
    val navController = rememberNavController()
    val viewModel: TodoViewModel = hiltViewModel()

    NavHost(navController, startDestination = "main") {
        composable(MAIN) { MainScreen(navController, viewModel) }
        composable(DETAILS) { DetailsScreen(navController, viewModel) }
    }
}