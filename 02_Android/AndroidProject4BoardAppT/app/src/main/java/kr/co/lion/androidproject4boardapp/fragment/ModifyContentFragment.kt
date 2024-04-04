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
    // 초기화 메뉴를 누르면 입력요소에 설정할 값을 가지고 있는 데이터
    var resetSubject = ""
    var resetContentType = 0
    var resetContentText = ""
    var resetImage: Bitmap? = null

    // 사용자에 의해서 이미지가 변경되었는지..
    var isChangeImage = false
    // 사용자가 이미지를 삭제했는지
    var isRemoveImage = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        // fragmentModifyContentBinding = FragmentModifyContentBinding.inflate(inflater)
        fragmentModifyContentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_modify_content, container, false)
        modifyContentViewModel = ModifyContentViewModel()
        fragmentModifyContentBinding.modifyContentViewModel = modifyContentViewModel
        fragmentModifyContentBinding.lifecycleOwner = this

        contentActivity = activity as ContentActivity

        // 글 번호를 담는다.
        contentIdx = arguments?.getInt("contentIdx")!!

        settingToolbarModifyContent()
        settingCameraLauncher()
        settingAlbumLauncher()
        settingInputForm()
        settingButtonModifyContentImageDelete()

        return fragmentModifyContentBinding.root
    }

    // 툴바 설정
    fun settingToolbarModifyContent(){
        fragmentModifyContentBinding.apply {
            toolbarModifyContent.apply {
                // 타이틀
                title = "글 수정"
                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    contentActivity.removeFragment(ContentFragmentName.MODIFY_CONTENT_FRAGMENT)
                }

                // 메뉴
                inflateMenu(R.menu.menu_modify_content)
                setOnMenuItemClickListener{

                    when(it.itemId){
                        // 카메라
                        R.id.menuItemModifyContentCamera -> {
                            startCameraLauncher()
                        }
                        // 앨범
                        R.id.menuItemModifyContentAlbum -> {
                            startAlbumLauncher()
                        }
                        //  초기화
                        R.id.menuItemModifyContentReset -> {
                            resetInputForm()
                        }
                        // 완료
                        R.id.menuItemModifyContentDone -> {
                            // 입력 요소 검사
                            val chk = checkInputForm()

                            if(chk == true){
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

    // 입력 요소 초기화
    fun settingInputForm(){
        // 입력 요소에 띄어쓰기를 넣어준다.
        modifyContentViewModel.textFieldModifyContentSubject.value = " "
        modifyContentViewModel.textFieldModifyContentText.value = " "

        CoroutineScope(Dispatchers.Main).launch {

            // 현재 글 번호에 해당하는 글 데이터를 가져온다.
            val contentModel = ContentDao.selectContentData(contentIdx)

            // 가져온 글 정보 중에 초기화를 위한 데이터를 프로퍼티에 담아준다.
            // 초기화 메뉴를 누르면 입력요소에 설정할 값을 가지고 있는 데이터
            resetSubject = contentModel?.contentSubject!!
            resetContentType = contentModel?.contentType!!
            resetContentText = contentModel?.contentText!!


            // 가져온 데이터를 보여준다.
            modifyContentViewModel.textFieldModifyContentSubject.value = contentModel?.contentSubject
            modifyContentViewModel.settingContentType(contentModel?.contentType!!)
            modifyContentViewModel.textFieldModifyContentText.value = contentModel?.contentText

            // 이미지 데이터를 불러온다.
            if(contentModel?.contentImage != null) {
                ContentDao.gettingContentImage(contentActivity, contentModel.contentImage!!, fragmentModifyContentBinding.imageViewModifyContent)

                // 이미지뷰로 부터 이미지를 가져와 초기화를 위한 프로퍼티에 담아준다.
                val bitmapDrawable = fragmentModifyContentBinding.imageViewModifyContent.drawable as BitmapDrawable
                resetImage = bitmapDrawable.bitmap
            }
        }
    }

    // 입력 요소를 초기화 한다.
    fun resetInputForm(){
        // 가져온 데이터를 보여준다.
        modifyContentViewModel.textFieldModifyContentSubject.value = resetSubject
        modifyContentViewModel.settingContentType(resetContentType)
        modifyContentViewModel.textFieldModifyContentText.value = resetContentText

        // 이미지 데이터를 불러온다.
        if(resetImage != null) {
            fragmentModifyContentBinding.imageViewModifyContent.setImageBitmap(resetImage)
        }

        // 사용자가 이미지를 변경했는지의 값을 초기화한다.
        isChangeImage = false
        isRemoveImage = false
    }

    // 카메라 런처 설정
    fun settingCameraLauncher(){
        val contract1 = ActivityResultContracts.StartActivityForResult()
        cameraLauncher = registerForActivityResult(contract1){
            // 사진을 사용하겠다고 한 다음에 돌아왔을 경우
            if(it.resultCode == AppCompatActivity.RESULT_OK){
                // 사진 객체를 생성한다.
                val bitmap = BitmapFactory.decodeFile(contentUri.path)

                // 회전 각도값을 구한다.
                val degree = Tools.getDegree(contentActivity, contentUri)
                // 회전된 이미지를 구한다.
                val bitmap2 = Tools.rotateBitmap(bitmap, degree.toFloat())
                // 크기를 조정한 이미지를 구한다.
                val bitmap3 = Tools.resizeBitmap(bitmap2, 1024)

                fragmentModifyContentBinding.imageViewModifyContent.setImageBitmap(bitmap3)
                isChangeImage = true

                // 사진 파일을 삭제한다.
                val file = File(contentUri.path)
                file.delete()
            }
        }
    }

    // 카메라 런처를 실행하는 메서드
    fun startCameraLauncher(){
        // 촬영한 사진이 저장될 경로를 가져온다.
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

    // 앨범 런처를 실행하는 메서드
    fun startAlbumLauncher(){
        // 앨범에서 사진을 선택할 수 있도록 셋팅된 인텐트를 생성한다.
        val albumIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        // 실행할 액티비티의 타입을 설정(이미지를 선택할 수 있는 것이 뜨게 한다)
        albumIntent.setType("image/*")
        // 선택할 수 있는 파들의 MimeType을 설정한다.
        // 여기서 선택한 종류의 파일만 선택이 가능하다. 모든 이미지로 설정한다.
        val mimeType = arrayOf("image/*")
        albumIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
        // 액티비티를 실행한다.
        albumLauncher.launch(albumIntent)
    }

    // 입력 요소의 유효성 검사
    fun checkInputForm():Boolean{
        // 입력한 내용을 가져온다.
        val subject = modifyContentViewModel.textFieldModifyContentSubject.value!!
        val text = modifyContentViewModel.textFieldModifyContentText.value!!

        if(subject.isEmpty()){
            Tools.showErrorDialog(contentActivity, fragmentModifyContentBinding.textFieldModifyContentSubject,
                "제목 입력 오류", "제목을 입력해주세요")
            return false
        }

        if(text.isEmpty()){
            Tools.showErrorDialog(contentActivity, fragmentModifyContentBinding.textFieldModifyContentText,
                "내용 입력 오류", "내용을 입력해주세요")
            return false
        }

        return true
    }

    // 이미지 삭제 버튼
    fun settingButtonModifyContentImageDelete(){
        fragmentModifyContentBinding.apply {
            buttonModifyContentImageDelete.setOnClickListener {
                // 이미지 뷰의 이미지를 변경한다.
                fragmentModifyContentBinding.imageViewModifyContent.setImageResource(R.drawable.panorama_24px)
                isChangeImage = true
                isRemoveImage = true
            }
        }
    }

    // 글 정보를 업로드하는 메서드
    fun updateContentData(){
        CoroutineScope(Dispatchers.Main).launch {
            // 서버에서의 첨부 이미지 파일 이름
            var serverFileName: String? = null

            // 사용자가 이미지를 변경하였다면(삭제 X)
            if (isChangeImage == true && isRemoveImage == false) {
                // 이미지의 뷰의 이미지 데이터를 파일로 저장한다.
                Tools.saveImageViewData(
                    contentActivity,
                    fragmentModifyContentBinding.imageViewModifyContent,
                    "uploadTemp.jpg"
                )
                // 서버에서의 파일 이름
                serverFileName = "image_${System.currentTimeMillis()}.jpg"
                // 서버로 업로드한다.
                ContentDao.uploadImage(contentActivity, "uploadTemp.jpg", serverFileName)
            }

            // 업로드할 정보를 담아준다.
            val contentSubject = modifyContentViewModel.textFieldModifyContentSubject.value!!
            val contentType = modifyContentViewModel.gettingContentType()
            val contentText = modifyContentViewModel.textFieldModifyContentText.value!!

            // 객체에 담아준다.
            val contentModel =
                ContentModel(contentIdx, contentSubject, contentType, contentText, null, 0, "", 0)

            // 업로드된 이미지가 있다면
            if (serverFileName != null) {
                contentModel.contentImage = serverFileName
            }

            // 업로드한다.
            ContentDao.updateContentData(contentModel, isRemoveImage)

            // 이전으로 돌아간다.
            Tools.hideSoftInput(contentActivity)
            contentActivity.removeFragment(ContentFragmentName.MODIFY_CONTENT_FRAGMENT)
        }
    }
}








