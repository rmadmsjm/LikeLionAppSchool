package kr.co.lion.jetpack

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
fun MainScreen(navHostController: NavHostController){

    Scaffold(
        // 상단 툴바
        topBar = {
            TopAppBar(
                // 툴바에 표시될 타이틀
                title = {
                    Text(text = "메모 목록")
                },
                // 툴바의 색상
                colors = TopAppBarDefaults.topAppBarColors(
                    // 툴바 색상
                    containerColor =  Color.White,
                    // 툴바의 타이틀 색상
                    titleContentColor = Color.Black
                ),
                // 툴바의 메뉴
                actions = {
                    IconButton(
                        onClick = {
                            // InputScreen이 보이도록 한다.
                            navHostController.navigate(ScreenName.InputScreen.name)
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.Add,
                            contentDescription = "메모 추가",
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
            // Column 은 위에서 아래방향으로 화면 요소들을 배치하기 위해 사용한다.
            // Row는 좌측에서 우측 방향으로 화면 요소들을 배치하기 위해 사용한다.
            // Box는 겹쳐지게 화면 요소들을 배치하기 위해 사용한다.
            // 위의 3개는 배치한 화면 요소들이 단말기 화면 밖으로 벗어난다고 해도 모두 생성된다.
            // Lazy로 시작하는 것들도 위와 유사하지만 화면상에 보이지 않는 화면 요소들을
            // 생성이 대기하고 있다가 보이게 되는 순간에 생성된다.
            // 보였다가 사라진 요소들을 사용 대기상태가 되고 재사용 된다.
            LazyColumn {
                // 리스트
                // 100개의 항목을 가진 리스트를 생성한다.
                // LazyColumn 안에 있기 때문에 보이지 않는 항목들은 생성이 대기된다
                // 사라진 항목들은 새롭게 나타난 항목들을 위해 재사용 된다.
                // it 에는 몇 번째 항목인지의 값이 들어온다.
                items(100){
                    // 항목 하나의 모양을 구성한다.
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            // 항목을 눌렀을 때
                            .clickable {
                                // 결과 화면이 보이도록 한다.
                                navHostController.navigate(ScreenName.OutputScreen.name)
                            }
                    ) {
                        Text(text = "항목입니다 : $it")
                    }
                    Divider()
                }
            }

        }
    }
}