package com.example.bai3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.backgroundz
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bai3.ui.theme.Bai3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bai3Theme {
                MainScreen()
            }
        }
    }
}

// 2. Viết một hàm nhận vào điểm và trả về xếp loại
fun getXepLoai(diem: Double): String {
    return if (diem !in 0.0..10.0) {
        "Không hợp lệ"
    } else if (diem >= 8.0) {
        "Giỏi"
    } else if (diem >= 6.5) {
        "Khá"
    } else if (diem >= 5.0) {
        "Trung bình"
    } else {
        "Yếu"
    }
}

// 3. Màu chữ của xếp loại cũng chọn bằng if
fun getMauXepLoai(diem: Double): Color {
    return if (diem !in 0.0..10.0) {
        Color(0xFF757575) // Xám cho Không hợp lệ
    } else if (diem >= 8.0) {
        Color(0xFF2E7D32) // Xanh lá cho Giỏi
    } else if (diem >= 6.5) {
        Color(0xFF1976D2) // Xanh dương cho Khá
    } else if (diem >= 5.0) {
        Color(0xFFED6C02) // Cam cho Trung bình
    } else {
        Color(0xFFD32F2F) // Đỏ cho Yếu
    }
}

// Màu nền của thẻ/badge xếp loại chọn bằng if
fun getMauNenXepLoai(diem: Double): Color {
    return if (diem !in 0.0..10.0) {
        Color(0xFFEEEEEE)
    } else if (diem >= 8.0) {
        Color(0xFFE8F5E9)
    } else if (diem >= 6.5) {
        Color(0xFFE3F2FD)
    } else if (diem >= 5.0) {
        Color(0xFFFFF3E0)
    } else {
        Color(0xFFFFEBEE)
    }
}

@Composable
fun MainScreen() {
    // 1. Khai báo một biến điểm có giá trị 8.5
    val diemChinh = 8.5

    // 4. Phần "Kiểm tra thêm": gọi hàm với các điểm 9, 7, 5.5, 3 và 11
    val danhSachDiemKiemTra = listOf(9.0, 7.0, 5.5, 3.0, 11.0)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2B3E6B))
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "Xếp loại học lực",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F6F9))
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Thẻ hiển thị điểm chính
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Điểm trung bình",
                        fontSize = 14.sp,
                        color = Color(0xFF666666)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "$diemChinh",
                        fontSize = 44.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF222222)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Hiển thị xếp loại chính
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = getMauNenXepLoai(diemChinh),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = getXepLoai(diemChinh),
                            color = getMauXepLoai(diemChinh),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tiêu đề phần Kiểm tra thêm
            Text(
                text = "KIỂM TRA THÊM",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF757575),
                modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
            )

            // Danh sách các điểm kiểm tra thêm
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                danhSachDiemKiemTra.forEach { diem ->
                    val diemText = if (diem % 1.0 == 0.0) diem.toInt().toString() else diem.toString()

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Điểm $diemText",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF333333)
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            // Xếp loại cho điểm kiểm tra thêm
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = getMauNenXepLoai(diem),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = getXepLoai(diem),
                                    color = getMauXepLoai(diem),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Bai3Theme {
        MainScreen()
    }
}
