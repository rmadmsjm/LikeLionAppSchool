package kr.co.lion.androidproject4boardapp

class Tools {
    companion object {
    }
}

// MainActivity에 보여질 Fragment의 이름
enum class MainFragmentName(var str: String) {
    LOGIN_FRAGMENT("LoginFragment"),
    JOIN_FRAGMENT("JoinFragment"),
    ADD_USER_INFO_FRAGMENT("AddUserInfoFragment")
}

// ContentActivity에 보여질 Fragment의 이름
enum class ContentFragmentName(var str: String) {
    MAIN_FRAGMENT("MainFragment"),
    ADD_CONTENT_FRAGMENT("AddContentFragment"),
    READ_CONTENT_FRAGMENT("ReadContentFragment"),
    MODIFY_CONTENT_FRAGMENT("ModifyContentFragment")
}