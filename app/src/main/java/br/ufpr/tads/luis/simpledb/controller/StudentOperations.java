package br.ufpr.tads.luis.simpledb.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import br.ufpr.tads.luis.simpledb.model.Student;

public class StudentOperations {
    private SimpleDBWrapper dbHelper;
    private String[] STUDENT_TABLE_COLUMNS = {SimpleDBWrapper.STUDENT_ID, SimpleDBWrapper.STUDENT_NAME};
    private SQLiteDatabase db;

    public StudentOperations(Context context) {
        dbHelper = new SimpleDBWrapper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Student addStudent(String name) {
        ContentValues values = new ContentValues();
        values.put(SimpleDBWrapper.STUDENT_NAME, name);
        long studentID = db.insert(SimpleDBWrapper.TABLE_NAME, null, values);

        Cursor cursor = db.query(SimpleDBWrapper.TABLE_NAME, STUDENT_TABLE_COLUMNS, SimpleDBWrapper.STUDENT_ID + "=" + studentID, null, null, null, null);

        cursor.moveToFirst();
        Student student = parseStudent(cursor);
        cursor.close();
        return student;
    }

    private Student parseStudent(Cursor cursor) {
        Student student = new Student();
        int idx = cursor.getColumnIndex(SimpleDBWrapper.STUDENT_ID);
        if (idx > 0) {
            student.setId(cursor.getLong(idx));
        }
        idx = cursor.getColumnIndex(SimpleDBWrapper.STUDENT_NAME);
        if (idx > 0) {
            student.setName(cursor.getString(idx));
        }
        return student;
    }
}

