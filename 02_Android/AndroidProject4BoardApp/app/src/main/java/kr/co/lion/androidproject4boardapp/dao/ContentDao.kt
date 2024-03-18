package kr.co.lion.androidproject4boardapp.dao

import android.content.Context
import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.androidproject4boardapp.model.ContentModel
import kr.co.lion.androidproject4boardapp.model.UserModel
import java.io.File

class ContentDao {
    companion object {
        // 이미지 데이터를 firebase storage에 업로드
        // fileName : 단말기 상의 파일 이름
        // uploadFileName : 서버 상의 파일 이름 (절대 중복되지 않는 이름으로 관리)
        suspend fun uploadImage(context: Context, fileName: String, uploadFileName: String) {
            // 외부저장소까지의 경로 가져오기
            val filePath = context.getExternalFilesDir(null).toString()
            // 서버로 업로드할 파일 경로
            val file = File("${filePath}/${fileName}")
            val uri = Uri.fromFile(file)

            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // storage에 접근할 수 있는 객체 가져오기
                // 폴더의 이름과 파일 이름을 저장하기
                val storageRef = Firebase.storage.reference.child("image/$uploadFileName")
                // 업로드
                // uri : 단말기 상의 파일 이름
                storageRef.putFile(uri)
            }

            job1.join()
        }

        // 게시글 번호 시쿼스 값 가져오기
        suspend fun getContentSequence():Int{
            var contentSequence = -1

            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("Sequence")
                // 게시글 번호 시퀀스 값을 가지고 있는 문서에 접근할 수 있는 객체 가져오기
                val documentReference = collectionReference.document("ContentSequence")
                // 문서내에 있는 데이터를 가져올 수 있는 객체 가져오기
                val documentSnapShot = documentReference.get().await()
                contentSequence = documentSnapShot.getLong("value")?.toInt()!!
            }
            job1.join()

            return contentSequence
        }

        // 게시글 번호 시퀀스 값 업데이트
        suspend fun updateContentSequence(contentSequence: Int) {
            var job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("Sequence")
                // 게시글 번호 시퀀스 값을 가지고 있는 문서에 접근할 수 있는 객체 가져오기
                val documentReference = collectionReference.document("ContentSequence")
                // 저장할 데이터를 담을 HashMap
                val map = mutableMapOf<String, Long>()
                map["value"] = contentSequence.toLong()
                // 저장
                documentReference.set(map)
            }

            job1.join()
        }

        // 게시글 정보 저장
        suspend fun insertContentData(contentModel: ContentModel){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("ContentData")
                // 컬럭션에 문서를 추가
                // 문서를 추가할 때 객체나 맵을 지정함
                // 추가된 문서 내부의 필드는 객체가 가진 프로퍼티의 이름이나 맵에 있는 데이터의 이름과 동일하게 결정됨
                collectionReference.add(contentModel)
            }
            job1.join()
        }
    }
}