package ddwu.umc.chapter02

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PageSize


data class UserData(
    val id: Int,
    val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val avatar: String
)

data class SingleUserResponse(val data: UserData)
data class UserListResponse(val page: Int, val data: List<UserData>)

// API (내 프로필, 팔로잉 목록) 모두 호출
interface ReqResApi {
    // 1번 유저(내 정보) 조회
    @GET("api/users/{id}")
    suspend fun getUser(
        @Path("id") userId: Int,
        @Header("x-api-key") apiKey: String = "reqres_def8c0b458de43c6a6d1a13bbe845d90"
    ): SingleUserResponse

    // 전체 유저(팔로잉 리스트) 조회
    @GET("api/users")
    suspend fun getUserList(
        @Header("x-api-key") apiKey: String = "reqres_def8c0b458de43c6a6d1a13bbe845d90"
    ): UserListResponse
}

object RetrofitClient {
    private const val BASE_URL = "https://reqres.in/"
    val api: ReqResApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReqResApi::class.java)
    }
}

// 메인 화면 UI
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen() {
    // 내 정보 담을 변수
    var myProfile by remember { mutableStateOf<UserData?>(null) }
    // 팔로잉 리스트(진짜 서버 데이터) 담을 리스트 변수
    var followingList by remember { mutableStateOf<List<UserData>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    // 화면 켜질 때 API 2개 모두 쏘기
    LaunchedEffect(Unit) {
        try {
            // 1. 내 정보 가져오기 (userId 1번)
            val profileResponse = RetrofitClient.api.getUser(userId = 1)
            myProfile = profileResponse.data

            // 2. 팔로잉 리스트 가져오기 (전체 유저)
            val listResponse = RetrofitClient.api.getUserList()
            followingList = listResponse.data

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // 내 프로필 영역]
        if (isLoading) {
            Box(modifier = Modifier.size(90.dp).clip(CircleShape).background(Color.LightGray))
        } else {
            AsyncImage(
                model = myProfile?.avatar,
                contentDescription = "프로필 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(90.dp).clip(CircleShape).background(Color.LightGray)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (isLoading) "불러오는 중..." else "${myProfile?.firstName} ${myProfile?.lastName}",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(24.dp))
                .padding(horizontal = 32.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "프로필 수정", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // [4단 메뉴 영역]
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ProfileMenuItem(iconResId = R.drawable.ic_archive, title = "주문")
            Divider(modifier = Modifier.height(24.dp).width(1.dp), color = Color(0xFFE0E0E0))
            ProfileMenuItem(iconResId = R.drawable.ic_identificationcard, title = "패스")
            Divider(modifier = Modifier.height(24.dp).width(1.dp), color = Color(0xFFE0E0E0))
            ProfileMenuItem(iconResId = R.drawable.ic_calendarblank, title = "이벤트")
            Divider(modifier = Modifier.height(24.dp).width(1.dp), color = Color(0xFFE0E0E0))
            ProfileMenuItem(iconResId = R.drawable.ic_gear, title = "설정")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider(thickness = 8.dp, color = Color(0xFFF5F5F5))

        // [배너 영역]
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "나이키 멤버 혜택", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "0개 사용 가능", fontSize = 12.sp, color = Color(0xFF9E9E9E))
            }
            Text(text = ">", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Divider(thickness = 8.dp, color = Color(0xFFF5F5F5))

        //[팔로잉 리스트]
        Column(modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "팔로잉 (${followingList.size})", // 서버에서 받아온 리스트 개수 반영
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "편집", fontSize = 14.sp, color = Color(0xFF9E9E9E))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 서버 데이터가 들어왔을 때만 Pager 렌더링
            if (followingList.isNotEmpty()) {
                val pagerState = rememberPagerState(pageCount = { followingList.size })

                HorizontalPager(
                    state = pagerState,
                    pageSize = PageSize.Fixed(110.dp), // 한 화면에 여러 장 보이도록 사이즈 고정
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    pageSpacing = 12.dp
                ) { page ->
                    val user = followingList[page]

                    // 사진만 정사각형으로 꽉 차게
                    AsyncImage(
                        model = user.avatar,
                        contentDescription = "팔로잉 프로필",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(110.dp)
                    )
                }
            } else if (!isLoading) {
                // 팔로잉 목록이 없을 경우 예외 처리
                Text(
                    text = "팔로잉 목록이 없습니다.",
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        //[푸터]
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5))
                .padding(vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "회원 가입일: 2025년 9월", fontSize = 12.sp, color = Color(0xFF9E9E9E))
        }
    }
}

@Composable
fun ProfileMenuItem(iconResId: Int, title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = title,
            tint = Color.Unspecified, // XML 고유 색상 유지
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}