import 'package:flutter/material.dart';
import 'package:flutter03_lionflix/screen/detail_screen.dart';
import 'package:flutter03_lionflix/widget/drama_top_app_bar.dart';

class DramaScreen extends StatefulWidget {
  const DramaScreen({super.key});

  @override
  State<DramaScreen> createState() => _DramaScreenState();
}

class _DramaScreenState extends State<DramaScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: DramaTopAppBar(),
        body: GridView.builder(
          gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            // 컬럼의 개수
              crossAxisCount: 4,
              // 수평 방향 여백
              mainAxisSpacing: 10,
              // 수직 방향 여백
              crossAxisSpacing: 10,
              // 항목의 가로 세로 비율
              childAspectRatio: 0.7
          ),
          itemBuilder: (context, index) => makeGridItem(context),
        )
    );
  }
}

// 그리드 뷰의 항목 하나를 구성하는 함수
Widget makeGridItem(BuildContext context){
  return InkWell(
    onTap: () {
      Navigator.of(context).push(
          MaterialPageRoute(
              builder: (context) => DetailScreen(),
              fullscreenDialog: true
          )
      );
    },
    child: Image.asset('lib/assets/images/movie7.jpg'),
  );
}
