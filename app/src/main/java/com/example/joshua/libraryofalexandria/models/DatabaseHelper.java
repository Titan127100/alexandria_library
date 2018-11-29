package com.example.joshua.libraryofalexandria.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Joshua on 4/20/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Set Database Properties
    public static final String DATABASE_NAME = "BookDB";
    public static final int DATABASE_VERSION = 1;

    // Constructor for this class
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Initialise the SQL database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Book.CREATE_STATEMENT);
    }
    //Update the database with a fresh new one
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Book.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    // Add book using contentvalues which are what databases accept
    // Remember that a database should only be opened for a short period of time in order to avoid over writing
    public void addBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Book.COLUMN_TITLE, book.getTitle());
        values.put(Book.COLUMN_ISBN, book.getISBN());
        values.put(Book.COLUMN_AUTHOR, book.getAuthor());
        values.put(Book.COLUMN_PUBLISHER, book.getPublisher());
        values.put(Book.COLUMN_EDITION, book.getEdition());
        values.put(Book.COLUMN_YEAROFPUBLICATION, book.getYearOfPublication());
        values.put(Book.COLUMN_GENRE, book.getGenre());
        values.put(Book.COLUMN_DESCRIPTION, book.getDescription());

        db.insert(Book.TABLE_NAME, null, values);
        db.close();
    }

    // Retreives all books in the database
    public HashMap<Long, Book> getAllBook() {
        HashMap<Long, Book> people = new LinkedHashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Book.TABLE_NAME, null);
        // Add each person to hashmap (Each row has 1 person)
        while (cursor.moveToNext()) {
            Book book = new Book(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8));
            people.put(book.getId(), book);
        }
        cursor.close();
        db.close();
        if(people.size() == 0) {
            // If there are no people in the db then add some default people
            createDefaultBook();
            people = getAllBook();
        }
        return people;
    }

    //removes a book when user wishes for it
    public void removeBook(Book book)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Book.TABLE_NAME,
                Book.COLUMN_ID + " = ?",
                new String[] {String.valueOf(book.getId())});
    }

    private void createDefaultBook(){
        addBook(new Book(0, "The Revenant", "978 0 00 812402 1", "Michael Punke", "The Borough Press", "1", "2015", "adventure", "When expert tracker Glass is viciously mauled by a bear, death seems inevitable."));
        addBook(new Book(1, "The Revenant", "978 0 00 812402 1", "Michael Punke", "The Borough Press", "1", "2015", "adventure", "When expert tracker Glass is viciously mauled by a bear, death seems inevitable."));
        addBook(new Book(2, "The Revenant", "978 0 00 812402 1", "Michael Punke", "The Borough Press", "1", "2015", "adventure", "When expert tracker Glass is viciously mauled by a bear, death seems inevitable."));
        addBook(new Book(3, "The Revenant", "978 0 00 812402 1", "Michael Punke", "The Borough Press", "1", "2015", "adventure", "When expert tracker Glass is viciously mauled by a bear, death seems inevitable."));
        addBook(new Book(4, "The Revenant", "978 0 00 812402 1", "Michael Punke", "The Borough Press", "1", "2015", "adventure", "When expert tracker Glass is viciously mauled by a bear, death seems inevitable."));
    }
}
