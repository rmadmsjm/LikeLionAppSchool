package kr.co.lion.androidproject4boardapp.dao

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.androidproject4boardapp.model.ReplyModel

class ReplyDao {
    companion object {
        // 댓글 번호 시쿼스 값 가져오기
        suspend fun getReplySequence(): Int {
            var replySequence = -1

            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("Sequence")
                // 댓글 번호 시퀀스 값을 가지고 있는 문서에 접근할 수 있는 객체 가져오기
                val documentReference = collectionReference.document("ReplySequence")
                // 문서내에 있는 데이터를 가져올 수 있는 객체 가져오기
                val documentSnapShot = documentReference.get().await()
                replySequence = documentSnapShot.getLong("value")?.toInt()!!
            }
            job1.join()

            return replySequence
        }

        // 댓글 시퀀스 값 업데이트
        suspend fun updateReplySequence(replySequence:Int){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("Sequence")
                // 댓글 번호 시퀀스 값을 가지고 있는 문서에 접근할 수 있는 객체 가져오기
                val documentReference = collectionReference.document("ReplySequence")
                // 저장할 데이터를 담을 HashMap 만들기
                val map = mutableMapOf<String, Long>()
                map["value"] = replySequence.toLong()
                // 저장
                documentReference.set(map)
            }
            job1.join()
        }

        // 댓글 정보 저장하기
        suspend fun insertReplyData(replyModel: ReplyModel){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("ReplyData")
                // 컬럭션에 문서 추가
                // 문서를 추가할 때 객체나 맵 지정하기
                // 추가된 문서 내부의 필드는 객체가 가진 프로퍼티의 이름이나 맵에 있는 데이터의 이름과 동일하게 결정됨
                collectionReference.add(replyModel)
            }
            job1.join()
        }
    }
}