import 'package:flutter/material.dart';

class MoreListView extends StatefulWidget {
  const MoreListView({super.key});

  @override
  State<MoreListView> createState() => _MoreListViewState();
}

class _MoreListViewState extends State<MoreListView> {
  @override
  Widget build(BuildContext context) {
    return ListView(
      children: [
        InkWell(
          onTap: () {},
          child: Text('마이 페이지', style : TextStyle(fontSize: 20)),
        ),
        Divider(),
        InkWell(
          onTap: () {},
          child: Text('메뉴1', style : TextStyle(fontSize: 20)),
        ),
        Divider(),
        InkWell(
          onTap: () {},
          child: Text('메뉴2', style : TextStyle(fontSize: 20)),
        ),
        Divider(),
        InkWell(
          onTap: () {},
          child: Text('메뉴3', style : TextStyle(fontSize: 20)),
        ),
      ],
    );
  }
}