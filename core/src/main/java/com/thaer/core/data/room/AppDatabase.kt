package com.thaer.core.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.thaer.core.data.models.local.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java, "message_database"
                        )
                            .addCallback(roomCallBack)
                            .build()

                    }
                }
            }
            return INSTANCE as AppDatabase
        }

        private val roomCallBack: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let {
                    val userDB = it.userDao()
                    GlobalScope.launch {
                        userDB.apply {
                            insertUsers(
                                listOf(
                                    User(1, "John", "Doe", 30), User(2, "Jake", "Doe", 26)
                                )
                            )
                        }
                    }
                }
            }
        }

    }

}
