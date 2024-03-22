package kr.co.lion.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.lion.jetpack.ui.theme.JetPackComposeTestTheme

// JetPack Compose를 사용하는 안드로이드 애플리케이션의 Activity는
// ComponentActivity를 상속 받는다.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Activity가 관리할 화면을 지정해준다.
        setContent {
            JetPackComposeTestTheme {
                MemoApp()
            }
        }
    }
}

// @Composable : 화면 요소를 만들고 구성하기 위한 함수들에 붙혀준다.
// 이 함수에서 만들어는 화면 요소들은 실제로 화면상에 반영된다.
@Composable
fun MemoApp(modifier: Modifier = Modifier){
    // 레이아웃
    // Box : 중첩해서 배치한다.
    // Row : 좌측에서 우측으로 배치한다.
    // Column : 위에서 아래로 배치한다.

    // padding : 내부의 여백
    Column(
        modifier = Modifier
            // 내부의 여백
            .padding(10.dp)
            // 크기
            // fillMaxWidth() : 가로 길이를 꽉 채운다.
            // fillMaxHeight() : 세로 길이를 꽉 채운다.
            // fillMaxSize() : 가로 세로 길이를 꽉 채운다.
            .fillMaxSize())
    {
        // remember 프로퍼티
        // 화면 회전이 발생했거나 다른 화면으로 갔다 왔거나 할 때
        // 화면 요소들이 초기화 되는 경우가 있다.
        // remember 프로퍼티에 저장된 값은 어떤한 일이 벌어지더라도 초기화 되지 않는다.
        // View에 지정하는 값을 저장하는 용도로 사용한다.
        val textFieldState = remember {
            mutableStateOf("")
        }
        val resultState = remember {
            mutableStateOf("")
        }

        TextFieldFirst(textFieldState)

        // 여백
        Spacer(modifier = Modifier.padding(top = 10.dp))

        ButtonTest(textFieldState, resultState)

        Spacer(modifier = Modifier.padding(top = 10.dp))

        TextResult(resultState)

    }
}

// 텍스트 필드 구성
@Composable
fun TextFieldFirst(textFieldState: MutableState<String>){
    // 텍스트 필드
    TextField(
        // 텍스트 필드를 통해 보여줄 값이나 프로퍼티를 지정한다.
        // remember 프로퍼티가 관리하는 값을 넣어주는 코드를 작성해준다.
        // 여기에 넣어주는 값이 화면상에 나타난다.
        value = textFieldState.value,
        // TextField의 입력값이 변경되는 동작하는 리스너
        // TextField를 클릭하고 입력을 하면 실제로는 입력이 되지만
        // 화면상에는 어떠한 변화가 이루어지지 않는다.
        // 여기에서 value 에 값을 넣어주는 작을 해야 한다.
        onValueChange = {textValue -> textFieldState.value = textValue},
        modifier = Modifier.fillMaxWidth())
}

// 버튼
@Composable
fun ButtonTest(textFieldState: MutableState<String>, resultState: MutableState<String>) {
    Button(
        // 버튼을 눌렀을 때 동작하는 리스너
        onClick = {
                  // 텍스트 필드와 연결되어 있는 remember 프로퍼티의 값을
                  // 텍스트와 연결되어 있는 remember 프로퍼티에 담아준다.
                  resultState.value = textFieldState.value
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "버튼입니다")
    }
}

// 결과
@Composable
fun TextResult(resultState:MutableState<String>){
    Text(text = resultState.value)
}

// 미리보기
// 미리보기는 개발시 화면 테스트를 위해 사용한다.
@Preview
@Composable
fun Preview(){
    JetPackComposeTestTheme {
        MemoApp()
    }
}
