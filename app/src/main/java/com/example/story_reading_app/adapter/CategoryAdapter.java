package com.example.story_reading_app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.story_reading_app.R;
import com.example.lib.model.CategoryModel;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<CategoryModel> {
    Activity context;
    int resource;
    private ArrayList<Integer> categoryIds = new ArrayList<Integer>();

    public CategoryAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = (Activity) context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View view = layoutInflater.inflate(this.resource, null);

        CategoryModel model = getItem(position);

        if(this.resource == R.layout.item_catagory){
            //khai bao
            TextView txtNameCategory = view.findViewById(R.id.txtNameCategory);

            //gan gia tri
            txtNameCategory.setText(model.getName());

        }else if(this.resource == R.layout.category_layout){
            //khai bao
            TextView txtName = view.findViewById(R.id.txtNameCategory);
            TextView txtDescribes = view.findViewById(R.id.txtDescribes);

            //gan gia tri
            txtName.setText(model.getName());
            txtDescribes.setText(model.getDescribes());

        } else if(this.resource == R.layout.admin_item_list_category){
            TextView name = view.findViewById(R.id.txtAdminCateNameItem);
            TextView describes = view.findViewById(R.id.txtAdminCateDescribesItem);
            CheckBox cbCateDeleteItem = view.findViewById(R.id.cbCateDeleteItem);

            name.setText(model.getName());
            describes.setText(model.getDescribes());

            //check box delete
            cbCateDeleteItem.setTag(position);
            cbCateDeleteItem.setChecked(categoryIds.contains(model.getId()));
            cbCateDeleteItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (!compoundButton.isChecked()) {
                        categoryIds.remove(model.getId());
                    } else if (compoundButton.isChecked()) {
                        if (!categoryIds.contains(model.getId())) {
                            categoryIds.add(model.getId());
                        }
                    }
                }
            });
        }
        return view;
    }

    // get category id choose
    public ArrayList<Integer> getSelectedChecckedCategory() {
        return categoryIds;
    }

    public void clearSelectedCheckedCategory() {
        categoryIds.clear();
    }
}
