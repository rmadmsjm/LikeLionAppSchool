package com.lion.project2.dao

import com.lion.project2.model.AnimalModel
import java.io.*

class AnimalInfoDAO {

    companion object {
        // 파일이 있는지 확인하는 메서드
        fun isExistAnimalInfoFile() : Boolean {
            // File 객체 생성
            val saveFile = File("animalData.dat")
            // 파일이 존재하는지 확인
            // 파일이 있으면 true를 반환하고 없으면 false를 반환함
            return saveFile.exists()
        }

        // 파일에서 데이터를 읽어와 ArrayList에 담고 반환함
        fun getAnimalInfoList(): ArrayList<AnimalModel>? {
            // 파일이 없다면 null 반환함
            if (isExistAnimalInfoFile() == false) {
                return null
            }
            // 파일에서 데이터를 읽어올 기본 스트림을 생성.
            val fileInputStream = FileInputStream("animalData.dat")
            // 읽어온 데이터를 객체로 복원하는 필터 스트림을 생성
            val objectInputStraem = ObjectInputStream(fileInputStream)
            // 읽어오기
            val resultList = objectInputStraem.readObject() as ArrayList<AnimalModel>
            // 파일을 닫기
            objectInputStraem.close()
            fileInputStream.close()

            // 객체 반환
            return resultList
        }

        // 데이터를 파일에 씀
        fun saveAnimalInfoList(dataList: ArrayList<AnimalModel>) {
            // 파일에 데이터를 쓰기 위한 기본 스트림 생성
            // 쓰기를 할 때는 파일이 없을 경우 파일이 생성됨
            var fileOutputStream = FileOutputStream("animalData.dat")
            // 객체를 파일에 쓸 수 있는 형태로 가공하는 필터스트림을 생성해서 연결함
            val objectOutputStream = ObjectOutputStream(fileOutputStream)
            // 객체를 파일에 씀
            objectOutputStream.writeObject(dataList)
            // 파일 닫기
            objectOutputStream.flush()
            objectOutputStream.close()
            fileOutputStream.close()
        }
    }
}