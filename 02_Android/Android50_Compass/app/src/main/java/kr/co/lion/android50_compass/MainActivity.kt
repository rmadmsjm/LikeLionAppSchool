package kr.co.lion.android50_compass

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android50_compass.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 가속도 센서로 측정한 값을 담을 배열을 담을 프로퍼티
    val accValues = floatArrayOf(0.0f, 0.0f, 0.0f)
    // 자기장 센서로 측정한 값을 담을 배열을 담을 프로퍼티
    val magValues = floatArrayOf(0.0f, 0.0f, 0.0f)

    // 센서로부터 값이 측정된 적 있는지
    var isGetAcc = false
    var isGetMag = false

    // 각 센서의 리스너
    var accelerometerSensorListener: AccelerometerSensorListener? = null
    var magneticSensorListener: MagneticSensorListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            // 방위값 측정 시작 Button
            button.setOnClickListener {
                // 센서 관리 객체 추출
                val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

                // 센서 객체 가져오기
                val accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                val magSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

                // 리스너 생성
                accelerometerSensorListener = AccelerometerSensorListener()
                magneticSensorListener = MagneticSensorListener()

                // 센서에 리스너 연결
                sensorManager.registerListener(accelerometerSensorListener, accSensor, SensorManager.SENSOR_DELAY_UI)
                sensorManager.registerListener(magneticSensorListener, magSensor, SensorManager.SENSOR_DELAY_UI)
            }

            // 방위값 측정 종료 Button
            button2.setOnClickListener {
                if(accelerometerSensorListener != null && magneticSensorListener != null) {
                    // 센서 관리 객체 추출
                    val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

                    // 센서 리스너 해제
                    sensorManager.unregisterListener(accelerometerSensorListener)
                    sensorManager.unregisterListener(magneticSensorListener)

                    accelerometerSensorListener = null
                    magneticSensorListener = null
                }
            }
        }
    }

    // 가속도 센서 리스너
    inner class AccelerometerSensorListener : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if(event != null) {
                // 측정된 값을 배열에 담기
                accValues[0] = event.values[0]
                accValues[1] = event.values[1]
                accValues[2] = event.values[2]

                isGetAcc = true

                getAzimuth()
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }
    }

    // 자기장 센서 리스너
    inner class MagneticSensorListener : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if(event != null) {
                // 자기장 센서로 측정된 값을 배열에 담는다.
                magValues[0] = event.values[0]
                magValues[1] = event.values[1]
                magValues[2] = event.values[2]

                isGetMag = true

                getAzimuth()
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }
    }

    // 방위값 계산 메서드
    fun getAzimuth() {
        // 두 센서 모두 값이 측정된 적이 있는 경우에만
        if(isGetAcc == true && isGetMag == true) {
            // 방위값 등을 계산하기 위한 계산 행렬
            val R = FloatArray(9)
            val I = FloatArray(9)

            // 계산 행렬 구하기
            SensorManager.getRotationMatrix(R, I, accValues, magValues)

            // 방위값 추출
            val values = FloatArray(3)
            SensorManager.getOrientation(R, values)

            // 라디언 값으로 나오는 결과 각도 값으로 변환
            var azimuth = Math.toDegrees(values[0].toDouble())
            val pitch = Math.toDegrees(values[1].toDouble())
            val roll = Math.toDegrees(values[2].toDouble())

            // 방위값이 음수인 경우, 360 더하기
            if(azimuth < 0) {
                azimuth += 360
            }

            activityMainBinding.apply {
                textView.text = "방위값 : $azimuth"
                textView2.text = "좌우 기울기 값 : $pitch"
                textView3.text = "앞뒤 기울기 값 : $roll"

                // 이미지 뷰 회전
                imageView.rotation = (360 - azimuth).toFloat()
            }
        }
    }
}