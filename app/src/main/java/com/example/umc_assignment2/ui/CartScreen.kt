package com.example.umc_assignment2.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.umc_assignment2.R

@Composable
fun CartScreen(
    onOrderClick: () -> Unit  // 주문하기 버튼 클릭 → 구매하기 탭으로 이동
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // bagcircle 이미지 - 세로 26% 위치
        Image(
            painter = painterResource(id = R.drawable.bagcircle),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 220.dp)  // 전체 높이의 약 26%
        )

        // cart_empty 텍스트
        Text(
            text = stringResource(id = R.string.cart_empty),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 300.dp)
        )

        // cart_desc 텍스트
        Text(
            text = stringResource(id = R.string.cart_desc),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 320.dp)
        )

        // 주문하기 버튼 - 하단 고정
        Button(
            onClick = onOrderClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF050505)
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 92.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(54.dp)
        ) {
            Text(
                text = stringResource(id = R.string.order_button),
                color = Color.White
            )
        }
    }
}