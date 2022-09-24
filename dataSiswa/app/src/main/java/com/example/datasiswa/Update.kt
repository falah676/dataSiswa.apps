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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class Update : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val inputName: EditText = findViewById(R.id.input_name)
        val inputAge: EditText = findViewById(R.id.input_age)
        val inputBirthday: EditText = findViewById(R.id.input_ttl)
        val inputAddress: EditText = findViewById(R.id.input_adrees)
        val inputPhoneNumber: EditText = findViewById(R.id.input_phone_number)
        val buttonUpdate: Button = findViewById(R.id.button_update)
        val database = StudentDatabase.getInstance(this)

        val getAge = intent.getIntExtra("age", 0) ?: 0

        inputName.setText(intent.getStringExtra("name"))
        inputAge.setText(getAge.toString())
        inputBirthday.setText(intent.getStringExtra("birthday"))
        inputAddress.setText(intent.getStringExtra("address"))
        inputPhoneNumber.setText(intent.getStringExtra("phoneNumber"))

        buttonUpdate.setOnClickListener {

            val name = inputName.text.toString()
            val age = inputAge.text.toString().toInt()
            val birthday = inputBirthday.text.toString()
            val address = inputAddress.text.toString()
            val phoneNumber = inputPhoneNumber.text.toString()
            val getid = intent.getIntExtra("id", 0)

            val student = Student(
                name = name,
                age = age,
                ttl = birthday,
                adrees = address,
                phoneNumber = phoneNumber,
                id = getid
            )

            MaterialAlertDialogBuilder(this@Update)
                .setTitle(resources.getString(R.string.update_title))
                .setMessage(resources.getString(R.string.update_text))
                .setPositiveButton(resources.getString(R.string.update)){ dialog, which ->
                    lifecycleScope.launch {
                        database.studentDao().updateStudent(student)
                        Toast.makeText(this@Update, "Berhasil Mengubah data", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                .setNegativeButton(resources.getString(R.string.cancel)){dialog, which ->
                    dialog.dismiss()
                }
                .show()

        }

        val back = findViewById<MaterialToolbar>(R.id.toolbar)
        back.setNavigationOnClickListener {
            finish()
        }
    }
}