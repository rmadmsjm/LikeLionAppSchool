import 'package:flutter/material.dart';

class LikeTopAppBar extends StatefulWidget implements PreferredSizeWidget{
  const LikeTopAppBar({super.key});

  @override
  State<LikeTopAppBar> createState() => _LikeTopAppBarState();

  @override
  Size get preferredSize => Size.fromHeight(kToolbarHeight);
}

class _LikeTopAppBarState extends State<LikeTopAppBar> {
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
          Text('LionFlix'),
        ],
      ),
    );
  }
}