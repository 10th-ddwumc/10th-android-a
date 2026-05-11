package ddwu.umc.chapter02

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp) // 좌우 여백
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Discover",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "3월 23일 월요일",
            fontSize = 14.sp,
            color = Color(0xFF767676)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.image_nike),
            contentDescription = "Nike Image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(378f / 505f) // XML에 있던 비율 맞춤
        )
    }
}