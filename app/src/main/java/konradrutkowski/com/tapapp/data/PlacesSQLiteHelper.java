package konradrutkowski.com.tapapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import konradrutkowski.com.tapapp.fsquare.FSquarePlace;

public class PlacesSQLiteHelper extends SQLiteOpenHelper {

    private static final int database_VERSION = 1;
    private static final String database_NAME = "FSquarePlaceDB";
    private static final String table_FSQUAREPLACES = "FSquarePlaces";
    private static final String FSquarePlace_ID = "id";
    private static final String FSquarePlace_NAME = "name";
    private static final String FSquarePlace_CATEGORY = "category";
    private static final String FSquarePlace_FSQID = "fsqid";
    private static final String FSquarePlace_ADDRES = "address";
    private static final String FSquarePlace_CITY = "city";
    private static final String FSquarePlace_URL = "url";
    private static final String FSquarePlace_DISTANCE = "distance";
    private static final String FSquarePlace_CHECKINS = "checkins";


    private static final String[] COLUMNS = { FSquarePlace_ID, FSquarePlace_NAME, FSquarePlace_CATEGORY };

    public PlacesSQLiteHelper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FSQUAREPLACE_TABLE = "CREATE TABLE FSquarePlaces ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, " + "category TEXT, " + "fsqid TEXT, " + "address TEXT,"
                + "city TEXT," + "url TEXT," + "distance TEXT," + "checkins TEXT );" ;
        db.execSQL(CREATE_FSQUAREPLACE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS FSquarePlaces");
        this.onCreate(db);
    }

    public void createFSquarePlace(FSquarePlace fSquarePlace) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FSquarePlace_NAME, fSquarePlace.getName());
        values.put(FSquarePlace_CATEGORY, fSquarePlace.getCategory());
        values.put(FSquarePlace_FSQID, fSquarePlace.getID());
        values.put(FSquarePlace_ADDRES, fSquarePlace.getAddress());
        values.put(FSquarePlace_CITY, fSquarePlace.getCity());
        values.put(FSquarePlace_URL, fSquarePlace.getUrl());
        values.put(FSquarePlace_DISTANCE, fSquarePlace.getDistance());
        values.put(FSquarePlace_CHECKINS, fSquarePlace.getCheckins());

        db.insert(table_FSQUAREPLACES, null, values);

        db.close();
    }


    public ArrayList<FSquarePlace> getAllFSquarePlaces() {
        ArrayList<FSquarePlace> fSquarePlaces = new ArrayList<>();

        String query = "SELECT  * FROM " + table_FSQUAREPLACES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        FSquarePlace fSquarePlace = null;
        if (cursor.moveToFirst()) {
            do {
                fSquarePlace = new FSquarePlace();
                fSquarePlace.setDbID(Integer.parseInt(cursor.getString(0)));
                fSquarePlace.setName(cursor.getString(1));
                fSquarePlace.setCategory(cursor.getString(2));
                fSquarePlace.setID(cursor.getString(3));
                fSquarePlace.setAddress(cursor.getString(4));
                fSquarePlace.setCity(cursor.getString(5));
                 fSquarePlace.setUrl(cursor.getString(6));
                fSquarePlace.setDistance(cursor.getString(7));
                fSquarePlace.setCheckins(cursor.getString(8));
                fSquarePlaces.add(fSquarePlace);
            } while (cursor.moveToNext());
        }
        return fSquarePlaces;
    }
}