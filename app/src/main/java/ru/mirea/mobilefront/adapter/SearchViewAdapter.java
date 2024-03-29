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
import ru.mirea.mobilefront.model.Task;

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.BookViewHolder> {

    Context context;
    List<Task> taskList;

    public SearchViewAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItems = LayoutInflater.from(context).inflate(R.layout.book_panel, parent, false);
        return new BookViewHolder(categoryItems);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Task task = taskList.get(position);

        holder.taskName.setText(task.getName());
        holder.taskPriority.setText(task.getPriorityName().toString());





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Обработчик говна
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static final class BookViewHolder extends RecyclerView.ViewHolder{

        TextView taskName;
        TextView taskPriority;





        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            taskName = itemView.findViewById(R.id.book_card_name);
            taskPriority = itemView.findViewById(R.id.book_card_name2);




        }
    }

}
