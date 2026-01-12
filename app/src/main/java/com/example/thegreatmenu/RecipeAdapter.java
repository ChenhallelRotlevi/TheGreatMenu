package com.example.thegreatmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> dataSource;
    private LayoutInflater inflater;

    public RecipeAdapter(Context context, ArrayList<String> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.item_recipe, parent, false);
        }

        // קבלת המחרוזת של המתכון
        String recipeFullText = (String) getItem(position);

        // חיבור לרכיבי ה-XML
        TextView titleTextView = rowView.findViewById(R.id.tvRecipeName);
        TextView subtitleTextView = rowView.findViewById(R.id.tvRecipeType);

        // לוגיקה פשוטה לפיצול הטקסט (למשל: "פסטה (חלבי)")
        if (recipeFullText.contains("(")) {
            String[] parts = recipeFullText.split("\\(");
            titleTextView.setText(parts[0].trim());
            subtitleTextView.setText("(" + parts[1]); // מחזיר את החלק השני עם הסוגריים
        } else {
            titleTextView.setText(recipeFullText);
            subtitleTextView.setText("כללי");
        }

        return rowView;
    }
}