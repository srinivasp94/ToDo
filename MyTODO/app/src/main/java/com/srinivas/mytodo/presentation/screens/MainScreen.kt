package com.srinivas.mytodo.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.srinivas.mytodo.R
import com.srinivas.mytodo.data.model.ToDo
import com.srinivas.mytodo.domain.state.ResponseState
import com.srinivas.mytodo.presentation.viewmodels.TodoViewModel
import com.srinivas.mytodo.ui.components.NormalTextComponent
import com.srinivas.mytodo.ui.components.NormalTextComponentWithClick
import com.srinivas.mytodo.ui.components.ShowCircularLoader
import com.srinivas.mytodo.ui.navigation.DETAILS
import com.srinivas.mytodo.ui.theme.LightYellow
import com.srinivas.mytodo.ui.theme.Pink80

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController, viewModel: TodoViewModel) {
    val todoListResponse by viewModel.todoList.collectAsState()
    val searchQuery = remember { mutableStateOf("") }

    Scaffold(
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                MainContent(todoListResponse)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size(80.dp),
                onClick = { navController.navigate(DETAILS) },
                shape = RoundedCornerShape(40.dp),
                containerColor = Color.Yellow,
                contentColor = Color.Black
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = stringResource(R.string.fab_description),
                    modifier = Modifier.size(30.dp),
                )
            }
        }
    )
}

@Composable
fun MainContent(todoListResponse: ResponseState<List<ToDo>>) {

    Surface(modifier = Modifier.background(Color.White)) {
        when (todoListResponse) {
            is ResponseState.Loading -> {
                ShowCircularLoader()
            }

            is ResponseState.Empty -> {
                val emptyMessage = (todoListResponse as ResponseState.Empty).emptyText
                NormalTextComponent(emptyMessage)
            }

            is ResponseState.Success -> {
                val todoList =
                    (todoListResponse as ResponseState.Success<List<ToDo>>).data as List<ToDo>
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(items = todoList, itemContent = { todo: ToDo ->
                        todo.let {
                            ShowTodoItem(it)
                        }

                    })
                }
            }

            is ResponseState.Error ->
                NormalTextComponent(stringResource(R.string.error_while_fetching_todos))
        }
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
