# Flutter 중요 내용 정리

### 상태
- UI 요소의 속성에 적용되는 값

### StatelessWidget
- 상태를 관리하는 요소가 없는 위젯
- 상태 관리를 하는 요소가 없기 때문에 눈에 보이는 UI요소에 대해 값을 지정하는 작업을 직접해줘야 함
- 비교적 구조가 간단함
- 눈에 보이는 요소를 위젯이 직접 관리함
- 어플 실행 후 종료될 때까지 절대 변하는 부분이 없을 경우 사용함

### StatefulWidget
- 상태를 관리하는 요소가 있는 위젯
- 상태 관리 요소가 있기 때문에 상태값을 변경시키는 것만으로 UI요소에 변화를 줄 수 있음
- StatelessWidget에 비해 구조가 조금 복잡함
- 눈에 보이는 요소를 상태 관리 요소가 관리함
- 어플 실행 후 종료될 때까지 변하는 부분이 있는 경우 사용함

### Container
- 눈에 보이는 UI 요소를 배치하는 요소
- Container는 배치되는 모양이나 기능에 따라 여러 가지가 제공됨
- 안드로이드 네이티브에서 layout에 해당하는 부분

### Scaffold
- 눈에 보이는 화면의 전체적인 구조를 관리하는 요소
- 상단 앱바, 하단 네비게이션바, 플로팅 버튼 등을 설정할 수 있음

---

# 프로젝트 설명

### 1. 프로젝트 기본 코드 작성
main.dart
```dart
import 'package:flutter/material.dart';

void main() {
}

// 애플리케이션 전체 클래스
class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}

```

### 2. MyApp 실행
main.dart - main()
```dart
void main() {
  // 애플리케이션 실행
  runApp(const MyApp());
}
```

### 3. 상태 관리 객체의 build 메서드에 MaterialApp 설정
main.dart - _MyAppState - build()
```dart

// 여기서 반환하는 객체의 구성을 보고 화면을 만듦
return MaterialApp(

);
```

### 4. 어플 타이틀과 테마 설정
main.dart - _MyAppState - build()
```dart
// 어플의 타이틀
// 앱바를 따로 설정하지 않으면 title 문자열이 보여진다.
title: '멋쟁이 사자',
// 테마
// 어플 전체에 적용될 테마
theme: ThemeData(
    // 컬러 시스템 설정
    colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
    // Material 3 를 적용할 것인지...
    useMaterial3: true
),
```

### 5. 눈에 보이는 화면을 구성하는 부분을 만둘기
main.dart - _MyAppState - build()
```dart
      home: Scaffold(

      ),
```
### 6. 상단 앱바 구성
main.dart - _MyAppState - build()
```dart
// 상단 바
appBar: AppBar(
  // 배경색
    backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        // 상단 바의 타이틀을 문자열 요소로 지정한다.
        title : Text("멋쟁이 사자")
    ),
```

### 7. 화면 본문 부분 작성
main.dart - _MyAppState - build()
```dart
// 화면 본문 부분
body : Center(

)
```
### 8. 화면 중앙에 문자열 요소 두 개 배치
main.dart - _MyAppState - build()
```dart
// Column : 위에서 아래 방향으로 배치
// Row : 좌측에서 우측으로 배치
// Stack : 겹쳐서 배치한다.
child:  Column(
      // 화면 정중앙 정렬
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
        Text("첫 번째 Flutter 애플리케이션 입니다"),
        Text(
            "100",
            style: TextStyle(fontSize: 50),
        )
    ],
)
```

### 9. FloatingActionButton
main.dart - _MyAppState - build()
```dart
// FloatingActionButton
floatingActionButton: FloatingActionButton(
    // 버튼을 눌렀을 때
    onPressed: () {},
    // 버튼 내부에 보여줄 것
    child: Icon(Icons.add),
)
```

### 10. 숫자값을 담을 변수 선언
main.dart - _MyAppState
```dart
// FloatingActionButton을 눌렀을 때 1씩 증가시킨 값을 담을 변수
int number = 0;
```

### 11. 두 번째 Text 요소 수정
- 문자열 값을 변수 출력으로 변경
- 상단의 const는 삭제
```dart
Text(
    "$number",
    style: TextStyle(fontSize: 50),
) 
```

### 12. FloatingActionButton 이벤트
- 버튼을 눌렀을 때 변수의 값을 1 증가시킴
```dart
// 버튼을 눌렀을 때
onPressed: () {
    // setState 함수에서 변수의 값을 변경시키는 작업을 하게되면
    // 이 변수를 사용하는 모든 부분에 적용된다.
    setState(() {
        // number 변수 값을 증가시킨다.
        number++;
        });
},
```
