package kr.co.lion.roomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TestDao {

    // 데이터 저장
    // 매개변수로 들어오는 모델 중에
    // primary key로 지정된 프로퍼티는 1부터 1씩 증가되는 값으로 저장되고
    // 그 외에는 프로퍼티에 들어있는 값이 저장된다.
    // 예) insert into TestTable (testData1, testData2) values (?, ?)
    @Insert
    fun insertData(testModel: TestModel)

    // 데이터 수정
    // 매개변수로 들어오는 모델 중에
    // primary key로 지정된 프로퍼티를 조건절로 하고
    // 그 외에는 프로퍼티에 들어있는 값으로 수정된다.
    // 예) update TestTable testData1 = ?, testData2 = ? where testIdx = ?
    @Update
    fun updateData(testModel: TestModel)

    // 데이터 삭제
    // 매개변수로 들어오는 모델의 프로퍼티 중에 primary key로 지정된 프로퍼티를 조건절로하는
    // 쿼리문이 만들어진다.
    // 예) delete from TestTable testIdx = ?
    @Delete
    fun deleteData(testModel: TestModel)

    // 만약 자동으로 만들어지는 쿼리문이 아닌 다른 쿼리문을 쓰겠다면
    // Query라는 어노테이션을 이용한다.
    // 데이터를 가져오는 것도 Query라는 어노테이션을 사용한다.

    // 모든 행의 데이터를 다 가져온다.
    // 반환타입에 지정된 객체에 행의 데이터를 담아 전달해준다.
    @Query("select testIdx, testData1, testData2 from TestTable")
    fun selectDataAll() : List<TestModel>

    // 행 하나의 데이터를 가져온다.
    // 쿼리문에 값에 해당하는 부분은 매개변수의 이름을 지정한다.
    // :매개변수이름
    @Query("select testIdx, testData1, testData2 from TestTable where testIdx = :idx")
    fun selectDataOne(idx:Int) : TestModel
}






