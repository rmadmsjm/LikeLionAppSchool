package kr.co.lion.ex16_sqlitedatabase3_2

import android.content.Context

class MemoDao {
    companion object {
        // select all
        fun selectAllMemo(context: Context) : MutableList<MemoModel> {
            // 쿼리문
            val sql = """select memoIdx, memoSubject, memoText
                |from MemoTable
                |order by memoIdx desc
            """.trimMargin()

            // 객체 담을 리스트
            val memoList = mutableListOf<MemoModel>()

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            // 데이터 담기
            while (cursor.moveToNext()) {
                // 컬럼 순서값 가져오기
                val idx1 = cursor.getColumnIndex("memoIdx")
                val idx2 = cursor.getColumnIndex("memoSubject")
                val idx3 = cursor.getColumnIndex("memoText")

                // 데이터 가져오기
                val memoIdx = cursor.getInt(idx1)
                val memoSubject = cursor.getString(idx2)
                val memoText = cursor.getString(idx3)

                // 객체에 담기
                val memoModel = MemoModel(memoIdx, memoSubject, memoText)

                // 리스트에 객체 담기
                memoList.add(memoModel)
            }

            dbHelper.close()

            return memoList
        }

        // select one
        fun selectOneMemo(context: Context, memoIdx:Int) : MemoModel {
            // 쿼리문
            val sql = """select memoIdx, memoSubject, memoText
                |from MemoTable
                |where memoIdx = ?
            """.trimMargin()

            // ? 에 바인딩될 값
            val args = arrayOf("$memoIdx")

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, args)

            // 데이터 담기
            cursor.moveToNext()

            // 컬럼 순서값 가져오기
            val idx1 = cursor.getColumnIndex("memoIdx")
            val idx2 = cursor.getColumnIndex("memoSubject")
            val idx3 = cursor.getColumnIndex("memoText")

            // 데이터 가져오기
            val memoIdx = cursor.getInt(idx1)
            val memoSubject = cursor.getString(idx2)
            val memoText = cursor.getString(idx3)

            // 객체에 담기
            val memoModel = MemoModel(memoIdx, memoSubject, memoText)

            dbHelper.close()

            return memoModel
        }

        // insert
        fun insertMemo(context: Context, memoModel: MemoModel) {
            // 쿼리문
            val sql = """insert into MemoTable
                |(memoSubject, memoText)
                |values (?, ?)
            """.trimMargin()

            // ? 에 바인딩될 값
            val args = arrayOf(memoModel.memoSubject, memoModel.memoText)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)

            dbHelper.close()
        }

        // update
        fun updateMemo(context: Context, memoModel: MemoModel) {
            // 쿼리문
            val sql = """update MemoTable
                |set memoSubject = ?, memoText = ?
                |where memoIdx = ?
            """.trimMargin()

            // ? 에 바인딩될 값
            val args = arrayOf(memoModel.memoSubject, memoModel.memoText, memoModel.memoIdx)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)

            dbHelper.close()
        }

        // delete
        fun deleteMemo(context: Context, memoIdx: Int) {
            // 쿼리문
            val sql = """delete from MemoTable
                |where memoIdx = ?
            """.trimMargin()

            // ? 에 바인딩될 값
            val args = arrayOf(memoIdx)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)

            dbHelper.close()
        }
    }
}