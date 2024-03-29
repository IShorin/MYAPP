package ru.mirea.mobilefront.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    String name;
    String deadLine;
    String startLine;
    String employee;
    PriorityName priorityName;

}
