package com.example.datasiswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.data.Student
import com.example.data.StudentDatabase
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView:RecyclerView = findViewById(R.id.rv)
        val buttonInsert: ExtendedFloatingActionButton = findViewById(R.id.button_add)

        val database = StudentDatabase.getInstance(this)

        lifecycleScope.launch{
            //GET DATA STUDENTS
            val studentList: Flow<List<Student>> = database.studentDao().getListStudent()
            studentList.collect{
                val adapter = StudentAdapter(it)
                recyclerView.adapter = adapter

                adapter.updateClickListener = {
                    lifecycleScope.launch {
                        val intent = Intent(this@MainActivity,Update::class.java)
                        intent.putExtra("id", it.id)
                        intent.putExtra("name", it.name)
                        intent.putExtra("age", it.age)
                        intent.putExtra("birthday", it.ttl)
                        intent.putExtra("address", it.adrees)
                        intent.putExtra("phoneNumber", it.phoneNumber)
                        startActivity(intent)
                    }
                }

                adapter.itemClickListener = {
                    //TODO PINDAH KE DETAIL KE ACTIVITY
                    val intent = Intent(this@MainActivity, Detail::class.java)
                    intent.putExtra("id", it.id)
                    //cara ribet
//
//                    intent.putExtra("name", it.name)
//                    intent.putExtra("age", it.age)
//                    intent.putExtra("address", it.adrees)
//                    intent.putExtra("ttl", it.ttl)
//                    intent.putExtra("phoneNumber", it.phoneNumber)

                    startActivity(intent)
                }


                adapter.deleteClickListener = {student ->
                    //TODO DELETE STUDENT

                    MaterialAlertDialogBuilder(this@MainActivity)
                        .setTitle(resources.getString(R.string.delete))
                        .setMessage(resources.getString(R.string.delete_text))
                        .setPositiveButton(resources.getString(R.string.yes)){ dialog, which ->
                            lifecycleScope.launch {
                                database.studentDao().deleteStudent(student)
                                Toast.makeText(this@MainActivity, "Berhsil Menghapaus Data", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .setNegativeButton(resources.getString(R.string.cancel)){dialog, which ->
                            dialog.dismiss()
                        }
                        .show()

                }

                buttonInsert.setOnClickListener{
                    val intent = Intent(this@MainActivity, activity_insert::class.java)
                    startActivity(intent)
                }
            }

        }
    }
}