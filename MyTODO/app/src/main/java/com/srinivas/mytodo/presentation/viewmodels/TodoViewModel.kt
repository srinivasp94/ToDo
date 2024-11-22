package com.srinivas.mytodo.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srinivas.mytodo.data.model.ToDo
import com.srinivas.mytodo.domain.repository.TodoRepository
import com.srinivas.mytodo.domain.state.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {

    private val _todoList = MutableStateFlow<ResponseState<List<ToDo>>>(ResponseState.Loading())
    val todoList: StateFlow<ResponseState<List<ToDo>>> = _todoList

    init {
        fetchTodos()
    }


    private fun fetchTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllTodos().collectLatest { data ->
                _todoList.value = data
            }
        }
    }

    fun addTodo(text: String) {
        viewModelScope.launch {
            repository.insertTodo(ToDo(todoText = text))
            fetchTodos()
        }
    }

    fun deleteTodo(todo: ToDo) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
            fetchTodos()
        }
    }
}
