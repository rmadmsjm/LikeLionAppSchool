import 'package:flutter/material.dart';
import 'package:flutter03_lionflix/widget/search_top_app_bar.dart';

class SearchScreen extends StatefulWidget {
  const SearchScreen({super.key});

  @override
  State<SearchScreen> createState() => _SearchScreenState();
}

class _SearchScreenState extends State<SearchScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: SearchTopAppBar(),
    );
  }
}
