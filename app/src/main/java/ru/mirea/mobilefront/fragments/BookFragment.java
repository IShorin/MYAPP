package ru.mirea.mobilefront.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.mirea.mobilefront.R;
import ru.mirea.mobilefront.adapter.BookViewAdapter;
import ru.mirea.mobilefront.dto.BookSimple;
import ru.mirea.mobilefront.service.BookService;

public class BookFragment extends Fragment {
    BookViewAdapter bookViewAdapter;
    RecyclerView newBooksView;
    RecyclerView topBooksView;
    BookService bookService = new BookService();
    MutableLiveData<List<BookSimple>> newBooksList;
    MutableLiveData<List<BookSimple>> topBooksList;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater
                .inflate(R.layout.fragment_book,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("Мейн страница создана", "debug");
    }




}