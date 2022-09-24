package com.example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.sql.RowId

@Dao
interface StudentDao {

    @Query("SELECT * FROM student")
    fun getListStudent(): Flow<List<Student>>

    @Insert
    suspend fun insertStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Query("SELECT * FROM student WHERE id = :studentId")
    suspend fun getStudentDetail(studentId: Int): Student
}