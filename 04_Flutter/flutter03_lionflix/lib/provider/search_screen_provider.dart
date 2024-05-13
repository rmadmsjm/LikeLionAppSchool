import 'package:flutter/material.dart';

class SearchScreenProvider extends ChangeNotifier {
  // 사용자가 입력한 검색어를 담을 변수
  String _searchKeyword = "";

  String get searchKeyword => _searchKeyword;

  void setKeyword(String keyword) {
    _searchKeyword = keyword;
    // 모든 리스너를 동작시킴
    notifyListeners();
  }
}