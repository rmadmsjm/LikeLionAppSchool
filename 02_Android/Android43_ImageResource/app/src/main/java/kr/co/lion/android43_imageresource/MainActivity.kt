package kr.co.lion.android43_imageresource

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android43_imageresource.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            button.setOnClickListener {
                // imageView에 설정한 애니메이션 이미지 추출
                val animaltionDrawable = imageView2.drawable as AnimationDrawable
                // 애니메이션 가동
                animaltionDrawable.start()
            }

            button2.setOnClickListener {
                // imageView에 설정한 애니메이션 이미지 추출
                val animaltionDrawable = imageView2.drawable as AnimationDrawable
                // 애니메이션 정지
                animaltionDrawable.stop()
            }
        }
    }
}