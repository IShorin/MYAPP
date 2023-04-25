package ru.mirea.mobilefront.service;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.Getter;
import ru.mirea.mobilefront.model.Employee;

public class EmployeeService {
    @Getter
    public static final MutableLiveData<List<Employee>> employeeMutableLiveData = new MutableLiveData<>();
    public static void podgon (){
        Employee employeeMas0 = new Employee("Иван","Иванов","Иванович","Команда Альфа","К.Э. - 50%");
        Employee employeeMas1 = new Employee("Андрей","Иванов","Иванович","Команда Альфа","К.Э. - 65%");
        Employee employeeMas2 = new Employee("Боб","Бобов","Бобович","Команда Бэта","К.Э. - 80%");
        Employee employeeMas3 = new Employee("Заруб","Зарубов","Зарубович","Команда Бэта","К.Э. - 82%");
        Employee employeeMas4 = new Employee("Михаил","Гонько","Алексеевич","Команда Бэта","К.Э. - 85%");
        ArrayList<Employee> list = new ArrayList<>(List.of(employeeMas0,employeeMas1,employeeMas2,employeeMas3,employeeMas4));
        employeeMutableLiveData.postValue(list);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Employee> filterEmployeesByAttribute(String attribute) {
        String lowerCaseAttribute = attribute.toLowerCase();
        return Objects.requireNonNull(employeeMutableLiveData.getValue()).stream()
                .filter(employee ->
                        employee.getName().toLowerCase().contains(lowerCaseAttribute) ||
                                employee.getSurename().toLowerCase().contains(lowerCaseAttribute) ||
                                employee.getFathername().toLowerCase().contains(lowerCaseAttribute) ||
                                employee.getEmail().toLowerCase().contains(lowerCaseAttribute))
                .collect(Collectors.toList());
    }

}
