package com.thanhnghia.testiqnavi;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "dbIQTEST";

    private static final String TABLE_QUESTION = "question";

    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "ques";
    private static final String KEY_ANS = "ans";

    private static final String KEY_ANSA = "ansa";
    private static final String KEY_ANSB = "ansb";
    private static final String KEY_ANSC = "ansc";
    private static final String KEY_ANSD = "ansd";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + TABLE_QUESTION + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_QUES + " TEXT,"
                + KEY_ANS + " TEXT,"
                + KEY_ANSA + " TEXT,"
                + KEY_ANSB + " TEXT,"
                + KEY_ANSC + " TEXT,"
                + KEY_ANSD + " TEXT" + ")"; */

        String CREATE_QUESTIONS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_QUESTION + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_QUES + " TEXT,"
                + KEY_ANS + " TEXT,"
                + KEY_ANSA + " TEXT,"
                + KEY_ANSB + " TEXT,"
                + KEY_ANSC + " TEXT,"
                + KEY_ANSD + " TEXT" + ")";
        db.execSQL(CREATE_QUESTIONS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new question
    void addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues ();
        values.put(KEY_QUES, question.getQuestion());
        values.put(KEY_ANS, question.getAns());

        values.put(KEY_ANSA, question.get_ansA());
        values.put(KEY_ANSB, question.get_ansB());
        values.put(KEY_ANSC, question.get_ansC());
        values.put(KEY_ANSD, question.get_ansD());
        // Inserting Row
        db.insert(TABLE_QUESTION, null, values);
        db.close(); // Closing database connection
    }

    Question getQuestion(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_QUESTION, new String[]{KEY_ID,
                        KEY_QUES, KEY_ANS, KEY_ANSA, KEY_ANSB, KEY_ANSC, KEY_ANSD}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Question question = new Question(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        // return question
        return question;
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<Question> ();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setID(Integer.parseInt(cursor.getString(0)));
                question.setQues(cursor.getString(1));
                question.setAns(cursor.getString(2));
                // ans A B C D
                question.set_ansA(cursor.getString(3));
                question.set_ansB(cursor.getString(4));
                question.set_ansC(cursor.getString(5));
                question.set_ansD(cursor.getString(6));

                // Adding question to list
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        return questionList;
    }


    // Deleting single question
    public void deleteQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_QUESTION, KEY_ID + " = ?",
                new String[]{String.valueOf(question.getID())});

        db.close();
    }


    public int getQuestionsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_QUESTION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    boolean isTableExists(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase ();
        if (tableName == null || db == null || !db.isOpen())
        {
            return false;
        }
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", tableName});
        if (!cursor.moveToFirst())
        {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }
}