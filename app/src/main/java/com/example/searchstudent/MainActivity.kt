package com.example.searchstudent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etSearch: EditText
    private lateinit var listView: ListView
    private lateinit var studentAdapter: StudentAdapter

    private val students = listOf(
        Student("Phan Viet Hoang", "20210372"),
        Student("Tran Hoang Son", "20230303"),
        Student("Bui Manh Chien", "20220123"),
        Student("Pham Tien Dat", "20200356"),
        Student("Hoang Van Cong", "20215678")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSearch = findViewById(R.id.etSearch)
        listView = findViewById(R.id.listView)

        studentAdapter = StudentAdapter(this, students)
        listView.adapter = studentAdapter

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val keyword = s.toString()

                if (keyword.length > 2) {
                    val filteredList = students.filter {
                        it.name.contains(keyword, ignoreCase = true) || it.mssv.contains(keyword, ignoreCase = true)
                    }
                    studentAdapter.updateData(filteredList)
                } else {
                    studentAdapter.updateData(students)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
