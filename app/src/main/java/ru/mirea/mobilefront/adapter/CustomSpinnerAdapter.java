package ru.mirea.mobilefront.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ru.mirea.mobilefront.R;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] items;
    private final int[] images;

    public CustomSpinnerAdapter(Context context, String[] items, int[] images) {
        super(context, R.layout.spinner_item_layout, items);
        this.context = context;
        this.items = items;
        this.images = images;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_item_layout, parent, false);

        TextView textView = row.findViewById(R.id.textView);
        ImageView imageView = row.findViewById(R.id.imageView);

        textView.setText(items[position]);
        imageView.setImageResource(images[position]);

        return row;
    }
}
