// 1부터 100까지의 홀수의 합을 출력

fun main() {
    var num = 1
    var sum = 0
    while(num <= 100) {
        if(num % 2 != 0) {
            sum += num
        }
        num++
    }
    println("sum : $sum")
}