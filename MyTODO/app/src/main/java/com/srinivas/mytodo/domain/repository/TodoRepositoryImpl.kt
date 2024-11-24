package com.srinivas.mytodo.domain

import android.util.Log
import com.srinivas.mytodo.ui.components.AppConstants
import com.srinivas.mytodo.data.model.ToDo
import com.srinivas.mytodo.data.room.dao.TodoDao
import com.srinivas.mytodo.domain.repository.TodoRepository
import com.srinivas.mytodo.domain.state.ResponseState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(private val dao: TodoDao) : TodoRepository {
    override suspend fun insertTodo(todo: ToDo) {
        dao.insertTodo(todo = todo)
    }

    override fun getAllTodos(): Flow<ResponseState<List<ToDo>>> {
        return flow {
            delay(2000)
            emit(ResponseState.Loading())
            Log.d("##Emit","Loading")
            val list = dao.getAllTodos()
            if (list.isEmpty()) {
                Log.d("##Emit", "Empty")
                emit(ResponseState.Empty(AppConstants.EMPTY_LIST_TEXT))
            }
            else {
                Log.d("##Emit","list ")
                emit(ResponseState.Success(list))
            }
        }.catch {
            Log.d("##Emit","Error")
            emit(ResponseState.Error(AppConstants.ERROR_TEXT))
        }

    }

    override suspend fun deleteTodo(todo: ToDo) {
       dao.deleteTodo(todo = todo)
    }

}