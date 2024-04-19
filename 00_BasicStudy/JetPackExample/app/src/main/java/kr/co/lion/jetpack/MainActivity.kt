package kr.co.lion.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kr.co.lion.jetpack.ui.theme.JetPackExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            JetPackExampleTheme {
                // 애플리케이션 메인 코드 함수 호출
                MemoApp()
            }
        }
    }
}

// 각 화면을 지칭할 이름
enum class ScreenName(){
    MainScreen,
    InputScreen,
    OutputScreen
}

// 애플리케이션 메인 코드
@Composable
fun MemoApp(modifier: Modifier = Modifier){
    // 화면 네비게이션을 관리하는 객체
    val navController = rememberNavController()
    // 보여줄 화면을 등록한다.
    NavHost(
        // 화면 네비게이션을 관리하는 객체
        navController = navController,
        // 첫 화면의 이름
        startDestination = ScreenName.MainScreen.name) {

        // 사용할 화면들을 등록해준다.
        // route : 화면의 이름을 지정한다.
        // 새로운 화면을 보여주고 싶을 때 지정하는 이름이 route에 등록되어 있는
        // 화면을 보여주게 된다.
        // 메인 화면
        composable(
            route = ScreenName.MainScreen.name,
            // 화면 전환 애니메이션 설정
            // A 화면에서 B화면으로 이동된다고 가정한다.
            // https://developer.android.com/develop/ui/compose/animation/composables-modifiers?hl=ko
            // 다음 화면으로 전환 될때 B 화면에 적용되는 애니메이션
            enterTransition = {
                slideInHorizontally(
                    // 화면의 초기 위치
                    initialOffsetX = {it},
                    // 애니메이션 부가 설정
                    // durationMillis : 애니메이션 동작 시간
                    // delayMillis : 애니메이션 대기 시간
                    animationSpec = tween(durationMillis = 200, delayMillis = 200)
                )
            },
            // 다음 화면으로 전환 될때 A 화면에 적용되는 애니메이션
            exitTransition = {
                //slideOutHorizontally()
                fadeOut(
                    animationSpec = tween(
                        durationMillis = 200,
                        delayMillis = 200
                    )
                )
            },
            // 다음 화면에서 돌아올 때 A 화면에 적용되는 애니메이션
            popEnterTransition = {
                // slideInHorizontally()
                fadeIn(
                    animationSpec = tween(
                        durationMillis = 200,
                        delayMillis = 200
                    )
                )
            },
            // 다음 화면에서 돌아올 때 B 화면에 적용되는 애니메이션
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = {it},
                    animationSpec = tween(
                        durationMillis = 200,
                        delayMillis = 200
                    )
                )
            }
        ) {
            // MainScreen이 구성되도록 호출한다.
            MainScreen(navHostController = navController)
        }

        // 입력 화면
        composable(
            route = ScreenName.InputScreen.name,
            enterTransition = {
                slideInHorizontally(
                    // 화면의 초기 위치
                    initialOffsetX = {it},
                    // 애니메이션 부가 설정
                    // durationMillis : 애니메이션 동작 시간
                    // delayMillis : 애니메이션 대기 시간
                    animationSpec = tween(durationMillis = 200, delayMillis = 200)
                )
            },
            // 다음 화면으로 전환 될때 A 화면에 적용되는 애니메이션
            exitTransition = {
                //slideOutHorizontally()
                fadeOut(
                    animationSpec = tween(
                        durationMillis = 200,
                        delayMillis = 200
                    )
                )
            },
            // 다음 화면에서 돌아올 때 A 화면에 적용되는 애니메이션
            popEnterTransition = {
                // slideInHorizontally()
                fadeIn(
                    animationSpec = tween(
                        durationMillis = 200,
                        delayMillis = 200
                    )
                )
            },
            // 다음 화면에서 돌아올 때 B 화면에 적용되는 애니메이션
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = {it},
                    animationSpec = tween(
                        durationMillis = 200,
                        delayMillis = 200
                    )
                )
            }
        ) {
            // InputScreen이 구성되도록 호출한다.
            InputScreen(navHostController = navController)
        }

        // 결과 화면
        composable(
            route = ScreenName.OutputScreen.name,
            enterTransition = {
                slideInHorizontally(
                    // 화면의 초기 위치
                    initialOffsetX = {it},
                    // 애니메이션 부가 설정
                    // durationMillis : 애니메이션 동작 시간
                    // delayMillis : 애니메이션 대기 시간
                    animationSpec = tween(durationMillis = 200, delayMillis = 200)
                )
            },
            // 다음 화면으로 전환 될때 A 화면에 적용되는 애니메이션
            exitTransition = {
                //slideOutHorizontally()
                fadeOut(
                    animationSpec = tween(
                        durationMillis = 200,
                        delayMillis = 200
                    )
                )
            },
            // 다음 화면에서 돌아올 때 A 화면에 적용되는 애니메이션
            popEnterTransition = {
                // slideInHorizontally()
                fadeIn(
                    animationSpec = tween(
                        durationMillis = 200,
                        delayMillis = 200
                    )
                )
            },
            // 다음 화면에서 돌아올 때 B 화면에 적용되는 애니메이션
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = {it},
                    animationSpec = tween(
                        durationMillis = 200,
                        delayMillis = 200
                    )
                )
            }
        ){
            ResultScreen(navHostController = navController)
        }

    }
}






