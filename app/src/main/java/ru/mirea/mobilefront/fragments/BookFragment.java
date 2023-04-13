package ru.mirea.mobilefront.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.mirea.mobilefront.R;
import ru.mirea.mobilefront.adapter.TaskViewAdapter;
import ru.mirea.mobilefront.model.Task;
import ru.mirea.mobilefront.service.TaskService;

public class BookFragment extends Fragment {
    RecyclerView newTasks;
    RecyclerView doneTasks;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater
                .inflate(R.layout.fragment_book,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("Мейн страница создана", "debug");

        Button button = view.findViewById(R.id.edit_task_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the AddTaskBottomFragment
                AddTaskBottomFragment fragment = AddTaskBottomFragment.newInstance(0);

                // Get the fragment manager
                FragmentManager fragmentManager = getParentFragmentManager();

                // Show the fragment using a tag
                fragment.show(fragmentManager, "AddTaskBottomFragment");
            }
        });

        newTasks = view.findViewById(R.id.new_tasks_view);


        TaskService.getTaskMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                TaskViewAdapter adapter = new TaskViewAdapter(getContext(), tasks);
                newTasks.setAdapter(adapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                newTasks.setLayoutManager(layoutManager);
            }
        });
    }
    private boolean isFirstFragmentVisible = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !isFirstFragmentVisible) {
            isFirstFragmentVisible = true;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Выполняем здесь инициализацию фрагмента
                    // Например, загрузка данных, настройка RecyclerView и т.д.
                }
            }, 200); // Установите задержку в миллисекундах здесь
        }
    }




}