import 'package:flutter/material.dart';
import 'package:flutter03_lionflix/widget/like_list_view.dart';
import 'package:flutter03_lionflix/widget/like_top_app_bar.dart';

class LikeScreen extends StatefulWidget {
  const LikeScreen({super.key});

  @override
  State<LikeScreen> createState() => _LikeScreenState();
}

class _LikeScreenState extends State<LikeScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: LikeTopAppBar(),
      body: Container(
        padding: EdgeInsets.fromLTRB(10, 10, 10, 0),
        child: Expanded(
          child: LikeListView(),
        ),
      ),
    );
  }
}