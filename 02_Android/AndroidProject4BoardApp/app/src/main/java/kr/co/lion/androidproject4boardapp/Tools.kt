package kr.co.lion.androidproject4boardapp

class Tools {
    companion object {
    }
}

// MainActivity에 보여질 Fragment의 이름
enum class MainFragmentName(var str: String) {
    LOGIN_FRAGMENT("LoginFragment"),
    JOIN_FRAGMENT("JoinFragment")
}