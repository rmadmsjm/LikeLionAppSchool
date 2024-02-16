package kr.co.lion.ex15_sqlitedatabase2

import android.content.Context

class StudentDao {
    companion object {
        // CRUD -> Create, Read, Update, Delete
        // Create -> Insert
        // Read -> Select
        // Update -> Update
        // Delete -> Delete

        // select one
        fun selectOneStudent(context: Context, idx: Int) : StudentModel {
            // 쿼리문
            val sql = """select idx, name, age, kor, eng, math
                |from StudentTable
                |where idx = ?
            """.trimMargin()

            // ? 에 바인딩될 값
            val args = arrayOf("$idx")

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, args)

            // 데이터 가져오기
            // 첫 번째 행에 접근
            cursor.moveToNext()

            // 컬럼이 몇 번째 있는가
            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("name")
            val idx3 = cursor.getColumnIndex("age")
            val idx4 = cursor.getColumnIndex("kor")
            val idx5 = cursor.getColumnIndex("eng")
            val idx6 = cursor.getColumnIndex("math")

            // 값 추출
            val idx = cursor.getInt(idx1)
            val name = cursor.getString(idx2)
            val age = cursor.getInt(idx3)
            val kor = cursor.getInt(idx4)
            val eng = cursor.getInt(idx5)
            val math = cursor.getInt(idx6)

            // 객체에 데이터 담기
            val studentModel = StudentModel(idx, name, age, kor, eng, math)

            // 데이터베이스 닫기
            dbHelper.close()

            return  studentModel
        }

        // select all
        fun selectAllStudent(context: Context) : MutableList<StudentModel> {
            // 쿼리문
            val sql = """select idx, name, age, kor, eng, math
                |from StudentTable
                |order by idx desc
            """.trimMargin()

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            // 데이터 담을 리스트
            val studentList = mutableListOf<StudentModel>()

            // 데이터 가져오기
            while (cursor.moveToNext()) {
                // 컬럼이 몇 번째 있는가
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("name")
                val idx3 = cursor.getColumnIndex("age")
                val idx4 = cursor.getColumnIndex("kor")
                val idx5 = cursor.getColumnIndex("eng")
                val idx6 = cursor.getColumnIndex("math")

                // 값 추출
                val idx = cursor.getInt(idx1)
                val name = cursor.getString(idx2)
                val age = cursor.getInt(idx3)
                val kor = cursor.getInt(idx4)
                val eng = cursor.getInt(idx5)
                val math = cursor.getInt(idx6)

                // 객체에 데이터 담기
                val studentModel = StudentModel(idx, name, age, kor, eng, math)

                studentList.add(studentModel)
            }

            // 데이터베이스 닫기
            dbHelper.close()

            return studentList
        }

        // insert
        fun insertStudent(context: Context, studentModel: StudentModel) {
            // 쿼리문
            val sql = """insert into StudentTable
                |(name, age, kor, eng, math)
                |values (?, ?, ?, ?, ?)
            """.trimMargin()

            // ? 에 바인딩될 값
            val args = arrayOf(studentModel.name, studentModel.age, studentModel.kor, studentModel.eng, studentModel.math)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)

            // 데이터베이스 닫기
            dbHelper.close()
        }

        // update
        fun updateStudent(context: Context, studentModel: StudentModel) {
            // 쿼리문
            val sql = """update StudentTable
                |set name = ?, age = ?, kor = ?, eng = ?, math = ?
                |where idx = ?
            """.trimMargin()

            // ? 에 바인딩될 값
            val args = arrayOf(studentModel.name, studentModel.age, studentModel.kor, studentModel.eng, studentModel.math, studentModel.idx)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)

            // 데이터베이스 닫기
            dbHelper.close()
        }

        // delete
        fun deleteStudent(context: Context, idx: Int) {
            // 쿼리문
            val sql = """delete from StudentTable
                |where idx = ?
            """.trimMargin()

            // ? 에 바인딩될 값
            val args = arrayOf(idx)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)

            // 데이터베이스 닫기
            dbHelper.close()
        }
    }
}