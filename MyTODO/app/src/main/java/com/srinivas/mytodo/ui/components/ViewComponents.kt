package com.srinivas.mytodo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NormalTextComponent(title: String, textSize: TextUnit = 18.sp,fontWeight: FontWeight  = FontWeight.Normal) {
    Text(
        modifier = Modifier
            .padding(16.dp),
        text = title,
        fontSize = textSize,
        fontWeight = fontWeight,
        color = Color.Black
    )
}

@Composable
fun NormalTextComponentWithClick(title: String, onTextClick: () -> Unit) {
    Text(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                onTextClick.invoke()
            },
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Black
    )
}

@Composable
fun ShowCircularLoader() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp),
            color = Color.Blue,
            strokeWidth = 3.dp
        )
    }
}

