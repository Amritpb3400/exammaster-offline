package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.QuestionDao
import com.example.data.local.dao.StudyNoteDao
import com.example.data.local.dao.TestAttemptDao
import com.example.data.local.dao.TimetableDao
import com.example.data.local.dao.UserDao
import com.example.data.local.entity.QuestionEntity
import com.example.data.local.entity.StudyNoteEntity
import com.example.data.local.entity.TestAttemptEntity
import com.example.data.local.entity.TimetableSlotEntity
import com.example.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        QuestionEntity::class,
        TestAttemptEntity::class,
        TimetableSlotEntity::class,
        StudyNoteEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun questionDao(): QuestionDao
    abstract fun testAttemptDao(): TestAttemptDao
    abstract fun timetableDao(): TimetableDao
    abstract fun studyNoteDao(): StudyNoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "examprep_database.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
