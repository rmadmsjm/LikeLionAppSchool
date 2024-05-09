import 'package:flutter/material.dart';
import 'package:flutter03_lionflix/screen/detail_screen.dart';

class LikeListView extends StatefulWidget {
  const LikeListView({super.key});

  @override
  State<LikeListView> createState() => _LikeListViewState();
}

class _LikeListViewState extends State<LikeListView> {
  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: 10,
      itemBuilder: (context, index) => makeListItem(context),
    );
  }
}

// 리스트 뷰의 항목 하나를 구성하는 함수
Widget makeListItem(BuildContext context){

  return Container(
    padding: EdgeInsets.only(top: 10),
    child: InkWell(
      onTap: () {
        Navigator.of(context).push(
            MaterialPageRoute(
                builder: (context) => DetailScreen(),
                fullscreenDialog: true
            )
        );
      },
      child: Row(
        // 양쪽에 하나씩 배치되도록 한다.
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Row(
            children: [
              Image.asset(
                'lib/assets/images/movie7.jpg',
                width: 100,
              ),
              Padding(padding: EdgeInsets.only(right: 10)),
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text("영화제목"),
                  Text("출연진 : 배우1, 배우2, 배우3"),
                  Text("제작진 : 제작1, 제작2, 제작3"),
                ],
              ),
            ],
          ),
          IconButton(
            icon: Icon(Icons.delete),
            onPressed: () {},
          )
        ],
      ),
    ),
  );
}