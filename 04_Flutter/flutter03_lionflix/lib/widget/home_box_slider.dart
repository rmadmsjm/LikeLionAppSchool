import 'package:flutter/material.dart';
import 'package:flutter03_lionflix/screen/detail_screen.dart';

class HomeBoxSlider extends StatefulWidget {
  const HomeBoxSlider({super.key});

  @override
  State<HomeBoxSlider> createState() => _HomeBoxSliderState();
}

class _HomeBoxSliderState extends State<HomeBoxSlider> {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.fromLTRB(10, 10, 10, 0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text('지금 뜨는 컨텐츠'),
          Padding(padding: EdgeInsets.only(top: 10)),
          Container(
            height: 120,
            child: ListView.builder(
              scrollDirection: Axis.horizontal,
              itemCount: 10,
              itemBuilder: (context, index) {
                return makeListItem(context);
              },
            ),
          )
        ],
      ),
    );
  }
}

// ListView의 항목 하나를 구헝하는 함수
Widget makeListItem(BuildContext context) {
  return InkWell(
    onTap: () {
      Navigator.of(context).push(
          MaterialPageRoute(
              builder: (context) => DetailScreen(),
              fullscreenDialog: true
          )
      );
    },
    child: Container(
      padding: EdgeInsets.only(right: 10),
      child: Image.asset('lib/assets/images/movie2.jpg'),
    ),
  );
}
