package kr.co.lion.androidproject4boardapp.dao

import android.util.Log
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

        // 아이디 중복 확인
        // 입력한 아이디가 저장되어 있는 문서가 있는지 확인
        // 사용할 수 있는 아이디라면 true, 아니라면 false
        suspend fun checkUserIdExist(joinUserID: String): Boolean {
            var chk = false

            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("UserData")
                // UserId 필드가 사용자가 입력한 아이디와 같은 문서 가져오기
                // -> ⭐ 문서 객체를 가지고 있는 리스트 반환
                // -> 가져온 게 없어도 리스트로 반환
                // -> 리스트의 문서 개수가 0일 경우, 존재하지 않는 것
                // whereArrayContains, whereIn : 지정한 배열에 있는 값이 포함되어 있는 것들
                // whereEqualTo : 같은것
                // whereGreaterThan : 큰것
                // whereGreaterThanOrEqualTo : 크거나 같은 것
                // whereLessThan : 작은 것
                // whereLessThanOrEqualTo : 작거나 같은 것
                // whereNotEqualTo : 다른 것
                // (필드의 이름, 값) 형태로 넣기
                val querySnapshot = collectionReference.whereEqualTo("userId", joinUserID).get().await()
                // Log.d("test1234", "$querySnapshot")

                // 문서가 들어있는 리스트의 문서 개수
                // Log.d("test1234", "${querySnapshot.documents.size}")

                // 반환되는 리스트에 담긴 문서 객체가 없다면 존재하는 아이디로 취급
                chk = querySnapshot.isEmpty
                /*
                if(querySnapshot.isEmpty) {
                    chk = true
                }
                 */
            }

            job1.join()

            return chk
        }
    }
}