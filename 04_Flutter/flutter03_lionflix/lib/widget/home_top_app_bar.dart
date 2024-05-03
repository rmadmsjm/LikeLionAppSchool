import 'package:flutter/material.dart';

// PreferrendSizeWidget : 크기 조정이 가능한 위젯을 구현할 때 사용하는 클래스
// 높이나 가로 길이를 조절해야 하는 위젯을 만들 때 반드시 붙여야 함
// appBar에 등록되는 위젯은 반드시 구현해야 함
class HomeTopAppBar extends StatefulWidget implements PreferredSizeWidget {
  const HomeTopAppBar({super.key});

  @override
  State<HomeTopAppBar> createState() => _HomeTopAppBarState();

  @override
  // material에서 설정한 height
  Size get preferredSize => Size.fromHeight(kToolbarHeight);
}

class _HomeTopAppBarState extends State<HomeTopAppBar> {
  @override
  Widget build(BuildContext context) {
    // 앱바
    return AppBar(
      // 타이틀
      title: Row(
        children: [
          Image.asset(
            'lib/assets/images/youtube_logo.png',
            fit: BoxFit.contain,
            height: 25,
          ),
          Padding(padding: EdgeInsets.only(right: 10)),
          Text('LionFlix')
        ],
      ),
      // 앱바 우측 메뉴
      actions: [
        // tv 메뉴
        IconButton(
            onPressed: () {},
            icon: Icon(Icons.tv)
        ),
        // 영화 메뉴
        IconButton(
            onPressed: () {},
            icon: Icon(Icons.movie)
        ),
        // 찜 메뉴
        IconButton(
            onPressed: () {},
            icon: Icon(Icons.favorite)
        )
      ],
    );
  }
}
