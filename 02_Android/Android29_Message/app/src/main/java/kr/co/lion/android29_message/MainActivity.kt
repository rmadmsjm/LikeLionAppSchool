package kr.co.lion.android29_message

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kr.co.lion.android29_message.databinding.ActivityMainBinding
import kr.co.lion.android29_message.databinding.CustomDialogBinding

// Toast : 잠깐 보여줬다가 사라지는 메시지
// 어플 화면이 떠있지 않을 때 주로 사용함

// SnackBar : 잠깐 보여졌다가 사라지는 메시지
// 어플 화면이 떠있을 경우 사용함
// Toast와 다르게 지속적으로 띄울 수 있으며 ACtion을 두어 이벤트를 설정할 수 있음

// Dialog : 애플리케이션 화면에 메시지를 띄울 때 사용
// 사용자가 버튼을 눌러야만 없어지는 메시지

// Notification : 단말기의 알림 메시지 창에 나타나는 메시지
// 메시지를 통해 어플 실행을 유도하기 위해 사용
// Android 8 이상부터는 메시지를 관리하는 채널을 등록해야 함
// Android 13 이상부터는 알림 메시지를 보여줄 수 잇는 권한을 확인 받아야 함

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // Notification 메시지 사용을 위한 관한
    val permissionList = arrayOf(
        Manifest.permission.POST_NOTIFICATIONS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 알림 메시지 사용을 위한 권한 확인
        requestPermissions(permissionList, 0)

        // Notification Message 사용을 위한 채녈 등록
        addNotificationChannel("c1", "메시지 채널 1")
        addNotificationChannel("c2", "메시지 채널 2")

        activityMainBinding.apply {
            buttonToast.setOnClickListener {
                // Toast 객체 생성
                // 두 번째 : 메시지
                // 세 번째 : 표시할 시간
                val t1 = Toast.makeText(this@MainActivity, "토스트 메시지", Toast.LENGTH_SHORT)
                // Toast 메시지 보여주기
                t1.show()
            }

            buttonSnackBar.setOnClickListener {
                // SnackBar 객체 생성
                // 마지막 매개변수 : 메시지가 보여질 시간
                // LENGTH_SHORT : 적당히 짧은 시간
                // LENGTH_LONG : 적당히 긴 시간
                // LENGTH_INDEFINITE : 개발자가 코드로 없애거나 다른 스낵바 메시지가 뜰 때까지 보여줌
                //                     보통 Action을 줄 경우 사용함
//                val snackbar = Snackbar.make(it, "snackBar", Snackbar.LENGTH_SHORT)
                val snackbar = Snackbar.make(it, "snackBar", Snackbar.LENGTH_INDEFINITE)

                // 메시지 색상
                snackbar.setTextColor(Color.RED)
                // 배경색
                snackbar.setBackgroundTint(Color.BLUE)
                // 애니메이션 종류
//                snackbar.animationMode = Snackbar.ANIMATION_MODE_FADE // 기본
                snackbar.animationMode = Snackbar.ANIMATION_MODE_SLIDE

                // Action 설정
                snackbar.setAction("Action") {
                    textView.text = "Action을 눌렀습니다"
                }

                // SnackBar 보여주기
                snackbar.show()
            }

            buttonBasicDialog.setOnClickListener {
                val builder = MaterialAlertDialogBuilder(this@MainActivity).apply {
                    // 타이틀
                    setTitle("기본 다이얼로그")
                    // 메시지
                    setMessage("기본 다이얼로그 입니다")
                    // 중립 버튼
                    setNeutralButton("Neutral") { dialogInterface: DialogInterface, i: Int ->
                        textView.text = "기본 다이얼로그 : Neutral"
                    }
                    // 긍정 버튼
                    setPositiveButton("Positive") { dialogInterface: DialogInterface, i: Int ->
                        textView.text = "기본 다이얼로그 : Positive"
                    }
                    // 부정 버튼
                    setNegativeButton("Negative") { dialogInterface: DialogInterface, i: Int ->
                        textView.text = "기본 다이얼로그 : Negative"
                    }
                }

                // 다이얼로그 띄우기
                builder.show()
            }

            buttonCustomDialog.setOnClickListener {
                val builder = MaterialAlertDialogBuilder(this@MainActivity).apply {
                    setTitle("Custom Dialog")

                    // 뷰 설정
                    val customDialogBinding = CustomDialogBinding.inflate(layoutInflater)
                    setView(customDialogBinding.root)

                    setNegativeButton("취소", null)
                    setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                        textView.text = "입력1 : ${customDialogBinding.editTextDialog1.text}\n"
                        textView.append("입력2 : ${customDialogBinding.editTextDialog2.text}")
                    }
                }

                builder.show()
            }

            buttonNotification1.setOnClickListener {
                // NotificationBuiler 가져오기
                // 채널의 id 지정
                val builder = getNotificationBuilder("c1")
                // 작은 아이콘
                builder.setSmallIcon(android.R.drawable.ic_menu_add)
                // 큰 이미지
                val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
                builder.setLargeIcon(bitmap)
                // 숫자 설정
                builder.setNumber(100)
                // 타이틀 설정
                builder.setContentTitle("content title 1")
                // 내용
                builder.setContentText("content text 1")

                // 메시지 객체 생성
                val notification = builder.build()
                // 알림 메시지 관리하는 객체 추출
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                // 메시지 표시
                // 첫 번째 매개변수 : 메시지 번호
                // 메시지 번호를 통해 표시할 메시지를 지정할 수 있음
                // 지정한 메시지 번호의 메시지가 이미 보여지고 있는 상태라면 그 메시지를 없애고 새롭게 보여줌
                // 하나의 메시지를 갱신할 때 같은 정수를 넣어주고 새로운 메시지를 보여줄 때는 다른 정수를 넣어줌
                notificationManager.notify(10, notification)
            }

            buttonNotification2.setOnClickListener {
                // NotificationBuiler 가져오기
                // 채널의 id 지정
                val builder = getNotificationBuilder("c2")
                // 작은 아이콘
                builder.setSmallIcon(android.R.drawable.ic_dialog_alert)
                // 큰 이미지
                // val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
                // builder.setLargeIcon(bitmap)
                // 숫자 설정
                // builder.setNumber(100)
                // 타이틀 설정
                builder.setContentTitle("content title 2")
                // 내용
                builder.setContentText("content text 2")

                // 메시지 객체 생성
                val notification = builder.build()
                // 알림 메시지 관리하는 객체 추출
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                // 메시지 표시
                // 첫 번째 매개변수 : 메시지 번호
                // 메시지 번호를 통해 표시할 메시지를 지정할 수 있음
                // 지정한 메시지 번호의 메시지가 이미 보여지고 있는 상태라면 그 메시지를 없애고 새롭게 보여줌
                // 하나의 메시지를 갱신할 때 같은 정수를 넣어주고 새로운 메시지를 보여줄 때는 다른 정수를 넣어줌
                notificationManager.notify(20, notification)
            }
        }
    }

    // Notification Channel 등록
    // 첫 번째 : 코드에서 채널을 관리히가 위한 이름
    // 두 번째 : 사용자에게 보여줄 채널 이름
    fun addNotificationChannel(id:String, name:String) {
        // Android 8.0 이상일 때만 동작
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 알림 메시지를 관리하는 객체 가져오기
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            // 해당 채널이 등록되어 있는지 확인
            // 채널이 등록되어 있지 않으면 null 반환
            val channel = notificationManager.getNotificationChannel(id)

            // 등록된 채널이 없다면
            if(channel == null) {
                // 채널 객체 생성
                val newChannel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
                // 진동을 사용할 것인가
                newChannel.enableVibration(true)
                // 채널 등록
                notificationManager.createNotificationChannel(newChannel)
            }
        }
    }

    // Notification 메시지를 생성하기 위한 객체를 반환하는 메서드
    fun getNotificationBuilder(id:String) : NotificationCompat.Builder {
        // Android 8.0 이상이면 마지막 매개변수에 채널 id를 설정해줘야 함
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val builder = NotificationCompat.Builder(this, id)
            return builder
        }  else {
            val builder = NotificationCompat.Builder(this)
            return builder
        }
    }
}