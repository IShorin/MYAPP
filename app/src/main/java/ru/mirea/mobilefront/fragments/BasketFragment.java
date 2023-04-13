package ru.mirea.mobilefront.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.Map;

import ru.mirea.mobilefront.R;
import ru.mirea.mobilefront.adapter.BookVerticalBasketViewAdapter;
import ru.mirea.mobilefront.dto.BookFull;
import ru.mirea.mobilefront.service.BasketService;

public class BasketFragment extends Fragment {
    private MaterialButton paymentButton;
    private RecyclerView basketView;
    private ImageView backgroundTint;
    private TextView basketFinalPrice;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (ViewGroup)inflater.inflate(R.layout.fragment_basket,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }


}
