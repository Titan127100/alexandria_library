package com.example.joshua.libraryofalexandria;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.joshua.libraryofalexandria.models.Book;

import org.w3c.dom.Text;

public class ViewBooksActivity extends AppCompatActivity {
    public static final int EDIT_BOOK_REQUEST = 2;

    Book mCurrentBook;

    // Initialising all necessary Views
    private TextView mCurrentTitle;
    private TextView mCurrentAuthor;
    private TextView mCurrentISBN;
    private TextView mCurrentPublisher;
    private TextView mCurrentEdition;
    private TextView mCurrentYearOfPublication;
    private TextView mCurrentGenre;
    private TextView mCurrentDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);

        // Setting all initialised views to xml views
        mCurrentTitle = (TextView) findViewById(R.id.textView);
        mCurrentAuthor = (TextView) findViewById(R.id.textView2);
        mCurrentISBN = (TextView) findViewById(R.id.textView7);
        mCurrentPublisher = (TextView) findViewById(R.id.textView16);
        mCurrentEdition = (TextView) findViewById(R.id.textView10);
        mCurrentYearOfPublication = (TextView) findViewById(R.id.textView13);
        mCurrentGenre = (TextView) findViewById(R.id.textView14);
        mCurrentDescription = (TextView) findViewById(R.id.textView15);

        // Getting book from another activity
        Intent intent = getIntent();
        mCurrentBook = intent.getParcelableExtra("Book");

        // Setting all text within views to their appropriate information
        mCurrentTitle.setText(mCurrentBook.getTitle());
        mCurrentAuthor.setText(mCurrentBook.getAuthor());
        mCurrentISBN.setText(mCurrentBook.getISBN());
        mCurrentPublisher.setText(mCurrentBook.getPublisher());
        mCurrentEdition.setText(mCurrentBook.getEdition());
        mCurrentYearOfPublication.setText(mCurrentBook.getYearOfPublication());
        mCurrentGenre.setText(mCurrentBook.getGenre());
        mCurrentDescription.setText(mCurrentBook.getDescription());

        // Making the toolbar display name of the book
        setTitle(mCurrentBook.getTitle().toString());
    }

    //Create our menu for editing (if completed
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit_book, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {switch(item.getItemId()){
        case R.id.action_edit:
            Intent i = new Intent(this, AddBook.class);
            startActivityForResult(i, EDIT_BOOK_REQUEST);
            return true;
        default:
            return super.onOptionsItemSelected(item);
    } }
}
