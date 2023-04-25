package ru.mirea.mobilefront.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.mirea.mobilefront.R;
import ru.mirea.mobilefront.fragments.SearchFragment;
import ru.mirea.mobilefront.model.Employee;

public class UserViewAdapter extends RecyclerView.Adapter<UserViewAdapter.BookViewHolder> {

    Context context;
    List<Employee> employeeList;

    public UserViewAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItems = LayoutInflater.from(context).inflate(R.layout.book_panel, parent, false);
        return new BookViewHolder(categoryItems);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.employeeName.setText(employee.getName());
        holder.employeeSureName.setText(employee.getSurename());
        holder.employeeFatherName.setText(employee.getFathername());
        holder.email.setText(employee.getEmail());
        holder.coefEff.setText(employee.getCoefEff());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Обработчик говна
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static final class BookViewHolder extends RecyclerView.ViewHolder{

        TextView employeeName;
        TextView employeeSureName;
        TextView employeeFatherName;
        TextView email;
        TextView coefEff;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            employeeName = itemView.findViewById(R.id.name_book_panel_id);
            employeeSureName = itemView.findViewById(R.id.surename_book_panel_id);
            employeeFatherName = itemView.findViewById(R.id.father_book_panel_id);
            email = itemView.findViewById(R.id.phone_text);
            coefEff = itemView.findViewById(R.id.phone_text5);
        }
    }

}
