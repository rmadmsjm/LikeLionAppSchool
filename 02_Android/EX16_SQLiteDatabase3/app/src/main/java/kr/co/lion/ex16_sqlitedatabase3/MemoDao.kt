package kr.co.lion.ex16_sqlitedatabase3

import android.content.Context

class MemoDao {
    companion object {
        // select one
        fun selectOneMemo(context: Context, idx: Int) : MemoModel {
            val sql = """select idx, title, content
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

            val idx = cursor.getInt(idx1)
            val title = cursor.getString(idx2)
            val content = cursor.getString(idx3)

            val memoModel = MemoModel(idx, title, content)

            dbHelper.close()

            return memoModel
        }

        // select all
        fun selectAllMemo(context: Context) : MutableList<MemoModel> {
            val sql = """select idx, title, content
                |from MemoTable
                |order by idx desc
            """.trimMargin()

            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            val memoList = mutableListOf<MemoModel>()

            while (cursor.moveToNext()) {
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("title")
                val idx3 = cursor.getColumnIndex("content")

                val idx = cursor.getInt(idx1)
                val title = cursor.getString(idx2)
                val content = cursor.getString(idx3)

                val memoModel = MemoModel(idx, title, content)

                memoList.add(memoModel)
            }

            dbHelper.close()

            return memoList
        }

        // insert
        fun insertMemo(context: Context, memoModel: MemoModel) {
            val sql = """insert into MemoTable
                |(idx, title, content)
                |values (?, ?, ?)
            """.trimMargin()

            val args = arrayOf(memoModel.idx, memoModel.title, memoModel.content)

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

            dbHelper.writableDatabase
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