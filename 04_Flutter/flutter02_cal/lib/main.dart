import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main(){
  runApp(const CalApp());
}

class CalApp extends StatefulWidget {
  const CalApp({super.key});

  @override
  State<CalApp> createState() => _CalAppState();
}

class _CalAppState extends State<CalApp> {
  // 결과 Text 요소의 문자열 변수
  var result_text = '결과 : 0';
  // 첫 번째 숫자 입력 요소 컨트롤러
  var number1_controller = TextEditingController();
  // 두 번째 숫자 입력 요소 컨트롤러
  var number2_controller = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: '사칙연산 계산기',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true
      ),
      home: Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: Text('사칙연산 계산기'),
        ),
        body: Container(
          padding: EdgeInsets.all(10),
          child: Column(
            children: [
              // 첫 번째 입력 요소
              TextField(
                // 입력 요소의 모양 설정
                decoration: InputDecoration(
                  // 외곽선 모양
                  border: OutlineInputBorder(),
                  // hint
                  labelText: '첫 번째 숫자',
                ),
                // 입력 가능한 데이터 양식
                inputFormatters: [
                  // 숫자만 입력 가능하도록 설정
                  FilteringTextInputFormatter.digitsOnly
                ],
                // 키보드 타입 설정 : 숫자 키보드
                keyboardType: TextInputType.number,
                // 포커스
                autofocus: true,
                // 컨트롤러 연결
                controller: number1_controller,
              ),
              // 위젯 여백을 설정할 수 있는 Padding 요소 사용
              // 두 번째 입력 요소
              Padding(
                padding: EdgeInsets.only(top: 20),
                child: TextField(
                  decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: '두 번째 숫자'
                  ),
                  inputFormatters: [
                    FilteringTextInputFormatter.digitsOnly
                  ],
                  keyboardType: TextInputType.number,
                  controller: number2_controller,
                ),
              ),
              // 버튼 배치를 위한 Row
              // +, - 버튼
              Padding(
                padding: EdgeInsets.only(top: 20),
                child: Row(
                  children: [
                    Expanded(
                        child: OutlinedButton(
                            onPressed: () => setResult("+"),
                            child: Text('+')
                        )
                    ),
                    SizedBox(width: 10),
                    Expanded(
                        child: OutlinedButton(
                            onPressed: () => setResult("-"),
                            child: Text('-')
                        )
                    ),
                  ],
                ),
              ),
              // *, / 버튼
              Padding(
                padding: EdgeInsets.only(top: 20),
                child: Row(
                  children: [
                    Expanded(
                        child: OutlinedButton(
                            onPressed: () => setResult("*"),
                            child: Text('*')
                        ),
                    ),
                    SizedBox(width: 10),
                    Expanded(
                        child: OutlinedButton(
                            onPressed: () => setResult("/"),
                            child: Text('/')
                        )
                    )
                  ],
                ),
              ),
              // 결과
              Padding(
                padding: EdgeInsets.only(top: 20),
                child: Text(
                  result_text,
                  style: TextStyle(
                    fontSize: 30
                  ),
                ),
              )
            ],
          ),
        )
      ),
    );
  }

  // 연산 후 출력하는 함수
  void setResult(String op) {
    setState(() {
      // 입력한 값 가져오기
      int number1 = int.parse(number1_controller.text);
      int number2 = int.parse(number2_controller.text);

      // 연산자에 따른 연산 결과 출력
      switch(op) {
        case '+' :
          result_text = '결과 : ${number1 + number2}';
        case '-' :
          result_text = '결과 : ${number1 - number2}';
        case '*' :
          result_text = '결과 : ${number1 * number2}';
        case '/' :
          result_text = '결과 : ${number1 / number2}';
      }
    });
  }
}