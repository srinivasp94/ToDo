package com.srinivas.mytodo.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.srinivas.mytodo.R
import com.srinivas.mytodo.presentation.viewmodels.TodoViewModel
import com.srinivas.mytodo.ui.components.NormalTextComponent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController: NavController, viewModel: TodoViewModel) {

    var isAlertShowing by remember { mutableStateOf(false) }
    val todoText = remember { mutableStateOf("") }
    //val isAlertShowing = viewModel.showError.value

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = todoText.value,
                onValueChange = { todoText.value = it },
                placeholder = { Text("Enter TODO item") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(36.dp))
            Button(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Yellow,
                    contentColor = Color.Black
                ),
                onClick = {
                    if (todoText.value.isEmpty() ||
                        todoText.value.equals("Error", ignoreCase = true)
                    ) {
                        isAlertShowing = true
                    } else {
                        viewModel.addTodo(todoText.value)
                        navController.popBackStack()
                    }
                }

            ) {
                NormalTextComponent(stringResource(R.string.add_todo), textSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }

            if (isAlertShowing) {
                AlertDialog(
                    onDismissRequest = {},
                    title = {
                        NormalTextComponent(
                            stringResource(R.string.error_alert_title),
                            textSize = 24.sp
                        )
                    },
                    text = { NormalTextComponent(stringResource(R.string.error_alert_message)) },
                    confirmButton = {
                        TextButton(onClick = {
                            isAlertShowing = false
                            navController.popBackStack()
                        }) {
                            NormalTextComponent(
                                stringResource(R.string.confirm_text),
                                textSize = 20.sp
                            )
                        }
                    }
                )
            }
        }
    }
}
