package com.example.joshua.libraryofalexandria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.joshua.libraryofalexandria.models.Book;

/**
 * Created by Joshua on 4/19/2018.
 */

public class MainMenuActivity extends AppCompatActivity{

    ImageButton mBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ui);

        //Instantiate UI elements
        mBookList = (ImageButton) findViewById(R.id.bookListButton);
        // Setting a listener for when a user clicks the mBookList button
        mBookList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, SearchBooksActivity.class);
                startActivity(intent);
            }
        });


    }


}

