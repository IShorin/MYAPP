package ru.mirea.mobilefront.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.mobilefront.R;
import ru.mirea.mobilefront.adapter.UserViewAdapter;
import ru.mirea.mobilefront.dto.BookSimple;
import ru.mirea.mobilefront.model.Employee;
import ru.mirea.mobilefront.service.EmployeeService;

public class SearchFragment extends Fragment {
    RecyclerView searchBooksView;
    MutableLiveData<List<BookSimple>> searchBookList;
    EditText searchLine;
    AppCompatButton searchButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return (ViewGroup)inflater.inflate(R.layout.fragment_search,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        searchBooksView = view.findViewById(R.id.book_search_view);
        searchLine = view.findViewById(R.id.book_search_text);
        searchButton = view.findViewById(R.id.book_search_button);

        searchButton.setOnClickListener(v -> {
                    String searchQuery = searchLine.getText().toString();
                    List<Employee> filteredEmployees = EmployeeService.filterEmployeesByAttribute(searchQuery);
                    UserViewAdapter adapter2 = new UserViewAdapter(getContext(), filteredEmployees);
                    searchBooksView.setAdapter(adapter2);
                });

        List<Employee> employeeList = EmployeeService.getEmployeeMutableLiveData().getValue();
        UserViewAdapter adapter = new UserViewAdapter(view.getContext(), employeeList);
        searchBooksView.setAdapter(adapter);
        searchBooksView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

    }

}