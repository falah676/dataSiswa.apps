package com.example.datasiswa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.Student

class StudentAdapter(val studentList: List<Student>):
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    //untuk delete data siswa
    var deleteClickListener: ((Student) -> Unit)? = null
    //untuk pindah ke detail siswa
    var itemClickListener: ((Student) -> Unit)? = null
    //untuk edit data siswa
    var updateClickListener: ((Student) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
       val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student: Student = studentList[position]
        holder.textStudentName.text = student.name
        holder.textAgeStudent.text = "Umur: ${student.age} Tahun"

        holder.imageDelete.setOnClickListener {
            deleteClickListener?.invoke(student) //callback untuk delete data
        }

        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(student) //callback untuk pindah
        }
        holder.imageEditText.setOnClickListener {
            updateClickListener?.invoke(student)
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textStudentName = itemView.findViewById<TextView>(R.id.text_student_name)
        val textAgeStudent = itemView.findViewById<TextView>(R.id.text_student_age)
        val imageDelete = itemView.findViewById<ImageView>(R.id.image_delete)
        val imageEditText = itemView.findViewById<ImageView>(R.id.image_edit)
    }

}
