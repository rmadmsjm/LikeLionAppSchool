import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';
import 'package:flutter03_lionflix/screen/detail_screen.dart';
import 'package:smooth_page_indicator/smooth_page_indicator.dart';

class HomeCarouselSlider extends StatefulWidget {
  const HomeCarouselSlider({super.key});

  @override
  State<HomeCarouselSlider> createState() => _HomeCarouselSliderState();
}

class _HomeCarouselSliderState extends State<HomeCarouselSlider> {
  // CarouselSlider에서 보여주고 잇는 이미지와 순서값
  var imagePosition = 0;

  // 포스터 이미지
  var images = [
    Image.asset('lib/assets/images/movie1.jpg'),
    Image.asset('lib/assets/images/movie2.jpg'),
    Image.asset('lib/assets/images/movie3.jpg'),
    Image.asset('lib/assets/images/movie4.jpg'),
    Image.asset('lib/assets/images/movie5.jpg'),
    Image.asset('lib/assets/images/movie6.jpg'),
    Image.asset('lib/assets/images/movie7.jpg'),
    Image.asset('lib/assets/images/movie8.jpg'),
    Image.asset('lib/assets/images/movie9.jpg'),
    Image.asset('lib/assets/images/movie10.jpg'),
  ];

  // 영화 제목
  var movieTitle = [
    '영화 1',
    '영화 2',
    '영화 3',
    '영화 4',
    '영화 5',
    '영화 6',
    '영화 7',
    '영화 8',
    '영화 9',
    '영화 10'
  ];

  // 찜 여부
  var movieLike = [
    true, false, true, false, true, false, true, false, true, false
  ];

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Column(
        children: [
          // 회전목마
          CarouselSlider(
            items: images,
            options: CarouselOptions(
              // 이미지를 보여주는 영역 안에서 보여줄 이미지 크기 비율, 1.0이 최대
              viewportFraction: 1.0,
              // 자동 슬라이드 활성화 여부
              autoPlay: true,
              // 자동 슬라이드 인터벌
              autoPlayInterval: Duration(seconds: 5),
              // 이미지 변경될 때 동작하는 리스너
              onPageChanged: (index, reason) {
                // 현재 보여지는 이미지 순서값 설정
                setState(() {
                  imagePosition = index;
                });
              },
            ),
          ),
          // 영화 제목
          Padding(padding: EdgeInsets.only(top: 10)),
          Text(
            movieTitle[imagePosition],
            style: TextStyle(fontSize: 20),
          ),
          // 버튼
          Row(
            // 배치되는 뷰의 간격 설정
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              // 찜
              Column(
                children: [
                  movieLike[imagePosition] ?
                    IconButton(
                        onPressed: () {},
                        icon: Icon(Icons.check),
                    ) :
                    IconButton(
                      onPressed: () {},
                      icon: Icon(Icons.add),
                    ),
                  Text("내가 찜한 콘텐츠", style:TextStyle(fontSize: 11))
                ],
              ),
              Padding(padding: EdgeInsets.only(right: 10)),
              // 재생 버튼
              TextButton(
                  onPressed: () {}, 
                  child: Row(
                    children: [
                      Icon(
                        Icons.play_arrow,
                        color: Colors.black,
                      ),
                      Padding(padding: EdgeInsets.all(3)),
                        Text(
                            '재생',
                            style:TextStyle(color:Colors.black)
                        )
                    ],
                  ),
                // 버튼 모양
                style: ButtonStyle(
                  backgroundColor: MaterialStatePropertyAll(Colors.white),
                  shape: MaterialStatePropertyAll(
                      RoundedRectangleBorder(
                          borderRadius: BorderRadius.all(Radius.circular(5.0))
                      )
                  ),
                ),
              ),
              Padding(padding: EdgeInsets.only(right: 10)),
              // 정보
              Column(
                children: [
                  IconButton(
                      onPressed: () {
                        // DetailScreen 띄우기
                        Navigator.of(context).push(
                          MaterialPageRoute(
                            // 보여질 다음 화면 설정
                            builder: (context) => DetailScreen(),
                            // 다이얼로그로 보여지게 함
                            fullscreenDialog: true
                          )
                        );
                      },
                      icon: Icon(Icons.info)
                  ),
                  Text(
                    '정보',
                    style: TextStyle(fontSize: 11),
                  )
                ],
              )
            ],
          ),
          // indicator
          Padding(padding: EdgeInsets.only(top: 20)),
          AnimatedSmoothIndicator(
            activeIndex: imagePosition,
            count: images.length,
            effect: WormEffect(
              dotWidth: 5,
              dotHeight: 5
            )
          )
        ],
      ),
    );
  }
}