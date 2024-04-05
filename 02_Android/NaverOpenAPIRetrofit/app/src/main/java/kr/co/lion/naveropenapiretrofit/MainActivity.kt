package kr.co.lion.naveropenapiretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.co.lion.naveropenapiretrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
💡 웹 통신 기초
요청방식(GET, POST, DELETE, ADD, 등...)

1️⃣ GET
   사용자가 서버로 전달하는 파라미터 데이터가 주소에 붙어져서 전달됨
   서버로 전달되는 데이터의 총량이 적음
   요청 속도가 빠름
   서버로 전달하는 파라미터가 공개됨
   주소를 포함한 파라미터 데이터의 총 길이가 255글자를 넘지 못함
   영문, 숫자, 특수문자 외의 다른 글자를 허용하지 않음 (띄어쓰기도 허용하지 않음)
   (최근의 웹 브라우저나 라이브러리는 데이터에 띄어쓰기가 있으면 띄어쓰기에 해당하는 글자로 자동 변환해서 서버로 전달해줌)

2️⃣ POST
   사용자가 서버로 전달하는 파라미터 데이터가 HTTP 요청 해더에 담겨져서 전달됨
   서버로 전달되는 데이터의 총량이 많음
   요청 속도가 느림
   파라미터 데이터의 총량은 제한이 없음
   모든 글자를 허용함

3️⃣ HTTP 요청 헤더
   서버에 요청을 할때 데이터를 숨겨서 전달할 수 있는 수단
   HTTP 요청 헤더에 담아서 전달되는 데이터는 사용자가 알 수 없음
   서버에 요청을 하게 되면 기본적으로 몇 가지 데이터(단말기 정보, 브라우저의 정보 등)가 담기고 그밖에 개발자가 추가적으로 데이터를 담아 전달 수 있음

4️⃣ OPEN API 사용 절차
   1. 반드시 관련 문서를 찾기
      https://developers.naver.com/docs/serviceapi/search/news/news.md#%EB%89%B4%EC%8A%A4

   2. API 키(혹은 접속시 필요한 개발자 구분값) 발급 방법을 확인하기
      애플리케이션을 등록하면 클라이언트 ID와 클라이언트 시크릿을 발급 받을 수 있음

   3. 요청할 페이지의 주소를 확인하기
      https://openapi.naver.com/v1/search/news.json

   4. HTTP 요청 헤더로 전달할 데이터가 있는지 확인하기
      X-Naver-Client-Id: 애플리케이션 등록 시 발급받은 클라이언트 아이디 값
      X-Naver-Client-Secret: 애플리케이션 등록 시 발급받은 클라이언트 시크릿 값

   5. 파라미터로 전달할 데이터가 있는지 확인하기
      query : 검색어. UTF-8로 인코딩되어야 함
      display : 한 번에 표시할 검색 결과 개수(기본값: 10, 최댓값: 100)
      start : 검색 시작 위치(기본값: 1, 최댓값: 1000)
      sort : 검색 결과 정렬 방법 - sim: 정확도순으로 내림차순 정렬(기본값) / date: 날짜순으로 내림차순 정렬

   6. 요청 방식(GET or POST)을 확인하기
      GET

   7. 서버가 전달해주는 데이터의 양식을 확인하기
   8. 오류 코드를 확인하기
   9. POSTMAN 을 이용해 요청 테스트 하기

5️⃣ ⭐ Retrofit 사용
    1. AndroidManifest.xml 에 Internet 권한 추가하기
       <uses-permission android:name="android.permission.INTERNET"/>
    2. build.gradle.kts에 라이브러리 설정하기
       implementation("com.squareup.retrofit2:retrofit:2.11.0")
       implementation("com.squareup.retrofit2:converter-gson:2.11.0")
       implementation("com.squareup.retrofit2:converter-scalars:2.11.0")
       implementation("com.google.code.gson:gson:2.10.1")
    3. 서버가 전달하는 데이터를 담을 클래스 작성하기
       본 프로젝트는 'ResultNews.kt'에 작성함
    4. 요청 정보를 설정할 Interface 작성하기
       본 프로젝트는 'NaverAPI.kt'에 작성함
 */

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            buttonRequest.setOnClickListener {
                textViewResult.text = ""

                // 요청을 위한 Retrofit 객체 생성하기
                val retrofitBuilder = Retrofit.Builder()
                // 기본 주소
                retrofitBuilder.baseUrl("https://openapi.naver.com/v1/search/")
                // 가져온 데이터를 분석하여 객체에 담아줄 도구 설정하기
                // JSON 형식의 문서이므로 GsonConverter를 사용함
                retrofitBuilder.addConverterFactory(GsonConverterFactory.create())

                val retrofit = retrofitBuilder.build()

                // 요청을 위해 사용할 객체
                // 앞서 만든 NaverAPI 인터페이스(요청에 필요한 정보를 가진 인터페이스)를 지정하기
                val api = retrofit.create(NaverAPI::class.java)
                // 요청 객체 추출하기
                // 이때, 요청을 위한 인터페이스의 메서드를 호출함
                val clientId = "euoLhpbTmIT59qxE9ZBD"
                val clientSecret = "zAh0yIoPEO"
                val keyword = inputKeyword.text.toString()
                val display = 100
                val callNews = api.getSearchNews(clientId, clientSecret, keyword, display)

                // 요청하기
                // 요청이 완료되면 동작할 콜백을 설정하기
                callNews.enqueue(object: Callback<ResultNews>{
                    // 요청에 성공했을 때
                    override fun onResponse(call: Call<ResultNews>, response: Response<ResultNews>) {
                        // 서버로부터 가져온 JSON 데이터는 이미 분석이 완료되어 객체에 담겨져 전달됨
                        // 우리는 객체에 담겨져 있는 데이터를 필요한 용도로 사용하면 됨
                        response.body()?.apply {
                            textViewResult.append("lastBuildDate : ${lastBuildDate}\n")
                            textViewResult.append("total : ${total}\n")
                            textViewResult.append("start : ${start}\n")
                            textViewResult.append("display : ${display}\n\n")

                            items.forEach {
                                textViewResult.append("title : ${it.title}\n")
                                textViewResult.append("originallink : ${it.originallink}\n")
                                textViewResult.append("link : ${it.link}\n")
                                textViewResult.append("description : ${it.description}\n")
                                textViewResult.append("pubDate : ${it.pubDate}\n\n")
                            }
                        }
                    }

                    // 요청에 실패했을 때
                    override fun onFailure(call: Call<ResultNews>, error: Throwable) {
                        Log.e("test1234", "실패", error)
                    }
                })
            }
        }
    }
}