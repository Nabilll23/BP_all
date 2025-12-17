import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table") // Nama tabel di SQLite [cite: 42]
data class Student(
    @PrimaryKey(autoGenerate = true) // ID akan bertambah otomatis [cite: 44]
    val id: Int = 0,
    val nama: String,
    val nim: String
)