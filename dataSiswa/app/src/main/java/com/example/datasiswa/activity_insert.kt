package com.example.datasiswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.data.Student
import com.example.data.StudentDatabase
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch

class activity_insert : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        val inputName:EditText = findViewById(R.id.input_name)
        val inputAge:EditText = findViewById(R.id.input_age)
        val inputBirthday:EditText = findViewById(R.id.input_ttl)
        val inputAddress:EditText = findViewById(R.id.input_adrees)
        val phoneNumber: EditText = findViewById(R.id.input_phone_number)
        val buttonSave: Button = findViewById(R.id.button_save)
        val database = StudentDatabase.getInstance(this)

        buttonSave.setOnClickListener {
            val name = inputName.text.toString()
            val age = inputAge.text.toString().toInt()
            val birthday = inputBirthday.text.toString()
            val address = inputAddress.text.toString()
            val phoneNumber = phoneNumber.text.toString()

            //kalau ada yang tidak ingin di isi (contoh kasus disini id nya tidak ingin diisi)
            val student = Student(
             name = name,
             age = age,
             ttl = birthday,
             adrees = address,
             phoneNumber = phoneNumber
            )

            lifecycleScope.launch {
                database.studentDao().insertStudent(student)
                Toast.makeText(this@activity_insert, "Berhasil Menambah data", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        val back = findViewById<MaterialToolbar>(R.id.toolbar)
        back.setNavigationOnClickListener {
            finish()
        }
    }
}