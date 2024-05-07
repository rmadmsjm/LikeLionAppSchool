import 'package:flutter/material.dart';
import 'package:flutter03_lionflix/screen/detail_screen.dart';

class SearchListView extends StatefulWidget {
  const SearchListView({super.key});

  @override
  State<SearchListView> createState() => _SearchListViewState();
}

class _SearchListViewState extends State<SearchListView> {
  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: 10,
      itemBuilder: (context, index) => makeListItem(context)
      // itemBuilder: (context, index) => makeListItem2(),
    );
  }
}

// 리스트뷰의 항목 하나를 구성하는 함수
Widget makeListItem(BuildContext context) {
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
        children: [
          Image.asset(
            'lib/assets/images/movie4.jpg',
            width: 100,
          ),
          Padding(padding: EdgeInsets.only(right: 10)),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                '영화 제목1',
                style: TextStyle(fontSize: 15),
              ),
              Padding(padding: EdgeInsets.only(top: 10)),
              Text(
                '출연진 : 배우1, 배우2, 배우3',
                style: TextStyle(fontSize: 12),
              ),
              Padding(padding: EdgeInsets.only(top: 5)),
              Text(
                '제작진 : 제작1, 제작2',
                style: TextStyle(fontSize: 12),
              )
            ],
          ),
        ],
      ),
    ),
  );
}

// ListTile 사용 예
// https://api.flutter.dev/flutter/material/ListTile-class.html
Widget makeListItem2() {
  return ListTile(
    leading: Image.asset('lib/assets/images/movie6.jpg'),
    title: Text('영화 제목 1'),
    subtitle: Text('출연진 : 배우1, 배우2, 배우3\n제작진 : 제작1, 제작2'),
    isThreeLine: true,
    onTap: () {
    },
  );
}