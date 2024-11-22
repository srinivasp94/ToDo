package com.srinivas.mytodo.di

import android.content.Context
import androidx.room.Room
import com.srinivas.mytodo.data.room.ToDoRoomDB
import com.srinivas.mytodo.data.room.dao.TodoDao
import com.srinivas.mytodo.domain.TodoRepositoryImpl
import com.srinivas.mytodo.domain.repository.TodoRepository
import com.srinivas.mytodo.ui.components.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiAppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ToDoRoomDB {
        return Room.databaseBuilder(
            context,
            ToDoRoomDB::class.java,
            AppConstants.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(database: ToDoRoomDB): TodoDao {
        return database.todoDao()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(todoDao : TodoDao) :TodoRepository{
        return TodoRepositoryImpl(todoDao)
    }
}