import 'package:flutter/material.dart';

class MoreTopAppBar extends StatefulWidget implements PreferredSizeWidget{
  const MoreTopAppBar({super.key});

  @override
  State<MoreTopAppBar> createState() => _MoreTopAppBarState();

  @override
  // TODO: implement preferredSize
  Size get preferredSize => Size.fromHeight(kToolbarHeight);
}

class _MoreTopAppBarState extends State<MoreTopAppBar> {
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