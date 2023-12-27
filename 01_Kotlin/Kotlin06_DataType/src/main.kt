// 기억장소는 리터럴 작성 문법에 맞춰서 값을 작성하면 작성한 값을 저장하기 위해 기억장소가 만들어짐
// 이때, 위치는 VM이 알아서 정하고, 용도왕 크기는 작성한 값의 문법을 보고 결정함
// 이러한 값은 프로그램 실행 중에 변경될 수 없음
// 만약 프로그램 실행 중에 어떤 의미의 값이 변한다면 기억 장소를 개발자에 의해 만들고
// 기억장소에 접근하여 값을 저장하고 가져다 사용할 수 잇는 변수를 사용해야 함
// 변수라는 것을 생성할 때 위치는 VM이 알아서 정해주고 용도와 크기는 개밝자가 정해야 함
// 이를 자료형이라 부름

fun main() {
    // 정수 자료형
    println("Byte 용량 : " + Byte.SIZE_BYTES)
    println("Byte 최소 값 : " + Byte.MIN_VALUE)
    println("Byte 최대 값 : " + Byte.MAX_VALUE)
    println()

    println("Short 용량 : " + Short.SIZE_BYTES)
    println("Short 최소 값 : " + Short.MIN_VALUE)
    println("Short 최대 값 : " + Short.MAX_VALUE)
    println()

    // 정수값을 작성하면 4 byte 기억공간이 만들어지고 저장되기 때문에
    // Int를 정수의 기본 자료형으로 부름
    println("Int 용량 : " + Int.SIZE_BYTES)
    println("Int 최소 값 : " + Int.MIN_VALUE)
    println("Int 최대 값 : " + Int.MAX_VALUE)
    println()

    println("Long 용량 : " + Long.SIZE_BYTES)
    println("Long 최소 값 : " + Long.MIN_VALUE)
    println("Long 최대 값 : " + Long.MAX_VALUE)
    println()

    // 부호가 없는 정수형
    // 저장공간에 저장할 수 있는 값의 가지수를 절반 짤라서 양수와 음수로 나눠서 사용하는데
    // 부호가 없는 자료형을 쓰면 0부터의 범위가 됨
    // 양수쪽으로 더 큰 범위의 숫자를 저장할 수 있음
    println("UByte 용량 : " + UByte.SIZE_BYTES)
    println("UByte 최소 값 : " + UByte.MIN_VALUE)
    println("UByte 최대 값 : " + UByte.MAX_VALUE)
    println()

    println("UShort 용량 : " + UShort.SIZE_BYTES)
    println("UShort 최소 값 : " + UShort.MIN_VALUE)
    println("UShort 최대 값 : " + UShort.MAX_VALUE)
    println()

    println("UInt 용량 : " + UInt.SIZE_BYTES)
    println("UInt 최소 값 : " + UInt.MIN_VALUE)
    println("UInt 최대 값 : " + UInt.MAX_VALUE)
    println()

    println("ULong 용량 : " + ULong.SIZE_BYTES)
    println("ULong 최소 값 : " + ULong.MIN_VALUE)
    println("ULong 최대 값 : " + ULong.MAX_VALUE)
    println()

    // 실수
    println("Float 용량 : " + Float.SIZE_BYTES)
    println("Float 최소 값 : " + Float.MIN_VALUE)
    println("Float 최대 값 : " + Float.MAX_VALUE)
    println()

    // 실수 리터럴을 작성하면 8 byte 기억공간이 만들어져 저장됨
    // 이에, Double이 실수의 기본 자료형임
    println("Double 용량 : " + Double.SIZE_BYTES)
    println("Double 최소 값 : " + Double.MIN_VALUE)
    println("Double 최대 값 : " + Double.MAX_VALUE)
    println()

    // 논리
    // Boolean
    // 논리형은 1 byte를 사용함

    // 문자
    println("Char 용량 : " + Char.SIZE_BYTES)
    println("Char 최소 값 : " + Char.MIN_VALUE)
    println("Char 최대 값 : " + Char.MAX_VALUE)
    println()

    // 문자열 : 정확히는 자료형이 아님
    // String
}