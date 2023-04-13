package ru.mirea.mobilefront.service;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import lombok.Getter;
import ru.mirea.mobilefront.model.Task;

public class TaskService {
    @Getter
    public static final MutableLiveData<List<Task>> taskMutableLiveData = new MutableLiveData<>();

}
