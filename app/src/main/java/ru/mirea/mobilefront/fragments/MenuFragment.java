package ru.mirea.mobilefront.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import ru.mirea.mobilefront.R;
import ru.mirea.mobilefront.adapter.BookVerticalViewAdapter;
import ru.mirea.mobilefront.adapter.OrderViewAdapter;
import ru.mirea.mobilefront.dto.OrderDto;
import ru.mirea.mobilefront.service.OrderService;

public class MenuFragment extends Fragment {

    RecyclerView orderView;
    TextView finalText;
    @Getter
    static FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (ViewGroup)inflater.inflate(R.layout.fragment_order,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }
}