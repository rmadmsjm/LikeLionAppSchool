package kr.co.lion.androidproject4boardapp.dao

import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.androidproject4boardapp.ReplyState
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

        // 댓글 목록 가져오기
        suspend fun gettingReplyList(contentIdx: Int): MutableList<ReplyModel> {
            // 댓글 정보를 담을 리스트
            val plyList = mutableListOf<ReplyModel>()

            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("ReplyData")
                // 댓글 상태가 정상 상태이고 댓글 번호를 기준으로 내림차순 정렬되게 데이터를 가져올 수 있는 Query 생성하기
                // 댓글 상태가 정상 상태인 것만 가져오기
                var query = collectionReference.whereEqualTo("replyState", ReplyState.REPLY_STATE_NORMAL.num)
                // 글 번호에 해당하는 것만 가져오기
                query = query.whereEqualTo("replayContentIdx", contentIdx)
                // 댓글 번호를 기준으로 내림 차순 정렬
                query = query.orderBy("replyIdx", Query.Direction.DESCENDING)

                val queryShapshot = query.get().await()
                // 가져온 문서의 수 만큼 반복
                queryShapshot.forEach {
                    // 현재 번째의 문서를 객체 받아오기
                    val replyModel = it.toObject(ReplyModel::class.java)
                    // 리스트에 객체 담기
                    plyList.add(replyModel)
                }

            }
            job1.join()

            return plyList
        }

        // 댓글 상태 변경하기
        suspend fun updateReplyState(replyIdx:Int, newState:ReplyState){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("ReplyData")
                // 컬렉션이 가지고 있는 문서들 중에 replyIdx 필드가 지정된 댓글 번호값하고 같은 Document 가져오기
                val query = collectionReference.whereEqualTo("replyIdx", replyIdx).get().await()

                // 저장할 데이터를 담을 HashMap 만들기
                val map = mutableMapOf<String, Any>()
                map["replyState"] = newState.num.toLong()
                // 저장
                // 가져온 문서 중 첫 번째 문서에 접근하여 데이터 수정하기
                query.documents[0].reference.update(map)
            }
            job1.join()
        }
    }
}