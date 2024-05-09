import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:flutter/material.dart';

// 영화 데이터 전체 가져오기
Future<List<Map<String, dynamic>>> getMovieData() async {
  // movie_data 컬렉션에 저장되어 있는 모든 문서 져오기
  var querySnapshot = await FirebaseFirestore.instance.collection('movie_data').get();

  // 데이터 담을 리스트
  List<Map<String, dynamic>> results = [];

  // 데이터 리스트에 담기
  // 컬렉션에 담긴 모든 문서 가져와 반복
  for(var doc in querySnapshot.docs) {
    // 문서에 담긴 데이터를 map으로 추출해 리스터에 담기
    results.add(doc.data());
  }

  return results;
}

// 이미지 데이터 가져오기
Future<Image> getImageData(String fileName) async {
  // 이미지 가져올 수 있는 주소 가져오기
  String imageUrl = await FirebaseStorage.instance.ref('poster/$fileName').getDownloadURL();
  // print(imageUrl);

  // 이미지 관리하는 객체
  Image resultImage = Image.network(imageUrl);

  return resultImage;
}