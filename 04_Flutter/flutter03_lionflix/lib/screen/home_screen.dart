import 'package:flutter/material.dart';
import 'package:flutter03_lionflix/widget/home_box_slider.dart';
import 'package:flutter03_lionflix/widget/home_carousel_slider.dart';
import 'package:flutter03_lionflix/widget/home_circle_slider.dart';
import 'package:flutter03_lionflix/widget/home_top_app_bar.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: HomeTopAppBar(),
      // ListView : ScrollView 처럼 사용
      // ListView.builder : RecyclerView 처럼 사용
      body: ListView(
        children: [
          // 상단 회전목마
          HomeCarouselSlider(),
          Padding(padding: EdgeInsets.only(top: 20)),
          // 미리 보기
          HomeCircleSlider(),
          Padding(padding: EdgeInsets.only(top: 20)),
          // 지금 뜨는 컨텐츠
          HomeBoxSlider(),
        ],
      )
    );
  }
}
