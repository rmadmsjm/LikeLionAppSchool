void main() {
  // 변수 선언
  int a1 = 100;
  double a2 = 11.11;
  bool a3 = true;
  String a4 = "안녕하세요";

  print('a1 : $a1');
  print('a2 : $a2');
  print('a3 : $a3');
  print('a4 : $a4');

  // 자료형이 다른 값을 넣으면 오류 발생
  // a1 = 11.11;

  print("--------------------------");
  
  // var : 자료형 대신 사용하면 저장하는 값의 종류를 보고 타입이 결정됨
  var a5 = 100;
  var a6 = 11.11;

  print('a5 : $a5');
  print('a6 : $a6');

  // 정수형으로 결정된 변수에 실수값을 넣기
  // var로 선언된 정수형 변수이므로 오류가 발생함
  // a5 = 11.11;

  // dynamic : 자료형 대신 사용하면 값의 타입이 결정되지 않음
  // 정수, 실수, 문자열 등 다양한 타입의 값을 하나의 변수에 담을 수 있음
  dynamic a7 = 100;
  print('a7 : $a7');

  a7 = 11.11;
  print('a7 : $a7');

  a7 = true;
  print('a7 : $a7');

  // const
  // 빌드타임 상수
  // 기억장소를 정의할 때 관리할 값을 지정해야 함
  const int a8 = 100;
  // const와 var는 같이 쓸 수 없음
  // const var a9 = 200;
  const dynamic a10 = 200;

  print('a8 : $a8');
  print('a10 : $a10');

  // 상수로 정의되어 있기 때문에 값을 새로 저장할 수 없음
  // a8 = 500;
  // a10 = 600;

  // 함수 호출을 통해 값을 변환 받는 코드처럼 프로그램이 실행되어야만 구할 수 있는 값을
  // const 함수에 넣는 작업을 하면 오류가 발생함
  // const a11 = DateTime.now();

  // final
  // 런타임 상수
  // 프로그램 실행 중에 발생되는 값을 상수로 정의해 쓰고자 할 때 사용함
  final int a12 = 100;
  // final과 var는 같이 쓸 수 없음
  // final var a13 = 200;
  final dynamic a14 = 200;

  // 프로그램 실행 중에 구하는 값을 저장하는 상수를 정의하는 것도 가능함
  final a15 = DateTime.now();

  print('a12 : $a12');
  print('a14 : $a14');
  print('a15 : $a15');
}