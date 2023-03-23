package com.example.custom.TopBar

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CTopBar(title:String,textIcon:String = "Envoyez"){
    TopAppBar(
        elevation = 4.dp,
        title = {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text =title)
//                Spacer(modifier = Modifier.width(82.dp))
            }

        },
        backgroundColor =  Color.DarkGray,
        navigationIcon = {
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        }, actions = {
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(Icons.Filled.Send, null)
            }
        })
}
@Composable
fun CTopBarBody(){

}
@Composable
@Preview
fun CTopBarPreview(){
    CTopBar("A propos")
}
