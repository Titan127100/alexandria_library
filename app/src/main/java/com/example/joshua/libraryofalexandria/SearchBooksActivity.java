package com.example.joshua.libraryofalexandria;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.joshua.libraryofalexandria.models.Book;
import com.example.joshua.libraryofalexandria.models.DatabaseHelper;

import java.util.ArrayList;

public class SearchBooksActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener, AdapterView.OnItemLongClickListener{

    public static final int ADD_BOOK_REQUEST = 1;
    // Initialising all necessary variables
    private ListView mListView;
    private BookAdapter mAdapter;
    private ArrayList<Book> mBookList;
    private DatabaseHelper mDBHelper;

    public SearchView mSearchView;
    public Book mNewBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_books);

        // Get our database handler
        mDBHelper = new DatabaseHelper(getApplicationContext());

        // Initialize the Book List with the values from our database
        mBookList = new ArrayList<>(mDBHelper.getAllBook().values());

        //initialise the Book List
        mBookList = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.bookListView);

        // Create Adapter and associate it with our BookList
        mAdapter = new BookAdapter(this, mBookList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemLongClickListener(this);
        updateListCount();

        // When tapping on a cell go to the ViewBooksActivity with a book as a parameter
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchBooksActivity.this, ViewBooksActivity.class);
                mNewBook = mBookList.get(i);
                intent.putExtra("Book", mNewBook);
                startActivity(intent);
            }
        });
    }

    //Create our menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_search, menu);

        // Setting up the search into the toolbar
        SearchManager manager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.search);
        searchItem.collapseActionView();
        mSearchView = (SearchView) searchItem.getActionView();
        mSearchView.setSearchableInfo(manager.
                getSearchableInfo(getComponentName()));
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    // Options menu that contains the add book function
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {switch(item.getItemId()){
        case R.id.action_add:
            Intent i = new Intent(this, AddBook.class);
            startActivityForResult(i, ADD_BOOK_REQUEST);
            return true;
        default:
            return super.onOptionsItemSelected(item);
    } }

    // Overridden method to get results from an activity launched from this activity previously
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_BOOK_REQUEST){
            if(resultCode == RESULT_OK){
                boolean canAddBook = true;

                // Get the Book object from the intent and add it to our list
                Book newBook = data.getParcelableExtra("result");
                // Check if person already exists (By name)
                for (Book book : mBookList)
                {
                    if (newBook.getTitle().equals(book.getTitle()))
                    {
                        Toast.makeText(SearchBooksActivity.this,
                                "Book already exists in database",
                                Toast.LENGTH_LONG).show();
                        canAddBook = false;
                    }
                }
                // If person doesnt exist then add it
                if (canAddBook)
                {
                    mDBHelper.addBook(newBook);
                    mBookList.add(newBook);
                    refreshListView();
                    Toast.makeText(SearchBooksActivity.this,
                            "Book has been added!",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void refreshListView() {
        mAdapter.notifyDataSetChanged();
        updateListCount();
    }

    // Function to initialize default values for the person list

    private void updateListCount(){
        // Get total size of person list & set title
        int numBook = mBookList.size();
        setTitle("All Book: " + numBook);
    }

    // When you hold down on a cell
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int
            position, long l) {
        // Build a dialog to delete item
        AlertDialog.Builder builder =
                new AlertDialog.Builder(view.getContext());
        builder.setTitle("Remove Book?");
        builder.setMessage("Are you sure you wish to remove this book?");
        builder.setPositiveButton("Delete",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Remove book from list and database
                        Book book = mBookList.remove(position);
                        mDBHelper.removeBook(book);
                        // Update ListView
                        refreshListView();
                        Toast.makeText(getBaseContext(),
                                "Book has been deleted",
                                Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mAdapter.getFilter().filter(newText);
        return true;
    }
}
