import 'package:flutter/material.dart';
import 'package:flutter03_lionflix/screen/detail_screen.dart';

class HomeCircleSlider extends StatefulWidget {
  const HomeCircleSlider({super.key});

  @override
  State<HomeCircleSlider> createState() => _HomeCircleSliderState();
}

class _HomeCircleSliderState extends State<HomeCircleSlider> {
  @override
  Widget build(BuildContext context) {
    // 다수의 항목을 보여주기 위해 사용할 때는 builder를 이용해 구성
    return Container(
      padding: EdgeInsets.fromLTRB(10, 10, 10, 0),
      child: Column(
        // 정렬
        // mainAxisAlignment : 순방향 정렬
        // crossAxisAlignment : 반대방향 정렬 (가로 방향)
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text('미리 보기'),
          Container(
            // ListView의 크기를 컨테이너를 통해 설정
            height: 120,
            child: ListView.builder(
              // 스크롤 방향 가로로 설정
              scrollDirection: Axis.horizontal,
              // 전체 항목의 개수
              itemCount: 10,
              // 항목 하나를 구성하기 위해 호출하는 함수
              // 여기서 반환하는 위젯이 항목 하나가 됨
              itemBuilder: (context, index) {
                return makeListItem(context);
              },
            ),
          ),
        ],
      ),
    );
  }
}

// ListView의 항목 하나를 구성해 반환하는 함수
Widget makeListItem(BuildContext context) {
  // InkWell : 사용자 이벤트를 처리할 수 있는 컨테이너
  //           화면 요소에 사용용자 이벤트에 관련된 리스너가 없을 경우 사용
  return InkWell(
    // 눌렀을 때의 리스너
    onTap: () {
      // DetailScreen 띄우기
      Navigator.of(context).push(
        MaterialPageRoute(
          builder: (context) => DetailScreen(),
          fullscreenDialog: true
        )
      );
    },
    child: Container(
      padding: EdgeInsets.only(right: 10),
      // 동그라미 형태로 보여주는 컨테이너
      child: CircleAvatar(
        // 배경 이미지
        backgroundImage: AssetImage('lib/assets/images/movie1.jpg'),
        // 크기
        radius: 48,
      ),
    ),
  );
}
