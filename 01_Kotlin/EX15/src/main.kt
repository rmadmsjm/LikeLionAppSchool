import java.util.*

/*
학생 정보를 관리하는 프로그램을 작성한다
학생은 총 6명이다
농구부 학생은 학생의 이름, 부소속, 총 슛개수로 구성된다
축구부 학생은 학생의 이름, 부소속, 총 퇴장 개수로 구성된다
야구부 학생은 학생의 이름, 부소속, 홈런 개수로 구성된다

농구부 학생은 달린다, 슛을 쏜다 라는 행동을 할 수 있다
축구부 학생은 달린다, 퇴장 당한다 라는 행동을 할 수 있다
야구부 학생은 달린다, 홈런을 친디 라는 행동을 할 수 있다

프로그램이 시작되면 농구부 학생 2명, 축구부 학생 2명, 야구부 학생 2명 순서대로 입력을 받는다

입력 시 부소속은 입력 받지 않는다

모든 학생의 정보의 입력이 완료되면
농구부의 학생들에 대한 달린다와 슛을 쏜다라는 행동을 하게 하고
축구부 학생들에 대한 달린다와 퇴장 당한다라는 행동을 하게 하고
야구부 학생들에 대한 달린다와 홈런을 친다라는 행동을 하게 한다

그 이후, 모든 학생들의 정보를 출력한다
 */

// step1) 출력 화면을 구현
// 출력 화면 구현 시 필요한 데이터는 임의의 데이터로 설정함

// step2) 프로그램에서 필요한 기능을 정리
// 1. 달리기 기능
// 2. 슛 기능
// 3. 퇴장 기능
// 4. 홈런 기능
// 5. 학생 정보 입력
// 6. 학생 정보 출력

// step3) step2에서 정리한 기능을 그룹으로 묶음
// 1. 달리기 기능 -> 농구부 학생, 축구부 학생, 야구부 학생
// 2. 슛 기능 -> 농구부 학생
// 3. 퇴장 기능 -> 축구부 학생
// 4. 홈런 기능 -> 야구부 학생
// 5. 학생들 정보 입력 -> 학생 관리
// 6. 학생들 정보 출력 -> 학생 관리
// 7. 학생 정보 입력 -> 농구부 학생, 축구부 학생, 야구부 학생
// 8. 학생 정보 출력 -> 농구부 학생, 축구부 학생, 야구부 학생

// step4) 출력 화면과 step2, step3에서 정의한 기능을 보고 필요한 데이터를 정리
// 필요한 데이터 : 화면을 구성하기 위해 필요한 데이터, 입력받는 데이터, 발생되는 데이터, 계산되는 데이터 등
// 1. 학생명
// 2. 소속명
// 3. 슛 개수
// 4. 퇴장 개수
// 5. 홈런 개수

// step5) step4에서 정리한 변수들을 그룹으로 묶음
// 1. 학생명 -> 농구부 학생, 축구부 학생, 야구부 학생
// 2. 소속명 -> 농구부 학생, 축구부 학생, 야구부 학생
// 3. 슛 개수 -> 농구부 학생
// 4. 퇴장 개수 -> 축구부 학생
// 5. 홈런 개수 -> 야구부 학생

// step6) 클래스 정의
// 클래스 내부에 변수와 메서드 작성
// 메서드의 코드는 아직 작성하지 않음

// step7) 메서드 내부의 코드 작성 및 테스트

// step8) main에서 필요한 만큼 객체를 생성하고 메서드를 호출하여 프로그램 완료

// ⚠️주의⚠️  main에서 절대로 객체가 관리하는 변수에 직접 접근하지 말 것

fun main() {
    // 학생 관리 객체 생성
    val studentManger = StudentManger()

    // 학생 정보 입력
    studentManger.inputStudentInfo()

    // 학생 행동
    studentManger.doStudent()

    // 학생 정보 출력
    studentManger.printStudentInfo()
}

// 학생 관리 클래스
class StudentManger {

    lateinit var scanner: Scanner

    lateinit var s1:BasketBallStudent
    lateinit var s2:BasketBallStudent
    lateinit var s3:SoccerStudent
    lateinit var s4:SoccerStudent
    lateinit var s5:BaseBallStudent
    lateinit var s6:BaseBallStudent

    // 각 객체 생성
    init {
        scanner = Scanner(System.`in`)

        s1 = BasketBallStudent()
        s2 = BasketBallStudent()
        s3 = SoccerStudent()
        s4 = SoccerStudent()
        s5 = BaseBallStudent()
        s6 = BaseBallStudent()
    }

    // 학생들 정보 입력
    fun inputStudentInfo() {
        s1.inputStudentInfo(scanner)
        s2.inputStudentInfo(scanner)
        s3.inputStudentInfo(scanner)
        s4.inputStudentInfo(scanner)
        s5.inputStudentInfo(scanner)
        s6.inputStudentInfo(scanner)
    }

    // 학생들 정보 출력
    fun printStudentInfo() {
        s1.printStudentInfo()
        s2.printStudentInfo()
        s3.printStudentInfo()
        s4.printStudentInfo()
        s5.printStudentInfo()
        s6.printStudentInfo()
    }

    // 학생들 행동 메서드 호출
    fun doStudent() {
        s1.run()
        s1.shoot()
        s2.run()
        s2.shoot()
        s3.run()
        s3.out()
        s4.run()
        s4.out()
        s5.run()
        s5.homeRun()
        s6.run()
        s6.homeRun()
    }
}

// 각 운동부 학생들이 상속받을 클래스
open class Student(var partName:String) {
    // 학생명
    lateinit var studentName:String

    // 달리기 기능
    fun run() {
        println("$partName ${studentName}(이)가 달린다")
    }

    // 학생 정보 입력
    open fun inputStudentInfo(scanner: Scanner) {
        println()
        print("이름 : ")
        studentName = scanner.next()
    }

    // 학생 정보 출력
    open fun printStudentInfo() {
        println()
        println("이름 : $studentName")
        println("소속명 : $partName")
    }
}

// 농구부 학생 클래스
class BasketBallStudent : Student("농구부") {
    // 슛 개수
    var shootCount = 0

    // 슛 기능
    fun shoot() {
        println("$partName ${studentName}(이)가 슛을 쏜다")
    }

    // 농구부 학생 정보 입력
    override fun inputStudentInfo(scanner: Scanner) {
        super.inputStudentInfo(scanner)
        print("슛 개수 : ")
        shootCount = scanner.nextInt()
    }

    // 농구부 학생 정보 출력
    override fun printStudentInfo() {
        super.printStudentInfo()
        println("슛 개수 : $shootCount")
    }
}

// 축구부 학생 클래스
class SoccerStudent : Student("축구부") {
    // 퇴장 개수
    var outCount = 0

    // 퇴장 기능
    fun out() {
        println("$partName ${studentName}(이)가 퇴장 당한다")
    }

    // 축구부 학생 정보 입력
    override fun inputStudentInfo(scanner: Scanner) {
        super.inputStudentInfo(scanner)
        print("퇴장 개수 : ")
        outCount = scanner.nextInt()
    }

    // 축구부 학생 정보 출력
    override fun printStudentInfo() {
        super.printStudentInfo()
        println("퇴장 개수 : $outCount")
    }
}

// 야구부 학생 클래스
class BaseBallStudent : Student("야구부") {
    // 홈런 개수
    var homeRunCount = 0

    // 홈런 기능
    fun homeRun() {
        println("$partName ${studentName}(이)가 홈런을 친다")
    }

    // 야구부 학생 정보 입력
    override fun inputStudentInfo(scanner: Scanner) {
        super.inputStudentInfo(scanner)
        print("홈런 개수 : ")
        homeRunCount = scanner.nextInt()
    }

    // 야구부 학생 정보 출력
    override fun printStudentInfo() {
        super.printStudentInfo()
        println("홈런 개수 : $homeRunCount")
    }

}