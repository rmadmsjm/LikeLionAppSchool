import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';

import '../screen/drama_screen.dart';
import '../screen/movie_screen.dart';

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
            onPressed: () {
              Navigator.of(context).push(
                  MaterialPageRoute(
                    builder: (context) => DramaScreen(),
                  )
              );
            },
            icon: Icon(Icons.tv)
        ),
        // 영화 메뉴
        IconButton(
            onPressed: () {
              Navigator.of(context).push(
                  MaterialPageRoute(
                    builder: (context) => MovieScreen(),
                  )
              );
            },
            icon: Icon(Icons.movie)
        ),
        // 찜 메뉴
        IconButton(
            onPressed: () {
              // addTest();
              // printTest();
            },
            icon: Icon(Icons.favorite)
        )
      ],
    );
  }
}

// 테스트 데이터 저장
Future<void> addTest() async {
  await FirebaseFirestore.instance.collection('test').add({
    'data1' : 100,
    'data2' : 11.11,
    'data3' : '안녕하세요'
  });
}

// 테스트 데이터 출력
Future<void> printTest() async {
  var result = await FirebaseFirestore.instance.collection('test').get();

  for(var doc in result.docs) {
    var map = doc.data();
    print('firebase test : $map');
  }
}
