package kr.co.lion.androidproject4boardapp.fragment

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.androidproject4boardapp.ContentActivity
import kr.co.lion.androidproject4boardapp.ContentFragmentName
import kr.co.lion.androidproject4boardapp.ContentState
import kr.co.lion.androidproject4boardapp.ContentType
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.Tools
import kr.co.lion.androidproject4boardapp.dao.ContentDao
import kr.co.lion.androidproject4boardapp.databinding.FragmentAddContentBinding
import kr.co.lion.androidproject4boardapp.model.ContentModel
import kr.co.lion.androidproject4boardapp.viewmodel.AddContentViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddContentFragment : Fragment() {

    lateinit var fragmentAddContentBinding: FragmentAddContentBinding
    lateinit var contentActivity: ContentActivity
    lateinit var addContentViewModel: AddContentViewModel

    // Activity 실행을 위한 런처
    lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    lateinit var albumLauncher:ActivityResultLauncher<Intent>

    // 촬영된 사진이 저장된 경로 정보를 가지고 있는 Uri 객체
    lateinit var contentUri: Uri

    // 이미지 첨부한 적 있는지
    var isAddPicture = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        // fragmentAddContentBinding = FragmentAddContentBinding.inflate(inflater)
        fragmentAddContentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_content, container, false)
        addContentViewModel = AddContentViewModel()
        fragmentAddContentBinding.addContentViewModel = addContentViewModel
        fragmentAddContentBinding.lifecycleOwner = this

        contentActivity = activity as ContentActivity

        settingToolbar()
        settingInputForm()
        settingCameraLauncher()
        settingAlbumLauncher()

        return fragmentAddContentBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentAddContentBinding.apply {
            toolbarAddContent.apply {
                // 타이틀
                title = "게시글 작성"

                // 네비게이션
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    contentActivity.removeFragment(ContentFragmentName.ADD_CONTENT_FRAGMENT)
                }

                // 메뉴
                inflateMenu(R.menu.menu_add_content)
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        // 카메라
                        R.id.menuItemAddContentCamera -> {
                            startCameraLauncher()
                        }
                        // 앨범
                        R.id.menuItemAddContentAlbum -> {
                            startAlbumLauncher()
                        }
                        // 초기화
                        R.id.menuItemAddContentReset -> {
                            settingInputForm()
                        }
                        // 완료
                        R.id.menuItemAddContentDone -> {
                            val chk = checkInputForm()

                            if(chk == true) {
                                // 글 데이터 업로드
                                uploadContentData()

                                // ReadContentFragment로 이동
                                // contentActivity.replaceFragment(ContentFragmentName.READ_CONTENT_FRAGMENT, true, true, null)
                            }
                        }
                    }
                    true
                }
            }
        }
    }

    // 입력 요소 설정
    fun settingInputForm() {
        addContentViewModel.textFieldAddContentSubject.value = ""
        addContentViewModel.textFieldAddContentText.value = ""
        addContentViewModel.settingContentType(ContentType.TYPE_FREE)

        fragmentAddContentBinding.imageViewAddContent.setImageResource(R.drawable.panorama_24px)
        isAddPicture = false

        Tools.showSoftInput(contentActivity, fragmentAddContentBinding.textFieldAddContentSubject)
    }

    // 카메라 런처 설정
    fun settingCameraLauncher() {
        val contrat1 = ActivityResultContracts.StartActivityForResult()
        cameraLauncher = registerForActivityResult(contrat1) {
            // 사진을 사용하겠다고 한 다음에 돌아왔을 경우
            if(it.resultCode == AppCompatActivity.RESULT_OK){
                // 사진 객체를 생성
                val bitmap = BitmapFactory.decodeFile(contentUri.path)

                // 회전 각도값 구하기
                val degree = Tools.getDegree(contentActivity, contentUri)
                // 회전된 이미지 구하기
                val bitmap2 = Tools.rotateBitmap(bitmap, degree.toFloat())
                // 크기를 조정한 이미지 구하기
                val bitmap3 = Tools.resizeBitmap(bitmap2, 1024)

                fragmentAddContentBinding.imageViewAddContent.setImageBitmap(bitmap3)
                isAddPicture = true

                // 사진 파일 삭제
                val file = File(contentUri.path)
                file.delete()
            }
        }
    }

    // 카메라 런처 실행
    fun startCameraLauncher() {
        // 촬영한 사진이 저장될 경로 가져오기
        contentUri = Tools.getPictureUri(contentActivity, "kr.co.lion.androidproject4boardapp.file_provider")

        if(contentUri != null){
            // 실행할 액티비티를 카메라 액티비티로 지정한다.
            // 단말기에 설치되어 있는 모든 애플리케이션이 가진 액티비티 중에 사진촬영이
            // 가능한 액티비가 실행된다.
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // 이미지가 저장될 경로를 가지고 있는 Uri 객체를 인텐트에 담아준다.
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)
            // 카메라 액티비티 실행
            cameraLauncher.launch(cameraIntent)
        }
    }

    // 앨범 런처 설정
    fun settingAlbumLauncher() {
        // 앨범 실행을 위한 런처
        val contract2 = ActivityResultContracts.StartActivityForResult()
        albumLauncher = registerForActivityResult(contract2){
            // 사진 선택을 완료한 후 돌아왔다면
            if(it.resultCode == AppCompatActivity.RESULT_OK){
                // 선택한 이미지의 경로 데이터를 관리하는 Uri 객체를 추출한다.
                val uri = it.data?.data
                if(uri != null){
                    // 안드로이드 Q(10) 이상이라면
                    val bitmap = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                        // 이미지를 생성할 수 있는 객체를 생성한다.
                        val source = ImageDecoder.createSource(contentActivity.contentResolver, uri)
                        // Bitmap을 생성한다.
                        ImageDecoder.decodeBitmap(source)
                    } else {
                        // 컨텐츠 프로바이더를 통해 이미지 데이터에 접근한다.
                        val cursor = contentActivity.contentResolver.query(uri, null, null, null, null)
                        if(cursor != null){
                            cursor.moveToNext()

                            // 이미지의 경로를 가져온다.
                            val idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                            val source = cursor.getString(idx)

                            // 이미지를 생성한다
                            BitmapFactory.decodeFile(source)
                        }  else {
                            null
                        }
                    }

                    // 회전 각도값을 가져온다.
                    val degree = Tools.getDegree(contentActivity, uri)
                    // 회전 이미지를 가져온다
                    val bitmap2 = Tools.rotateBitmap(bitmap!!, degree.toFloat())
                    // 크기를 줄인 이미지를 가져온다.
                    val bitmap3 = Tools.resizeBitmap(bitmap2, 1024)

                    fragmentAddContentBinding.imageViewAddContent.setImageBitmap(bitmap3)
                    isAddPicture = true
                }
            }
        }
    }

    // 앨범 런처 실행
    fun startAlbumLauncher() {
        // 앨범에서 사진을 선택할 수 있도록 셋팅된 인텐트 생성
        val albumIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        // 실행할 액티비티의 타입 설정(이미지를 선택할 수 있는 것이 뜨게 함)
        albumIntent.setType("image/*")
        // 선택할 수 있는 파들의 MimeType 설정
        // 여기서 선택한 종류의 파일만 선택이 가능. 모든 이미지로 설정
        val mimeType = arrayOf("image/*")
        albumIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
        // 액티비티 실행
        albumLauncher.launch(albumIntent)
    }

    // 입력 요소 유효성 검사
    fun checkInputForm(): Boolean {
        // 입력한 내용 가져오기
        val subject = addContentViewModel.textFieldAddContentSubject.value!!
        val text = addContentViewModel.textFieldAddContentText.value!!

        if(subject.isEmpty()) {
            Tools.showErrorDialog(contentActivity, fragmentAddContentBinding.textFieldAddContentSubject, "제목 입력 오류", "제목을 입력해주세요")
            return false
        }

        if(text.isEmpty()) {
            Tools.showErrorDialog(contentActivity, fragmentAddContentBinding.textFieldAddContentText, "내용 입력 오류", "내용을 입력해주세요")
            return false
        }

        return true
    }

    // 글 작성 처리
    fun uploadContentData() {
        CoroutineScope(Dispatchers.IO).launch {
            // 서버에서의 첨부 이미지 파일 이름
            var serverFileName: String? = null

            // 첨부된 이미지가 있다면
            if(isAddPicture == true) {
                // imageView의 이미지 데이터를 파일로 저장하기
                Tools.saveImageViewData(contentActivity, fragmentAddContentBinding.imageViewAddContent, "uploadTemp.jpg")
                // 서버 파일 이름
                serverFileName = "image_${System.currentTimeMillis()}.jpg"
                // 서버로 업로드하기
                ContentDao.uploadImage(contentActivity, "uploadTemp.jpg", serverFileName)
            }

            // 게시글 시퀀스 값 가져오기
            val contentSequence = ContentDao.getContentSequence()
            // 게시글 시퀀스 값 업데이트
            ContentDao.updateContentSequence(contentSequence + 1)

            // 업로드할 정보 담기
            val contentIdx = contentSequence + 1
            val contentSubject = addContentViewModel.textFieldAddContentSubject.value!!
            val contentType = addContentViewModel.gettingContentType().num
            val contentText = addContentViewModel.textFieldAddContentText.value!!
            val contentImage = serverFileName
            val contentWriterIdx = contentActivity.loginUserIdx
            val sdf = SimpleDateFormat("yyyy.MM.dd")
            val contentWriteDate = sdf.format(Date())
            val contentState = ContentState.CONTENT_STATE_NORMAL.number

            val contentModel = ContentModel(contentIdx, contentSubject, contentType, contentText, contentImage, contentWriterIdx, contentWriteDate, contentState)

            // 업로드
            ContentDao.insertContentData(contentModel)

            // ReadContentFragment로 이동
            Tools.hideSoftInput(contentActivity)
            contentActivity.replaceFragment(ContentFragmentName.READ_CONTENT_FRAGMENT, true, true, null)
        }
    }
}