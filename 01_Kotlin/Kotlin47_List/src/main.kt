// List : 0부터 1씩 증가하는 순서값을 가지고 객체를 관리함
// 배열과 다르게 관리하는 객체의 수를 늘리거나 줄일 수 있음
// Kotlin은 ArrayList 객체로 생성됨
// Kotlin은 list를 불변형과 가변형으로 제공함
// 불변형은 추가, 수정, 삭제 등이 불가능함
// 불변형은 가변형에 비해 메서드나 프로퍼티의 수가 더 적어 메모리를 적게 차지하고 데이터를 가져오는데 속도가 더 빠름 (차이 느끼지 못 함)

fun main() {
    // 리스트 생성
    // 불변형 리스트
    // 리스트 생성 이후 값의 추가, 수정, 삽입, 삭제 등 불가능
    val list1 = listOf(10, 20, 30, 40, 50)
    println("list1 : $list1")

    val list2 = listOf("문자열1", "문자열2", "문자열3")
    println("list2 : $list2")

    // 타입이 달라도 됨
    val list3 = listOf(100, 11.11, "문자열", true)
    println("list3 : $list3")

    // 수정 가능한 리스트는 ArrayList로 생성됨
    // 텅 비어 있는 수정 가능한 리스트 생성 : 나중에 객체 추가 가능
    val list4 = mutableListOf<Any>()
    // 생성시 관리할 객체를 지정할 수 있다.
    val list5 = mutableListOf("문자열1", "문자열2", "문자열3")
    println("list4 : $list4")
    println("list5 : $list5")

    // 비어있는 수정 불가능한 리스트
    val list6 = emptyList<Int>()
    println("list6 : $list6")

    // null이 있을 경우
    // null 을 포함한다.
    val list7 = listOf(10, 20, null, 40, null, 60, 70)
    // null 을 포함하지 않는다
    val list8 = listOfNotNull(10, 20, null, 40, null, 60, 70)

    println("list7 : $list7")
    println("list8 : $list8")

    // for 문 사용
    for(item in list1){
        println("item : $item")
    }

    // 관리하는 객체의 개수
    println("list size : ${list1.size}")

    // 관리하는 객체에 접근
    // [ ] 연산자를 통해 순서값(0부터 1씩 증가)를 지정하여 접근함
    println("list1 0 : ${list1[0]}")
    println("list1 1 : ${list1[1]}")

    val list9 = listOf(10, 20, 30, 10, 20, 30)

    // 지정한 객체가 앞에서부터 몇 번째 있는지 반환
    val index1 = list9.indexOf(20)
    println("index1 : $index1")

    // 지정한 객체를 뒤에서부터 찾고, 그 객체가 앞에서부터 몇 번째에 있는지 반환
    val index2 = list9.lastIndexOf(20)
    println("index2 : $index2")

    // indexOf, lastIndexOf 모두 없는 것을 지정하면 -1을 반환
    val index3 = list9.indexOf(100)
    println("index3 : $index3")

    // 일부를 발췌하여 새로운 리스트 생성
    // 순서값 1 ~ (3-1) 까지
    val list10 = list9.subList(1, 3)
    println("list10 : $list10")

    println("-------------------------------------------------------")

    // 불변형
    val list20 = listOf(10, 20, 30)
    // 가변형
    val list21 = mutableListOf(10, 20, 30)

    // 객체를 추가
    // 리스트 뒤에 추가
    list21.add(40)
    list21.add(50)
    list21.addAll(listOf(60, 70, 80, 90, 100))
    println("list21 : $list21")

    // 수정 불가능한 리스트에 추가
    // 추가, 수정, 삭제, 삽입 등과 관련된 메서드 없음
    // list20.add(40)

    // 삽입
    // add 메서드를 이용할 때 위치를 지정하면 그 위체에 삽입
    // add(순서값, 객체) : 순서값에 해당하는 위치에 객체를 삽입하고 그 이후의 객체들은 뒤로 밀림
    list21.add(1, 100)
    println("list21 : $list21")

    // 삽입할 대수가 다수인 경우 addAll 사용
    list21.addAll(3, listOf(2000, 3000, 4000, 5000))
    println("list21 : $list21")

    // 값 수정
    // 두 번째 값을 1000으로 덮어 씌우기
    list21.set(1, 1000)
    println("list21 : $list21")

    list21[1] = 10000
    println("list21 : $list21")

    // 제거
    // 지정한 값을 제거함
    list21.remove(10000)
    println("list21 : $list21")

    // 없는 객체를 제거
    // 오류 발생하지 않고, 아무 일도 안 일어남
    list21.remove(50000)
    println("list21 : $list21")

    // 여러 객체를 지정하여 객체
    list21.removeAll(listOf(10, 30, 50))
    println("list21 : $list21")

    // 위치를 지정하여 제거
    // 두 번째 객체 제거
    list21.removeAt(1)
    println("list21 : $list21")

    // 모두 삭제
    list21.clear()
    println("list21 : $list21")

    val list100 = listOf(10, 20, 30, 40, 50)

    // list 안에 저장되어 있는 객체를 추철하여 새로운 mutable list 생성
    val list200 = list100.toMutableList()
    list200.add(60)
    println("list200 : $list200")

    // mutable list 안에 저장되어 있는 객체를 추출하여 새로운 list를 생성
    val list300 = list200.toList()
    // list300.add(70)
    println("list300 : $list300")
}