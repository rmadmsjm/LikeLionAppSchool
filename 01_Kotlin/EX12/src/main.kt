import java.util.Scanner

/*
동물원에는 여러 동물이 있다
동물은 사자, 고양이, 새가 있다
사자는 다리가 4개이고 '어흥'이라는 소리를 낸다
고양이는 다리가 4개이고 '야옹'이라는 소리를 낸다
새는 다리가 2개이고 '짹짹'이라는 소리를 낸다
프로그램이 실행되면 3가지 동물에 관련된 정보를 입력 받는다
입력 받는 정보는 동물의 종류와 다리의 개수, 내는 소리이다
입력이 완료되면 각 동물의 정보를 모두 출력하고 동물의 다리 개수 총합을 구해 출력한다
 */

/*
동물 정보 입력 - 종류, 다리 개수, 소리 -> 동물
다리 개수 총합 구하기 -> 동물들
정보 출력 -> 동물
다리 개수 총합 출력 -> 동물들
 */

fun main() {
    val animalMangerClass = AnimalMangerClass()
    animalMangerClass.inputAnimalInfo()
    animalMangerClass.printAnimalInfo()
    animalMangerClass.legTotalNum()
    animalMangerClass.printlegTotal()
}

// 동물 정보 class
class AnimalInfo {
    var type = ""
    var leg = 0
    var sound = ""

    // 동물 정보 입력
    fun inputAnimalInfo(scanner: Scanner) {
        print("동물의 종류를 입력하세요 : ")
        type = scanner.next()
        print("동물의 다리 개수를 입력하세요 : ")
        leg = scanner.nextInt()
        print("동물의 소리를 입력하세요 : ")
        sound = scanner.next()
        println()
    }

    // 동물 정보 출력
    fun printAnimalInfo() {
        println("종류 : $type")
        println("다리 개수 : $leg")
        println("소리 : $sound")
        println()
    }

}

// 동물들 class
class AnimalMangerClass {
    var legTotal = 0
    val animal1 = AnimalInfo()
    val animal2 = AnimalInfo()
    val animal3 = AnimalInfo()

    // 동물 정보 입력
    fun inputAnimalInfo() {
        val scanner = Scanner(System.`in`)
        animal1.inputAnimalInfo(scanner)
        animal2.inputAnimalInfo(scanner)
        animal3.inputAnimalInfo(scanner)
    }

    // 동물 정보 출력
    fun printAnimalInfo() {
        animal1.printAnimalInfo()
        animal2.printAnimalInfo()
        animal3.printAnimalInfo()
    }

    // 동물 다리 총합 구하기
    fun legTotalNum() {
        legTotal = animal1.leg + animal2.leg + animal3.leg
    }

    // 동물 다리 총합 출력
    fun printlegTotal() {
        println("동물의 다리 총합은 $legTotal")
    }
}


// ----------강사님 설명----------
// step1) 출력 화면을 구현
// 출력 화면 구현 시 필요한 데이터는 임의의 데이터로 설정함

// step2) 프로그램에서 필요한 기능을 정리

// step3) step2에서 정리한 기능을 그룹으로 묶음

// step4) 출력 화면과 step2, step3에서 정의한 기능을 보고 필요한 데이터를 정리
// 필요한 데이터 : 화면을 구성하기 위해 필요한 데이터, 입력받는 데이터, 발생되는 데이터, 계산되는 데이터 등

// step5) step4에서 정리한 변수들을 그룹으로 묶음

// step6) 클래스 정의
// 클래스 내부에 변수와 메서드 작성
// 메서드의 코드는 아직 작성하지 않음

// step7) 메서드 내부의 코드 작성 및 테스트

// step8) main에서 필요한 만큼 객체를 생성하고 메서드를 호출하여 프로그램 완료

// ⚠️주의⚠️  main에서 절대로 객체가 관리하는 변수에 직접 접근하지 말 것
