import 'package:flutter/material.dart';
import 'package:flutter03_lionflix/provider/tab_page_index_provider.dart';
import 'package:provider/provider.dart';

class MainBottomNavigationBar extends StatefulWidget {
  const MainBottomNavigationBar({super.key});

  @override
  State<MainBottomNavigationBar> createState() => _MainBottomNavigationBarState();
}

class _MainBottomNavigationBarState extends State<MainBottomNavigationBar> {

  // 네비게이션 바에서 선택되어 있는 아이콘 메뉴의 순서값 변수
  // int tabPageIndex = 0;

  @override
  Widget build(BuildContext context) {
    // 네비게이션 바에서 선택되어 있는 아이콘 메뉴의 순서값을 관리하는 Provider
    // listen : true로 설정하면 Provider를 이용할 때 화면 전체가 다시 만들어짐
    //          false로 설정하면 화면 전체가 다시 만들어지지 않음
    var tabPageIndexProvider = Provider.of<TabPageIndexProvider>(context, listen: false);

    // 하단에 배치될 내비게이션 바
    return NavigationBar(
      // 네비게이션 바를 구성하는 아이콘
      destinations: [
          NavigationDestination(
              // 평상시의 아이콘
              icon: Icon(Icons.home_outlined),
              // 눌러졌을 때의 아이콘
              selectedIcon: Icon(Icons.home),
              // 아이콘 하단에 표시될 문자열
              label: "Home"
          ),
          NavigationDestination(
              icon: Icon(Icons.search_outlined),
              selectedIcon: Icon(Icons.search),
              label: "Search"
          ),
          NavigationDestination(
              icon: Icon(Icons.save_alt_outlined),
              selectedIcon: Icon(Icons.save_alt),
              label: "Saved"
          ),
          NavigationDestination(
              icon: Icon(Icons.list_outlined),
              selectedIcon: Icon(Icons.list),
              label: "more"
          ),
      ],
      // 네비게이션 바에 배치된 아이콘 메뉴 중 어떤 것을 선택하는지
      selectedIndex: tabPageIndexProvider.currentPageIndex,
      // 하단 메뉴를 눌렀을 때
      // vale : 사용자가 누른 메뉴 항목의 순서값
      onDestinationSelected: (value) {
        setState(() {
          // tabPageIndex = value;
          tabPageIndexProvider.setCurrentPageIndex(value);
        });
      },
      // indicator 색상
      indicatorColor: Colors.white12,
    );
  }
}