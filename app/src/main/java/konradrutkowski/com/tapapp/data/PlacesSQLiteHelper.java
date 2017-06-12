package konradrutkowski.com.tapapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import konradrutkowski.com.tapapp.fsquare.Place;

public class PlacesSQLiteHelper extends SQLiteOpenHelper {

    private static final int database_VERSION = 1;
    private static final String DATABASE_NAME = "PlacesDB";
    private static final String TABLE_NAME_PLACES = "Places";
    private static final String PLACE_ID = "id";
    private static final String PLACE_NAME = "name";
    private static final String PLACE_CATEGORY = "category";
    private static final String PLACE_FSQUARE_ID = "fsqid";
    private static final String PLACE_ADDRESS = "address";
    private static final String PLACE_CITY = "city";
    private static final String PLACE_URL = "url";
    private static final String PLACE_DISTANCE = "distance";
    private static final String PLACE_CHECKINS = "checkins";


    private static final String[] COLUMNS = {PLACE_ID, PLACE_NAME, PLACE_CATEGORY};

    public PlacesSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FSQUAREPLACE_TABLE = "CREATE TABLE Places ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, " + "category TEXT, " + "fsqid TEXT, " + "address TEXT,"
                + "city TEXT," + "url TEXT," + "distance TEXT," + "checkins TEXT );" ;
        db.execSQL(CREATE_FSQUAREPLACE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_PLACES);
        this.onCreate(db);
    }

    public void createFSquarePlace(Place place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PLACE_NAME, place.getName());
        values.put(PLACE_CATEGORY, place.getCategory());
        values.put(PLACE_FSQUARE_ID, place.getID());
        values.put(PLACE_ADDRESS, place.getAddress());
        values.put(PLACE_CITY, place.getCity());
        values.put(PLACE_URL, place.getUrl());
        values.put(PLACE_DISTANCE, place.getDistance());
        values.put(PLACE_CHECKINS, place.getCheckins());
        db.insert(TABLE_NAME_PLACES, null, values);
        db.close();
    }


    public ArrayList<Place> getALlPlaces() {
        ArrayList<Place> places = new ArrayList<>();

        String query = "SELECT  * FROM " + TABLE_NAME_PLACES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Place place;
        if (cursor.moveToFirst()) {
            do {
                place = new Place();
                place.setDbID(Integer.parseInt(cursor.getString(0)));
                place.setName(cursor.getString(1));
                place.setCategory(cursor.getString(2));
                place.setID(cursor.getString(3));
                place.setAddress(cursor.getString(4));
                place.setCity(cursor.getString(5));
                place.setUrl(cursor.getString(6));
                place.setDistance(cursor.getString(7));
                place.setCheckins(cursor.getString(8));
                places.add(place);
            } while (cursor.moveToNext());
        }
        return places;
    }
}