package kr.co.lion.naveropenapiretrofit

/*
서버가 전달하는 데이터를 담을 객체를 생성할 클래스
프로퍼티의 이름을 서버가 전달하는 데이터의 이름과 ⭐동일하게⭐ 해주면 자동으로 값이 들어옴
정수, 실수, 문자열, 불리언 값은 각 값의 타입에 맞는 프로퍼티로 정의해줌
{ } : 클래스로 정의해줌
[ ] : List로 정의해줌
 */
data class ResultNews(
    var lastBuildDate: String = "",
    var total: Int = 0,
    var start: Int = 0,
    var display: Int = 0,
    var items: List<ItemClass>
)

data class ItemClass(
    var title: String = "",
    var originallink: String = "",
    var link: String = "",
    var description: String = "",
    var pubDate: String = ""
)