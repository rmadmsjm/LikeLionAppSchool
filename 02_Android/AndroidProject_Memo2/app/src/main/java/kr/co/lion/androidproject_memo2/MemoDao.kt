package kr.co.lion.androidproject_memo2

import android.content.Context

class MemoDao {
    companion object {
        // select one
        fun selectOneMemo(context: Context, idx: Int) : MemoModel {
            val sql = """select idx, title, content, date
                |from MemoTable
                |where idx = ?
            """.trimMargin()

            val args = arrayOf("$idx")

            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, args)

            cursor.moveToNext()

            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("title")
            val idx3 = cursor.getColumnIndex("content")
            val idx4 = cursor.getColumnIndex("date")

            val idx = cursor.getInt(idx1)
            val title = cursor.getString(idx2)
            val content = cursor.getString(idx3)
            val date = cursor.getString(idx4)

            val memoModel = MemoModel(idx, title, content, date)

            dbHelper.close()

            return memoModel
        }

        // select all
        fun selectAllMemo(context: Context) : MutableList<MemoModel> {
            val sql = """select *
                |from MemoTable
                |order by idx desc
            """.trimMargin()

            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            val memoList = mutableListOf<MemoModel>()

            while(cursor.moveToNext()) {
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("title")
                val idx3 = cursor.getColumnIndex("content")
                val idx4 = cursor.getColumnIndex("date")

                val idx = cursor.getInt(idx1)
                val title = cursor.getString(idx2)
                val content = cursor.getString(idx3)
                val date = cursor.getString(idx4)

                val memoModel = MemoModel(idx, title, content, date)
                memoList.add(memoModel)
            }

            dbHelper.close()

            return memoList
        }

        // insert
        fun insertMemo(context: Context, memoModel: MemoModel) {
            val sql = """insert into MemoTable
                |(idx, title, content, date)
                |values (?, ?, ?, ?)
            """.trimMargin()

            val args = arrayOf(memoModel.idx, memoModel.title, memoModel.content, memoModel.date)

            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        // update
        fun updateMemo(context: Context, memoModel: MemoModel) {
            val sql = """update MemoTable
                |set title = ?, content = ?
                |where idx = ?
            """.trimMargin()

            val args = arrayOf(memoModel.title, memoModel.content, memoModel.idx)

            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        // delete
        fun deleteMemo(context: Context, idx: Int) {
            val sql = """delete from MemoTable
                |where idx = ?
            """.trimMargin()

            val args = arrayOf(idx)

            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }
    }
}