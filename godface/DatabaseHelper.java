package com.example.hp.godface;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.google.android.gms.vision.face.Landmark.BOTTOM_MOUTH;
import static com.google.android.gms.vision.face.Landmark.LEFT_CHEEK;
import static com.google.android.gms.vision.face.Landmark.LEFT_EAR;
import static com.google.android.gms.vision.face.Landmark.LEFT_EYE;
import static com.google.android.gms.vision.face.Landmark.LEFT_MOUTH;
import static com.google.android.gms.vision.face.Landmark.NOSE_BASE;
import static com.google.android.gms.vision.face.Landmark.RIGHT_CHEEK;
import static com.google.android.gms.vision.face.Landmark.RIGHT_EAR;
import static com.google.android.gms.vision.face.Landmark.RIGHT_EYE;
import static com.google.android.gms.vision.face.Landmark.RIGHT_MOUTH;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FaceDetection.db";
    public String TABLE_NAME = "face_table";
    public  String COL_1 = "ID";
    public  String COL_2 = "leftEye";
    public  String COL_3 = "rightEye";
    public  String COL_4 = "nose";
    public  String COL_5 = "bottommouth";
    public  String COL_6 = "leftmouth";
    public  String COL_7 = "rightmouth";
    public String COL_8 = "leftear";
    public String COL_9 = "rightear";
    public String COL_10= "leftcheek";
    public String COL_11 = "rightcheek";

    public String CREATE_DATABASE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "+ COL_1 + "INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COL_2 + " TEXT, "
            + COL_3 + " TEXT, "
            + COL_4 + " TEXT, "
            + COL_5 + " TEXT, "
            + COL_6 + " TEXT, "
            + COL_7 + " TEXT, "
            + COL_8 + " TEXT, "
            + COL_9 + " TEXT, "
            + COL_10 + " TEXT, "
            + COL_11 + " TEXT "
            + ")";
    public DatabaseHelper (Context context ) { super(context,DATABASE_NAME,null,1 );}

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("create table " +TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,LEFT_EYE TEXT,RIGHT_EYE TEXT,NOSE TEXT , BOTTOMMOUTH TEXT , LEFTMOUTH TEXT , RIGHTMOUTH  TEXT , LEFTEAR TEXT ,RIGHTEAR TEXT ,LEFTCHEEK TEXT ,RIGHTCHEEK TEXT )");
       // db.execSQL("show tables");
 /*db.execSQL(" CREATE TABLE " + DATABASE_NAME + " (" +
                  COL_1 + " TEXT PRIMARY KEY, " +
                   LEFT_EYE  + " TEXT NOT NULL, " +
                   RIGHT_EYE+ " TEXT NOT NULL, " +
                  NOSE_BASE + " TEXT NOT NULL, " +
                   BOTTOM_MOUTH + " TEXT NOT NULL, " +
                LEFT_MOUTH+ " TEXT NOT NULL, " +
                        RIGHT_MOUTH + " TEXT NOT NULL, " +
                        LEFT_EAR +" TEXT NOT NULL, " +
                        RIGHT_EAR + " TEXT NOT NULL, " +
                       LEFT_CHEEK+ " TEXT NOT NULL, " +
                         RIGHT_CHEEK+ " TEXT NOT NULL);"
            );
   */
    db.execSQL(CREATE_DATABASE_TABLE );
    }


  @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       //db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
           // onCreate(db);
    }

    public boolean insertdata(double lefteye , double righteye ,double nose ,double bottomMouth ,double leftMouth , double rightMouth , double leftEar ,double rightEar ,double leftCheeck ,double rightcheek )
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2 ,lefteye);
        contentValues.put(COL_3, righteye);
        contentValues.put(COL_4, nose);
        contentValues.put(COL_5 ,bottomMouth);
        contentValues.put(COL_6,leftMouth);
        contentValues.put(COL_7 ,rightMouth);
        contentValues.put(COL_8,leftEar);
        contentValues.put(COL_9,rightEar);
        contentValues.put(COL_10,leftCheeck);
        contentValues.put(COL_11,rightcheek);
        long result = database.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
       Log.d("DATABASE helper " , " show all the data");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

}
