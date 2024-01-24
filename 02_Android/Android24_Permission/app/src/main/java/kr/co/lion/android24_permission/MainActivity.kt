package kr.co.lion.android24_permission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android24_permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 확인할 권한 목록
    // 이 중에서 허용 안 되어 있는 것만 물어봄
    val permissionList = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 권한 확인
        requestPermissions(permissionList, 0)

        activityMainBinding.apply {
            button.setOnClickListener {
                // 위치 사용 권한 확인
                val chk1 = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                if(chk1 == PackageManager.PERMISSION_GRANTED) {
                    textView.text = "위치 권한이 허용되어 있습니다\n"
                } else if(chk1 == PackageManager.PERMISSION_DENIED) {
                    textView.text = "위치 권한이 허용되어 있지 않습니다\n"
                }

                // 연락처 권란
                val chk2 = checkSelfPermission(Manifest.permission.READ_CONTACTS)
                if(chk2 == PackageManager.PERMISSION_GRANTED) {
                    textView.append("연락처 권한이 허용되어 있습니다\n")
                } else {
                    textView.append("연락처 권한이 허용되어 있지 않습니다\n")
                }
            }
        }
    }
}