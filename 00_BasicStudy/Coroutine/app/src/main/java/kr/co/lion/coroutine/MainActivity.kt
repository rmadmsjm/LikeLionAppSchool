package kr.co.lion.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.lion.coroutine.databinding.ActivityMainBinding

/*
코루틴
동시에 작업을 하거나 오류가 발생할 가능성이 높은 코드를 처리하는데 사용함
쓰래드와 유사하지만 쓰래드의 단점을 보완하기 위해 만들어졌음
1. 쓰래드보다 메모리 사용량이 적어 작업의 처리가 더 빨리 끝남
2. 비동기적 처리(동시에 여러 작업을 수행)를 위해 사용하지만 동기적(순차 처리)으로 운영하기가 쉬움
3. 중간에 중단하기기 쉬움
4. 다른 루틴에서 발생시킨 데이터를 가져오는게 매우 쉬움

launch : 코루틴을 발생시킴
         코루틴이 호출하는 함수가 반환 값이 없을 때 사용함
join : launch로 실행시킨 코루틴이 끝날 때 가지 메인 루틴의 코드를 일시 정시 시킴
       동기처리를 하고자 할 때 사용함
async : 코루틴을 발생시킴
        코루틴이 호출하는 함수가 반환하는 값이 있을 때 사용함
await : async로 가동시킨 코루틴이 호출하는 함수가 반환하는 값을 받을 수 있음
        값을 반환할 때까지 메인 루팅이 대기상태가 되기 때문에 await을 호출하는 시점을 잘 잡아주는 것이 매우 중요함
 */

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var w1:Job
    lateinit var w2:Job
    lateinit var w3:Job
    lateinit var mainWorking:Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {

            button.setOnClickListener {
                // 여러 코루틴을 관리할 수 있는 객체
                // CoroutineScope : 발생시키는 코루틴의 용도를 지정함
                // Main : 안드로이드의 MainThread가 처리해줌, 화면에 관련된 작업이 가능함
                // IO : 데이터 입출력 용. 별도의 쓰래드가 발생함. 네트워크 처리나 데이터 베이스, 파일처리 등에서 사용함

                // launch : 코루틴 가동
                CoroutineScope(Dispatchers.Main).launch {
                    // 코루틴 가동
                    // launch로 가동시키면 각각이 독립적으로 동시에 실행되는 효과를 얻을 수 있음
                    launch {
                        working1()
                    }
                    launch {
                        working2()
                    }
                    launch {
                        working3()
                    }
                }
            }

            button2.setOnClickListener {
                // 코루틴으로 운영하는 작업을 동기적(순서대로) 처리할 수 있음
                CoroutineScope(Dispatchers.Main).launch {
                    // 코루틴 가동
                    val job1 = launch {
                        working1()
                    }

                    // 첫 번째 코루틴이 끝날때 까지 대기함
                    job1.join()

                    val job2 = launch {
                        working2()
                    }

                    // 두 번째 코루틴이 끝날때 까지 대기함
                    job2.join()

                    launch {
                        working3()
                    }
                }
            }

            button3.setOnClickListener {
                // 실행되는 코루틴을 관리하는 객체를 변수에 담아줌
                mainWorking = CoroutineScope(Dispatchers.Main).launch {
                    // 코루틴 가동
                    // 코루틴을 관리하는 객체를 변수에 담아줌
                    w1 = launch {
                        working1()
                    }
                    w2 = launch {
                        working2()
                    }
                    w3 = launch {
                        working3()
                    }
                }
            }

            button4.setOnClickListener {
                // w1과 w3만 중단시킴
                // w1.cancel()
                // w3.cancel()

                // 모든 코루틴의 수행을 중단시킴
                mainWorking.cancel()
            }

            button5.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    // 코루틴 가동
                    // 코루틴으로 운영하는 함수에서 반화는 값을 받으려면 launch가 아닌 async 로 가동해야 함
                    val job1 = async {
                        working4()
                    }
                    val job2 = async {
                        working5()
                    }
                    val job3 = async {
                        working6()
                    }

                    // await : 코루틴으로 운영하는 함수가 반환하는 값을 받아올 수 있음
                    // 코루틴이 관리하는 코드가 수행이 완료되면 return 부분은 수행하지 않고 대기 상태가 됨
                    // 이 때 await을 호출하면 return 부분이 수행되어 값을 반환하게 됨
                    textView.text = "job1 : ${job1.await()}"
                    textView2.text = "job2 : ${job2.await()}"
                    textView3.text = "job2 : ${job3.await()}"
                }
            }

            button6.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    // 코루틴 가동
                    // 코루틴으로 운영하는 함수에서 반화는 값을 받으려면 launch가 아닌 async 로 가동해야 함
                    val job1 = async {
                        working4()
                    }

                    // 여기서 await을 호출함
                    // await 을 호출하면 코루틴의 작업이 끝날 때 까지 대기하고 있다가 값을 반환하면 그 값을 받은 다음 다음으로 이어 나감
                    textView.text = "job1 : ${job1.await()}"

                    val job2 = async {
                        working5()
                    }
                    textView2.text = "job2 : ${job2.await()}"

                    val job3 = async {
                        working6()
                    }
                    textView3.text = "job3 : ${job3.await()}"
                }
            }
        }
    }

    // 코루틴으로 운영할 코드를 가지고 있는 메서드
    // suspend fun : 함수 내부의 코드를 중단하거나 일시정지하는 등의 관리가 가능한 함수
    // 코루틴으로 운영할 코드를 가지고 있는 메서드(함수)는 os에서 코드의 흐름을 관리해야 하기 때문에 suspend fun 으로 정의하는 것이 좋음
    suspend fun working1(){
        for(a1 in 0..10){
            // 500ms 쉬었다 가기 (코루틴에서만 사용 가능)
            delay(500)
            val now = System.currentTimeMillis()
            activityMainBinding.textView.text = "working1 : $now"
        }
    }

    suspend fun working2(){
        for(a1 in 0..10){
            delay(500)
            val now = System.currentTimeMillis()
            activityMainBinding.textView2.text = "working2 : $now"
        }
    }

    suspend fun working3(){
        for(a1 in 0..10){
            delay(500)
            val now = System.currentTimeMillis()
            activityMainBinding.textView3.text = "working3 : $now"
        }
    }

    // 코루틴이 운영할 함수 (반환값을 가지고 있음)
    suspend fun working4():Int{

        var a2 = 0

        for(a1 in 0..10){
            // 500ms 쉬었다 가기 (코루틴에서만 사용 가능)
            delay(500)
            val now = System.currentTimeMillis()
            activityMainBinding.textView.text = "working4 : $now"

            a2++
        }

        return a2
    }

    suspend fun working5():Int{

        var a2 = 0

        for(a1 in 0..10){
            // 500ms 쉬었다 가기 (코루틴에서만 사용 가능)
            delay(500)
            val now = System.currentTimeMillis()
            activityMainBinding.textView2.text = "working5 : $now"

            a2++
        }

        return a2
    }

    suspend fun working6():Int{

        var a2 = 0

        for(a1 in 0..10){
            // 500ms 쉬었다 가기 (코루틴에서만 사용 가능)
            delay(500)
            val now = System.currentTimeMillis()
            activityMainBinding.textView3.text = "working6 : $now"

            a2++
        }

        return a2
    }
}


