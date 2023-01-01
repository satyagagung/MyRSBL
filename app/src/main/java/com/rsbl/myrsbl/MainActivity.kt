package com.rsbl.myrsbl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rsbl.myrsbl.data.BottomNavItem
import com.rsbl.myrsbl.ui.theme.MyRSBLTheme

enum class MyRSBLScreen(){
    Splash,
    Main,
    Profile
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyRSBLTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.Blue,
        elevation = 5.dp
    ) {
        items.forEach{ item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = item.route == navController.currentDestination?.route,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.LightGray,
                onClick = { onItemClick(item) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally){
                        if(item.badgeCount > 0){
                            BadgedBox(
                                badge = { Badge { Text(item.badgeCount.toString()) } }
                            ) {
                                Icon(imageVector = item.icon,
                                contentDescription = item.name
                                )
                            }
                        }else{
                            Icon(imageVector = item.icon,
                                contentDescription = item.name
                            )
                        }
                        if(selected){
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun Navigation(){
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        topBar = {
            if(!currentRoute.equals(MyRSBLScreen.Splash.name)) {
                TopAppBar(
                    title = {
                        Row() {
                            Text(
                                text = stringResource(id = R.string.app_name) + " - " + currentRoute.toString(),
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            if(!currentRoute.equals(MyRSBLScreen.Splash.name)){
                BottomNavBar(
                    items = listOf(
                        BottomNavItem(
                            name = "Home",
                            route = MyRSBLScreen.Main.name,
                            icon = Icons.Default.Home
                        ),
                        BottomNavItem(
                            name = "Profile",
                            route = MyRSBLScreen.Profile.name,
                            icon = Icons.Default.Person,
                            badgeCount = 1
                        )
                    ),
                    navController = navController,
                    onItemClick = {
                        navController.navigate(it.route){
                            popUpTo(it.route){ inclusive = true}
                        }
                    })
            }
        }

    ){
        NavHost(
            navController = navController,
            startDestination = MyRSBLScreen.Splash.name
        ){
            composable(route = MyRSBLScreen.Splash.name){
                ScreenSplash(navController = navController)
            }
            composable(route = MyRSBLScreen.Main.name){
                ScreenMain()
            }
            composable(route = MyRSBLScreen.Profile.name){
                ScreenProfile()
            }
        }
    }
}

