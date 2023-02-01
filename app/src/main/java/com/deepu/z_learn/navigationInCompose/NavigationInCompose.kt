package com.deepu.z_learn.navigationInCompose

/**
 * Navigation with compose
 */

/*
   //dependency needed
    implementation("androidx.navigation:navigation-compose:2.5.3")


  NavController is the Central API, it is stateful, so will keep track of the backstack of composables

  val navController = rememberNavController()

  use the NavController and the state it provides via currentBackStackEntryAsState() to be used as the source of truth for updating composables outside of your screens

  Each NavController must be associated with a single NavHost composable.The NavHost links the NavController with a navigation graph that specifies the composable destinations that you should be able to navigate between

  each destination in navGraph is associated with a route

   1.Create NavHost
  NavHost(navController = navController, startDestination = "profile") {
    composable("profile") { Profile() }
    composable("friendslist") { FriendsList() }
    }

   2.Navigate
  navController.navigate("friendslist")

  additional options:
  Pop everything up to the "home" destination off the back stack before
   navigating to the "friendslist" destination
navController.navigate("friendslist") {
    popUpTo("home")
}

Pop everything up to and including the "home" destination off
 the back stack before navigating to the "friendslist" destination
navController.navigate("friendslist") {
    popUpTo("home") { inclusive = true }
}

 Navigate to the "search” destination only if we’re not already on
the "search" destination, avoiding multiple copies on the top of the
 back stack
navController.navigate("search") {
    launchSingleTop = true
}


Navigate calls triggered by other composable functions
@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "profile"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("profile") {
            ProfileScreen(
                onNavigateToFriends = { navController.navigate("friendsList") },

            )
        }
        composable("friendslist") { FriendsListScreen() }
    }
}

@Composable
fun ProfileScreen(
    onNavigateToFriends: () -> Unit,

) {

    Button(onClick = onNavigateToFriends) {
        Text(text = "See friends list")
    }
}

*/
