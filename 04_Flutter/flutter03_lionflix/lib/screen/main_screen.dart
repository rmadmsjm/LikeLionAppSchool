import 'package:flutter/material.dart';
import 'package:flutter03_lionflix/provider/tab_page_index_provider.dart';
import 'package:flutter03_lionflix/screen/home_screen.dart';
import 'package:flutter03_lionflix/screen/search_screen.dart';
import 'package:provider/provider.dart';

import 'like_screen.dart';

class MainScreen extends StatefulWidget {
  const MainScreen({super.key});

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  @override
  Widget build(BuildContext context) {
    // Provider
    var tabPageIndexProvider = Provider.of<TabPageIndexProvider>(context, listen: false);
    // 보여줄 화면 순서값
    var currentPageIndex = tabPageIndexProvider.currentPageIndex;

    // Provider 리스너 등록
    // Provider에서 notifyListeners(); 를 호출하면 동작함
    tabPageIndexProvider.addListener(() {
      // 화면 순서값 변경
      setState(() {
        currentPageIndex = tabPageIndexProvider.currentPageIndex;
      });
    });

    return Container(
      child: [
        HomeScreen(),
        SearchScreen(),
        LikeScreen(),
        Center(child: Text('more')),
      ][currentPageIndex],
    );
  }
}
