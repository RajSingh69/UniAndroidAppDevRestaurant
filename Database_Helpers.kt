package com.example.mobileappdevcoursework

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//class UserDatabaseHelper(context: NewUserPage) : SQLiteOpenHelper(context,
//    DATABASE_NAME, null, DATABASE_VERSION) {
//
//
//    /**
//     * This method is called when the database is created for the first time.
//     */
//    override fun onCreate(db: SQLiteDatabase) {
//        val createUserQuery =
//            "CREATE	TABLE " + "$TABLE_USER(" +
//                    "$COLUMN_UID INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    "$COLUMN_USER_NAME TEXT," +
//                    "$COLUMN_USER_EMAIL TEXT," +
//                    "$COLUMN_USER_NUMBER INTEGER," +
//                    "$COLUMN_USER_AGE INTEGER," +
//                    "$COLUMN_USER_PASSWORD TEXT," +
//
//                    "$COLUMN_USER_INDIAN_FOOD INTEGER," +
//                    "$COLUMN_USER_ITALIAN_FOOD INTEGER," +
//                    "$COLUMN_USER_ASIAN_FOOD INTEGER," +
//                    "$COLUMN_USER_AFRICAN_FOOD INTEGER," +
//                    "$COLUMN_USER_NEW_CREATIONS INTEGER," +
//                    "$COLUMN_USER_FOOD_FAILS INTEGER," +
//
//                    "$COLUMN_USER_BOOKING_LOCATION TEXT," +
//                    "$COLUMN_USER_BOOKING_DATE TEXT," +
//                    "$COLUMN_USER_BOOKING_TIME TEXT)"
//
//        db.execSQL(createUserQuery)
//    }
//
//    /**
//     * This method is called when the database needs to be upgraded.
//     * It drops the existing tasks table and calls onCreate to recreate it.
//     */
//    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
//        onCreate(db)
//    }
//
//
//    /**
//     * Here is where we add data into the database
//     */
//    fun addUserInfoToDb(theUserName: String,
//                        theUserEmail: String,
//                        theUserNumber: Int,
//                        theUserAge: Int,
//                        theUserPassword: String,
//
//                        theUserIndianFood: Boolean,
//                        theUserItalianFood: Boolean,
//                        theUserAsianFood: Boolean,
//                        theUserAfricanFood: Boolean,
//                        theUserFoodCreations: Boolean,
//                        theUserFoodFails: Boolean,
//
//                        theUserBookingLocation: String,
//                        theUserBookingDate: String,
//                        theUserBookingTime: String) {
//
//        val userValues = ContentValues()
//        userValues.put(COLUMN_USER_NAME, theUserName)
//        userValues.put(COLUMN_USER_EMAIL, theUserEmail)
//        userValues.put(COLUMN_USER_NUMBER, theUserNumber)
//        userValues.put(COLUMN_USER_AGE, theUserAge)
//        userValues.put(COLUMN_USER_PASSWORD, theUserPassword)
//
//        userValues.put(COLUMN_USER_INDIAN_FOOD, theUserIndianFood)
//        userValues.put(COLUMN_USER_ITALIAN_FOOD, theUserItalianFood)
//        userValues.put(COLUMN_USER_ASIAN_FOOD, theUserAsianFood)
//        userValues.put(COLUMN_USER_AFRICAN_FOOD, theUserAfricanFood)
//        userValues.put(COLUMN_USER_NEW_CREATIONS, theUserFoodCreations)
//        userValues.put(COLUMN_USER_FOOD_FAILS, theUserFoodFails)
//
//        userValues.put(COLUMN_USER_BOOKING_LOCATION, theUserBookingLocation)
//        userValues.put(COLUMN_USER_BOOKING_DATE, theUserBookingDate)
//        userValues.put(COLUMN_USER_BOOKING_TIME, theUserBookingTime)
//
//        val db = this.writableDatabase
//        db.insert(TABLE_USER, null, userValues)
//        db.close()
//
//    }
//
//    fun getNameUserDatabase(): Cursor {
//        val db = this.readableDatabase
//        return db.rawQuery("SELECT * FROM " + TABLE_USER, null)
//    }
//
//    companion object{
//
//        const val DATABASE_VERSION = 1
//        const val DATABASE_NAME = "UserDatabase"
//        const val TABLE_USER = "UserTable"
//        const val COLUMN_UID = "UID"
//        const val COLUMN_USER_NAME = "UserName"
//        const val COLUMN_USER_NUMBER = "UserNumber"
//        const val COLUMN_USER_EMAIL = "UserEmail"
//        const val COLUMN_USER_AGE = "UserAge"
//        const val COLUMN_USER_PASSWORD = "UserPassword"
//
//        const val COLUMN_USER_INDIAN_FOOD = "UserIndianFood"
//        const val COLUMN_USER_ITALIAN_FOOD = "UserItalianFood"
//        const val COLUMN_USER_ASIAN_FOOD = "UserAsianFood"
//        const val COLUMN_USER_AFRICAN_FOOD = "UserAfricanFood"
//        const val COLUMN_USER_NEW_CREATIONS = "UserFoodCreations"
//        const val COLUMN_USER_FOOD_FAILS = "UserFoodFails"
//
//        const val COLUMN_USER_BOOKING_LOCATION = "UserBookingLocation"
//        const val COLUMN_USER_BOOKING_DATE = "UserBookingDate"
//        const val COLUMN_USER_BOOKING_TIME = "UserBookingTime"
//    }
//
//}
//
//
///**
// * NEXT DATABASE GOES HERE
// */