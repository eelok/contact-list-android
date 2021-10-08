package com.eelok.contactmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.eelok.contactmanager.R;
import com.eelok.contactmanager.model.Contact;
import com.eelok.contactmanager.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    //create my table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create table nameOftable(id int primary key, name text, phoneNumber text)
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" +
                Util.KEY_ID + " INTEGER PRIMARY KEY," +
                Util.KEY_NAME + " TEXT," +
                Util.KEY_PHONE_NUMBER + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE); //table will be created
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE = String.valueOf(R.string.drop_table);
        sqLiteDatabase.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});
        //create a table again
        onCreate(sqLiteDatabase);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());
        db.insert(Util.TABLE_NAME, null, values);
        Log.d("DBHandler", "addContact: " + "idem added");
        db.close();
    }

    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                Util.TABLE_NAME,
                new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONE_NUMBER},
                Util.KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Contact contact = new Contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));

        return contact;
    }

    public List<Contact> getAllContacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        String getAllContacts = "SELECT * FROM " + Util.TABLE_NAME;
        List<Contact> listOfContacts = new ArrayList<>();

        Cursor cursor = db.rawQuery(getAllContacts, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                listOfContacts.add(contact);
            } while (cursor.moveToNext());
        }
        return listOfContacts;
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        return db.update(
                Util.TABLE_NAME,
                values,
                Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())}
        );
    }

    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                Util.TABLE_NAME,
                Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())}
        );
        db.close();
    }

    public int getCount() {
        SQLiteDatabase db = this.getReadableDatabase();

        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(Util.TABLE_NAME, null, null);
//        db.close();
    }
}
