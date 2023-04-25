package ru.mirea.mobilefront.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import ru.mirea.mobilefront.R;
import ru.mirea.mobilefront.adapter.CustomSpinnerAdapter;
import ru.mirea.mobilefront.model.PriorityName;
import ru.mirea.mobilefront.model.Task;
import ru.mirea.mobilefront.service.TaskService;

public class AddTaskBottomFragment extends BottomSheetDialogFragment {
    private static int position;
    private Button button;
    private TextView textView;
    private Button secondButton;
    private TextView secondTextView;
    private TextView task_priority_text;


    public static AddTaskBottomFragment newInstance(int n){
        position = n;
        return new AddTaskBottomFragment();
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.add_task_bottom_panel,container,false);

        button = view.findViewById(R.id.startline_btn);
        textView = view.findViewById(R.id.startline_date);

        secondTextView= view.findViewById(R.id.deadline_date);
        secondButton = view.findViewById(R.id.deadline_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a Calendar object and set it to the current date
                Calendar calendar = Calendar.getInstance();

                // Create a DatePickerDialog and set its OnDateSetListener
                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Create a Calendar object and set the selected date
                                Calendar selectedDate = Calendar.getInstance();
                                selectedDate.set(year, month, dayOfMonth);

                                // Format the date as a string
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                                String dateString = dateFormat.format(selectedDate.getTime());

                                // Set the text of the TextView to the formatted date string
                                textView.setText(dateString);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                // Show the DatePickerDialog
                datePickerDialog.show();
            }
        });
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a Calendar object and set it to the current date
                Calendar calendar = Calendar.getInstance();

                // Create a DatePickerDialog and set its OnDateSetListener
                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Create a Calendar object and set the selected date
                                Calendar selectedDate = Calendar.getInstance();
                                selectedDate.set(year, month, dayOfMonth);

                                // Format the date as a string
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                                String dateString = dateFormat.format(selectedDate.getTime());

                                // Set the text of the second TextView to the formatted date string
                                secondTextView.setText(dateString);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                // Show the DatePickerDialog
                datePickerDialog.show();
            }
        });

//Тут нейронка 2
        task_priority_text = view.findViewById(R.id.task_priority_text);
        Spinner spinner_priority = view.findViewById(R.id.my_spinner);
        String[] data = {"LOW", "MEDIUM", "HIGH"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_priority.setAdapter(adapter);
        spinner_priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item from the Spinner
                String selectedItem = parent.getItemAtPosition(position).toString();

                // Update the TextView with the selected item
                task_priority_text.setText(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }

        });
        String[] items = {"НЕ НАЗНАЧЕН","БОБ БОБОВИЧ БОБОВ", "ИВАН ИВАНОВИЧ ИВАНОВ"};
        int[] images = {R.drawable.person_24, R.drawable.person_24, R.drawable.person_24};
        Spinner spinner_person = view.findViewById(R.id.my_spinner2);
        CustomSpinnerAdapter adapter_custom = new CustomSpinnerAdapter(getContext(), items, images);
        adapter_custom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        TextView textPerson = view.findViewById(R.id.employee_assign_text);
        spinner_person.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный элемент из Spinner
                String selectedItem = (String) parent.getItemAtPosition(position);

                // Устанавливаем выбранный элемент в TextView
                textPerson.setText(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ничего не делаем, если ничего не выбрано
            }
        });

        spinner_person.setAdapter(adapter_custom);


        return view;
    }




    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button button = view.findViewById(R.id.add_task_button);
        EditText name = view.findViewById(R.id.task_name_text);
        TextView prior = view.findViewById(R.id.task_priority_text);


        TextView deadLineText = view.findViewById(R.id.deadline_date);
        TextView startLineText = view.findViewById(R.id.startline_date);
        TextView employeeText = view.findViewById(R.id.employee_assign_text);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = new Task();

                task.setName(name.getText().toString());
                task.setPriorityName(PriorityName.valueOf(prior.getText().toString()));

                task.setDeadLine(deadLineText.getText().toString());
                task.setStartLine(startLineText.getText().toString());
                task.setEmployee(employeeText.getText().toString());

                List<Task> taskList = TaskService.getTaskMutableLiveData().getValue();
                assert taskList != null;
                taskList.add(task);
                TaskService.getTaskMutableLiveData().postValue(taskList);
                dismiss();
            }
        });
    }
}

