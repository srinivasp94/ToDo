package com.srinivas.mytodo.domain.repository

import com.srinivas.mytodo.data.model.ToDo
import com.srinivas.mytodo.domain.state.ResponseState
import kotlinx.coroutines.flow.Flow


interface TodoRepository {
    suspend fun insertTodo(todo: ToDo)
    fun getAllTodos() : Flow<ResponseState<List<ToDo>>>
    suspend fun deleteTodo(todo: ToDo)
}