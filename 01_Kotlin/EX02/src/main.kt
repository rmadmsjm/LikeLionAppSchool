import java.util.Scanner

/*
키보드로부터 입력을 받아 짝수라면 "짝수입니다" 출력
홀수라면 "홀수입니다" 출력
 */

fun main() {
    checkNum(inputNum())
}

fun inputNum() : Int {
    val scanner = Scanner(System.`in`)
    print("숫자를 입력하세요 : ")
    val num = scanner.nextInt()
    return num
}

fun checkNum(num:Int){
    if(num%2==0){
        println("짝수입니다")
    } else {
        println("홀수입니다")
    }
}