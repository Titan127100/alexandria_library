package com.example.joshua.libraryofalexandria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.joshua.libraryofalexandria.models.Book;

import java.util.ArrayList;

/**
 * Created by Joshua on 4/20/2018.
 */

public class BookAdapter extends BaseAdapter implements Filterable{
    private Context mCurrentContext;
    private ArrayList<Book> mFilteredList;
    private BookFilter mFilter;
    private ArrayList<Book> mBookList;

    public BookAdapter(Context con, ArrayList<Book> book){
        mCurrentContext = con;
        mFilteredList = null;
        mBookList = book;
        initList();
    }


    @Override
    public int getCount() {
        return mFilteredList.size();
    }

    @Override
    public Object getItem(int i) {
        return mFilteredList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Check if view already exists. If not inflate it
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) mCurrentContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Create a list item based off layout definition
            view = inflater.inflate(R.layout.list_book_item, null);
        }

        // Assign values to the TextViews using Book Object
        TextView titleView = (TextView) view.findViewById(R.id.nameTitleView);
        TextView authorView = (TextView) view.findViewById(R.id.nameAuthorView);

        titleView.setText(mFilteredList.get(i).getTitle());
        authorView.setText(mFilteredList.get(i).getAuthor());

        return view;
    }

    // Initialising the list
    private void initList(){
        mBookList.add(new Book(0,"The Revenant", "978 0 00 812402 1", "Michael Punke", "The Borough Press", "1", "2015", "adventure", "When expert tracker Glass is viciously mauled by a bear, death seems inevitable."));
        mFilteredList = mBookList;
    }

    @Override
    public Filter getFilter() {
        if(mFilter == null){
            mFilter = new BookFilter();
        }
        return mFilter;
    }

    private class BookFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0){
                ArrayList<Book> tempList = new ArrayList<>();

                for(Book book : mBookList){
                    if(book.getTitle().toLowerCase().contains(constraint.toString().toLowerCase()))
                        tempList.add(book);
                }

                results.count = tempList.size();
                results.values = tempList;
            }
            else{
                results.count = mBookList.size();
                results.values = mBookList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mFilteredList = (ArrayList<Book>) results.values;
            notifyDataSetChanged();
        }
    }
}
