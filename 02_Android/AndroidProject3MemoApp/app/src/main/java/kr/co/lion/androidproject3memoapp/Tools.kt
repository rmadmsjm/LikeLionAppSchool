package kr.co.lion.androidproject3memoapp

class Tools {
}

// Fragment 이름
enum class FragmentName(var str: String) {
    MAIN_FRAGMENT("MainFragment"),
    MEMO_ADD_FRAGMENT("MemoAddFragment"),
    MEMO_READ_FRAGMENT("MemoReadFragment"),
    MEMO_MODIFY_FRAGMENT("MemoModifyFragment")
}

// MainFragment의 하위 Fragment 이름
enum class MainSubFragmentName(var str: String) {
    CALENDAR_FRAGMENT("CalendarFragment"),
    SHOW_ALL_FRAGMENT("ShowAllFragment")
}