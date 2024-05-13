import 'package:flutter/material.dart';
import 'package:flutter03_lionflix/provider/tab_page_index_provider.dart';
import 'package:provider/provider.dart';

class SearchSearchBar extends StatefulWidget {
  const SearchSearchBar({super.key});

  @override
  State<SearchSearchBar> createState() => _SearchSearchBarState();
}

class _SearchSearchBarState extends State<SearchSearchBar> {
  @override
  Widget build(BuildContext context) {
    // Provider 가져오기
    var searchScreenProvider = Provider.of<TabPageIndexProvider>(context, listen: false);

    return SearchBar(
      // 좌측에 매치되는 아이콘
      leading: Icon(Icons.search),
      // 내부 여백
      padding: MaterialStatePropertyAll(EdgeInsets.fromLTRB(20, 0, 20, 0)),
      // 키보드의 submit 버튼을 눌렀을 때
      // value : 사용자가 입력한 내용이 들어옴
      onSubmitted: (value) {
        // Provider의 set 메서드 호출
        searchScreenProvider.setKeyword(value);
      },
    );
  }
}
