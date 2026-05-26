package com.example.umc_assignment2.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.umc_assignment2.Product
import com.example.umc_assignment2.R

@Composable
fun ProductItem(
    product: Product,
    onItemClick: (Product) -> Unit,
    onWishClick: (Product) -> Unit,
    showWishButton: Boolean = false,
    isGrid: Boolean = false
) {
    Column(
        modifier = Modifier
            .width(280.dp)
            .clickable { onItemClick(product) }
    ) {
        Box {
            val imageResId = LocalContext.current.resources.getIdentifier(
                product.imageRes, "drawable", LocalContext.current.packageName
            )
            Image(
                painter = if (imageResId != 0) painterResource(id = imageResId)
                else painterResource(id = R.drawable.resource__),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(8.dp)
                    )
            )

            if (showWishButton) {
                IconButton(
                    onClick = { onWishClick(product) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "찜",
                        tint = Color.Gray
                    )
                }
            }
        }

        Text(
            text = product.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp),
            maxLines = 1
        )

        Text(
            text = product.price,
            fontSize = 13.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}