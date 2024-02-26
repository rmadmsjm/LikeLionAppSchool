package kr.co.lion.androidproject2lbs

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import kr.co.lion.androidproject2lbs.databinding.ActivityMainBinding

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

    // 확인할 권한 목록
    val permissionList = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
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
                    // 현재 위치 다시 측정하기
                    getMyLocation()
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
            // 사용자 우치 정보 프로바이더로 분기
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

        // 위도와 경도를 관리하는 객체 생성
        val userLocation = LatLng(location.latitude, location.longitude)

        // 지도를 이동시키기 위한 객체 생성
        // 첫 번째 : 표시할 지도의 위치(위도 경고)
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
}