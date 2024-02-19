package kr.co.lion.android38_filestream

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.android38_filestream.databinding.ActivityMainBinding
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream

// 내부 저장소 : 다른 애플리케이션이 접근할 수 없음, 소량의 데이터를 저장하는 목적으로 사용함
// openFileOutput 메서드와 openFileInput 메서드로 스트림을 추출함
// button, button2 예제

// 외부 저장소의 Android/data : 다른 애플리케이션이 접근할 수 없음, 대량의 데이터를 저장하는 목적으로 사용함
// getExternalFilesDir 메서드를 통해 외부 저장소까지의 경로를 얻어 오고 이를 통해 스트림을 직접 생성하여 사용함
// button3, button4 예제

// 외부 저정소의 Android/data를 제외한 모든 곳 : 애플리케이션에서 파일에 직접 접근하는 것이 불가능
// 단말기에 설치되어 있는 파일 관리 어플리케이션의 Activity를 실행하고 사용자가 선택한 파일의 경로를 얻어올 수 있음
// 이를 통해 스트림을 직접 생성하여 사용함
// button5, button6 예제

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 확인 받을 권한 목록
    val permissionArray = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    // 파일 앱을 실행하기 위한 런처
    lateinit var writeActivityLauncher: ActivityResultLauncher<Intent>
    lateinit var readActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 권한 확인
        // 권한 확인을 묻는 건 Android OS 버전에 따라 다름
        requestPermissions(permissionArray, 0)

        // 외부 저장소 접근을 위한 런처
        val contrat1 = ActivityResultContracts.StartActivityForResult()
        writeActivityLauncher = registerForActivityResult(contrat1) {
            // 파일을 선택하면 resultCode는 RESULT_OK가 들어옴
            if(it.resultCode == RESULT_OK) {
                // 사용자가 선택한 파일의 정보를 가지고 있는 Intent로부터 파일 정보를 가져오기
                if(it.data != null) {
                    // 저장할 파일에 접근할 수 있는 객체로 부터 파일 정보를 가져오기
                    // w : 쓰기, a : 이어 쓰기, r : 읽기
                    val descriptor = contentResolver?.openFileDescriptor(it.data?.data!!, "w")
                    // 스트림 생성
                    val fileOutputStream = FileOutputStream(descriptor?.fileDescriptor)
                    val dataOutputStream = DataOutputStream(fileOutputStream)

                    dataOutputStream.writeInt(300)
                    dataOutputStream.writeDouble(33.33)
                    dataOutputStream.writeBoolean(true)
                    dataOutputStream.writeUTF("문자열3")

                    dataOutputStream.flush()
                    dataOutputStream.close()
                }
            }
        }

        val contract2 = ActivityResultContracts.StartActivityForResult()
        readActivityLauncher = registerForActivityResult(contract2) {
            // 파일을 선택하면 resultCode는 RESULT_OK가 들어옴
            if(it.resultCode == RESULT_OK) {
                // 사용자 선택한 파일의 정보를 가지고 있는 Intent로 부터 파일 정보를 가져오기
                if (it.data != null) {
                    // 저장할 파일에 접근할 수 있는 객체로 부터 파일 정보를 가져오기
                    val descriptor = contentResolver?.openFileDescriptor(it.data?.data!!, "r")
                    // 스트림 생성
                    val fileInputStream = FileInputStream(descriptor?.fileDescriptor)
                    val dataInputStream = DataInputStream(fileInputStream)
                    // 데이터 읽어오기
                    val data1 = dataInputStream.readInt()
                    val data2 = dataInputStream.readDouble()
                    val data3 = dataInputStream.readBoolean()
                    val data4 = dataInputStream.readUTF()

                    dataInputStream.close()
                    fileInputStream.close()

                    activityMainBinding.textView.apply {
                        text = "data1 : ${data1}\n"
                        append("data2 : ${data2}\n")
                        append("data3 : ${data3}\n")
                        append("data4 : $data4")
                    }
                }
            }
        }

        activityMainBinding.apply {
            // 내부 저장소
            // data\data\어플리케이션 패키지\files 폴더에 저장됨
            // 저장한 어플리케이션에서만 접근할 수 있음
            // 저장한 어플리케이션에서만 접근할 수 있기 때문에 코드 상에서 자유로운 접근이 가능하고, 필요한 권한도 없음
            // 다른 어플린케이션이 접근할 수 없으면 사용자가 파일에 접근하는 것도 불가능함
            button.setOnClickListener {
                // MODE_PRIVATE : 덮어 쓰기
                // MODE_APPEND : 이어서 쓰기

                // Stream 가져오기
                val fileOutputStream = openFileOutput("data1.dat", MODE_PRIVATE)
                val dataOutputStream = DataOutputStream(fileOutputStream)
                dataOutputStream.writeInt(100)
                dataOutputStream.writeDouble(11.11)
                dataOutputStream.writeBoolean(true)
                dataOutputStream.writeUTF("문자열1")

                dataOutputStream.flush()
                dataOutputStream.close()

                textView.text = "내부 저장소 쓰기 완료"
            }

            // 내부 저장소 읽기
            button2.setOnClickListener {
                // Stream 가져오기
                // 읽기이기 때문에 Mode 없음
                val fileInputStream = openFileInput("data1.dat")
                val dataInputStream = DataInputStream(fileInputStream)

                // 데이터 읽어오기
                val data1 = dataInputStream.readInt()
                val data2 = dataInputStream.readDouble()
                val data3 = dataInputStream.readBoolean()
                val data4 = dataInputStream.readUTF()

                dataInputStream.close()
                fileInputStream.close()

                textView.apply {
                    text = "data1 : ${data1}\n"
                    append("data2 : ${data2}\n")
                    append("data3 : ${data3}\n")
                    append("data4 : ${data4}")
                }
            }

            // 외부 저장소
            // 외부 저장소 영역의 Android\data\ 폴더에 저장됨
            // Android\data\ 경로에 애플리케이션 패키지 명으로 폴더가 만들어지고, files 폴더도 만들어짐
            // 이곳에 파일이 저장됨
            button3.setOnClickListener {
                // 외부 저장소 까지의 경로를 가져옴
                // null을 넣으면 files 까지의 경로를 가져옴
                val filePath = getExternalFilesDir(null).toString()!!

                // 이를 통해 스트림 생성
                val fileOutputStream = FileOutputStream("${filePath}/data2.dat")
                val dataOutputStream = DataOutputStream(fileOutputStream)

                dataOutputStream.writeInt(200)
                dataOutputStream.writeDouble(22.22)
                dataOutputStream.writeBoolean(false)
                dataOutputStream.writeUTF("문자열2")

                dataOutputStream.flush()
                dataOutputStream.close()

                textView.text = "외부 저장소 앱 데이터 폴더에 저장"
            }

            button4.setOnClickListener {
                // 외부 저장소까지의 경로
                val filePath = getExternalFilesDir(null).toString()
                // 스트림 생성
                val fileInputStream = FileInputStream("${filePath}/data2.dat")
                val dataInputStream = DataInputStream(fileInputStream)

                // 데이터 읽어오기
                val data1 = dataInputStream.readInt()
                val data2 = dataInputStream.readDouble()
                val data3 = dataInputStream.readBoolean()
                val data4 = dataInputStream.readUTF()

                dataInputStream.close()

                textView.apply {
                    text = "data1 : ${data1}\n"
                    append("data2 : ${data2}\n")
                    append("data3 : ${data3}\n")
                    append("data4 : $data4")
                }
            }

            // 파일 어플을 통한 접근
            // 외부 저장소의 Android\data 폴더를 제외한 모든 폴더의 접근은 파일 어플을 실행하여 사용자가 파일을 골라야 접근 가능
            button5.setOnClickListener {
                // 파일 관리 어플의 Activity 실행
                val fileIntent = Intent(Intent.ACTION_CREATE_DOCUMENT)
                fileIntent.addCategory(Intent.CATEGORY_OPENABLE)
                // MimeType 설정
                // MimeType : 파일에 저장되어 있는 데이터의 양식이 무엇인지 나타내는 문자열
                // https://www.iana.org/assignments/media-types/media-types.xhtml#audio
                // 모든 파일 : */*
                // 모든 이미지 파일 : image/*
                // 모든 영상 파일 : video/*
                // 모든 소리 파일 : audio/*
                fileIntent.type = "*/*"
                writeActivityLauncher.launch(fileIntent)
            }

            // 읽어 오기
            button6.setOnClickListener {
                // 파일 관리 어플의 Activity 실행
                val fileIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                fileIntent.type = "*/*"
                readActivityLauncher.launch(fileIntent)
            }
        }
    }
}