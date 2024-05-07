import 'package:flutter/material.dart';

class SearchTopAppBar extends StatefulWidget implements PreferredSizeWidget {
  const SearchTopAppBar({super.key});

  @override
  State<SearchTopAppBar> createState() => _SearchTopAppBarState();

  @override
  Size get preferredSize => Size.fromHeight(kToolbarHeight);
}

class _SearchTopAppBarState extends State<SearchTopAppBar> {
  @override
  Widget build(BuildContext context) {
    return AppBar(
      title: Row(
        children: [
          Image.asset(             
            'lib/assets/images/youtube_logo.png',
            fit: BoxFit.contain,
            height: 25,
          ),
          Padding(padding: EdgeInsets.only(right: 10)),
          Text('LionFlix')
        ],
      ),
    );
  }
}
