package ddwu.umc.chapter02

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CartScreen(onNavigateToShop: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        //중앙 정렬된 빈 장바구니
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bagcircle),
                contentDescription = "빈 장바구니 아이콘",
                modifier = Modifier.size(60.dp)
            )

            // marginBottom 16dp
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "장바구니가 비어 있습니다.",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            // marginBottom 8dp
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "제품을 추가하면 여기에 표시됩니다.",
                color = Color(0xFF888888), //XML에 있던 #888888
                fontSize = 14.sp
            )
        }

        // 2. 하단 '주문하기' 버튼 (구매하기 화면으로 이동)
        Button(
            onClick = onNavigateToShop,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 20.dp)
                .height(56.dp), // XML에 있던 높이 56dp
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black), // 검은색 배경
            shape = CircleShape
        ) {
            Text(
                text = "주문하기",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}