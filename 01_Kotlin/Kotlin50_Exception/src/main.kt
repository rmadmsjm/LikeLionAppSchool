// 예외 : 대처가 가능한 오류를 의미
// Java에서는 예외 상황에 대한 것을 클래스로 만들어 제공함
// 예외가 발생하면 발생된 예외와 관련된 클래스의 객체를 생성해 개발자에게 전달함
// 이 객체에는 오류와 관련된 정보가 담겨 있음

// try : 개발자가 구현하는 코드 부분
// catch : try에서 예외가 발생했을 대 동작하는 코드
// try에서 예외가 발생하면 try 부분의 수행은 중단되고 catch로 넘어감

// 예외가 발생하면 VM은 프로그램을 강제 종료함
// 예외 처리의 목적은 예외 발생 시 프로그램이 강제 종료되지 않도록 하고 catch에 작성한 코드가 동작할 수 있도록 함에 있음

fun main() {

    try {
        val a1 = 10/0

        // val str:String? = null
        // println(str!!.length)

        // val str2 = "안녕하세요"
        // val a2:Int = str2.toInt()
    } catch (e:Exception) {
        // 10 을 0으로 나눴을 때의 예외 처리
        // e.printStackTrace()
        println("수학 오류 발생")
    } catch (e:NullPointerException) {
        // null을 가진 변수를 통해 객체에 접근하려고 했을
        println("널 접근 오류 발생")
    } catch (e:NumberFormatException) {
        // 숫자로 변환할 수 없는 문자열을 숫자로 변환하고
        println("숫자 양식 오류 발생")
    } catch(e:Exception){
        // 그 밖의 모든 예외에 대한 처리
        println("그 밖의 오류가 발행하였습니다")
    }
    println("이 부분이 수행 됨?")
}