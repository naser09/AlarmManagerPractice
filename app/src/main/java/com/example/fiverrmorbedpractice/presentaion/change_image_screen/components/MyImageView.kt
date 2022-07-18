package com.example.fiverrmorbedpractice.presentaion.change_image_screen.components

import androidx.compose.animation.*
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MyImageView(painter: Painter , currentPos:Int , maxPos:Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.SpaceBetween) {

        AnimatedContent(targetState = painter) {
            this.transition.AnimatedContent(transitionSpec = {
                ContentTransform(
                    targetContentEnter = slideInHorizontally(tween(1000) , { it }),
                    initialContentExit = slideOutHorizontally(tween(1000) , { -it })
                )
            }) {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp, max = 270.dp)
                    .clip(RoundedCornerShape(15))
                    .padding(horizontal = 10.dp)) {
                    Image(painter = painter, contentDescription = "" ,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop)
                }
            }
        }


        Row() {
            for (pos in 1..maxPos){
                Canvas(modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .size(10.dp)){
                    this.drawCircle(color = if (currentPos == pos) Color.Green else Color.Gray)
                }
            }
        }
    }

}