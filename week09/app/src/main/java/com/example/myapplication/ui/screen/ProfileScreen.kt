package com.example.myapplication.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.data.User
import androidx.compose.foundation.Image
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel()
) {
    val profile by viewModel.profile.collectAsState()
    val followingList by viewModel.followingList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // 프로필 영역
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // 프로필 이미지
            AsyncImage(
                model = profile?.avatar,
                contentDescription = "프로필 이미지",
                modifier = Modifier
                    .padding(top = 21.dp)
                    .size(84.dp)
                    .clip(CircleShape)
            )

            // 닉네임
            Text(
                text = if (profile != null) "${profile!!.first_name} ${profile!!.last_name}" else "닉네임",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 30.dp)
            )

            // 프로필 수정 버튼
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE4E4E4)),
                modifier = Modifier.padding(top = 30.dp)
            ) {
                Text(
                    text = "프로필 수정",
                    fontSize = 16.sp
                )
            }

            // 아이콘 메뉴 (주문, 패스, 이벤트, 설정)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                listOf(
                    Pair(R.drawable.ic_order, "주문"),
                    Pair(R.drawable.ic_pass, "패스"),
                    Pair(R.drawable.ic_event, "이벤트"),
                    Pair(R.drawable.ic_setting, "설정"),
                ).forEachIndexed { index, (icon, label) ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Image(
                            painter = painterResource(id = icon),
                            contentDescription = label,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = label,
                            fontSize = 12.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }

                    // 구분선
                    if (index < 3) {
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(31.dp)
                                .background(Color(0xFFCDCDCD))
                                .align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }

        // 구분선
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(Color(0xFFF6F6F6))
        )

        // 나이키 멤버 혜택
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "나이키 멤버 혜택",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "0개 사용 가능",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_caret_right),
                contentDescription = "더보기",
                modifier = Modifier.size(14.dp)
            )
        }

        // 구분선
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(Color(0xFFF6F6F6))
        )

        // 팔로잉
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 28.dp, bottom = 28.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "팔로잉 (${followingList.size})",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "편집",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            val pagerState = rememberPagerState(pageCount = { followingList.size })

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                pageSpacing = 6.dp,
                beyondViewportPageCount = 0,
                pageSize = androidx.compose.foundation.pager.PageSize.Fixed(107.dp)
            ) { page ->
                FollowingItem(user = followingList[page])
            }
        }

        // 회원 가입일
        Text(
            text = "회원 가입일: 2025년 9월",
            fontSize = 12.sp,
            color = Color(0xFF767676),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF6F6F6))
                .padding(vertical = 19.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
fun FollowingItem(user: User) {
    AsyncImage(
        model = user.avatar,
        contentDescription = "${user.first_name} 프로필",
        modifier = Modifier
            .size(width = 107.dp, height = 106.dp)
            .background(Color(0xFFD9D9D9))
    )
}