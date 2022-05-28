package com.example.loginfirebase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception


class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "user.db"
        private const val TBL_USER = "tbl_user"
        private const val ID = "id"
        private const val NAME = "name"
        private const val IDENT = "ident"
        private const val PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTBLUser = ("CREATE TABLE " + TBL_USER +
                "(${ID} INTEGER PRIMARY KEY, ${NAME} TEXT, " +
                "${IDENT} TEXT, ${PASSWORD} TEXT )"
                )
        db?.execSQL(createTBLUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_USER")
        onCreate(db)
    }

    fun insertUser(std: UserModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(IDENT, std.ident)
        contentValues.put(PASSWORD, std.password)

        val success = db.insert(TBL_USER, null, contentValues)
        db.close()
        return success
    }

    fun userLogin(identParam: String, passwordParam: String): ArrayList<UserModel> {
        val stdList: ArrayList<UserModel> = ArrayList()
        val selectQuery = "SELECT id, name, ident FROM $TBL_USER WHERE ident=$identParam AND password=$passwordParam"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var ident: String

        if(cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                ident = cursor.getString(cursor.getColumnIndexOrThrow("ident"))

                val std = UserModel(id = id, name = name, ident = ident, password = "***")
                stdList.add(std)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return stdList
    }

}




















