// 다른 파일에 있는 것을 사용할 때는 import 해야 함
import 'test1.dart';
// import 하는 파일에 별칭을 부여할 수 있음
// 별칭을 통해 파일에 정의되어 있는 요소들에 접근할 수 있음
// 만약 import 파일들 내에 중복된 것들이 있다면 별칭을 부여하여 이를 통해 구분해서 사용할 수 있음
import 'test2.dart' as test2;
// 하위 폴더에있는 dart 파일 import
import 'sub/test3.dart';

void main() {
  // 같은 파일에 있는 것은 자유롭게 사용 가능
  print("mainA1 : $mainA1");
  mainFunction();
  var mainClass = MainClass();
  mainClass.showInfo();

  // 다른 파일에 있는 것은 private 멤버를 제외하고 모두 사용 가능
  print("test1A1 : $test1A1");
  test1Function();
  var test1Class = Test1Class();
  test1Class.showInfo();

  // 별칭을 통해 접근
  print("test2.testA1 : ${test2.test1A1}");
  test2.test1Function();
  var test1Class2 = test2.Test1Class();
  test1Class2.showInfo();

  // 하위 폴더에 있는 dart 파일에 정의된 요소
  print("test3A1 : $test3A1");
  test3Function();
  var test3Class = Test3Class();
  test3Class.showInfo();
}

int mainA1 = 100;

void mainFunction(){
  print("maint.dart에 있는 함수");
}

class MainClass{
  void showInfo(){
    print("main.dart의 클래스");
  }
}