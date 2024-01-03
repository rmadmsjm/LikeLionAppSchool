import java.util.Scanner

/*
동물원
동물을 관리하는 프로그램을 작성한다
동물은 총 6마리이다
호랑이는 이름, 동물 종류, 다리 개수로 구성된다
사자는 이름, 동물 종류, 털 개수로 구성된다
여우는 이름, 동물 종류, 꼬리 개수로 구성된다

호랑이는 먹는다, 달린다 라는 행동을 할 수 있다
사자는 먹는다, 염색한다 라는 행동을 할 수 있다
여우는 먹는다, 유혹한다 라는 행동을 할 수 있다

프로그램이 시작되면 호랑이 2마리, 사자 2마리, 여우 2마리 순서대로 입력을 받는다

입력 시 동물 종류는 입력 받지 않는다

모든 동물 정보의 입력이 완료되면
호랑이는 먹는다와 달린다는 행동을 하고
사자는 먹는다와 염색한다는 행동을 하고
여우는 먹는다와 유혹한다는 행동을 한다

그 이후, 모든 정보를 출력한다
 */

// step1) 출력 화면 구현
// 출력 화면 구현 시 필요한 데이터는 임의의 데이터로 설정함

// step2) 프로그램에서 필요한 기능을 정리
// 1. 동물 정보 입력
// 2. 동물 정보 출력
// 3. 먹기 기능
// 4. 달리기 기능
// 5. 염색하기 기능
// 6. 유혹하기 기능
// 7. 동물들 정보 입력
// 8. 동물들 정보 출력

// step3) step2에서 정리한 기능을 그룹으로 묶음
// 1. 동물 정보 입력 -> 호랑이, 사자, 여우
// 2. 동물 정보 출력 -> 호랑이, 사자, 여우
// 3. 먹기 기능 -> 호랑이, 사자, 여우
// 4. 달리기 기능 -> 호랑이
// 5. 염색하기 기능 -> 사자
// 6. 유혹하기 기능 -> 여우
// 7. 동물들 정보 입력 -> 동물원
// 8. 동물들 정보 출력 -> 동물원

// step4) 출력 화면과 step2, step3에서 정의한 기능을 보고 필요한 데이터를 정리
// 필요한 데이터 : 화면을 구성하기 위해 필요한 데이터, 입력받는 데이터, 발생되는 데이터, 계산되는 데이터 등
// 1. 이름
// 2. 종류
// 3. 다리 개수
// 4. 털 개수
// 5. 꼬리 개수

// step5) step4에서 정리한 변수들을 그룹으로 묶음
// 1. 이름 -> 호랑이, 사자, 여우
// 2. 종류 -> 호랑이, 사자, 여우
// 3. 다리 개수 -> 호랑이
// 4. 털 개수 -> 사자
// 5. 꼬리 개수 -> 여우

// step6) 클래스 정의
// 클래스 내부에 변수와 메서드 작성
// 메서드의 코드는 아직 작성하지 않음

// step7) 메서드 내부의 코드 작성 및 테스트

// step8) main에서 필요한 만큼 객체를 생성하고 메서드를 호출하여 프로그램 완료

// 📌 복습 : Zoo 클래스 구성 및 객체 생성, Animal 클래스 입출력, 각 동물 클래스 입출력 내부 코드

fun main() {
    val zoo = Zoo()

    zoo.inputAnimalInfo()
    zoo.doAnimal()
    zoo.printAnimalInfo()
}

class Zoo {
    val scanner = Scanner(System.`in`)

    val tiger1 = Tiger()
    val tiger2 = Tiger()
    val lion1 = Lion()
    val lion2 = Lion()
    val fox1 = Fox()
    val fox2 = Fox()

    fun inputAnimalInfo() {
        tiger1.inputTigerInfo(scanner)
        tiger2.inputTigerInfo(scanner)
        lion1.inputLionInfo(scanner)
        lion2.inputLionInfo(scanner)
        fox1.inputFoxInfo(scanner)
        fox2.inputFoxInfo(scanner)
    }
    
    fun printAnimalInfo() {
        tiger1.printTigerInfo()
        tiger2.printTigerInfo()
        lion1.printLionInfo()
        lion2.printLionInfo()
        fox1.printFoxInfo()
        fox2.printFoxInfo()
    }

    fun doAnimal() {
        tiger1.eat()
        tiger1.run()
        tiger2.eat()
        tiger2.run()
        lion1.eat()
        lion1.dye()
        lion2.eat()
        lion2.dye()
        fox1.eat()
        fox1.tempt()
        fox2.eat()
        fox2.tempt()
    }
}

open class Animal(var type:String) {
    var name = ""

    fun eat() {
        println()
        println("${type} ${name}(이)가 먹는다")
    }
    
    fun inputName(scanner: Scanner) {
        print("이름 : ")
        name = scanner.next()
    }

    fun printName() {
        println()
        println("이름 : $name")
        println("종류 : $type")
    }
}

class Tiger : Animal("호랑이") {
    var leg = 0

    fun run() {
        println("${type} ${name}(이)가 뛴다")
    }
    
    fun inputTigerInfo(scanner: Scanner) {
        inputName(scanner)
        print("다리 개수 : ")
        leg = scanner.nextInt()
    }

    fun printTigerInfo() {
        printName()
        println("다리 개수 : $leg")
    }
}

class Lion : Animal("사자") {
    var fur = 0

    fun dye() {
        println("${type} ${name}(이)가 염색한다")
    }
    
    fun inputLionInfo(scanner: Scanner) {
        inputName(scanner)
        print("털 개수 : ")
        fur = scanner.nextInt()
    }

    fun printLionInfo() {
        printName()
        println("다리 개수 : $fur")
    }
}

class Fox : Animal("여우") {
    var tail = 0

    fun tempt() {
        println("${type} ${name}(이)가 유혹한다")
    }
    
    fun inputFoxInfo(scanner: Scanner) {
        inputName(scanner)
        print("꼬리 개수 : ")
        tail = scanner.nextInt()
    }

    fun printFoxInfo() {
        printName()
        println("다리 개수 : $tail")
    }
}