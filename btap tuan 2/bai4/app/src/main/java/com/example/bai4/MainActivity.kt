package com.example.bai4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bai4.ui.theme.Bai4Theme
import java.util.Locale

// 1. Tạo class Student
data class Student(
    val name: String,
    val score: Double?
)

// Data class chứa kết quả thống kê
data class ClassStats(
    val averageScore: Double,
    val gioiCount: Int,
    val nullCount: Int,
    val maxScore: Double?
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bai4Theme {
                ScoreBoardScreen()
            }
        }
    }
}

// 2. Hàm xếp loại theo điểm số (dùng lại từ Bài 2)
fun getRank(score: Double?): String {
    return when {
        score == null -> "Chưa có điểm"
        score >= 8.0 -> "Giỏi"
        score >= 6.5 -> "Khá"
        score >= 5.0 -> "Trung bình"
        else -> "Yếu"
    }
}

// Hàm lấy màu chữ hiển thị xếp loại
fun getRankColor(score: Double?): Color {
    return when {
        score == null -> Color(0xFF6B7280) // Gray
        score >= 8.0 -> Color(0xFF15803D) // Green
        score >= 6.5 -> Color(0xFF1D4ED8) // Blue
        score >= 5.0 -> Color(0xFFD97706) // Orange
        else -> Color(0xFFDC2626)          // Red
    }
}

// 3. Dùng for + if để tính các thông số lớp học
fun calculateStats(students: List<Student>): ClassStats {
    var totalScore = 0.0
    var scoredCount = 0
    var gioiCount = 0
    var nullCount = 0
    var maxScore: Double? = null

    for (student in students) {
        val score = student.score
        if (score != null) {
            totalScore += score
            scoredCount++
            if (score >= 8.0) {
                gioiCount++
            }
            if (maxScore == null || score > maxScore) {
                maxScore = score
            }
        } else {
            nullCount++
        }
    }

    val avg = if (scoredCount > 0) totalScore / scoredCount else 0.0
    return ClassStats(
        averageScore = avg,
        gioiCount = gioiCount,
        nullCount = nullCount,
        maxScore = maxScore
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreBoardScreen() {
    val sampleStudents = listOf(
        Student("Nguyễn Văn An", 9.0),
        Student("Trần Thị Bình", 7.2),
        Student("Lê Minh Chi", null),
        Student("Phạm Quốc Dũng", 4.5),
        Student("Võ Thanh Em", 5.8)
    )

    val stats = calculateStats(sampleStudents)
    val headerBgColor = Color(0xFF283863)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Bảng điểm lớp",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = headerBgColor
                )
            )
        },
        containerColor = Color(0xFFF3F4F6)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(4.dp))
                // Card tổng quan điểm trung bình và thống kê
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = headerBgColor)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "Điểm trung bình lớp",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = String.format(Locale.US, "%.2f", stats.averageScore),
                            color = Color.White,
                            fontSize = 38.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = "Giỏi",
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 12.sp
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "${stats.gioiCount}",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Column {
                                Text(
                                    text = "Chưa có điểm",
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 12.sp
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "${stats.nullCount}",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Column {
                                Text(
                                    text = "Cao nhất",
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 12.sp
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = if (stats.maxScore != null)
                                        String.format(Locale.US, "%.1f", stats.maxScore)
                                    else "-",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "DANH SÁCH · ${sampleStudents.size} SINH VIÊN",
                    color = Color(0xFF6B7280),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                )
            }

            items(sampleStudents) { student ->
                StudentItemCard(student = student)
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun StudentItemCard(student: Student) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = student.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1F2937)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = getRank(student.score),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = getRankColor(student.score)
                )
            }
            Text(
                text = if (student.score != null)
                    String.format(Locale.US, "%.1f", student.score)
                else "—",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScoreBoardScreenPreview() {
    Bai4Theme {
        ScoreBoardScreen()
    }
}