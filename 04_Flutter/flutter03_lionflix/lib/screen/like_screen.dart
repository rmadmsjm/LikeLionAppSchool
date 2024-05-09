import 'package:flutter/material.dart';

import '../widget/like_top_app_bar.dart';

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
    );
  }
}