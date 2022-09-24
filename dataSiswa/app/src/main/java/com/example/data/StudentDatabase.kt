package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Student::class])
abstract class StudentDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object{
        //buat penyimpanan ram(sementara)
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase {
            return INSTANCE?: synchronized(this) {
                val room = Room.databaseBuilder(context, StudentDatabase::class.java, "student.db")
                    .build()
                INSTANCE = room
                room
            }
                }
            }
        }

