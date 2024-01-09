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
.
.
.
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
전체 정보 입력
전체 정보 출력
보고서 출력

step3) step2에서 정리한 기능을 그룹으로 묶음
주행, 후진 -> 자동차
자장가 재생 -> 승용차
물건 싣기 -> 트럭
충전 -> 전기차
정보 입력 -> 자동차
정보 출력 -> 자동차
전체 정보 입력 -> 공장
전체 정보 출력 -> 공장
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
    // 공장 객체 생성
    val factory = Factory()
    // 전체 자동차 정보 입력
    factory.inputInfo()
    // 전체 자동차 정보 출력
    factory.printInfo()
    // 전체 자동차 기능 출력
    factory.printDoVehicle()
    // 자동차 보고서 출력
    factory.printReport()
}

// 공장 클래스
class Factory {
    // scanner
    private val scanner = Scanner(System.`in`)

    // 자동차 객체 배열
    private val vehicleArray = arrayOf(
        Car(),
        Car(),
        Car(),
        Car(),
        Truck(),
        Truck(),
        ElectricCar(),
        ElectricCar(),
        ElectricCar(),
        ElectricCar()
    )

    // 전체 자동차 정보 입력
    fun inputInfo() {
        println("---------- 자동차 정보 입력 ----------")
        for (index in 0..9) {
            vehicleArray[index].inputInfo(scanner)
        }
    }

    // 전체 자동차 정보 출력
    fun printInfo() {
        println()
        println("---------- 자동차 정보 ----------")
        for (index in 0..9) {
            vehicleArray[index].printInfo()
        }
    }

    // 전체 자동차 기능 출력
    fun printDoVehicle() {
        println()
        println("---------- 자동차 기능 ----------")
        for (index in 0..9) {
            vehicleArray[index].doVehicle()
        }
    }

    // 자동차 보고서 출력
    fun printReport() {
        var totalCar = 0
        var totalTruck = 0
        var totalElectricCar = 0
        var totalTire = 0
        var totalSeat = 0
        var totalCarSeat = 0
        var totalLoadage = 0
        var totalBatteryCapacity = 0

        for (index in 0..9) {
            // 배열에서 현재 인덱스의 차량 가져오기
            val vehicle = vehicleArray[index]

            // 총 타이어 개수와 총 좌석 개수 누적
            totalTire += vehicle.tire
            totalSeat += vehicle.seat

            // 자동차 타입에 따라 분기
            if (vehicle is Car) {
                // 총 승용차의 수와 총 카시트 개수 누적
                totalCar++
                totalCarSeat += vehicle.carSeat
            } else if (vehicle is Truck) {
                // 총 트럭의 수와 총 적재량 누적
                totalTruck++
                totalLoadage += vehicle.loadage
            } else if (vehicle is ElectricCar) {
                // 총 전기차의 수와 총 배터리 용량 누적
                totalElectricCar++
                totalBatteryCapacity += vehicle.batteryCapacity
            }
        }

        println()
        println("---------- 자동차 보고서 ----------")
        println("승용차 : ${totalCar}대")
        println("트력 : ${totalTruck}대")
        println("전기 자동차 : ${totalElectricCar}대")
        println("총 타이어의 개수 : ${totalTire}개")
        println("총 좌석의 개수 : ${totalSeat}개")
        println("총 카시트의 개수 : ${totalCarSeat}개")
        println("총 적재량 개수 : ${totalLoadage}kg")
        println("총 배터리 용량 : ${totalBatteryCapacity}mAh")
    }
}

// 자동차 클래스
open class Vehicle(var type:String) {
    // 타이어 개수
    var tire = 0
    // 좌석 개수
    var seat = 0

    // 자동차 정보 입력
    open fun inputInfo(scanner: Scanner) {
        println()
        print("${type}의 타이어 개수 : ")
        tire = scanner.nextInt()
        print("${type}의 좌석 개수 : ")
        seat = scanner.nextInt()
    }

    // 자동차 정보 출력
    open fun printInfo() {
        print("${type} ")
        print("타이어 ${tire}개, ")
        print("좌석 ${seat}개, ")
    }

    // 자동차 기능
    open fun doVehicle() {
        println("${type}(이)가 주행한다")
        println("${type}(이)가 후진한다")
    }
}

// 승용차 클래스
class Car : Vehicle("승용차") {
    // 카시트 개수
    var carSeat = 0

    // 승용차 기능
    override fun doVehicle() {
        super.doVehicle()
        println("${type}가 자장가를 재생한다")
    }

    // 승용차 정보 입력
    override fun inputInfo(scanner: Scanner) {
        super.inputInfo(scanner)
        print("${type}의 카시트 개수 : ")
        carSeat = scanner.nextInt()
    }

    // 승용차 정보 출력
    override fun printInfo() {
        super.printInfo()
        println("카시트 ${carSeat}개")
    }
}

// 트럭 클래스
class Truck : Vehicle("트럭") {
    // 적재량
    var loadage = 0

    // 트럭 기능
    override fun doVehicle() {
        super.doVehicle()
        println("${type}에 물건을 싣는다")
    }

    // 트럭 정보 입력
    override fun inputInfo(scanner: Scanner) {
        super.inputInfo(scanner)
        print("${type}의 적재량 : ")
        loadage = scanner.nextInt()
    }

    // 트럭 정보 출력
    override fun printInfo() {
        super.printInfo()
        println("적재량 ${loadage}kg")
    }
}

// 전기차 클래스
class ElectricCar : Vehicle("전기차") {
    // 배터리 용량
    var batteryCapacity = 0

    // 전기차 기능
    override fun doVehicle() {
        super.doVehicle()
        println("${type}를 충전한다")
    }

    // 전기차 정보 입력
    override fun inputInfo(scanner: Scanner) {
        super.inputInfo(scanner)
        print("${type}의 배터리 용량 : ")
        batteryCapacity = scanner.nextInt()
    }

    // 전기차 정보 출력
    override fun printInfo() {
        super.printInfo()
        println("배터리 용량 ${batteryCapacity}mAh")
    }
}