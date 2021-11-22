package com.jordiortuno.cleanmvvm.framework.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DocumentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MajesticReaderDatabase : RoomDatabase() {

  companion object {

    private const val DATABASE_NAME = "reader.db"

    private var instance: MajesticReaderDatabase? = null

    private fun create(context: Context): MajesticReaderDatabase =
        Room.databaseBuilder(context, MajesticReaderDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()


    fun getInstance(context: Context): MajesticReaderDatabase =
        (instance ?: create(context)).also { instance = it }
  }

  abstract fun documentDao(): DocumentDao

}