package com.adhiambooyier.mytraveldiary.common.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adhiambooyier.mytraveldiary.common.DATABASE_NAME
import com.adhiambooyier.mytraveldiary.data.DiaryEntryDao
import com.adhiambooyier.mytraveldiary.data.DiaryEntryModel

@Database(entities = [DiaryEntryModel::class], version = 1)
abstract class DiaryDatabase : RoomDatabase() {
    abstract fun entryDao(): DiaryEntryDao

    companion object {
        @Volatile
        private var dbInstance: DiaryDatabase? = null

        private val LOCK = Any()

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DiaryDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

        operator fun invoke(context: Context) = dbInstance ?: synchronized(LOCK) {
            dbInstance ?: buildDatabase(context).also { dbInstance = it }
        }
    }
}
