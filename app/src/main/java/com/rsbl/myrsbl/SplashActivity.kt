package com.rsbl.myrsbl

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.rsbl.myrsbl.ui.theme.MyRSBLTheme
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyRSBLTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenSplash()
                    LaunchedEffect(key1 = true){
                        delay(2500L)
                        val navigate = Intent(this@SplashActivity,MainActivity::class.java)
                        startActivity(navigate)
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish()
                    }
                }
            }
        }
    }
}


@Composable
fun ScreenSplash(){
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

    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize().background(color = MaterialTheme.colors.background)
    )
    {
        Image(
            painter = painterResource(id = R.drawable.logo_rsbl_1),
            contentDescription = "Logo",
            modifier = Modifier.scale((scale.value))
        )
    }
}