// map : 객체를 이름을 통해 관리하는 요소
// 순서에 대한 개념 없음

fun main() {
    // ' 이름 to 객체 ' 형태로 지정함
    // 수정 불가능한 map
    val map1 = mapOf("key1" to 10, "key2" to 20, "key3" to 30)
    println("map1 : $map1")

    // 이름은 문자열 아니어도 됨
    val map2 = mapOf(10 to "문자열1", 20 to "문자열2", 30 to "문자열3")
    println("map2 : $map2")

    // 수정 가능한 map
    val map3 = mutableMapOf("key1" to 10, "key2" to 20, "key3" to 30)
    println("map3 : $map3")

    // 다양한 타입의 값을 담기 가능
    val map4 = mapOf("key1" to 10, "key2" to 11.11, "key3" to "문자열")
    println("map4 : $map4")

    // 제네릭 설정 가능
    // map은 제네릭을 설정하지 않아도 되지만, 딱 한 가지 경우에는 무조건 설정해야 함

    // 한 가지 타입의 객체를 담는 map
    // <이름으로 사용할 객체의 클래스타입, 저장할 객체의 클래스타입>
    val map5 = mapOf<String, Int>("key1" to 10, "key2" to 20, "key3" to 30)
    println("map5 : $map5")

    // 다양한 타입의 객체를 담는 map
    // <이름으로 사용할 객체의 클래스타입, 저장할 객체의 클래스타입>
    val map6 = mapOf<String, Any>("key1" to 10, "key2" to 11.11, "key3" to "문자열")
    println("map6 : $map6")

    // 텅 비어 있는 map을 만들 때는 반드시 제네릭 설정해야 함
    val map7 = mapOf<String, Int>()
    val map8 = mutableMapOf<String, Any>()

    // 관리하고 있는 객체 추출
    val map9 = mapOf("key1" to 10, "key2" to 20, "key3" to 30)
    val map10 = mutableMapOf("key1" to 10, "key2" to 20, "key3" to 30)

    // 1) get 메서드 사용
    println("map9 key1 : ${map9.get("key1")}")
    println("map10 key1 : ${map10.get("key1")}")

    // 2) [] 사용
    println("map9 key1 : ${map9["key1"]}")
    println("map10 key1 : ${map10["key1"]}")

    println("-------------------------------------------------------")

    // 관리하는 객체의 개수
    println("map1 size : ${map1.size}")
    // 관리하는 이름 가져오기
    println("map1 keys : ${map1.keys}")
    // 관리하는 객체 가져오기
    println("map1 values : ${map1.values}")

    // 이 이름으로 저장된 객체가 있는가
    val chk1 = map1.containsKey("key1")
    val chk2 = map1.containsKey("key100")
    println("chk1 : $chk1")
    println("chk2 : $chk2")

    // 지정한 객체가 저장되어 있는가
    val chk3 = map1.containsValue(10)
    val chk4 = map1.containsValue(1000)
    println("chk3 : $chk3")
    println("chk4 : $chk4")
}