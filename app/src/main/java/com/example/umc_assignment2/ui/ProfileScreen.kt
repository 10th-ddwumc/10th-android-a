package com.example.umc_assignment2.ui

import android.R
import android.R.attr.fontWeight
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.umc_assignment2.viewmodel.UserViewModel

@Composable
fun ProfileScreen(viewModel: UserViewModel = hiltViewModel()) {

    val userOne by viewModel.userOne.collectAsState()
    val userList by viewModel.userList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchUserOne()
        viewModel.fetchUserList(1)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        AsyncImage(
            model = userOne?.avatar,
            contentDescription = "프로필 이미지",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "${userOne?.firstName ?: ""} ${userOne?.lastName ?: ""}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = {},
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(48.dp)
        ) {
            Text("프로필 수정", color = Color.Black, fontSize = 15.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MenuIconItem(icon = Icons.Default.List, label = "주문")
            VerticalDivider()
            MenuIconItem(icon = Icons.Default.AccountBox, label = "패스")
            VerticalDivider()
            MenuIconItem(icon = Icons.Default.DateRange, label = "이벤트")
            VerticalDivider()
            MenuIconItem(icon = Icons.Default.Settings, label = "설정")
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("나이키 멤버 혜택", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text("0개 사용 가능", color = Color.Gray, fontSize = 13.sp)
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(thickness = 8.dp, color = Color(0xFFF5F5F5))
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "팔로잉 (${userList.size})",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text("편집", color = Color.Gray, fontSize = 13.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (userList.isNotEmpty()) {
            val pagerState = rememberPagerState(pageCount = { userList.size })

            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 16.dp),
                pageSpacing = 8.dp,
                pageSize = PageSize.Fixed(120.dp),
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                val followUser = userList[page]
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = followUser.avatar,
                        contentDescription = followUser.firstName,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(110.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color.LightGray)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = followUser.firstName,
                        fontSize = 12.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "회원 가입일: 2025년 9월",
            color = Color.Gray,
            fontSize = 13.sp
        )
    }
}

@Composable
fun MenuIconItem(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.Gray,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun VerticalDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(40.dp)
            .background(Color.LightGray)
    )
}