package kr.co.lion.android49_sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android49_sensor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 조도 센서 리스너를 담을 프로퍼티
    var lightSensorListener: LightSensorListener? = null
    // 기압 센서 리스너를 담을 프로퍼티
    var pressureSensorListener: PressusreSenserListener? = null
    // 근접 센서 리스너를 담을 프로퍼티
    var proximitySenserListener: ProximitySenserListener? = null
    // 자이로스코프 리스너를 담을 프로퍼티
    var gyroscopeSenserListener: GyroscopeSenserListener? = null
    // 가속도 센서 리스너를 담을 프로퍼티
    var accelerometerSenserListener: AccelerometerSenserListener? = null
    // 마그네틱 필드 센서 리스너를 담을 프로퍼티
    var magentSenserListener: MagentSenserListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            // 조도 센서 Button
            button.setOnClickListener {
                // 리스너 연결이 되어 있지 않을 경우
                if(lightSensorListener == null) {
                    button.text = "조도 센서 해제"

                    // 리스너 객체 생성
                    lightSensorListener = LightSensorListener()

                    // 센서를 관리하는 객체 추출
                    val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

                    // 조도 센서 객체 가져오기
                    val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

                    // 조도 센서 리스너 연결
                    // 첫 번째 : 리스너
                    // 두 번째 : 연결할 센서 객체
                    // 세 번째 : 데이터 받아올 주기
                    // 반환값 : 센서 연결에 성공했는지 여부
                    val chk = sensorManager.registerListener(lightSensorListener, sensor, SensorManager.SENSOR_DELAY_UI)

                    // 센서 연결에 실패했다면
                    if(chk == false) {
                        lightSensorListener = null
                        textView.text = "조도 센서를 지원하지 않습니다"
                    }
                }
                // 리스너 연결이 되어 있을 경우 : 해제 구현
                else {
                    button.text = "조도 센서 연결"

                    // 센서를 관리하는 객체 추출
                    val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

                    // 조도 센서 리스너 해제
                    sensorManager.unregisterListener(lightSensorListener)

                    lightSensorListener = null
                }
            }

            // 기압 센서 Button
            button2.setOnClickListener {
                // 리스너 연결이 되어 있지 않을 경우
                if(pressureSensorListener == null) {
                    button2.text = "기압 센서 해제"

                    // 리스너 객체 생성
                    pressureSensorListener = PressusreSenserListener()

                    // 센서를 관리하는 객체 추출
                    val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

                    // 기압 센서 객체 가져오기
                    val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)

                    // 기압 센서 리스너 연결
                    // 첫 번째 : 리스너
                    // 두 번째 : 연결할 센서 객체
                    // 세 번째 : 데이터 받아올 주기
                    // 반환값 : 센서 연결에 성공했는지 여부
                    val chk = sensorManager.registerListener(pressureSensorListener, sensor, SensorManager.SENSOR_DELAY_UI)

                    // 센서 연결에 실패했다면
                    if(chk == false) {
                        pressureSensorListener = null
                        textView.text = "기압 센서를 지원하지 않습니다"
                    }
                }
                // 리스너 연결이 되어 있을 경우 : 해제 구현
                else {
                    button2.text = "기압 센서 연결"

                    // 센서를 관리하는 객체 추출
                    val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

                    // 조도 센서 리스너 해제
                    sensorManager.unregisterListener(pressureSensorListener)

                    pressureSensorListener = null
                }
            }

            // 근접 센서 Button
            button3.setOnClickListener {
                // 리스너 연결이 되어 있지 않을 경우
                if(proximitySenserListener == null) {
                    button3.text = "근접 센서 해제"

                    // 리스너 객체 생성
                    proximitySenserListener = ProximitySenserListener()

                    // 센서를 관리하는 객체 추출
                    val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

                    // 근접 센서 객체 가져오기
                    val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

                    // 근접 센서 리스너 연결
                    // 첫 번째 : 리스너
                    // 두 번째 : 연결할 센서 객체
                    // 세 번째 : 데이터 받아올 주기
                    // 반환값 : 센서 연결에 성공했는지 여부
                    val chk = sensorManager.registerListener(proximitySenserListener, sensor, SensorManager.SENSOR_DELAY_UI)

                    // 센서 연결에 실패했다면
                    if(chk == false) {
                        proximitySenserListener = null
                        textView.text = "근접 센서를 지원하지 않습니다"
                    }
                }
                // 리스너 연결이 되어 있을 경우 : 해제 구현
                else {
                    button3.text = "근접 센서 연결"

                    // 센서를 관리하는 객체 추출
                    val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

                    // 근접 센서 리스너 해제
                    sensorManager.unregisterListener(proximitySenserListener)

                    proximitySenserListener = null
                }
            }

            // 자이로스코프 Button
            button4.setOnClickListener {
                // 리스너 연결이 되어 있지 않을 경우
                if(gyroscopeSenserListener == null) {
                    button4.text = "자이로스코프 센서 해제"

                    // 리스너 객체 생성
                    gyroscopeSenserListener = GyroscopeSenserListener()

                    // 센서를 관리하는 객체 추출
                    val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

                    // 자이로스코프 센서 객체 가져오기
                    val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

                    // 자이로스코프 센서 리스너 연결
                    // 첫 번째 : 리스너
                    // 두 번째 : 연결할 센서 객체
                    // 세 번째 : 데이터 받아올 주기
                    // 반환값 : 센서 연결에 성공했는지 여부
                    val chk = sensorManager.registerListener(gyroscopeSenserListener, sensor, SensorManager.SENSOR_DELAY_UI)

                    // 센서 연결에 실패했다면
                    if(chk == false) {
                        gyroscopeSenserListener = null
                        textView.text = "자이로스코프 센서를 지원하지 않습니다"
                    }
                }
                // 리스너 연결이 되어 있을 경우 : 해제 구현
                else {
                    button4.text = "자이로스코프 센서 연결"

                    // 센서를 관리하는 객체 추출
                    val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

                    // 자이로스코프 센서 리스너 해제
                    sensorManager.unregisterListener(gyroscopeSenserListener)

                    gyroscopeSenserListener = null
                }
            }

            // 가속도 센서 Button
            button5.setOnClickListener {
                // 리스너 연결이 되어 있지 않을 경우
                if(accelerometerSenserListener == null) {
                    button5.text = "가속도 센서 해제"

                    // 리스너 객체 생성
                    accelerometerSenserListener = AccelerometerSenserListener()

                    // 센서를 관리하는 객체 추출
                    val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

                    // 가속도 센서 객체 가져오기
                    val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

                    // 가속도 센서 리스너 연결
                    // 첫 번째 : 리스너
                    // 두 번째 : 연결할 센서 객체
                    // 세 번째 : 데이터 받아올 주기
                    // 반환값 : 센서 연결에 성공했는지 여부
                    val chk = sensorManager.registerListener(accelerometerSenserListener, sensor, SensorManager.SENSOR_DELAY_UI)

                    // 센서 연결에 실패했다면
                    if(chk == false) {
                        accelerometerSenserListener = null
                        textView.text = "가속도 센서를 지원하지 않습니다"
                    }
                }
                // 리스너 연결이 되어 있을 경우 : 해제 구현
                else {
                    button5.text = "가속도 센서 연결"

                    // 센서를 관리하는 객체 추출
                    val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

                    // 가속도 센서 리스너 해제
                    sensorManager.unregisterListener(accelerometerSenserListener)

                    accelerometerSenserListener = null
                }
            }

            // 마그네틱 필드 Button
            button6.setOnClickListener {
                // 리스너 연결이 되어 있지 않을 경우
                if(magentSenserListener == null) {
                    button6.text = "마그네틱필드 센서 해제"

                    // 리스너 객체 생성
                    magentSenserListener = MagentSenserListener()

                    // 센서를 관리하는 객체 추출
                    val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

                    // 마그네틱필드 센서 객체 가져오기
                    val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

                    // 마그네틱필드 센서 리스너 연결
                    // 첫 번째 : 리스너
                    // 두 번째 : 연결할 센서 객체
                    // 세 번째 : 데이터 받아올 주기
                    // 반환값 : 센서 연결에 성공했는지 여부
                    val chk = sensorManager.registerListener(magentSenserListener, sensor, SensorManager.SENSOR_DELAY_UI)

                    // 센서 연결에 실패했다면
                    if(chk == false) {
                        magentSenserListener = null
                        textView.text = "마그네틱필드 센서를 지원하지 않습니다"
                    }
                }
                // 리스너 연결이 되어 있을 경우 : 해제 구현
                else {
                    button6.text = "마그네틱필드 센서 연결"

                    // 센서를 관리하는 객체 추출
                    val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

                    // 마그네틱필드 센서 리스너 해제
                    sensorManager.unregisterListener(magentSenserListener)

                    magentSenserListener = null
                }
            }
        }
    }

    // 조도 센서 리스너
    inner class LightSensorListener : SensorEventListener {
        // 센서에 변화가 일어날 때 호출되는 메서드
        // 이 메서드에서 측정된 값을 가져올 수 있음
        override fun onSensorChanged(event: SensorEvent?) {
            if(event != null) {
                // 조도값 가져오기
                val a1 = event.values[0]

                activityMainBinding.textView.text = "주변 밝기 : $a1 lux"
            }
        }

        // 센서의 감도가 변경되었을 때 호출되는 메서드
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }
    }

    // 기압 센서 리스너
    inner class PressusreSenserListener : SensorEventListener {
        // 센서에 변화가 일어날 때 호출되는 메서드
        // 이 메서드에서 측정된 값을 가져올 수 있음
        override fun onSensorChanged(event: SensorEvent?) {
            if(event != null) {
                // 공기압 값 가져오기
                val a1 = event.values[0]

                activityMainBinding.textView.text = "현재 기압 : $a1 millibar"
            }
        }

        // 센서의 감도가 변경되었을 때 호출되는 메서드
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }
    }

    // 근접 센서 리스너 : 얼마나 물체가 가까이 있는지
    inner class ProximitySenserListener : SensorEventListener {
        // 센서에 변화가 일어날 때 호출되는 메서드
        // 이 메서드에서 측정된 값을 가져올 수 있음
        override fun onSensorChanged(event: SensorEvent?) {
            if(event != null) {
                // 물체와의 거리 값 가져오기
                val a1 = event.values[0]

                activityMainBinding.textView.text = "물체와의 거리 : $a1 cm"
            }
        }

        // 센서의 감도가 변경되었을 때 호출되는 메서드
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }
    }

    // 자이로스코프 센서 리스너 : 각속도 측정
    inner class GyroscopeSenserListener : SensorEventListener {
        // 센서에 변화가 일어날 때 호출되는 메서드
        // 이 메서드에서 측정된 값을 가져올 수 있음
        override fun onSensorChanged(event: SensorEvent?) {
            if(event != null) {
                // X축의 각속도 값 가져오기
                val a1 = event.values[0]
                // Y축의 각속도 값 가져오기
                val a2 = event.values[1]
                // Z축의 각속도 값 가져오기
                val a3 = event.values[2]

                activityMainBinding.textView.text = "X축 각속도 : $a1"
                activityMainBinding.textView2.text = "Y축 각속도 : $a2"
                activityMainBinding.textView3.text = "Z축 각속도 : $a3"
            }
        }

        // 센서의 감도가 변경되었을 때 호출되는 메서드
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }
    }

    // 가속도 센서 리스너
    inner class AccelerometerSenserListener : SensorEventListener {
        // 센서에 변화가 일어날 때 호출되는 메서드
        // 이 메서드에서 측정된 값을 가져올 수 있음
        override fun onSensorChanged(event: SensorEvent?) {
            if(event != null) {
                // X축의 기울기 값 가져오기
                val a1 = event.values[0]
                // Y축의 기울기 값 가져오기
                val a2 = event.values[1]
                // Z축의 기울기 값 가져오기
                val a3 = event.values[2]

                activityMainBinding.textView.text = "X축 기울기 : $a1"
                activityMainBinding.textView2.text = "Y축 기울기 : $a2"
                activityMainBinding.textView3.text = "Z축 기울기 : $a3"
            }
        }

        // 센서의 감도가 변경되었을 때 호출되는 메서드
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }
    }

    // 마그네틱 필드 리스너
    inner class MagentSenserListener : SensorEventListener {
        // 센서에 변화가 일어날 때 호출되는 메서드
        // 이 메서드에서 측정된 값을 가져올 수 있음
        override fun onSensorChanged(event: SensorEvent?) {
            if(event != null) {
                // X축 주변 자기장 값 가져오기
                val a1 = event.values[0]
                // Y축 주변 자기장 값 가져오기
                val a2 = event.values[1]
                // Z축 주변 자기장 값 가져오기
                val a3 = event.values[2]

                activityMainBinding.textView.text = "X축 주변 자기장 : $a1"
                activityMainBinding.textView2.text = "Y축 주변 자기장 : $a2"
                activityMainBinding.textView3.text = "Z축 주변 자기장 : $a3"
            }
        }

        // 센서의 감도가 변경되었을 때 호출되는 메서드
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }
    }
}