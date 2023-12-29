import java.util.Scanner

/*
주민등록 번호를 입력받아 다음과 같이 출력한다.
주민등록 번호는 - 없이 13자리의 숫자를 입력받는다.
처음 두자리는 생년을 의미한다.
세번째 네번째는 생월을 의미한다.
다섯번째 여섯번째는 생일을 의미한다.
일곱번째 숫자는 다음과 같다.
9 : 1800년대생 남성
0 : 1800년대생 여성
1 : 1900년대생 남성
2 : 1900년대생 여성
3 : 2000년대생 남성
4 : 2000년대생 여성
5 : 1900년대생 외국인 남성
6 : 1900년대생 외국인 여성
7 : 2000년대생 외국인 남성
8 : 2000년대생 외국인 여성

출력은 다음과 같이 한다

1999년 10월 21에 태어난 남성입니다
 */

fun main() {
    printString(inputNum())
}

fun inputNum() : Long {
    var scanner = Scanner(System.`in`)
    print("주민번호 13자리를 '-' 없이 입력해주세요 : ")
    var num = scanner.nextLong()
    return num
}

fun printString(num:Long) {
    var year = num / 100_000_000_000L
    var monthDay = num % 100_000_000_000L
    var month = monthDay / 1_000_000_000L
    var day = (monthDay % 1_000_000_000L) / 10_000_000L
    var gender = (num % 10_000_000L) / 1_000_000L

    var yearCheck = if (year < 10) {
        "0${year}"
    } else {
        "$year"
    }

    var brithYear = when(gender) {
        9L, 0L -> "18${yearCheck}"
        1L, 2L, 5L, 6L -> "19${yearCheck}"
        3L, 4L, 7L, 8L -> "20${yearCheck}"
        else -> ""
    }

    var genderCheck = when(gender) {
        9L, 1L, 3L, 5L, 7L -> "남성"
        0L, 2L, 4L, 6L, 8L -> "여성"
        else -> ""
    }

    println("${brithYear}년 ${month}월 ${day}일에 태어난 ${genderCheck}입니다")
}

// ----------강사님 설명----------
// step1) 기능 정리
// 1. 주민등록번호를 입력 받는 기능
// 2. 생년을 추출
// 3. 생월 추출
// 4. 생일 추출
// 5. 일곱 번째 숫자 추출
// 6. 몇년대 생인지 파악
// 7. 성별 파악
// 8. 출력

// step2) 각 기능별 함수를 구현
// 아직 함수 내부의 코드는 작성하지 않음

// step3) 각 함수 내부 코드를 구현
// 이때, 함수 하나의 구현이 끝나면 의도한대로 동작하는지 반드시 테스트

// step4) 프로그램이 동작하도록 main 함수에서 각 함수들을 순서에 맞게 호출

/*
fun main() {
    // 주민등록번호 입력
    val juminNumber = inputJuminNumber()
    // 생년 추출
    val year = getBirthYear(juminNumber)
    // 생월 추출
    val month = getBirthMonth(juminNumber)
    // 생일 추출
    val date = getBirthDate(juminNumber)
    // 일곱 번째 숫자 추출
    val sevenNumber = getSevenNumber(juminNumber)
    // 연대
    val ages = getBirthAges(sevenNumber)
    // 성별
    val gender = getGender(sevenNumber)
    // 출력
    printResult(ages+year, month, date, gender)
}

// 1. 주민등록번호를 입력 받는 기능
fun inputJuminNumber():Long {
    val scanner = Scanner(System.`in`)

    // 주민등록번호를 입력 받기
    print("주민등록번호 13자리를 입력해주세요 (- 빼고) : ")
    val juminNumber = scanner.nextLong()

    // 입력 받은 주민등록번호 반환
    return juminNumber
}

// 2. 생년을 추출
fun getBirthYear(juminNumber:Long):Long {
    val birthYear = juminNumber / 100_000_000_000
    return birthYear
}

// 3. 생월 추출
fun getBirthMonth(juminNumber:Long):Long {
    val BirthMonthNum = juminNumber / 1_000_000_000
    val BirthMonth = BirthMonthNum % 100
    return BirthMonth
}

// 4. 생일 추출
fun getBirthDate(juminNumber:Long):Long {
    val BirthDateNum = juminNumber / 10_000_000
    val BirthDate = BirthDateNum % 100
    return BirthDate
}

// 5. 일곱 번째 숫자 추출
fun getSevenNumber(juminNumber:Long):Long {
    val sevenNumber = juminNumber / 1_000_000
    val sevenNum = sevenNumber % 10
    return sevenNum
}

// 6. 몇년대 생인지 파악
fun getBirthAges(sevenNumber:Long):Long = when(sevenNumber) {
    9L, 0L -> 1800
    1L, 2L, 5L, 6L -> 1900
    3L, 4L, 7L, 8L -> 2000
    else -> 0
}

// 7. 성별 파악
fun getGender(sevenNum:Long):String {
    // 짝수=여자, 홀수=남자 -> 2로 나눈 나머지
    if(sevenNum % 2 == 0L) {
        return "여성"
    } else {
        return "남성"
    }
}

// 8. 출력
fun printResult(year:Long, month:Long, date:Long, gender:String) {
    println("${year}년 ${month}월 ${date}일에 태어난 ${gender}입니다")
}
 */