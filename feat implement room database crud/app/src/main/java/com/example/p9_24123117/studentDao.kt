import androidx.room.*

@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(student: Student) // Simpan data [cite: 58, 59]

    @Query("SELECT * FROM student_table ORDER BY nama ASC")
    suspend fun getAllStudents(): List<Student> // Ambil semua data [cite: 60, 61]

    @Delete
    suspend fun deleteStudent(student: Student) // Hapus data [cite: 62, 63]
}