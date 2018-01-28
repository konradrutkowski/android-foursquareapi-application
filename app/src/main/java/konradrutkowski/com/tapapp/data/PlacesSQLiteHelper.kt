package konradrutkowski.com.tapapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import konradrutkowski.com.tapapp.fsquare.Place
import java.util.*

class PlacesSQLiteHelper(context: Context) : SQLiteOpenHelper(context, database_NAME, null, database_VERSION) {


    val allPlaces: ArrayList<Place>
        get() {
            val fSquarePlaces = ArrayList<Place>()

            val query = "SELECT  * FROM " + table_FSQUAREPLACES

            val db = this.writableDatabase
            val cursor = db.rawQuery(query, null)

            var place: Place? = null
            if (cursor.moveToFirst()) {
                do {
                    place = Place()
                    //place.dbID = Integer.parseInt(cursor.getString(0))
                    place.name = cursor.getString(1)
                    place.category = cursor.getString(2)
                    place.id = cursor.getString(3)
                    place.address = cursor.getString(4)
                    place.city = cursor.getString(5)
                    place.url = cursor.getString(6)
                    place.distance = cursor.getString(7)
                    place.checkins = cursor.getString(8)
                    fSquarePlaces.add(place)
                } while (cursor.moveToNext())
            }
            return fSquarePlaces
        }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_FSQUAREPLACE_TABLE = ("CREATE TABLE FSquarePlaces ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, " + "category TEXT, " + "fsqid TEXT, " + "address TEXT,"
                + "city TEXT," + "url TEXT," + "distance TEXT," + "checkins TEXT );")
        db.execSQL(CREATE_FSQUAREPLACE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS FSquarePlaces")
        this.onCreate(db)
    }

    fun createFSquarePlace(place: Place) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(FSquarePlace_NAME, place.name)
        values.put(FSquarePlace_CATEGORY, place.category)
        values.put(FSquarePlace_FSQID, place.id)
        values.put(FSquarePlace_ADDRES, place.address)
        values.put(FSquarePlace_CITY, place.city)
        values.put(FSquarePlace_URL, place.url)
        values.put(FSquarePlace_DISTANCE, place.distance)
        values.put(FSquarePlace_CHECKINS, place.checkins)

        db.insert(table_FSQUAREPLACES, null, values)

        db.close()
    }

    companion object {

        private val database_VERSION = 1
        private val database_NAME = "FSquarePlaceDB"
        private val table_FSQUAREPLACES = "FSquarePlaces"
        private val FSquarePlace_ID = "id"
        private val FSquarePlace_NAME = "name"
        private val FSquarePlace_CATEGORY = "category"
        private val FSquarePlace_FSQID = "fsqid"
        private val FSquarePlace_ADDRES = "address"
        private val FSquarePlace_CITY = "city"
        private val FSquarePlace_URL = "url"
        private val FSquarePlace_DISTANCE = "distance"
        private val FSquarePlace_CHECKINS = "checkins"


        private val COLUMNS = arrayOf(FSquarePlace_ID, FSquarePlace_NAME, FSquarePlace_CATEGORY)
    }
}