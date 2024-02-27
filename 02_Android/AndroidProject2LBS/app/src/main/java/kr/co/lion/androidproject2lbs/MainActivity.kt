package kr.co.lion.androidproject2lbs

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kr.co.lion.androidproject2lbs.databinding.ActivityMainBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 위치 정보 관리하는 객체
    lateinit var locationManager: LocationManager
    // 위치 측정 성공 시 동작할 리스너
    var gpsLocationListener: MyLocationListener? = null
    var networkLocationListener: MyLocationListener? = null

    // 구글 지도 객체를 담을 프로퍼티
    lateinit var mainGooleMap: GoogleMap

    // 현재 사용자 위치를 표시하기 위한 마커
    var myMarker: Marker? = null

    // 현재 사용자의 위치를 가진 객체
    var userLocation: Location? = null

    // 서버로부터 받아온 데이터를 담을 리스트
    val latitudeList = mutableListOf<Double>()
    val longitudeList = mutableListOf<Double>()
    val nameList = mutableListOf<String>()
    val vicinityList = mutableListOf<String>()

    // 주변 장소를 표시한 마커를 담을 리스트
    val markerList = mutableListOf<Marker>()

    // 확인할 권한 목록
    val permissionList = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    // 장소를 나타내는 단어
    var dialogData = arrayOf(
        "all",
        "accounting", "airport", "amusement_park",
        "aquarium", "art_gallery", "atm", "bakery",
        "bank", "bar", "beauty_salon", "bicycle_store",
        "book_store", "bowling_alley", "bus_station",
        "cafe", "campground", "car_dealer", "car_rental",
        "car_repair", "car_wash", "casino", "cemetery",
        "church", "city_hall", "clothing_store", "convenience_store",
        "courthouse", "dentist", "department_store", "doctor",
        "drugstore", "electrician", "electronics_store", "embassy",
        "fire_station", "florist", "funeral_home", "furniture_store",
        "gas_station", "gym", "hair_care", "hardware_store", "hindu_temple",
        "home_goods_store", "hospital", "insurance_agency",
        "jewelry_store", "laundry", "lawyer", "library", "light_rail_station",
        "liquor_store", "local_government_office", "locksmith", "lodging",
        "meal_delivery", "meal_takeaway", "mosque", "movie_rental", "movie_theater",
        "moving_company", "museum", "night_club", "painter", "park", "parking",
        "pet_store", "pharmacy", "physiotherapist", "plumber", "police", "post_office",
        "primary_school", "real_estate_agency", "restaurant", "roofing_contractor",
        "rv_park", "school", "secondary_school", "shoe_store", "shopping_mall",
        "spa", "stadium", "storage", "store", "subway_station", "supermarket",
        "synagogue", "taxi_stand", "tourist_attraction", "train_station",
        "transit_station", "travel_agency", "university", "eterinary_care", "zoo"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapsInitializer.initialize(this, MapsInitializer.Renderer.LATEST, null)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 권한 확인 받기
        requestPermissions(permissionList, 0)

        settingToolbar()
        settingGoogleMap()
    }

    // 툴바 설정
    fun settingToolbar() {
        activityMainBinding.apply {
            toolbarMap.apply {
                // 타이틀
                title = "구글 지도"

                // 메뉴
                inflateMenu(R.menu.main_menu)
                setOnMenuItemClickListener {
                    // 메뉴 id로 분기
                    when(it.itemId) {
                        // 현재 위치
                        R.id.main_menu_location -> {
                            // 현재 위치 다시 측정하기
                            getMyLocation()
                        }
                        // 주변 정보 가져오기
                        R.id.main_menu_place -> {
                            // 다이얼로그 띄우기
                            val materialAlertDialogBuilder = MaterialAlertDialogBuilder(this@MainActivity)
                            materialAlertDialogBuilder.setTitle("장소 종류 선택")
                            materialAlertDialogBuilder.setNegativeButton("취소", null)
                            materialAlertDialogBuilder.setItems(dialogData) { dialogInterface: DialogInterface, i: Int ->
                                // 주변 정보 가져오기
                                // 매개 변수에 i번째(사용자가 선택한 항목의 순서 값) 문자열 전달
                                getPlaceData(dialogData[i])
                            }
                            materialAlertDialogBuilder.show()
                        }
                        // 주변 정보 마커 초기화
                        R.id.main_menu_clear -> {
                            initPlaceData()
                        }
                    }

                    true
                }
            }
        }
    }

    // 구글 지도 셋팅
    fun settingGoogleMap() {
        // MapFragment 객체 가져오기
        val supportMapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        // 리스너 설정
        // 구글 지도 사용이 완료되면 동작함
        supportMapFragment.getMapAsync {
            // Snackbar.make(activityMainBinding.root, "구글 지도가 나타났습니다", Snackbar.LENGTH_SHORT).show()

            // 구글 지도 객체 담기
            mainGooleMap = it

            // 확대 축소 버튼
            mainGooleMap.uiSettings.isZoomControlsEnabled = true

            // 현재 위치로 이동시키는 버튼
            // 사용자 현재 위치도 표시됨
            // 만약 사용자 위치를 기본 모양이 아닌 다른 모양으로 표시하고 싶다면
            // "mainGooleMap.uiSettings.isMyLocationButtonEnabled = false" 이 부분도 같이 주석처리
            //mainGooleMap.isMyLocationEnabled = true

            // isMyLocationEnabled에 true를 넣어주면 현재 위치를 표시하는 것뿐만 아니라 현재 위치로 이동하는 버튼도 표시됨
            // 현재 위치를 표시하되, 버튼을 제거하려면
            //mainGooleMap.uiSettings.isMyLocationButtonEnabled = false

            // 지도의 타입
            // 데이터 사용량 : NONE < NOMAL < TERAIN < SATELLITE < HYBRID
            // 지도 뜨는 시간 : NONE < NOMAL < TERAIN < SATELLITE < HYBRID
            // 1) 지도 안 나옴
            // mainGooleMap.mapType = GoogleMap.MAP_TYPE_NONE
            // 2) 기본
            // mainGooleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            // 3) 색상으로 높낮이 구분?
            // mainGooleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            // 4) 위성
            // mainGooleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            // 5) 하이브리드
            // mainGooleMap.mapType = GoogleMap.MAP_TYPE_HYBRID

            // 위치 정보 관리하는 객체 가져오기
            locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            // 위치 정보 수집 성공 시 동작할 리스너
            // myLocationListener = MyLocationListener()

            // 단말기에 저장되어 있는 위치값 가져오기
            // 위치 정보 사용 권한 허용 여부 확인
            val a1 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)  == PackageManager.PERMISSION_GRANTED
            val a2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

            // 모든 권한이 허용되어 있다면
            if(a1 && a2) {
                // 저장되어 있는 위치값 가져오기 (없을 경우 null 반환)
                val location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                val location2 = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                // 현재 위치 지도에 표시하기
                if(location1 != null) {
                    setMyLocation(location1)
                } else if(location2 != null) {
                    setMyLocation(location2)
                }

                // 현재 위치 측정하기
                getMyLocation()
            }
        }
    }

    // 현재 위치를 가져오는 메서드
    fun getMyLocation() {
        // 위치 정보 사용 권한 허용 여부 확인
        val a1 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)  == PackageManager.PERMISSION_DENIED
        val a2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED

        if(a1 || a2) {
            return
        }

        // GPS 프로바이더 사용이 가능하다면
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true){
            // 첫 번째 : 사용할 프로바이더
            // 두 번째 : 리스너의 메서드가 호출 될 시간 주기(ms). 0을 넣어주면 최대한 짧은 시간 주기
            // 세 번째 : 리스너의 메서드가 호출 될 거리 주기(m). 0을 넣어주면 최대한 짧은 거리 주기
            if(gpsLocationListener == null) {
                gpsLocationListener = MyLocationListener()
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0.0f, gpsLocationListener!!)
            }
        }
        // Network 프로바이더 사용이 가능하다면
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) == true){
            if(networkLocationListener == null) {
                networkLocationListener = MyLocationListener()
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0.0f, networkLocationListener!!)
            }
        }
    }

    // 위치 측정이 성공하면 동작하는 리스너
    inner class MyLocationListener() : LocationListener{
        // 위치가 측정되면 호출되는 메서드
        // location : 측정된 위치 정보가 담긴 객체
        override fun onLocationChanged(location: Location) {
            // 사용자 위치 정보 프로바이더로 분기
            when(location.provider){
                // GPS 프로바이더일 경우
                LocationManager.GPS_PROVIDER -> {
                    // GPS 리스너 연결 해제
                    locationManager.removeUpdates(gpsLocationListener!!)
                    gpsLocationListener = null
                }
                // Network 프로바이더일 경우
                LocationManager.NETWORK_PROVIDER -> {
                    // Network 리스너 연결 해제
                    locationManager.removeUpdates(networkLocationListener!!)
                    networkLocationListener = null
                }
            }

            // 측정된 위치로 지도 움직이기
            setMyLocation(location)
        }
    }

    // 지도의 위치를 설정하는 메서드
    fun setMyLocation(location: Location) {
        // 위도와 경도 출력
        Snackbar.make(activityMainBinding.root, "provider : ${location.provider}\n위도 : ${location.latitude}, 경도 : ${location.longitude}",
            Snackbar.LENGTH_SHORT).show()

        // 현재 사용자의 위치를 프로퍼티에 담기
        userLocation = location

        // 위도와 경도를 관리하는 객체 생성
        val userLocation = LatLng(location.latitude, location.longitude)

        // 지도를 이동시키기 위한 객체 생성
        // 첫 번째 : 표시할 지도의 위치(위도 경도)
        // 두 번째 : 줌 배율
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(userLocation, 15.0f)

        // 카메라 이동시키기
        // mainGooleMap.moveCamera(cameraUpdate)
        mainGooleMap.animateCamera(cameraUpdate)

        // 현재 위치를 담은 마커 생성
        var markerOptions = MarkerOptions()
        markerOptions.position(userLocation)

        // 이미 마커가 있다면 제거
        // 없으면 위치가 바뀌어도 전의 위치가 그대로 존재함
        if(myMarker != null) {
            myMarker?.remove()
            myMarker = null
        }

        // 마커의 이미지 변경
        val markerBitmap = BitmapDescriptorFactory.fromResource(R.drawable.my_location)
        markerOptions.icon(markerBitmap)

        // 마커를 지도에 표시하기
        myMarker = mainGooleMap.addMarker(markerOptions)
    }

    // 주변 정보 가져오기
    fun getPlaceData(type: String) {
        // 안드로이드는 네트워크에 대한 코드를 Thread로 운영하도록 강제하고 잇음
        // 모바일 네트워크는 오류가 발생할 가능성이 크기 때문에 오류가 발생해도 애플리케이션이 종료되는 것을 방지하기 위함
        // Kotlin 자체가 예외처리를 의무로 하지 않지만, 네트워크에 관련된 작업은 예외처리를 통해 오류가 발생했을 때 사용자에게 알려주는 것이 중요
        try {
            // Thread 가동
            thread {
                // 사용자 위치를 받은 적이 있다면
                if(userLocation != null) {
                    // 접속할 서버 주소
                    val site = "https://maps.googleapis.com/maps/api/place/nearbysearch/json"
                    // 사용자 위치
                    val location = "${userLocation?.latitude},${userLocation?.longitude}"
                    // 반경
                    val radius = 5000
                    // 언어
                    val language = "ko"
                    // API 키
                    val apiKey = "AIzaSyAgalEqIb7eczj1acz6QG2C8Irm-cZM8sg"

                    // 다음 페이지 데이터 요청을 위한 토큰
                    var pagetoken: String? = null

                    // 지도에 표시되어 있는 마커 제거 및 데이터 담는 리스트 초기화
                    runOnUiThread {
                        initPlaceData()
                    }

                    // 페이지 토큰이 null이 아닐 때까지 반복
                    do {
                        SystemClock.sleep(1300)

                        // 접속할 주소
                        // 매개변수로 전달되는 type이 all이 아니면 type 붙여줌
                        val serverPath = if(type == "all") {
                            "${site}?location=${location}&radius=${radius}&language=${language}&key=${apiKey}"
                        } else {
                            "${site}?location=${location}&radius=${radius}&language=${language}&key=${apiKey}&type=${type}"
                        }
                        // Log.d("test1234", severPath)

                        // 만약 다음 페이지 토큰이 있을 경우의 접속할 주소
                        val serverPath2 = if(pagetoken != null) {
                            "${serverPath}&pagetoken=$pagetoken"
                        } else {
                            serverPath
                        }

                        // 서버 접속
                        val url = URL(serverPath2)
                        val httpURLConnection = url.openConnection() as HttpURLConnection

                        // 스트림 추출
                        // 문자를 읽어오기 위한 스트림
                        val inputStreamReader = InputStreamReader(httpURLConnection.inputStream)
                        // 라인 단위로 문자열을 읽어오는 스트림
                        val bufferedReader = BufferedReader(inputStreamReader)

                        // 읽어온 한 줄의 문자열을 담은 변수
                        // null 허용 : 더 이상 읽어올 게 없으면 null값 나옴
                        var str: String? = null
                        // 읽어온 문장을 누적해서 담을 객체
                        val stringBuffer = StringBuffer()

                        // 마지막까지 읽어오기
                        // 읽어올 것이 없다면 null 반환
                        do {
                            // 한 줄의 문자열 읽어오기
                            str = bufferedReader.readLine()
                            if(str != null) {
                                // StringBuffer에 누적시키기
                                stringBuffer.append(str)
                            }
                        } while(str != null)

                        // Log.d("test1234", stringBuffer.toString())

                        // 전체가 {}(객체)이므로 JSONObject 생성
                        val root = JSONObject(stringBuffer.toString())
                        // status 값이 OK인 경우 데이터 가져오기
                        if(root.has("status")) {
                            val status = root.getString("status")
                            if(status == "OK") {
                                // results 라는 이름의 배열 가져오기
                                if(root.has("results")) {
                                    val resultArray = root.getJSONArray("results")

                                    // 배열 안 객체 가져오기
                                    // 처음부터 끝까지 반복
                                    for(idx in 0 until resultArray.length()) {
                                        // idx 번째 JSON 객체 가져오기
                                        val resultObject = resultArray[idx] as JSONObject
                                        // geometry 이름의 객체 가져오기
                                        val geometryObject = resultObject.getJSONObject("geometry")
                                        // location 이름의 객체 가져오기
                                        val locationObect = geometryObject.getJSONObject("location")
                                        // 위도 가져오기
                                        val lat = locationObect.getDouble("lat")
                                        // 경도 가져오기
                                        val lng = locationObect.getDouble("lng")
                                        // 이름
                                        val name = resultObject.getString("name")
                                        // 간단한 주소
                                        val vicinity = resultObject.getString("vicinity")

                                        // Log.d("test1234", "${lat}, ${lng}, ${name}, ${vicinity}")

                                        // 리스트에 데이터 담기
                                        latitudeList.add(lat)
                                        longitudeList.add(lng)
                                        nameList.add(name)
                                        vicinityList.add(vicinity)
                                    }
                                }

                                // next_page_token 이 있다면
                                if(root.has("next_page_token")) {
                                    // 토큰값 담기
                                    pagetoken = root.getString("next_page_token")
                                } else {
                                    // null : next_page_token 이 없을 경우
                                    // do while 조건식의 토큰이 null이 아닐 경우이므로 null을 넣어서 반복을 중단시킴
                                    pagetoken = null
                                }

                            } else {
                                showDataError()
                            }
                        } else {
                            showDataError()
                        }
                    } while(pagetoken != null)

                    // 화면에 관련된 작업이므로 runOnUiTread에서 작업
                    runOnUiThread {
                        // 지도에 마커 표시
                        for(idx in 0 until latitudeList.size) {
                            // 마커 옵션
                            val placeMarkerOptions = MarkerOptions()
                            val placeLatLng = LatLng(latitudeList[idx], longitudeList[idx])
                            placeMarkerOptions.position(placeLatLng)
                            placeMarkerOptions.title(nameList[idx])
                            placeMarkerOptions.snippet(vicinityList[idx])

                            // 마커 표시
                            val placeMarker = mainGooleMap.addMarker(placeMarkerOptions)
                            // 나중에 제거하기 위해 리스트에 담기
                            markerList.add(placeMarker!!)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            showDataError()
        }
    }

    // 에러 메시지 메서드
    fun showDataError() {
        Snackbar.make(activityMainBinding.root, "일시적인 네트워크 장애가 있습니다", Snackbar.LENGTH_SHORT).show()
    }

    // 지도에 표시된 마커 제거 및 모든 리스트 초기화 메서드
    fun initPlaceData() {
        // 리스트에 있는 마커의 개수만큼 반복
        markerList.forEach {
            it.remove()
        }

        // 리스트 초기화
        latitudeList.clear()
        longitudeList.clear()
        nameList.clear()
        vicinityList.clear()
        markerList.clear()
    }
}