import 'package:flutter/material.dart';

class MyPageTopAppBar extends StatefulWidget implements PreferredSizeWidget{
  const MyPageTopAppBar({super.key});

  @override
  State<MyPageTopAppBar> createState() => _MyPageTopAppBarState();

  @override
  // TODO: implement preferredSize
  Size get preferredSize => Size.fromHeight(kToolbarHeight);
}

class _MyPageTopAppBarState extends State<MyPageTopAppBar> {
  @override
  Widget build(BuildContext context) {
    return AppBar(
      title: Row(
        children: [
          Image.asset(
            'lib/assets/images/youtube_logo.png',
            fit : BoxFit.contain,
            height: 25,
          ),
          Padding(padding: EdgeInsets.only(right: 10)),
          Text('LionFlix')
        ],
      ),
    );
  }
}