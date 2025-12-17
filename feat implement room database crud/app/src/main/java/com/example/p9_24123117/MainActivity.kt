import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etNim = findViewById<EditText>(R.id.etNim)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val rvStudent = findViewById<RecyclerView>(R.id.rvStudent)

        // 1. Inisialisasi Database [cite: 107]
        db = AppDatabase.getDatabase(this)

        // 2. Setup RecyclerView & Adapter [cite: 109]
        studentAdapter = StudentAdapter(arrayListOf())
        rvStudent.layoutManager = LinearLayoutManager(this)
        rvStudent.adapter = studentAdapter

        // 3. Load Data Awal [cite: 112]
        loadData()

        // 4. Aksi Tombol Simpan [cite: 114]
        btnSave.setOnClickListener {
            val nama = etNama.text.toString()
            val nim = etNim.text.toString()
            val newStudent = Student(nama = nama, nim = nim)

            // Simpan ke DB menggunakan Coroutine [cite: 119, 120]
            lifecycleScope.launch {
                db.studentDao().insertStudent(newStudent)
                loadData() // Refresh list [cite: 122]
                etNama.text.clear()
                etNim.text.clear()
            }
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            val students = db.studentDao().getAllStudents()
            studentAdapter.setData(students)
            studentAdapter.notifyDataSetChanged()
        }
    }
}