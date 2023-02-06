package ru.mirea.mobilefront;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.viewpager2.widget.ViewPager2;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import ru.mirea.mobilefront.adapter.BookTypeAdapter;
import ru.mirea.mobilefront.adapter.BottomSheetManager;
import ru.mirea.mobilefront.databinding.FragmentFullBookBinding;
import ru.mirea.mobilefront.adapter.MyFragmentAdapter;
import ru.mirea.mobilefront.dto.BookFull;
import ru.mirea.mobilefront.fragments.BasketFragment;
import ru.mirea.mobilefront.service.BasketService;
import ru.mirea.mobilefront.service.BookService;

public class MenuActivity extends FragmentActivity {

      private FragmentFullBookBinding fullBookBinding;
      private static BottomSheetBehavior<View> staticBottomSheetBehavior;
      private TabLayout tabLayout;
      private ViewPager2 viewPager2;
      private MyFragmentAdapter adapter;
      private AppCompatButton minus_btn;
      private AppCompatButton plus_btn;
      private EditText num_btn;
      private Button closeFullBookButton;
      private BottomSheetManager bottomSheetManager;
//    private static final int NUM_PAGES=5;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BasketService.getBasketBookList().postValue(new HashMap<BookFull, Integer>());
        super.onCreate(savedInstanceState);
        fullBookBinding = FragmentFullBookBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_menu);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager2=findViewById(R.id.full_book_frame);
        viewPager2.setUserInputEnabled(false); //отключение возможности скролить пальцем, оставляя только меню

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter=new MyFragmentAdapter(fragmentManager,getLifecycle());
        viewPager2.setAdapter(adapter);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition(), true); //false - отключает прокрутку (анимация)
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


    //Меню полной книги
        FrameLayout layout = findViewById(R.id.bottom_sheet);
        layout.bringToFront();
        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(layout);

        if (staticBottomSheetBehavior==null)
            staticBottomSheetBehavior = bottomSheetBehavior;

        bottomSheetManager = new BottomSheetManager(this, bottomSheetBehavior, layout);
        //bottomSheetLogic(this, bottomSheetBehavior, layout);

    }


    public static BottomSheetBehavior<View> getStaticBottomSheetBehavior(){
        return staticBottomSheetBehavior;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View rootView = findViewById(android.R.id.content);
        int appHeight = rootView.getHeight();
        //int appHeight = getWindow().getDecorView().getHeight();
        ViewGroup.LayoutParams layoutParams = viewPager2.getLayoutParams();
        layoutParams.height = appHeight-tabLayout.getHeight(); // set height to fill screen
    }


    @Override
    public void onBackPressed() {
        bottomSheetManager.onBackPressed(viewPager2);
    }
}