package kr.co.lion.androidproject_board

class Tool {
}

enum class FragmentName(var str: String) {
    LOGIN_FRAGMENT("LoginFragment"),
    JOIN_FRAGMENT("JoinFragment"),
    ADD_USER_INFO_FRAGMENT("AddUserInfoFragment")
}

enum class ContentFragmentName(var str: String) {
    MAIN_FRAGMENT("MainFragment"),
    ADD_CONTENT_FRAGMENT("AddContentFragment"),
    READ_CONTENT_FRAGMENT("ReadContentFragment")
}