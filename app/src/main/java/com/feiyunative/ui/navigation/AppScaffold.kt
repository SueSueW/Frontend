package com.feiyunative.ui.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.feiyunative.ui.screen.PlanScreen
import com.feiyunative.ui.screen.FocusTimelineScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun AppScaffold() {

    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Plan,
        BottomNavItem.Timeline
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        label = { Text(item.label) },
                        icon = {} // 🔴 暂时不用 icon，最稳
                    )
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Timeline.route
        ) {
            composable(BottomNavItem.Plan.route) {
                PlanScreen()
            }
            composable(BottomNavItem.Timeline.route) {
                FocusTimelineScreen()
            }
        }
    }
}
