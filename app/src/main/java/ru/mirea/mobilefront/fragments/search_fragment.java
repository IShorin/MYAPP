package ru.mirea.mobilefront.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.mirea.mobilefront.R;
import ru.mirea.mobilefront.design.BookSearchViewAdapter;
import ru.mirea.mobilefront.design.BookViewAdapter;
import ru.mirea.mobilefront.dto.BookSimple;
import ru.mirea.mobilefront.service.BookService;

public class search_fragment extends Fragment {
    RecyclerView searchBooksView;
    MutableLiveData<List<BookSimple>> searchBookList;
    BookService bookService = new BookService();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (ViewGroup)inflater.inflate(R.layout.fragment_search,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Новые книги, заполнение витрины
        searchBooksView = view.findViewById(R.id.book_search_view);
        searchBookList = BookService.getSearchBookList();

        searchBookList.observe(getViewLifecycleOwner(), new Observer<List<BookSimple>>() {
            @Override
            public void onChanged(List<BookSimple> booksFound) {
                //for (BookSimple book: bookSimples) System.out.println(book.toString());
                RecyclerView.LayoutManager layoutManager = new
                        LinearLayoutManager(view.getContext()
                        ,RecyclerView.VERTICAL
                        ,false);
                searchBooksView.setLayoutManager(layoutManager);
                BookSearchViewAdapter adapter = new BookSearchViewAdapter(view.getContext(), booksFound);
                searchBooksView.setAdapter(adapter);
            }
        });

        Button searchButton = (Button) view.findViewById(R.id.book_search_button);
        EditText bookSearch = (EditText) view.findViewById(R.id.book_search_text);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookService.searchForBook(bookSearch.getText().toString());
            }
        });



    }
}