package kr.co.lion.androidproject_board

class Tool {
}

enum class FragmentName(var str: String) {
    LOGIN_FRAGMENT("LoginFragment"),
    JOIN_FRAGMENT("JoinFragment"),
    ADD_USER_INFO_FRAGMENT("AddUserInfoFragment")
}

enum class ContentFragmentName(var str: String) {
    A_FRAGMENT("A"),
    B_FRAGMENT("B")
}