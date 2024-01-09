import java.util.Scanner

/*
문제) 문제 순서에 맞게 코드를 작성하여 구현해 주세요
✔️ 객체지향에 나오는 개념들을 최대한 많이 적용하여 작업해 주세요
✔️ 각 타입별 자동차의 수는 자유롭게 정해주세요

1. 자동차를 생산하는 공장을 구현한다
2. 자동차는 총 10대를 생산한다
3. 자동차 타입은 다음과 같다
    1) 승용차 타입
        구성요소 : 타입, 타이어 개수, 좌석 개수, 카시트 개수
        기능 : 주행한다, 후진한다, 자장가를 재생한다

    2) 트럭 타입
        구성요소 : 타입, 타이어 개수, 좌석 개수, 적재량
        기능 : 주행한다, 후진한다. 물건을 싣는다

    3) 전기 자동차
        구성요소 : 타입, 타이어 개수, 좌석 개수, 배터리 용량
        기능 : 주행한다, 후진한다, 충전한다.
4. 프로그램이 실행되면 자동차의 정보를 입력받는다 (단, 타입은 입력 받지 않는다)
5. 입력이 완료되면 모든 자동자의 정보를 출력한다
6. 출력 후 다음과 같은 보고서를 출력한다
    승용차 : 0대
    트럭 : 0대
    전기 자동차 : 0대
    총 타이어의 개수 : 0개
    총 좌석의 개수 : 0개
    총 카시트의 개수 : 0개
    총 적재량 : 0000kg
    총 배터리 용량 : 0000mAh
 */

/*
step1) 출력 화면 구현
승용차, 타이어 2개, 좌석 4개, 카시트 0개
승용차가 주행한다
승용차가 후진한다
승용차가 자장가를 재생한다
.
.
.
승용차 : n대
트럭 : n대
전기 자동차 : n대
총 타이어의 개수 : n개
총 좌석의 개수 : n개
총 카시트의 개수 : n개
총 적재량 : nkg
총 배터리 용량 : nmAh

step2) 프로그램에서 필요한 기능을 정리
주행, 후진
자장가 재생
물건 싣기
충전
정보 입력
정보 출력
보고서 출력

step3) step2에서 정리한 기능을 그룹으로 묶음
주행, 후진 -> 자동차
자장가 재생 -> 승용차
물건 싣기 -> 트럭
충전 -> 전기차
정보 입력 -> 공장
정보 출력 -> 공장
보고서 출력 -> 공장

step4) 출력 화면과 step2, step3에서 정의한 기능을 보고 필요한 데이터를 정리
타입, 타이어 개수, 좌석 개수
카시트 개수
적재량
배터리 용량

step5) step4에서 정리한 변수들을 그룹으로 묶음
타입, 타이어 개수, 좌석 개수 -> 자동차
카시트 개수 -> 승용차
적재량 -> 트럭
배터리 용량 -> 전기차

step6) 클래스 정의

step7) 메서드 내부의 코드 작성 및 테스트

step8) main에서 필요한 만큼 객체를 생성하고 메서드를 호출하여 프로그램 완료
 */

fun main() {
    val factory = Factory()
    factory.inputInfo()
    factory.printInfo()
    factory.report()
}

class Factory {
    private val scanner = Scanner(System.`in`)

    private val vehicle1 = Car()
    private val vehicle2 = Car()
    private val vehicle3 = Car()
    private val vehicle4 = Car()
    private val vehicle5 = Truck()
    private val vehicle6 = Truck()
    private val vehicle7 = ElectricCar()
    private val vehicle8 = ElectricCar()
    private val vehicle9 = ElectricCar()
    private val vehicle10 = ElectricCar()

    fun inputInfo() {
        vehicle1.inputInfo(scanner)
        vehicle2.inputInfo(scanner)
        vehicle3.inputInfo(scanner)
        vehicle4.inputInfo(scanner)
        vehicle5.inputInfo(scanner)
        vehicle6.inputInfo(scanner)
        vehicle7.inputInfo(scanner)
        vehicle8.inputInfo(scanner)
        vehicle9.inputInfo(scanner)
        vehicle10.inputInfo(scanner)
    }

    fun printInfo() {
        vehicle1.printInfo()
        vehicle2.printInfo()
        vehicle3.printInfo()
        vehicle4.printInfo()
        vehicle5.printInfo()
        vehicle6.printInfo()
        vehicle7.printInfo()
        vehicle8.printInfo()
        vehicle9.printInfo()
        vehicle10.printInfo()
    }

    fun report() {
        var totalCar = 0
        var totalTruck = 0
        var totalElectricCar = 0
        var totalTire = 0
        var totalSeat = 0
        var totalCarSeat = 0
        var totalLoadage = 0
        var totalBatteryCapacity = 0

        println("승용차 : ${totalCar}대")
        println("트력 : ${totalCar}대")
        println("전기 자동차 : ${totalCar}대")
        println("총 타이어의 개수 : ${totalCar}개")
        println("총 좌석의 개수 : ${totalCar}개")
        println("총 카시트의 개수 : ${totalCar}개")
        println("총 적재량 개수 : ${totalCar}kg")
        println("총 배터리 용량 : ${totalCar}mAh")
    }
}

open class Vehicle(var type:String) {
    var tire = 0
    var seat = 0

    open fun inputInfo(scanner: Scanner) {
        println()
        print("${type}의 타이어 개수 : ")
        tire = scanner.nextInt()
        print("${type}의 좌석 개수 : ")
        seat = scanner.nextInt()
    }

    open fun printInfo() {
        println()
        println("${type}의 타이어 개수 : ${tire}개")
        println("${type}의 타이어 개수 : ${seat}개")
    }

    open fun doVehicle() {
        println()
        println("${type}(이)가 주행한다")
        println("${type}(이)가 후진한다")
    }
}

class Car : Vehicle("승용차") {
    var carSeat = 0

    override fun doVehicle() {
        super.doVehicle()
        println("${type}가 자장가를 재생한다")
    }

    override fun inputInfo(scanner: Scanner) {
        super.inputInfo(scanner)
        print("${type}의 카시트 개수 : ")
        carSeat = scanner.nextInt()
    }

    override fun printInfo() {
        super.printInfo()
        println("${type}의 카시트 개수 : ${carSeat}개")
    }
}

class Truck : Vehicle("트럭") {
    var loadage = 0

    override fun doVehicle() {
        super.doVehicle()
        println("${type}에 물건을 싣는다")
    }

    override fun inputInfo(scanner: Scanner) {
        super.inputInfo(scanner)
        print("${type}의 적재량 : ")
        loadage = scanner.nextInt()
    }

    override fun printInfo() {
        super.printInfo()
        println("${type}의 적재량 : ${loadage}kg")
    }
}

class ElectricCar : Vehicle("전기차") {
    var batteryCapacity = 0

    override fun doVehicle() {
        super.doVehicle()
        println("${type}을 충전한다")
    }

    override fun inputInfo(scanner: Scanner) {
        super.inputInfo(scanner)
        print("${type}의 배터리 용량 : ")
        batteryCapacity = scanner.nextInt()
    }

    override fun printInfo() {
        super.printInfo()
        println("${type}의 배터리 용량 : ${batteryCapacity}mAh")
    }
}