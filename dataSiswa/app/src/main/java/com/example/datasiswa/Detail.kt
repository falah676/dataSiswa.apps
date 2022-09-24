package com.example.datasiswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.data.StudentDatabase
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch

class Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val database = StudentDatabase.getInstance(this)
        val getId = intent.getIntExtra("id", 0)

        val textName = findViewById<TextView>(R.id.name_text)
        val textAge = findViewById<TextView>(R.id.age_text)
        val textAddress = findViewById<TextView>(R.id.address_text)
        val textBirthday = findViewById<TextView>(R.id.ttl_text)
        val textphoneNumber = findViewById<TextView>(R.id.phoneNumber_text)
        lifecycleScope.launch{
            val student = database.studentDao().getStudentDetail(studentId = getId)
            textName.text = "Nama: ${student.name}"
            textAge.text = "Umur: ${student.age} Tahun"
            textAddress.text = "Alamat: ${student.adrees}"
            textBirthday.text = "Tanggal Lahir: ${student.ttl}"
            textphoneNumber.text = "No Hp: ${student.phoneNumber}"
        }
        val back = findViewById<MaterialToolbar>(R.id.toolbar)
        back.setNavigationOnClickListener {
            finish()
        }
    }
}