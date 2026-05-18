// ShopScreen.kt
package com.example.myapplication.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShopScreen() {
    val tabs = listOf("전체", "Top & T-Shirts", "Shoes")
    var selectedTab by remember { mutableStateOf(0) } // 화면이 재구성되어도 선택 상태 유지,
                                                             // 값 바뀌면 자동으로 UI 업데이트

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            tabs.forEachIndexed { index, title ->
                Text(
                    text = title,
                    color = if (selectedTab == index) Color.Black else Color.Gray,
                    modifier = Modifier
                        .clickable(
                            indication = null, // 클릭 시 회색 ripple 효과 제거
                            interactionSource = remember { MutableInteractionSource() } // indication을 null로 쓰려면 필요...
                        ) { selectedTab = index }
                        .drawBehind {
                            if (selectedTab == index) {
                                drawLine(
                                    color = Color.Black,
                                    start = Offset(0f, size.height + 10.dp.toPx()),
                                    end = Offset(size.width, size.height + 10.dp.toPx()),
                                    strokeWidth = 2.dp.toPx()
                                )
                            }
                        }
                )
            }
        }
    }
}