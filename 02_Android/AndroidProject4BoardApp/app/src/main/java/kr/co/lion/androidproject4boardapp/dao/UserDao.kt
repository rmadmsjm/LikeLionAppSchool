package kr.co.lion.androidproject4boardapp.dao

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.androidproject4boardapp.model.UserModel

class UserDao {
    companion object {
        // 사용자 번호 시퀀스 값 가져오기
        suspend fun getUserSequence(): Int {
            var userSequence = -1

            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("Sequence")
                // 사용자 번호 시퀀스 값을 가지고 있는 문서에 접근할 수 있는 객체 가져오기
                val documentReference = collectionReference.document("UserSequence")
                // 문서 내에 있는 데이터를 가져올 수 있는 객체 가져오기
                val documentSnapShot = documentReference.get().await()
                userSequence = documentSnapShot.getLong("value")?.toInt()!!
            }

            job1.join()

            return userSequence
        }

        // 사용자 시퀀스 값 업데이트
        suspend fun updateUserSequence(userSequence: Int) {
            var job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("Sequence")
                // 사용자 번호 시퀀스 값을 가지고 있는 문서에 접근할 수 있는 객체 가져오기
                val documentReference = collectionReference.document("UserSequence")
                // 저장할 데이터를 담을 HashMap
                val map = mutableMapOf<String, Long>()
                map["value"] = userSequence.toLong()
                // 저장
                documentReference.set(map)
            }

            job1.join()
        }

        // 사용자 정보 저장
        suspend fun insertUserData(userModel: UserModel){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("UserData")
                // 컬럭션에 문서를 추가
                // 문서를 추가할 때 객체나 맵을 지정함
                // 추가된 문서 내부의 필드는 객체가 가진 프로퍼티의 이름이나 맵에 있는 데이터의 이름과 동일하게 결정됨
                collectionReference.add(userModel)
            }
            job1.join()
        }
    }
}