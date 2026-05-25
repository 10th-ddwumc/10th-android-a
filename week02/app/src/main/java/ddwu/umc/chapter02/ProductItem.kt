package ddwu.umc.chapter02

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// 4. 개별 상품 아이템 (여기에 하트 기능 추가!)
@Composable
fun ProductItem(
    product: Product,
    showHeart: Boolean = true, // 하트를 보여줄지 결정하는 파라미터 추가 (기본값 true)
    modifier: Modifier = Modifier) {

    // 이 상품이 현재 위시리스트에 들어있는지 확인
    val isWished = globalWishlist.any { it.id == product.id }

    Column(modifier = modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // showHeart가 true일 때만 하트 아이콘 그리기
            if (showHeart) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd) // 우측 상단 고정
                        .padding(12.dp) // 사진과 여백
                        .size(40.dp) // 원형 배경의 크기
                        .clip(CircleShape) // 원형으로 자르기
                        .background(color = Color.White)
                        .clickable {
                            // 클릭 시
                            if (isWished) {
                                globalWishlist.removeAll { it.id == product.id }
                            } else {
                                globalWishlist.add(product)
                            }
                        }
                        .padding(8.dp) // 원형 배경 내부의 하트 여백
                ) {
                    Icon(
                        imageVector = if (isWished) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "찜하기",
                        tint = if (isWished) Color.Red else Color.Black,
                        modifier = Modifier.fillMaxSize() // Box 크기에 맞춤
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))


        // 베스트셀러 표시
        if (product.isBestSeller) {
            Text(
                text = "Bestseller",
                color = Color(0xFFFA5400),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
        }


        Text(text = product.name, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)

        if (product.subName != null) {
            Text(text = product.subName, color = Color(0xFF888888), fontSize = 12.sp)
        }

        if (product.colorsCount != null) {
            Text(text = "${product.colorsCount} Colours", color = Color(0xFF888888), fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "US$${product.price}", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
    }
}