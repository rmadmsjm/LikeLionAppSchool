package kr.co.lion.jetpack

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

// Material 3 가 아직 완성 버전이 아니기 때문에
// 실험적 요소(미완성된 UI 요소)를 사용할 때 반드시 붙혀줘야 하는 어노테이션
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(navHostController: NavHostController){

    Scaffold(
        // 상단 툴바
        topBar = {
            TopAppBar(
                // 툴바에 표시될 타이틀
                title = {
                    Text(text = "메모 보기")
                },
                // 툴바의 색상
                colors = TopAppBarDefaults.topAppBarColors(
                    // 툴바 색상
                    containerColor =  Color.White,
                    // 툴바의 타이틀 색상
                    titleContentColor = Color.Black
                ),
                // 네비게이션 아이콘
                navigationIcon = {
                    IconButton(
                        onClick = {
                            // 이전 화면이 보이도록 한다.
                            navHostController.popBackStack()
                        }
                    ) {
                        Icon(
                            // 표시할 아이콘
                            imageVector = Icons.Filled.ArrowBack,
                            // 설명 문자열(화면에 나타나지는 않는다)
                            contentDescription = "뒤로가기",
                            // 아이콘 색상
                            tint = Color.Black)
                    }
                }
            )
        },

        ) {
        // 위에서 아래방향으로 배치하는 레이웃
        Column(
            // fillMaxSize : 화면의 크기를 단말기 전체 화면으로 설정한다.
            // padding : 여백. Scaffold의 it 안에는 상단 툴바 만큼의 여백이 설정되어 있다.
            // background : 배경색상
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White)
        ) {

            Text(text = "제목 : 제목입니다")
            // 여백
            Spacer(modifier = Modifier.padding(top=10.dp))
            
            Text(text = "내용 : 내용입니다")
            
        }
    }
}