package ru.mirea.mobilefront.fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Map;

import ru.mirea.mobilefront.R;
import ru.mirea.mobilefront.service.OrderService;

public class OrderDialogFragment extends BottomSheetDialogFragment {
    private static int position;
    public static OrderDialogFragment newInstance(int n){
        position = n;
        return new OrderDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.order_bottom_panel,container,false);
        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }
}
