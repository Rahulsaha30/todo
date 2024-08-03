package eu.tutorials.wishlist

sealed class Screen(val route:String) {
    object  Homescreen:Screen("home_screen")
    object  Addscreen:Screen("add_screen")
}