package ru.mirea.mobilefront;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.mirea.mobilefront.adapter.MyFragmentAdapter;
import ru.mirea.mobilefront.dto.BookFull;
import ru.mirea.mobilefront.model.PriorityName;
import ru.mirea.mobilefront.model.Task;
import ru.mirea.mobilefront.service.TaskService;

public class MenuActivity extends FragmentActivity {

      private static BottomSheetBehavior<View> staticBottomSheetBehavior;
      private TabLayout tabLayout;
      private ViewPager2 viewPager2;
      private MyFragmentAdapter adapter;
      private AppCompatButton minus_btn;
      private AppCompatButton plus_btn;
      private EditText num_btn;
      private Button closeFullBookButton;
//    private static final int NUM_PAGES=5;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        {
            List<Task> taskList = new ArrayList<>();
            Task task1 = new Task();
            task1.setDescription("12.05.2023");
            task1.setName("Сделать наброски");
            task1.setPriorityName(PriorityName.HIGH);
            taskList.add(task1);
            TaskService.getTaskMutableLiveData().postValue(taskList);
        }

        super.onCreate(savedInstanceState);
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
        int currentItem = viewPager2.getCurrentItem();
        if (currentItem == 0) {
            // If the user is currently on the first page of the ViewPager2, you can finish the activity.
            super.onBackPressed();
        } else {
            // If the user is not on the first page, you can navigate back to the first page.
            viewPager2.setCurrentItem(0, true);
        }
    }
}