package com.example.shoppingapp.base;

import com.example.shoppingapp.adapter.ClassificationAdapter;

public interface BaseAdapterListener {


   void onCategoryClick(int id, String title, ClassificationAdapter.Type type);


}
