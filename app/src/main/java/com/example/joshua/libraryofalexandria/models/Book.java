package com.example.joshua.libraryofalexandria.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Joshua on 4/16/2018.
 */

public class Book implements Parcelable{

    //Database Constants
    public static final String TABLE_NAME = "book";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ISBN = "isbn";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_PUBLISHER = "publisher";
    public static final String COLUMN_EDITION = "edition";
    public static final String COLUMN_YEAROFPUBLICATION = "yearOfPublication";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_DESCRIPTION = "description";

    // Table create statement
    public static final String CREATE_STATEMENT = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + COLUMN_TITLE + " TEXT NOT NULL, "
            + COLUMN_ISBN + " TEXT NOT NULL, "
            + COLUMN_AUTHOR + " TEXT NOT NULL, "
            + COLUMN_PUBLISHER + " TEXT NOT NULL, "
            + COLUMN_EDITION + " TEXT NOT NULL, "
            + COLUMN_YEAROFPUBLICATION + " TEXT NOT NULL, "
            + COLUMN_GENRE + " TEXT NOT NULL, "
            + COLUMN_DESCRIPTION + " TEXT NOT NULL"
            + ")";

    private long _id;
    private String mTitle;
    private String mISBN;
    private String mAuthor;
    private String mPublisher;
    private String mEdition;
    private String mYearOfPublication;
    private String mGenre;
    private String mDescription;

    protected Book(Parcel in){
        _id = in.readLong();
        mTitle = in.readString();
        mISBN = in.readString();
        mAuthor = in.readString();
        mPublisher = in.readString();
        mEdition = in.readString();
        mYearOfPublication = in.readString();
        mGenre = in.readString();
        mDescription = in.readString();
    }

    public Book(long id, String title, String ISBN, String author, String publisher, String edition, String yearOfPublication, String genre, String description){
        this._id = id;
        this.mTitle = title;
        this.mISBN = ISBN;
        this.mAuthor = author;
        this.mPublisher = publisher;
        this.mEdition = edition;
        this.mYearOfPublication = yearOfPublication;
        this.mGenre = genre;
        this.mDescription = description;
    }

    public String getTitle(){ return mTitle; }
    public String getISBN(){ return mISBN; }
    public String getAuthor(){ return mAuthor; }
    public String getPublisher(){ return mPublisher; }
    public String getEdition(){ return mEdition; }
    public String getYearOfPublication(){ return mYearOfPublication;}
    public String getGenre(){ return mGenre; }
    public String getDescription(){ return mDescription; }

    public void setTitle(String title){ this.mTitle = title; }
    public void setISBN(String ISBN){ this.mISBN = ISBN; }
    public void setAuthor(String author){ this.mAuthor = author; }
    public void setPublisher(String publisher) {this.mPublisher = publisher; }
    public void setEdition(String edition) {this.mEdition = edition; }
    public void setYearOfPublication(String yearOfPublication) { this.mYearOfPublication = yearOfPublication; }
    public void setGenre(String genre) {this.mGenre = genre; }
    public void setDescription(String description) { this.mDescription = description; }

    public String toString(){
        return "Summary of the book\nTitle: " + mTitle + "\nISBN: " + mISBN + "\nAuthor: " + mAuthor + "\nPublisher "
                + mPublisher + "\nEdition: " + mEdition + "\nYear of Publication: " + mYearOfPublication + "\nGenre: "
                + mGenre + "\nDescription: " + mDescription;
    }

    // Creator object
    public static final Creator<Book> CREATOR = new Creator<Book>(){
        // Call a constructor we have created for parcels and return results
        @Override
        public Book createFromParcel(Parcel in) {return new Book(in); }
        // generates a new array
        @Override
        public Book[] newArray(int size) { return new Book[size]; }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(_id);
        parcel.writeString(mTitle);
        parcel.writeString(mISBN);
        parcel.writeString(mAuthor);
        parcel.writeString(mPublisher);
        parcel.writeString(mEdition);
        parcel.writeString(mYearOfPublication);
        parcel.writeString(mGenre);
        parcel.writeString(mDescription);
    }
    public long getId() { return _id; }

    public void setId(long _id) { this._id = _id; }
}
