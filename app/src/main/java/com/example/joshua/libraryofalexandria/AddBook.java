package com.example.joshua.libraryofalexandria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.joshua.libraryofalexandria.models.Book;

public class AddBook extends AppCompatActivity implements View.OnClickListener{

    // Declaring necessary variables for the activity
    private EditText mTitle;
    private EditText mISBN;
    private EditText mAuthor;
    private EditText mPublisher;
    private EditText mEdition;
    private EditText mYearOfPublication;
    private EditText mGenre;
    private EditText mDescription;
    private Button mAddBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        // Setting variables to their respective views
        mTitle = (EditText) findViewById(R.id.title);
        mISBN = (EditText) findViewById(R.id.ISBN);
        mAuthor = (EditText) findViewById(R.id.author);
        mPublisher = (EditText) findViewById(R.id.publisher);
        mEdition = (EditText) findViewById(R.id.edition);
        mYearOfPublication = (EditText) findViewById(R.id.yearOfPublication);
        mGenre = (EditText) findViewById(R.id.genre);
        mDescription = (EditText) findViewById(R.id.description);
        mAddBook = (Button) findViewById(R.id.addBook);

        // When button is clicked do something
        mAddBook.setOnClickListener(this);
    }

    // When button is clicked set all variables to the Strings of a book and send that back to the activity that started this activity
    @Override
    public void onClick(View view) {
        String title = mTitle.getText().toString();
        String ISBN = mISBN.getText().toString();
        String author = mAuthor.getText().toString();
        String publisher = mPublisher.getText().toString();
        String edition = mEdition.getText().toString();
        String yearOfPublication = mYearOfPublication.getText().toString();
        String genre = mGenre.getText().toString();
        String description = mDescription.getText().toString();

        Book newBook = new Book(0, title, ISBN, author, publisher, edition, yearOfPublication, genre, description);
        Intent intent = new Intent();
        intent.putExtra("result", newBook);
        setResult(RESULT_OK, intent);
        finish();
    }
}
