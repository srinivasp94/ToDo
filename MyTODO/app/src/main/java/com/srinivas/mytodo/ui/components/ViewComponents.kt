package com.srinivas.mytodo.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srinivas.mytodo.R
import com.srinivas.mytodo.data.model.ToDo
import com.srinivas.mytodo.ui.theme.LightYellow

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

@Composable
fun AppToolBar(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.Yellow),
        contentAlignment = Alignment.BottomStart
    ) {
        NormalTextComponent(stringResource(R.string.app_name))
    }
}

@Composable
fun ShowTodoItem(todo: ToDo) {
    Column(modifier = Modifier.fillMaxWidth().padding(5.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.Start
    ) {
        NormalTextComponentWithClick(todo.todoText, onTextClick = {
            Log.d("##", todo.todoText)
        })
        Divider(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp), thickness = 1.dp, color = LightYellow)

    }
}

