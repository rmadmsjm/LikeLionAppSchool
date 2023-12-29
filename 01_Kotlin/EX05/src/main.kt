// 1부터 100까지 총합을 출력

fun main() {
    var num = 1
    var sum = 0
    while (num <= 100) {
        sum += num
        num++
    }
    println("sum : $sum")
}