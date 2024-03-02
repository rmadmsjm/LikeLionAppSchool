package kr.co.lion.androidproject_memo2

class Tool {
}

enum class FragmentName(var str: String) {
    MAIN_FRAGMENT("MainFragment"),
    MEMO_ADD_FRAGMENT("MemoAddFragment"),
    MEMO_READ_FRAGMENT("MemoReadFragment")
}

enum class FragmentMainName(var str: String) {
    CALENDAR_FRAGMENT("CalendarFragment"),
    SHOW_ALL_FRAGMENT("ShowAllFragment")
}