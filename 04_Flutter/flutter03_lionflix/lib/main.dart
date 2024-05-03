import 'package:flutter/material.dart';
import 'package:flutter03_lionflix/provider/tab_page_index_provider.dart';
import 'package:flutter03_lionflix/screen/main_screen.dart';
import 'package:flutter03_lionflix/widget/main_bottom_navigaiton_bar.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(LionFlixApp());
}

class LionFlixApp extends StatefulWidget {
  const LionFlixApp({super.key});

  @override
  State<LionFlixApp> createState() => _LionFlixAppState();
}

class _LionFlixAppState extends State<LionFlixApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'LionFlix',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(
            seedColor: Colors.blue,
            // 전체적인 어플 테마 설정
            brightness: Brightness.dark
        ),
        useMaterial3: true
      ),
      // 화면 요소에서 프로바이더를 사용할 수 있도록 설정
      home: ChangeNotifierProvider(
        create: (BuildContext context) => TabPageIndexProvider(),
        child: Scaffold(
          bottomNavigationBar: MainBottomNavigationBar(),
          body: MainScreen(),
        ),
      )
    );
  }
}
