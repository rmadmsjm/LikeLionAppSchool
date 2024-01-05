public class JavaMain {
    // 정적 변수
    public static int javaValue1 = 100;
    // 정적 메서드
    public static void javaMethod1() {
        System.out.println("JavaMethod1");
    }

    // 자바 프로그램에서의 시작 메서드
    // 단축어 : psvm, main
    public static void main(String[] args) {
        // kotlin에서 정의한 companion object 의 정적 멤버 사용
        int a1 = TestClass2.Companion.getKotlinValue();

        // 단축어 : sout
        System.out.println("a1 : " + a1);
        TestClass2.Companion.kotlinMethod();
    }

}
