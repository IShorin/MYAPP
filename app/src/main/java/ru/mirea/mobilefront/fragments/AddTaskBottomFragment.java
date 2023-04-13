package ru.mirea.mobilefront.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import ru.mirea.mobilefront.R;
import ru.mirea.mobilefront.model.PriorityName;
import ru.mirea.mobilefront.model.Task;
import ru.mirea.mobilefront.service.TaskService;

public class AddTaskBottomFragment extends BottomSheetDialogFragment {
    private static int position;
    public static AddTaskBottomFragment newInstance(int n){
        position = n;
        return new AddTaskBottomFragment();
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
        Button button = view.findViewById(R.id.add_task_button);
        EditText name = view.findViewById(R.id.task_name_text);
        EditText prior = view.findViewById(R.id.task_priority_text);
        EditText dl = view.findViewById(R.id.task_deadline_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = new Task();
                task.setName(name.getText().toString());
                task.setPriorityName(PriorityName.valueOf(prior.getText().toString()));
                task.setDescription(dl.getText().toString());
                List<Task> taskList = TaskService.getTaskMutableLiveData().getValue();
                assert taskList != null;
                taskList.add(task);
                TaskService.getTaskMutableLiveData().postValue(taskList);
                dismiss();
            }
        });
    }
}
