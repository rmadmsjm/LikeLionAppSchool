package kr.co.lion.androidproject4boardapp.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
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
import kr.co.lion.androidproject4boardapp.dao.UserDao
import kr.co.lion.androidproject4boardapp.databinding.FragmentModifyContentBinding
import kr.co.lion.androidproject4boardapp.model.ContentModel
import kr.co.lion.androidproject4boardapp.viewmodel.ModifyContentViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class ModifyContentFragment : Fragment() {

    lateinit var fragmentModifyContentBinding: FragmentModifyContentBinding
    lateinit var contentActivity: ContentActivity
    lateinit var modifyContentViewModel: ModifyContentViewModel

    // Activity 실행을 위한 런처
    lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    lateinit var albumLauncher: ActivityResultLauncher<Intent>

    // 촬영된 사진이 저장된 경로 정보를 가지고 있는 Uri 객체
    lateinit var contentUri: Uri

    // 현재 글 번호를 담을 변수
    var contentIdx = 0

    // 초기화 메뉴를 누르면 입력 요소에 설정할 값을 가지고 있는 데이터
    var resetSubject = ""
    var resetContentType = 0
    var resetContentText = ""
    var resetImage: Bitmap? = null

    // 이미지 변경 상태를 담을 변수
    var isChangeImage = false
    // 이미지 삭제 상태를 담을 변수
    var isRemoveIamge = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        // fragmentModifyContentBinding = FragmentModifyContentBinding.inflate(inflater)
        fragmentModifyContentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_modify_content, container, false)
        modifyContentViewModel = ModifyContentViewModel()
        fragmentModifyContentBinding.modifyContentViewModel = modifyContentViewModel
        fragmentModifyContentBinding.lifecycleOwner = this

        contentActivity = activity as ContentActivity

        // 글 번호 담기
        contentIdx = arguments?.getInt("contentIdx")!!

        settingToolbar()
        settingBackButton()
        settingCameraLauncher()
        settingAlbumLauncher()
        settingInputForm()
        settingButtonModifyContentImageDelete()

        return fragmentModifyContentBinding.root
    }

    // 툴바 설정
    fun settingToolbar() {
        fragmentModifyContentBinding.apply {
            toolbarModifyContent.apply {
                // 타이틀
                title = "게시글 수정"

                // 네비게이션
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    backProcess()
                }

                // 메뉴
                inflateMenu(R.menu.menu_modify_content)
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        // 카메라
                        R.id.menuItemModifyContentCamera -> {
                            startCameraLauncher()
                        }
                        // 앨범
                        R.id.menuItemModifyContentAlbum -> {
                            startAlbumLauncher()
                        }
                        // 초기화
                        R.id.menuItemModifyContentReset -> {
                            resetInputForm()
                        }
                        // 완료
                        R.id.menuItemModifyContentDone -> {
                            val chk = checkInputForm()

                            if(chk == true) {
                                // contentActivity.removeFragment(ContentFragmentName.MODIFY_CONTENT_FRAGMENT)

                                updateContentData()
                            }
                        }
                    }

                    true
                }
            }
        }
    }

    // BackButton 동작 설정
    fun settingBackButton() {
        contentActivity.onBackPressedDispatcher.addCallback {
            // 뒤로 가기 처리 메서드 호출
            backProcess()
            // 콜백 제거
            remove()
        }
    }

    // 뒤로 가기 처리
    fun backProcess() {
        contentActivity.removeFragment(ContentFragmentName.MODIFY_CONTENT_FRAGMENT)
    }


    // 입력 요소 설정
    fun settingInputForm(){
        // modifyContentViewModel.textFieldModifyContentSubject.value = "제목입니다"
        // modifyContentViewModel.textFieldModifyContentText.value = "내용입니다"
        // modifyContentViewModel.settingContentType(ContentType.TYPE_FREE)

        // 데이터를 받아오는데 걸리는 시간 때문에 입력 요소에 띄어쓰기
        modifyContentViewModel.textFieldModifyContentSubject.value = " "
        modifyContentViewModel.textFieldModifyContentText.value = " "

        CoroutineScope(Dispatchers.Main).launch {
            // 현재 글 번호에 해당하는 글 데이터 가져오기
            val contentModel = ContentDao.selectContentData(contentIdx)

            // 가져온 글 정보 중, 초기화를 위한 데이터를 프포퍼티에 담기
            resetSubject = contentModel?.contentSubject!!
            resetContentType = contentModel?.contentType!!
            resetContentText = contentModel?.contentText!!

            // 가져온 데이터 보여주기
            modifyContentViewModel.textFieldModifyContentSubject.value = contentModel?.contentSubject
            modifyContentViewModel.settingContentType(contentModel?.contentType!!)
            modifyContentViewModel.textFieldModifyContentText.value = contentModel?.contentText

            // 이미지 데이터 보여주기
            if(contentModel?.contentImage != null) {
                ContentDao.gettingContentImage(contentActivity, contentModel.contentImage!!, fragmentModifyContentBinding.imageViewModifyContent)

                // imageView로부터 이미지를 가져와 초기화를 위한 프로퍼티에 담기
                val bitmapDrawable = fragmentModifyContentBinding.imageViewModifyContent.drawable as BitmapDrawable
                resetImage = bitmapDrawable.bitmap
            }
        }
    }

    // 입력 요소 초기화
    fun resetInputForm() {
        // 가져온 데이터 보여주기
        modifyContentViewModel.textFieldModifyContentSubject.value = resetSubject
        modifyContentViewModel.settingContentType(resetContentType)
        modifyContentViewModel.textFieldModifyContentText.value = resetContentText

        // 이미지 데이터 보여주기
        if(resetImage != null) {
            fragmentModifyContentBinding.imageViewModifyContent.setImageBitmap(resetImage)
        }

        // 이미지 변경 및 삭제 상태 초기화
        isChangeImage = false
        isRemoveIamge = false
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

                fragmentModifyContentBinding.imageViewModifyContent.setImageBitmap(bitmap3)
                isChangeImage = true

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

                    fragmentModifyContentBinding.imageViewModifyContent.setImageBitmap(bitmap3)
                    isChangeImage = true
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

    // 유효성 검사
    fun checkInputForm(): Boolean {
        // 입력한 내용 가져오기
        val subject = modifyContentViewModel.textFieldModifyContentSubject.value!!
        val text = modifyContentViewModel.textFieldModifyContentText.value!!

        if(subject.isEmpty()) {
            Tools.showErrorDialog(contentActivity, fragmentModifyContentBinding.textFieldModifyContentSubject, "제목 입력 오류", "제목을 입력해주세요")
            return false
        }

        if(text.isEmpty()) {
            Tools.showErrorDialog(contentActivity, fragmentModifyContentBinding.textFieldModifyContentSubject, "내용 입력 오류", "내용을 입력해주세요")
            return false
        }

        return true
    }

    // 이미지 삭제 버튼
    fun settingButtonModifyContentImageDelete() {
        fragmentModifyContentBinding.buttonModifyContentImageDelete.setOnClickListener {
            // imageView의 이미지 변경
            fragmentModifyContentBinding.imageViewModifyContent.setImageResource(R.drawable.panorama_24px)
            isChangeImage = true
            isRemoveIamge = true
        }
    }

    // 글 정보 업로드 하기
    fun updateContentData() {
        CoroutineScope(Dispatchers.Main).launch {
            // 서버에서의 첨부 이미지 파일 이름
            var serverFileName: String? = null

            // 사용자가 이미지 변경했을 경우, 이미지 삭제 X
            if(isChangeImage == true && isRemoveIamge == false) {
                // imageView의 이미지 데이터를 파일로 저장하기
                Tools.saveImageViewData(contentActivity, fragmentModifyContentBinding.imageViewModifyContent, "uploadTemp.jpg")
                // 서버 파일 이름
                serverFileName = "image_${System.currentTimeMillis()}.jpg"
                // 서버로 업로드하기
                ContentDao.uploadImage(contentActivity, "uploadTemp.jpg", serverFileName)
            }

            // 업로드할 정보 담기
            val contentSubject = modifyContentViewModel.textFieldModifyContentSubject.value!!
            val contentType = modifyContentViewModel.gettingContentType()
            val contentText = modifyContentViewModel.textFieldModifyContentText.value!!

            // 객체에 담기
            // 저장할 데이터를 HashMap에 담아 설정했기 때문에 나머지 값은 변경되지 않음(ContentDao.kt)
            val contentModel = ContentModel(contentIdx, contentSubject, contentType, contentText, null, 0, "", 0)

            // 업로드 된 이미지가 있다면
            if(serverFileName != null) {
                contentModel.contentImage = serverFileName
            }

            // 업로드
            ContentDao.updateContentData(contentModel, isRemoveIamge)

            // 키보드 내리기
            Tools.hideSoftInput(contentActivity)

            // 이전으로 돌아가기
            contentActivity.removeFragment(ContentFragmentName.MODIFY_CONTENT_FRAGMENT)
        }
    }
}