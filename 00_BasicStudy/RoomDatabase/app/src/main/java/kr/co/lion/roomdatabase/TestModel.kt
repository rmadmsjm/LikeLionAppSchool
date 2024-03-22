package kr.co.lion.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

// tableNam : 생성될 테이블의 이름
@Entity(tableName = "TestTable")
// 주 생성자에 정의한 프로퍼티들이 컬럼으로 생성된다.
data class TestModel(
    // idx 컬럼
    // autoGenerate에 true를 넣어주면 데이터를 저장할 때 마다 1씩 증가되는
    // 값으로 채워준다.
    @PrimaryKey(autoGenerate = true)
    var testIdx:Int = 0,
    var testData1:String = "",
    var testData2:Double = 0.0
)