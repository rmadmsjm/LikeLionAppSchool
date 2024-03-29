package kr.co.lion.androidproject4boardapp.dao

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.androidproject4boardapp.ContentState
import kr.co.lion.androidproject4boardapp.ContentType
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

        // 이미지 데이터 받아오기
        suspend fun gettingContentImage(context: Context, imageFileName: String, imageView: ImageView) {
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 이미지에 젖ㅂ근할 수 있는 객체 가져오기
                val storageRef = Firebase.storage.reference.child("image/$imageFileName")
                // 이미지의 주소를 가지고 있는 Uri 객체 받아오기
                val imageUri = storageRef.downloadUrl.await()
                // 이미지 데이터를 받아와 imageView에 보여주기
                CoroutineScope(Dispatchers.Main).launch {
                    Glide.with(context).load(imageUri).into(imageView)
                    // imageView 나타나게 하기
                    imageView.visibility = View.VISIBLE
                }
            }

            job1.join()

            // 이미지는 용량이 매우 클 수 있음
            // 즉 이미지 데이터를 내려받는데 시간이 오래 걸릴 수 있음
            // 이미지 데이터를 받아와 보여주는 코루틴을 작업을 끝날 떄까지 대기하지 않음
            // 데이터를 받아오는데 걸리는 오랜 시간 동안 화면에 아무것도 나타나지 않을 수 있기 때문
            // 따라서 이 메서드는 제일 마지막에 호출해야 함 (다른 것들 모두 보여준 후에 호출할 것)
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

        // 글 번호를 이용해 글 데이터를 가져와 반환
        suspend fun selectContentData(contentIdx: Int): ContentModel? {
            var contentModel: ContentModel? = null

            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("ContentData")
                // 컬렉션이 가지고 있는 문서 중 contentIdx 필드가 지정된 글 번호 값과 같은 Document 가져오기
                val querySnapshot = collectionReference.whereEqualTo("contentIdx", contentIdx).get().await()
                // 가져온 글 정보를 객체에 담아 변환 받기
                // contentIdx가 같은 글은 존재할 수 없기 때문에 첫 번째 객체를 바로 추출해서 사용함
                // toObject() : 지정한 클래스를 가지고 객체를 만든 다음 가져온 데이터의 필드의 이름과 동일한 이름의 프로퍼티에 필드의 값을 담음
                contentModel = querySnapshot.documents[0].toObject(ContentModel::class.java)
            }

            job1.join()

            return contentModel
        }

        // 게시글 목록 가져오기
        suspend fun gettingContentList(contentType: Int): MutableList<ContentModel> {
            // 게시글 정보를 담을 리스트
            val contentList = mutableListOf<ContentModel>()

            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("ContentData")
                // 게시글 상태가 정상이고, 게시글 번호를 기준으로 내림차순 정렬이 되도록 데이터 가져오는 쿼리 생성
                // 게시글 상태 정상인 것
                var query = collectionReference.whereEqualTo("contentState", ContentState.CONTENT_STATE_NORMAL.number)
                // 게시글 번호를 기준으로 내림차순 정렬
                query = query.orderBy("contentIdx", Query.Direction.DESCENDING)
                // 만약 전체 게시판이 아닐 경우 게시판 타입이 일치하는 것 가져오기
                if(contentType != ContentType.TYPE_ALL.num) {
                    query = query.whereEqualTo("contentType", contentType)
                }
                // 가져오는 문서
                val querySnapshot = query.get().await()
                // 가져온 문서의 수만큼 반복
                querySnapshot.forEach {
                    // 현재 번째의 문서를 객체로 받아오기
                    val contentModel = it.toObject(ContentModel::class.java)
                    // 리스트에 객체 담기
                    contentList.add(contentModel)
                }
            }

            job1.join()

            return contentList
        }

        // 글 상태 변경하기
        suspend fun updateContentState(contentIdx: Int, newState: ContentState) {
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("ContentData")
                // 글 번호가 contentIdx에 해당하는 문서에 접근할 수 있는 객체 가져오기
                // 컬렉션이 가지고 있는 문서 중 contentIdx 필드가 지정된 글 번호 값과 같은 Document 가져오기
                val query = collectionReference.whereEqualTo("contentIdx", contentIdx).get().await()

                // 저장할 데이터를 담을 HashMap 만들기
                val map = mutableMapOf<String, Any>()
                map["contentState"] = newState.number.toLong()
                // 저장
                // 가져온 문서 중 첫 번째 문서에 접근하여 데이터 수정
                // contentIdx가 같은 글은 존재할 수 없기 때문에 첫 번째 객체를 바로 추출해서 사용함
                query.documents[0].reference.update(map)
            }

            job1.join()
        }

        // 글 데이터 수정하기
        suspend fun updateContentData(contentModel: ContentModel, isRemoveImage: Boolean) {
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체 가져오기
                val collectionReference = Firebase.firestore.collection("ContentData")
                // 글 번호가 contentIdx에 해당하는 문서에 접근할 수 있는 객체 가져오기
                // 컬렉션이 가지고 있는 문서 중 수정할 글 정보 가져오기
                val query = collectionReference.whereEqualTo("contentIdx", contentModel.contentIdx).get().await()

                // 저장할 데이터를 담을 HashMap 만들기
                val map = mutableMapOf<String, Any?>()
                map["contentSubject"] = contentModel.contentSubject
                map["contentType"] = contentModel.contentType
                map["contentText"] = contentModel.contentText
                if(contentModel.contentImage != null) {
                    map["contentImage"] = contentModel.contentImage!!
                }

                // 사용자가 이미지를 삭제했을 경우
                if(isRemoveImage == true) {
                    map["contentImage"] = null
                }

                // 저장
                // 가져온 문서 중 첫 번째 문서에 접근하여 데이터 수정
                // contentIdx가 같은 글은 존재할 수 없기 때문에 첫 번째 객체를 바로 추출해서 사용함
                query.documents[0].reference.update(map)
            }

            job1.join()
        }
    }
}