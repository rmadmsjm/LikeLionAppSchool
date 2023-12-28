// if문 : 주어진 조건식의 결과가 true인 경우에만 관리하는 코드가 수행됨
// {}로 묶은 부분을 관리함

fun main() {
    val a1 = 10
    
    // if
    // 조건식의 결과가 true이므로 if문이 관리하는 코드가 수행됨
    if(a1 == 10) {
        println("a1은 10")
    }

    // 조건식의 결과가 false이므로 if문이 관리하는 코드가 수행되지 않음
    if(a1 == 20) {
        println("a1은 20")
    }

    // if-else
    // 조건식의 결과가 true이므로 if문이 관리하는 코드가 수행되고 else가 관리하는 코드는 수행되지 않음
    if(a1 == 10) {
        println("a1은 10")
    } else {
        println("a1은 10이 아님")
    }

    // 조건식의 결과가 false이므로 if문이 관리하는 코드가 수행되지 않고 else가 관리하는 코드가 수행됨
    if(a1 == 20) {
        println("a1은 20")
    } else {
        println("a1은 20이 아님")
    }
    
    // if-elseif-else
    // 위에서부터 아래 방향으로 조건식을 수행하다가 true인 부분을 수행하고 밖으로 나감
    // 만약 모든 조건식의 결곽가 false일 경우 else가 있으면 else부분을 수행하고
    // else가 없다면 아무것도 수행하지 않음
    if(a1 == 0) {
        println("a1은 0")
    } else if(a1 == 10) {
        println("a1은 10")
    } else if(a1 == 20) {
        println("a2는 20")
    } else {
        println("a1은 0, 10, 20이 아님")
    }

    // 조건식의 결과에 따라 변수의 값을 저장
    var a4 = ""
    var a5 = 10

    if(a5 == 10) {
        a4 = "10"
    } else {
        a4 = "10이 아님"
    }

    println("a4 : $a4")

    // 위의 코드는 아래와 같이 작성 가능
    // if문에 있는 조건식이 true이면 첫 번째 값이 반환되어 변수에 저장되고
    // false라면 두 번째 값이 반환되어 변수에 저장됨
    var a6 = if(a5 == 10) "10" else "10아님"
    println("a6 : $a6")

    // if문이 관리하는 코드가 다수의 줄로 구성되어 있다면...
    var a7 = ""

    if(a5 == 10) {
        var r1 = 10 + 20
        a7 = "r1은 $r1 입니다"
    } else {
        var r1 = 100 + 200
        a7 = "r1은 $r1 입니다"
    }
    println("a7 : $a7")

    // 위의 코드는 아래와 같이 작성 가능
    // 조건식의 결과가 true라면 if가 관리하는 코드 중 제일 마지막에 작성한 값이 변수에 저장되고
    // 변수에 저장되고 false라면 else가 관리하는 코드 중 제일 마지막에 작성한 값이 저장됨
    // else if는 지원하지 않음
    var a9 = if(a5 == 10) {
        var r1 = 10 + 20
        "r1은 $r1 입니다"
    } else {
        var r1 = 100 + 200
        "r1은 $r1 입니다"
    }
    println("a9 : $a9")
}