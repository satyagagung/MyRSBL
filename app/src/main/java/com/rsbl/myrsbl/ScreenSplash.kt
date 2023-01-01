package com.rsbl.myrsbl

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun ScreenSplash(navController: NavController){
    val scale = remember{
        Animatable(initialValue = 0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec =  tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1500L)
        scale.animateTo(
            targetValue = 0.0f,
            animationSpec =  tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(0.1f).getInterpolation(it)
                }
            )
        )
        navController.navigate(MyRSBLScreen.Main.name){
            popUpTo(MyRSBLScreen.Splash.name){ inclusive = true}
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.logo_rsbl_1),
            contentDescription = "Logo",
            modifier = Modifier.scale((scale.value))
        )
    }
}