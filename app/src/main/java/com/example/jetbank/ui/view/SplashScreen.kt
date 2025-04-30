package com.example.jetbank.ui.view

import android.annotation.SuppressLint
import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jetbank.R
import com.example.jetbank.ui.navigation.AppScreen
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(navController: NavController){
    val isPlaying by remember{ mutableStateOf(true) }
    val speed by remember { mutableStateOf(1f) }

    val composition by rememberLottieComposition(
        spec= LottieCompositionSpec.RawRes(R.raw.animation)
    )

    val progress by animateLottieCompositionAsState(
        composition=composition,
        isPlaying = isPlaying,
        iterations = LottieConstants.IterateForever,
        speed=speed,
        restartOnPlay = false
    )

    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(AppScreen.HOME_SCREEN.name)
    }

    Scaffold{ paddingValues->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ){
            LottieAnimation(
                composition=composition,
                progress={progress},
                modifier = Modifier.fillMaxSize().padding(50.dp),
                alignment = Alignment.Center
            )
        }
    }
}