package com.example.fiverrmorbedpractice.presentaion.change_image_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fiverrmorbedpractice.R
import com.example.fiverrmorbedpractice.presentaion.change_image_screen.components.MyImageView

@Composable
fun ScreenChangeImage(vm:ChangingImageViewModel) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val listIcon = listOf(Icons.Default.Check , Icons.Default.Refresh , Icons.Default.List ,Icons.Default.CheckCircle)
        val listImage = listOf<Painter>(painterResource(id = R.drawable.image1) ,
            painterResource(id = R.drawable.image2) ,
            painterResource(id = R.drawable.image3),
            painterResource(id = R.drawable.image4)
        )
        val painter = listImage.get(vm.slider.value.toInt())

        MyImageView(painter = painter , maxPos = listImage.size , currentPos = vm.slider.value.toInt().plus(1))


        val image = listIcon[vm.test.value.toInt().coerceIn(0..3)]
        Image(image, contentDescription = "" , modifier = Modifier.size(80.dp))

        Button(onClick = { vm.startTimer() }) {
            Text(text = "countdown")
        }
        Button(onClick = { vm.stopTimer() }) {
            Text(text = "stop Coundown")
        }
        Button(onClick = {
            println("naser curent time" + System.currentTimeMillis() ) }) {
            Text(text = "curent Time")
        }
    }
}