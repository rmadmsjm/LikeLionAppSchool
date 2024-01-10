// forEach : 컬렉션들이 관리하는 객체들을 하나씩 추루하면서 반복할 수 있도록 제공되는 메서드


fun main() {
    // 배열
    val array1 = arrayOf(10, 20, 30, 40, 50)

    // for
    // 배열이 관리하는 객체의 수만큼 반복하면서 반복 번째의 객체를 v1에 담음
    for(v1 in array1) {
        println("array1 v1 : $v1")
    }

    println("--------------------------------")

    // 배열이 관리하는 데이터의 개수만큼 람다식을 반복함
    // it에는 반복 번째의 데이터가 들어옴
    array1.forEach {
        println("array1 it : $it")
    }

    println("--------------------------------")

    // list
    val list1 = listOf(10, 20, 30, 40, 50)

    list1.forEach {
        println("list1 it : $it")
    }

    println("-------------------------------")

    // map
    val map1 = mapOf("a1" to 10, "a2" to 20)

    map1.forEach {
        println("map1 key : ${it.key}")
        println("map1 value : ${it.value}")
    }
}