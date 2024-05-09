import 'package:flutter/material.dart';
import 'package:flutter03_lionflix/dao/movie_dao.dart';
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
  // 영화 데이터 담을 상태 변수
  List<Map<String, dynamic>> movieData = [];
  // 영화 포스터 담을 상태 변수
  List<Image> posterData = [];

  // 화면이 보여질 때마다 호출되는 함수
  // initState()에 async 붙이면 오류 발생함
  // Android의 onResume() 과 비슷
  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    getData();
  }

  // 영화 데이터 가져오기
  Future<void> getData() async {
    // 영화 데이터 가져오기
    var tempMovieData = await getMovieData();
    // print('home screen : $tempMovieData');
    // getImageData(tempMovieData[0]['movie_poster']);

    // 영화 수만큼 이미지 객체 만들기
    posterData = List<Image>.generate(
        // 리스트가 담을 객체 개수
        tempMovieData.length,
        // 리스트가 담을 객체 생성해 반환
        // index에는 순서값이 들어옴
        (index) => Image.asset('lib/assets/images/loading.gif')
    );

    // 영화 데이터를 통해 상태 설정
    setState(() {
      movieData = tempMovieData;
    });

    // 포스터 데이터를 받아오면 상태 설정
    for(int i = 0; i < tempMovieData.length; i++) {
      // i번째 영화 포스터 객체 가져오기
      var tempImage = await getImageData(tempMovieData[i]['movie_poster']);
      // 받아온 이미지 객체를 포스터를 담을 리스트에 담고 상태 설정
      setState(() {
        posterData[i] = tempImage;
      });
    }
  }

  // 보여지는 화며을 구성할 때 호출되는 함수
  // 1. 처음 보여질 때
  // 2. 상태가 설정되었을 때
  // 3. 사용자에 의해 이벤트가 발생할 때
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: HomeTopAppBar(),
      // ListView : ScrollView 처럼 사용
      // ListView.builder : RecyclerView 처럼 사용
      body: ListView(
        children: [
          // 상단 회전목마
          HomeCarouselSlider(movieData, posterData),
          Padding(padding: EdgeInsets.only(top: 20)),
          // 미리 보기
          HomeCircleSlider(movieData, posterData),
          Padding(padding: EdgeInsets.only(top: 20)),
          // 지금 뜨는 컨텐츠
          HomeBoxSlider(),
        ],
      )
    );
  }
}
