package com.example.remedialucp2_249.view.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier // Pastikan ini diimport
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.remedialucp2_249.view.DetailBukuScreen
import com.example.remedialucp2_249.view.DetailPengarangScreen
import com.example.remedialucp2_249.view.EditPengarangScreen
import com.example.remedialucp2_249.view.EntryBukuScreen
import com.example.remedialucp2_249.view.EntryPengarangScreen
import com.example.remedialucp2_249.view.LibraryHomeScreen
import com.example.remedialucp2_249.view.ListBukuScreen
import com.example.remedialucp2_249.view.ListPengarangScreen
import com.example.ucp2.view.EditBukuScreen

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier // 1. Tambahkan parameter modifier di sini
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier // 2. Terapkan modifier pada NavHost agar padding dari Scaffold terbaca
    ) {
        composable(DestinasiHome.route) {
            LibraryHomeScreen(
                onAddPengarangClick = { navController.navigate(DestinasiEntryPengarang.route) },
                onAddBukuClick = { navController.navigate(DestinasiEntryBuku.route) },
                onListPengarangClick = { navController.navigate(DestinasiListPengarang.route) },
                onListBukuClick = { navController.navigate(DestinasiListBuku.route) }
            )
        }
        composable(DestinasiEntryPengarang.route) {
            EntryPengarangScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(DestinasiEntryBuku.route) {
            EntryBukuScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(DestinasiListPengarang.route) {
            ListPengarangScreen(
                navigateBack = { navController.popBackStack() },
                onItemClick = { navController.navigate("${DestinasiDetailPengarang.route}/$it") }
            )
        }
        composable(DestinasiListBuku.route) {
            ListBukuScreen(
                navigateBack = { navController.popBackStack() },
                onItemClick = { navController.navigate("${DestinasiDetailBuku.route}/$it") }
            )
        }
        composable(
            route = DestinasiDetailBuku.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailBuku.bukuIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailBukuScreen(
                navigateBack = { navController.popBackStack() },
                onEditClick = { navController.navigate("${DestinasiEditBuku.route}/$it") }
            )
        }
        composable(
            route = DestinasiEditBuku.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditBuku.bukuIdArg) {
                type = NavType.IntType
            })
        ) {
            EditBukuScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = DestinasiDetailPengarang.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailPengarang.pengarangIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailPengarangScreen(
                navigateBack = { navController.popBackStack() },
                onEditClick = { navController.navigate("${DestinasiEditPengarang.route}/$it") }
            )
        }
        composable(
            route = DestinasiEditPengarang.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditPengarang.pengarangIdArg) {
                type = NavType.IntType
            })
        ) {
            EditPengarangScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}