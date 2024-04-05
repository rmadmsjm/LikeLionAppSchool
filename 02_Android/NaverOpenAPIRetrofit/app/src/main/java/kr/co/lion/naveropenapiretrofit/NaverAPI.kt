package kr.co.lion.naveropenapiretrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/*
서버에 요청하기 위해 필요한 데이터(주소, 헤더 정보, 파라미터 등)을 설정하는 인터페이스
이 인터페이스를 구현하고 요청에 필요한 메서드들이 구현되어 있는 클래스가 자동으로 만들어짐
 */
interface NaverAPI {
    // 요청 방식과 주소 설정하기
    // 주소는 기본 주소와 변경 주소로 나뉨
    // 변경 주소를 설정하면 됨

    // GET 요청 : @GET
    // POST 요청 : @POST
    @GET("news.json")

    // 헤더 정보와 파라미터 정보를 설정하는 메서드
    fun getSearchNews(
        // 헤더 정보
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        // 파라미터
        @Query("query") query: String,
        @Query("display") display: Int
    ): Call<ResultNews>
}