package kr.co.lion.androidproject4boardapp.dao

import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.androidproject4boardapp.ContentState
import kr.co.lion.androidproject4boardapp.ContentType
import kr.co.lion.androidproject4boardapp.ReplyState
import kr.co.lion.androidproject4boardapp.model.ContentModel
import kr.co.lion.androidproject4boardapp.model.ReplyModel

class ReplyDao {
    companion object{
        // 댓글 번호 시퀀스값을 가져온다.
        suspend fun getReplySequence():Int{

            var replySequence = -1

            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = Firebase.firestore.collection("Sequence")
                // 댓글 번호 시퀀스 값을 가지고 있는 문서에 접근할 수 있는 객체를 가져온다.
                val documentReference = collectionReference.document("ReplySequence")
                // 문서내에 있는 데이터를 가져올 수 있는 객체를 가져온다.
                val documentSnapShot = documentReference.get().await()
                replySequence = documentSnapShot.getLong("value")?.toInt()!!
            }
            job1.join()

            return replySequence
        }

        // 댓글 시퀀스 값을 업데이트 한다.
        suspend fun updateReplySequence(replySequence:Int){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = Firebase.firestore.collection("Sequence")
                // 댓글 번호 시퀀스 값을 가지고 있는 문서에 접근할 수 있는 객체를 가져온다.
                val documentReference = collectionReference.document("ReplySequence")
                // 저장할 데이터를 담을 HashMap을 만들어준다.
                val map = mutableMapOf<String, Long>()
                map["value"] = replySequence.toLong()
                // 저장한다.
                documentReference.set(map)
            }
            job1.join()
        }

        // 댓글 정보를 저장한다.
        suspend fun insertReplyData(replyModel: ReplyModel){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = Firebase.firestore.collection("ReplyData")
                // 컬럭션에 문서를 추가한다.
                // 문서를 추가할 때 객체나 맵을 지정한다.
                // 추가된 문서 내부의 필드는 객체가 가진 프로퍼티의 이름이나 맵에 있는 데이터의 이름과 동일하게 결정된다.
                collectionReference.add(replyModel)
            }
            job1.join()
        }

        // 댓글 목록을 가져온다.
        suspend fun gettingReplyList(contentIdx:Int):MutableList<ReplyModel>{
            // 댓글 정보를 담을 리스트
            val plyList = mutableListOf<ReplyModel>()

            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = Firebase.firestore.collection("ReplyData")
                // 댓글 상태가 정상 상태이고 댓글 번호를 기준으로 내림차순 정렬되게 데이터를 가져올 수 있는
                // Query를 생성한다.
                // 댓글 상태가 정상 상태인 것만..
                var query = collectionReference.whereEqualTo("replyState", ReplyState.REPLY_STATE_NORMAL.num)
                // 글 번호에 해당하는 것들만
                query = query.whereEqualTo("replayContentIdx", contentIdx)
                // 댓글 번호를 기준으로 내림 차순 정렬..
                query = query.orderBy("replyIdx", Query.Direction.DESCENDING)

                val queryShapshot = query.get().await()
                // 가져온 문서의 수 만큼 반복한다.
                queryShapshot.forEach {
                    // 현재 번째의 문서를 객체로 받아온다.
                    val replyModel = it.toObject(ReplyModel::class.java)
                    // 객체를 리스트에 담는다.
                    plyList.add(replyModel)
                }

            }
            job1.join()

            return plyList
        }

        // 댓글의 상태를 변경하는 메서드
        suspend fun updateReplyState(replyIdx:Int, newState:ReplyState){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = Firebase.firestore.collection("ReplyData")
                // 컬렉션이 가지고 있는 문서들 중에 replyIdx 필드가 지정된 댓글 번호값하고 같은 Document들을 가져온다.
                val query = collectionReference.whereEqualTo("replyIdx", replyIdx).get().await()

                // 저장할 데이터를 담을 HashMap을 만들어준다.
                val map = mutableMapOf<String, Any>()
                map["replyState"] = newState.num.toLong()
                // 저장한다.
                // 가져온 문서 중 첫 번째 문서에 접근하여 데이터를 수정한다.
                query.documents[0].reference.update(map)
            }
            job1.join()
        }
    }
}