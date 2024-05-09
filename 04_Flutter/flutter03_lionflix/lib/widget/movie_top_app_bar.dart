import 'package:flutter/material.dart';

class MovieTopAppBar extends StatefulWidget implements PreferredSizeWidget{
  const MovieTopAppBar({super.key});

  @override
  State<MovieTopAppBar> createState() => _MovieTopAppBarState();

  @override
  // TODO: implement preferredSize
  Size get preferredSize => Size.fromHeight(kToolbarHeight);
}

class _MovieTopAppBarState extends State<MovieTopAppBar> {
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
