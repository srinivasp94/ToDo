package com.srinivas.mytodo.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.srinivas.mytodo.R
import com.srinivas.mytodo.ui.components.AppToolBar
import com.srinivas.mytodo.ui.components.NormalTextComponent
import com.srinivas.mytodo.ui.navigation.TodoApp
import com.srinivas.mytodo.ui.theme.MyTODOTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTODOTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { AppToolBar() },
                    content = { paddingValues ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(paddingValues)
                        ) {
                            TodoApp()
                        }
                    }
                )
            }
        }
    }
}


