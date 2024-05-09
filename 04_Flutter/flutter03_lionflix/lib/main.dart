import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:flutter03_lionflix/provider/tab_page_index_provider.dart';
import 'package:flutter03_lionflix/screen/main_screen.dart';
import 'package:flutter03_lionflix/widget/main_bottom_navigaiton_bar.dart';
import 'package:provider/provider.dart';
import 'package:firebase_core/firebase_core.dart';
import 'firebase_options.dart';

Future<void> main() async {
  // firebase의 await(동기화)때문에 Flutter VM이 화면처리를 못하는 문제가 발생함
  // 이를 위해 설정
  WidgetsFlutterBinding.ensureInitialized();

  // Firestore 초기 설정
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );

  // 초기 데이터 저장
  // addInitData();

  runApp(LionFlixApp());
}

class LionFlixApp extends StatefulWidget {
  const LionFlixApp({super.key});

  @override
  State<LionFlixApp> createState() => _LionFlixAppState();
}

class _LionFlixAppState extends State<LionFlixApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'LionFlix',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(
            seedColor: Colors.blue,
            // 전체적인 어플 테마 설정
            brightness: Brightness.dark
        ),
        useMaterial3: true
      ),
      // 화면 요소에서 프로바이더를 사용할 수 있도록 설정
      home: ChangeNotifierProvider(
        create: (BuildContext context) => TabPageIndexProvider(),
        child: Scaffold(
          bottomNavigationBar: MainBottomNavigationBar(),
          body: MainScreen(),
        ),
      )
    );
  }
}

Future<void> addInitData() async {
  await FirebaseFirestore.instance.collection('movie_data').add({
    'movie_idx' : 1,
    'movie_title' : '라라 랜드',
    'movie_info' : '2016 12+',
    'movie_type' : 1,
    'movie_detail_info' : "꿈을 꾸는 사람들을 위한 별들의 도시 '라라랜드'. 재즈 피아니스트 '세바스찬'(라이언 고슬링)과 성공을 꿈꾸는 배우 지망생 '미아'(엠마 스톤). 인생에서 가장 빛나는 순간 만난 두 사람은 미완성인 서로의 무대를 만들어가기 시작한다.",
    'movie_actor' : '라이언 고슬링, 엠마 스톤, 존 레전드 외',
    'movie_director' : '데이미언 셔젤',
    'movie_poster' : 'movie1.jpg'
  });

  await FirebaseFirestore.instance.collection('movie_data').add({
    'movie_idx' : 2,
    'movie_title' : '백두산',
    'movie_info' : '2019 12+',
    'movie_type' : 2,
    'movie_detail_info' : "대한민국 관측 역사상 최대 규모의 백두산 폭발 발생. 갑작스러운 재난에 한반도는 순식간에 아비규환이 되고, 남과 북 모두를 집어삼킬 추가 폭발이 예측된다.",
    'movie_actor' : '이병헌, 하정우, 마동석, 전혜진, 배수지 外',
    'movie_director' : '이해준, 김병서',
    'movie_poster' : 'movie2.jpg'
  });

  await FirebaseFirestore.instance.collection('movie_data').add({
    'movie_idx' : 3,
    'movie_title' : '1987',
    'movie_info' : '2017 15+',
    'movie_type' : 1,
    'movie_detail_info' : "증거인멸을 위해 박 처장(김윤석)의 주도 하에 경찰은 시신 화장을 요청하지만, 사망 당일 당직이었던 최 검사(하정우)는 이를 거부하고 부검을 밀어붙인다.",
    'movie_actor' : '김윤석, 하정우, 유해진, 김태리, 박희순, 이희준 외',
    'movie_director' : '장준환',
    'movie_poster' : 'movie3.jpg'
  });

  await FirebaseFirestore.instance.collection('movie_data').add({
    'movie_idx' : 4,
    'movie_title' : '기생충',
    'movie_info' : '2020 15+',
    'movie_type' : 2,
    'movie_detail_info' : "전원백수로 살 길 막막하지만 사이는 좋은 기택(송강호) 가족.",
    'movie_actor' : '송강호, 이선균, 조여정, 최우식, 박소담, 장혜진, 이정은 外',
    'movie_director' : '봉준호',
    'movie_poster' : 'movie4.jpg'
  });

  await FirebaseFirestore.instance.collection('movie_data').add({
    'movie_idx' : 5,
    'movie_title' : '부산행',
    'movie_info' : '2016 15+',
    'movie_type' : 1,
    'movie_detail_info' : "정체불명의 바이러스가 전국으로 확산되고 대한민국 긴급재난경보령이 선포된 가운데,",
    'movie_actor' : '공유, 정유미, 마동석, 최우식, 안소희, 김의성, 김수안 등',
    'movie_director' : '연상호',
    'movie_poster' : 'movie5.jpg'
  });

  await FirebaseFirestore.instance.collection('movie_data').add({
    'movie_idx' : 6,
    'movie_title' : '너의 이름은',
    'movie_info' : '2017 12+',
    'movie_type' : 2,
    'movie_detail_info' : "한 달 후, 천 년 만에 찾아온다는 혜성을 기다리고 있는 일본.",
    'movie_actor' : '카미키 류노스케, 카미시라이시 모네, 타니 카논, 이시카와 카이토, 나가사와 마사미, 나리타 료, 시마자키 노부나가, 유우키 아오이',
    'movie_director' : '신카이 마코토',
    'movie_poster' : 'movie6.jpg'
  });

  await FirebaseFirestore.instance.collection('movie_data').add({
    'movie_idx' : 7,
    'movie_title' : '어벤져스 : 엔드게임',
    'movie_info' : '2019 12+',
    'movie_type' : 1,
    'movie_detail_info' : "인피니티 워 이후 절반만 살아남은 지구, 마지막 희망이 된 어벤져스. 먼저 떠난 그들을 위해 모든 것을 걸었다! 위대한 어벤져스, 운명을 바꿀 최후의 전쟁이 펼쳐진다!",
    'movie_actor' : '로버트 다우니 주니어, 크리스 에반스, 마크 러팔로, 크리스 헴스워스, 스칼렛 요한슨, 제레미 레너, 돈 치들, 폴 러드, 브리 라슨, 카렌 길런, 다나이 구리라, 브래들리 쿠퍼, 조시 브롤린, 톰 홀랜드',
    'movie_director' : '앤서니 루소, 조 루소',
    'movie_poster' : 'movie7.jpg'
  });

  await FirebaseFirestore.instance.collection('movie_data').add({
    'movie_idx' : 8,
    'movie_title' : '파묘',
    'movie_info' : '2024 15+',
    'movie_type' : 2,
    'movie_detail_info' : "미국 LA, 거액의 의뢰를 받은 무당 ‘화림’(김고은)과 ‘봉길’(이도현)은 기이한 병이 대물림되는 집안의 장손을 만난다.",
    'movie_actor' : '최민식, 김고은, 유해진, 이도현',
    'movie_director' : '장재현',
    'movie_poster' : 'movie8.jpg'
  });

  await FirebaseFirestore.instance.collection('movie_data').add({
    'movie_idx' : 9,
    'movie_title' : '길복순',
    'movie_info' : '2023 19+',
    'movie_type' : 1,
    'movie_detail_info' : "청부살인업계의 전설적인 킬러 길복순이 회사와 재계약 직전, 죽거나 또는 죽이거나, 피할 수 없는 대결에 휘말리게 되는 이야기를 그린 액션 영화",
    'movie_actor' : '전도연, 설경구, 김시아, 이솜, 구교환 外',
    'movie_director' : '변성현',
    'movie_poster' : 'movie9.jpg'
  });

  await FirebaseFirestore.instance.collection('movie_data').add({
    'movie_idx' : 10,
    'movie_title' : '대부',
    'movie_info' : '1977 19+',
    'movie_type' : 2,
    'movie_detail_info' : "1945년, 비토 코를레오네의 딸 코니 코를레오네와 카를로 리치의 결혼식이 진행되고 있다.",
    'movie_actor' : '말런 브랜도, 알 파치노 外',
    'movie_director' : '프랜시스 포드 코폴라',
    'movie_poster' : 'movie10.jpg'
  });

  await FirebaseFirestore.instance.collection('hot').add({
    'hot_movie_idx' : [1, 3]
  });

  await FirebaseFirestore.instance.collection('like').add({
    'like_movie_index' : []
  });
}
