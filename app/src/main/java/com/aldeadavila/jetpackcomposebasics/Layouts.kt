package com.aldeadavila.jetpackcomposebasics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout



@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LayoutPreview(){
    //NewFrameLayout()
    //NewLinearLayout()
    NewConstraintLayout()
}


@Composable
fun NewConstraintLayout() {
   ConstraintLayout(Modifier.fillMaxSize()) {
       val (boxRed, boxBlue, boxGreen, boxMagenta, boxYellow) = createRefs()
       val topGuide = createGuidelineFromTop(0.2f) //20%
       val startGuide = createGuidelineFromStart(0.2f) //20%
       Box(modifier = Modifier.size(100.dp).background(Color.Red).constrainAs(boxRed){
           top.linkTo(parent.top)
           start.linkTo(parent.start)
           end.linkTo(boxBlue.start)
       })
       Box(modifier = Modifier.size(100.dp).background(Color.Blue).constrainAs(boxBlue){
           top.linkTo(parent.top)
           start.linkTo(parent.start)
           end.linkTo(boxBlue.start)
       })
       Box(modifier = Modifier.size(100.dp).background(Color.Green).constrainAs(boxGreen){
           top.linkTo(topGuide)
           start.linkTo(startGuide)
       })
       Box(modifier = Modifier.size(100.dp).background(Color.Magenta).constrainAs(boxMagenta){
           top.linkTo(boxYellow.bottom)
           bottom.linkTo(parent.bottom)
           end.linkTo(parent.end, margin = 16.dp)
       })
       Box(modifier = Modifier.size(100.dp).background(Color.Yellow).constrainAs(boxYellow){
           top.linkTo(parent.top)
           bottom.linkTo(parent.bottom)
           start.linkTo(parent.start)
           end.linkTo(parent.end)
       })
   }

}



@Composable
fun NewLinearLayout() {
    /*Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Center) {
        Text(text = "Cursos Android ANT")
        Text(text = "Frogames")

        Row() {
            TextANT()
            TextFrogames()
        }
    }*/
    Row (verticalAlignment = Alignment.CenterVertically) {
        TextFrogames()
        Column {
            TextANT()
            Text(text = "Frogame")
            Text(text = "Frogame")
            Row {
                TextANT()
                TextANT()
            }
        }
    }
}

@Composable
fun TextANT() {
    Text(text = "Cursos Android ANT", modifier = Modifier.background(Color.Cyan))
}

@Composable
fun TextFrogames() {
    Text(text = "Frogames", modifier= Modifier.background(Color.Blue))
}

@Composable
fun NewFrameLayout() {
    Box(contentAlignment = Alignment.CenterEnd) {
        Text(text = "Cursos Android ANT")
        Text(text = "Frogames")
    }
}
