package com.example.arifh.house_maide_app.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.arifh.house_maide_app.Model.Rating;
import com.example.arifh.house_maide_app.Model.Review;
import com.example.arifh.house_maide_app.Model.User;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASENAME = "HomeMaids.db";
    private static final int VERSION = 2;

    //Table name
    private static final String REGISTRATION_TABLE = "Servent_Table";
    private static final String REVIEW_TABLE = "Review_tb";
    private static final String RATING_PROFILE = "Rating_tb";

    //Registration Column...............
    private static final String ID = "Id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String PHONE_NUMBER = "phn_num";
    private static final String EMAIL = "Email";
    private static final String DATE_OF_BIRTH = "DateOfBirth";
    private static final String JOB_JOBLESS = "JobJobless";
    private static final String JOB_ADDRESS = "JobAddress";
    private static final String START_TIME = "Start_time";
    private static final String END_TIME = "End_time";
    private static final String CURRENT_SALARY = "CurrentSalary";
    private static final String PASSWORD = "password";
    private static final String TYPE = "type";
    SQLiteDatabase db;

    // Review Column................
    private static final String RID = "id";
    private static final String REVIEW = "review";


    //..............Rating Column...................///

    private static final String RATING_ID = "id";
    private static final String RATING_NUM = "Rating";


    public DbHelper(Context context) {
        super(context, DATABASENAME, null, VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL("CREATE TABLE " + RATING_PROFILE + "(" + RATING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + RATING_NUM + " TEXT)");
            db.execSQL("CREATE TABLE " + REVIEW_TABLE + " (" + RID + " INTEGER PRIMARY KEY AUTOINCREMENT," + REVIEW + " text)");
            db.execSQL("Create table " + REGISTRATION_TABLE + "(" + ID + " integer primary key autoincrement," + NAME + " text," + ADDRESS + " text," + PHONE_NUMBER + " text," + EMAIL + " text," + DATE_OF_BIRTH + " text," + JOB_JOBLESS + " text," + JOB_ADDRESS + "," + START_TIME + " text," + END_TIME + " text," + CURRENT_SALARY + " Text," + PASSWORD + " text," + TYPE + " text)");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + RATING_PROFILE);
        db.execSQL("drop table if exists " + REVIEW_TABLE);
        db.execSQL("drop table if exists " + REGISTRATION_TABLE);


        onCreate(db);
    }

    public void addRating(String rating) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RATING_NUM, rating);
        db.insert(RATING_PROFILE, null, contentValues);
    }

    public void addReview(String review) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(REVIEW, review);
        db.insert(REVIEW_TABLE, null, contentValues);
    }


    public void addUser(User user) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, user.getName());
        contentValues.put(ADDRESS, user.getAddress());
        contentValues.put(PHONE_NUMBER, user.getPhone_num());
        contentValues.put(EMAIL, user.getEmail());
        contentValues.put(DATE_OF_BIRTH, user.getDateOfBirth());
        contentValues.put(JOB_JOBLESS, user.getJobJobless());
        contentValues.put(JOB_ADDRESS, user.getJobAddress());
        contentValues.put(START_TIME, user.getStartTime());
        contentValues.put(END_TIME, user.getEndTime());
        contentValues.put(CURRENT_SALARY, user.getCurrentSalary());
        contentValues.put(PASSWORD, user.getPass());
        contentValues.put(TYPE, user.getType());

        db.insert(REGISTRATION_TABLE, null, contentValues);
        db.close();


    }

    public String checkUserExist(String Email, String password, String type) {
        db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT Email,password FROM registrationUser ", new String[]{Email, password});
        int count = c.getCount();

        if (count < 1) {
            c.close();
            return "If exist";
        } else if (count >= 1 && c.moveToFirst()) {

            password = c.getString(c.getColumnIndex(PASSWORD));
            c.close();
        }
        return password;
    }

    public User doLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        //String selectquery="SELECT * FROM TABLE_REGISTER";
        Cursor cursor = db.query(REGISTRATION_TABLE, new String[]{NAME, ADDRESS, PHONE_NUMBER, EMAIL, DATE_OF_BIRTH, JOB_JOBLESS, JOB_ADDRESS, START_TIME, END_TIME, CURRENT_SALARY, PASSWORD, TYPE}, "Email=? and password=?", new String[]{email, password}, null, null, null, null);
        //Cursor cursor=db.rawQuery("Select Email,Password from registrationUser",new String[]{email,password});

        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11));
        }
        return user;
    }

    public User viewServentProfile(String email) {
        User user = null;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] columnes = new String[]{NAME, ADDRESS, PHONE_NUMBER, EMAIL, DATE_OF_BIRTH, JOB_JOBLESS, JOB_ADDRESS, START_TIME, END_TIME, CURRENT_SALARY, PASSWORD, TYPE};
            Cursor cursor = db.query(REGISTRATION_TABLE, columnes, "Email=?", new String[]{email}, null, null, null, null);

            //Cursor cursor = db.rawQuery("select name,phn_num,address from registrationUser WHERE Email",null);
            if (cursor != null)
                cursor.moveToFirst();
            if (cursor != null && cursor.getCount() > 0) {
                user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11));
            }
            if (cursor != null) {
                cursor.close();
            }

        } catch (Exception er) {
            er.printStackTrace();
        }
        return user;

    }

    public Cursor viewRating(String name) {
        db = this.getReadableDatabase();
        String query = "Select * from " + RATING_PROFILE + "";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor viewReview() {
        db = this.getReadableDatabase();
        String query = "Select * from " + REVIEW_TABLE + "";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }


}


