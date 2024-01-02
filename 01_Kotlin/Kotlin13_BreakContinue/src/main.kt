// break : 가장 가까운 반복문의 수행을 중단시킴
// continue : 다음 반복으로 넘어감

fun main() {
    for(v1 in 1..10) {
        if(v1 > 5) {
            break
        }
        println("v1 : $v1")
    }

    for(v2 in 1..10) {
        if(v2 % 2 == 0) {
            continue
        }
        println("v2 : $v2")
    }
}