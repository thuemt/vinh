package com.example.bai2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bai2.ui.theme.Bai2Theme

// Yêu cầu 1: Tạo class Student gồm String name, String? email, String? phone.
data class Student(
    val name: String,
    val email: String?,
    val phone: String?
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bai2Theme {
                StudentListScreen()
            }
        }
    }
}

@Composable
fun StudentListScreen() {
    // Yêu cầu 2: Tạo list 5 sinh viên, trong đó có vài người thiếu email hoặc số điện thoại (null).
    val studentList = listOf(
        Student("Nguyễn Văn An", "an@sv.edu.vn", "0901 234 567"),
        Student("Trần Thị Bình", null, "0912 888 999"),
        Student("Lê Minh Chi", "chi@sv.edu.vn", null),
        Student("Phạm Quốc Dũng", null, null),
        Student("Võ Thanh Em", "em@sv.edu.vn", "0933 111 222")
    )

    // Yêu cầu 5: Dùng for để đếm số sinh viên thiếu email
    var missingEmailCount = 0
    for (student in studentList) {
        if (student.email == null) {
            missingEmailCount++
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            // Header: Danh sách sinh viên
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF152C5B))
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "Danh sách sinh viên",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF3F4F6))
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Yêu cầu 3: Dùng for để tạo các thẻ sinh viên trong danh sách
            for (student in studentList) {
                StudentCard(student = student)
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Yêu cầu 5: Hiển thị số sinh viên thiếu email ở cuối danh sách
            SummaryCard(
                totalCount = studentList.size,
                missingEmailCount = missingEmailCount
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun StudentCard(student: Student) {
    // Yêu cầu 4: Nếu một trường bị null thì hiện chữ "Chưa cập nhật", dùng toán tử ?: (Elvis)
    val emailText = student.email ?: "Chưa cập nhật"
    val phoneText = student.phone ?: "Chưa cập nhật"

    val isEmailMissing = student.email == null
    val isPhoneMissing = student.phone == null

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Column {
            // Tên sinh viên
            Text(
                text = student.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1F2937)
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Email
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Email: ",
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )
                Text(
                    text = emailText,
                    fontSize = 14.sp,
                    color = if (isEmailMissing) Color(0xFFE53935) else Color(0xFF4B5563),
                    fontStyle = if (isEmailMissing) FontStyle.Italic else FontStyle.Normal
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // SĐT
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "SĐT: ",
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )
                Text(
                    text = phoneText,
                    fontSize = 14.sp,
                    color = if (isPhoneMissing) Color(0xFFE53935) else Color(0xFF4B5563),
                    fontStyle = if (isPhoneMissing) FontStyle.Italic else FontStyle.Normal
                )
            }
        }
    }
}

@Composable
fun SummaryCard(totalCount: Int, missingEmailCount: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFE2E8F0))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tổng số sinh viên
            Text(
                text = buildAnnotatedString {
                    append("Tổng: ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("$totalCount sinh viên")
                    }
                },
                fontSize = 14.sp,
                color = Color(0xFF1F2937)
            )

            // Số sinh viên thiếu email
            Text(
                text = buildAnnotatedString {
                    append("Thiếu email: ")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE53935)
                        )
                    ) {
                        append(missingEmailCount.toString())
                    }
                },
                fontSize = 14.sp,
                color = Color(0xFF1F2937)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudentListScreenPreview() {
    Bai2Theme {
        StudentListScreen()
    }
}
