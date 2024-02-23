package kr.co.lion.android51_picture

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import kr.co.lion.android51_picture.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // Activity 실행을 위한 런처
    lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    lateinit var albumLauncher:ActivityResultLauncher<Intent>

    // 촬영된 사진이 저장된 경로 정보를 가지고 있는 Uri 객체
    lateinit var contentUri: Uri

    // 확인할 권한 목록
    val permissionList = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_MEDIA_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 권한 확인
        requestPermissions(permissionList, 0)

        // 사진 촬영을 위한 런처 생성
        val contract = ActivityResultContracts.StartActivityForResult()
        cameraLauncher = registerForActivityResult(contract) {
            // 사진을 사용하겠다고 한 다음 돌아왔을 경우
            if(it.resultCode == RESULT_OK) {
                // 사진 객체 생성
                val bitmap = BitmapFactory.decodeFile(contentUri.path)

                // 회전 각도값 구하기
                val degree = getDegree(contentUri)
                // 회전된 이미지 구하기
                val bitmap2 = rotateBitmap(bitmap, degree.toFloat())
                // 크기 조정한 이미지 구하기
                val bitmap3 = resizeBitmap(bitmap2, 1024)

                activityMainBinding.imageView.setImageBitmap(bitmap3)

                // 사진 파일 삭제
                val file = File(contentUri.path!!)
                file.delete()
            }
        }

        // 앨범 실행을 위한 런처 생성
        val contract2 = ActivityResultContracts.StartActivityForResult()
        albumLauncher = registerForActivityResult(contract2){
            // 사진 선택 완료 후 돌아왔다면
            if(it.resultCode == RESULT_OK) {
                // 선택한 이미지 경로 데이터를 관리하는 Uri 객체 추출하기
                val uri = it.data?.data!!

                if(uri != null) {
                    // Android Q(10) 이상이라면
                    val bitmap = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        // 이미지 생성할 수 있는 객체 생성
                        val source = ImageDecoder.createSource(contentResolver, uri)
                        // Bitmap 생성
                        ImageDecoder.decodeBitmap(source)
                    } else {
                        // 컨텐츠 프로바이더를 통해 이미지 데이터에 접근
                        val cursor = contentResolver.query(uri, null, null, null, null)
                        if(cursor != null) {
                            cursor.moveToNext()

                            // 이미지 경로 가져오기
                            val idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                            val source = cursor.getString(idx)

                            // 이미지 생성
                            BitmapFactory.decodeFile(source)
                        } else {
                            null
                        }
                    }

                    // 회전 각도값 가져오기
                    val degree = getDegree(uri)
                    // 회전 이미지 가져오기
                    val bitmap2 = rotateBitmap(bitmap!!, degree.toFloat())
                    // 크기 줄인 이미지 가져오기
                    val bitmap3 = resizeBitmap(bitmap2, 1024)

                    activityMainBinding.imageView.setImageBitmap(bitmap3)
                }
            }
        }

        activityMainBinding.apply {
            /*
            카메라로 사진 찍기
            step1) res/xml 폴더에 xml 파일을 만들고 이 파일에 사진이 저장될
                   외부 저장소까지의 경로를 기록하기 (이 예시에서는 res/xml/file_path에 해당)
            stpe2) AndroidManifest.xml에 step1에서 만든 파일의 경로 지정하기
             */
            button.setOnClickListener {
                // 촬영한 사진이 저장될 경로
                // 외부 저장소 중 애플리케이션 영역 경로 가져오기
                val rootPath = getExternalFilesDir(null).toString()
                // 이미지 파일명을 포함한 경로
                val picPath = "${rootPath}/tempImage.jpg"
                // File 객체 생성
                val file = File(picPath)
                // 사진이 저장된 위치를 관리할 Uri 생성
                // AndroidManifest.xml에 등록한 provider의 authorities
                val a1 = "kr.co.lion.android51_picture.file_provider"
                contentUri = FileProvider.getUriForFile(this@MainActivity, a1, file)

                if(contentUri != null) {
                    // 실행할 Activity를 카메라 Activity로 지정
                    // 단말기에 설지되어 있는 모든 애플리케이션이 가진 Activity 중에 사진 촬영이 가능한 Activity가 실행됨
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    // 이미지가 저장될 경로를 가지고 있는 Uri 객체를 Intent에 담기
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)
                    // 카메라 Activity 실행
                    cameraLauncher.launch(cameraIntent)
                }
            }

            // 앨범에서 사진 가져오기
            button2.setOnClickListener {
                // 앨범에서 사진을 선택할 수 있도록 셋팅된 Intent 생성하기
                val albumIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                // 실행할 액티비티와 타입 설정(이미지를 선택할 수 있는 것이 뜨게 함)
                albumIntent.setType("image/*")
                // 선택할 수 있는 파일의 MimeType을 설정하기
                // 여기서 선택한 종류의 파일만 선택이 가능함, 여기선 모든 이미졸 설정
                val mimeType = arrayOf("image/*")
                albumIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)

                // 액티비티를 실행
                albumLauncher.launch(albumIntent)
            }
        }
    }

    // 사진의 회전 각도값을 반환하는 메서드
    // ExifInterface : 사진, 영상, 소리 등의 파일에 기록한 정보
    // 위치, 날짜, 조리개 값, 노출 정도 등 다양한 정보가 기록됨
    // ExifInterface 정보에서 사진 회전 각도값을 가져와 그 값만큼 다시 돌려줌
    fun getDegree(uri: Uri) : Int {
        // 사진 정보를 가지고 있는 객체 가져오기
        var exifInterface: ExifInterface? = null

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // 이미지 데이터를 가져올 수 있는 Content Provider의 Uri 추출하기
            // val photoUri = MediaStore.setRequireOriginal(uri)

            // ExifInterface 정보를 읽어올 스트림 추출하기
            val inputStream = contentResolver.openInputStream(uri)!!

            // ExifInterface 객체 생성하기
            exifInterface = ExifInterface(inputStream)
        } else {
            // ExifInterface 객체 생성하기
            exifInterface = ExifInterface(uri.path!!)
        }

        if(exifInterface != null) {
            // 반환할 각도값을 담을 변수
            var degree = 0

            // ExifInterface 객체에서 회전 각도값 가져오기
            val ori = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)

            degree = when(ori) {
                ExifInterface.ORIENTATION_ROTATE_90 -> 90
                ExifInterface.ORIENTATION_ROTATE_180 -> 180
                ExifInterface.ORIENTATION_ROTATE_270 -> 270
                else -> 0
            }

            return degree
        }

        return 0
    }

    // 이미지 회전시키는 메서드
    fun rotateBitmap(bitmap: Bitmap, degree: Float) : Bitmap {
        // 회전 이미지를 생성하기 위한 변환 행렬
        val matrix = Matrix()
        matrix.postRotate(degree)

        // 회전 행렬을 적용하여 회전된 이미지 생성하기
        // 첫 번째 : 원본 이미지
        // 두 번째, 세 번째 : 원본 이미지에서 사용할 부분의 좌측 상단 x, y 좌표
        // 네 번째, 다섯 번째 : 원본 이미지에서 사용할 부분의 가로 세로 길이
        //                    여기에서는 이미지 데이터 전체를 사용할 것이기 때문에 전체 영역으로 잡음
        // 여섯 번째 : 변환 행렬, 적용한 변환 행렬이 무엇이냐에 따라 이미지 변형 방식이 달라짐
        val rotateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)

        return rotateBitmap
    }

    // 이미지 사이즈 조절 메서드
    fun resizeBitmap(bitmap: Bitmap, targetWidth: Int) : Bitmap {
        // 이미지의 확대/축소 비율 구하기
        val ratio = targetWidth.toDouble() / bitmap.width.toDouble()
        // 세로 길이 구하기
        val targetHeight = (bitmap.height * ratio).toInt()
        // 크기 조정한 Bitmap 생성
        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, false)

        return resizedBitmap
    }
}