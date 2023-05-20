package com.example.esemkalibrary.core.navigation

const val MAIN_GRAPH_ROUTE = "MAIN"
const val AUTH_GRAPH_ROUTE = "AUTH"
const val HOME_GRAPH_ROUTE = "HOME"
const val PROFILE_GRAPH_ROUTE = "PROFILE"
const val FORUM_GRAPH_ROUTE = "FORUM"
const val CART_GRAPH_ROUTE = "CART"


sealed class Screen(val route: String) {
    object Login: Screen("login")
    object SignUp: Screen("sign_up")
    object Main: Screen("main")
    object Home: Screen("home")
    object BookDetail: Screen("book_detail") {
        fun passBookId(bookId: String): String {
            return "book_detail/$bookId"
        }
    }
    object Cart: Screen("cart")
    object Profile: Screen("profile")
    object BorrowingDetail: Screen("borrowing_detail") {
        fun passBorrowingId(borrowingId: String): String {
            return "borrowing_detail/$borrowingId"
        }
    }
    object Forum: Screen("forum")
    object AddThread: Screen("add_thread")
    object ThreadDetail: Screen("thread_detail")
}
