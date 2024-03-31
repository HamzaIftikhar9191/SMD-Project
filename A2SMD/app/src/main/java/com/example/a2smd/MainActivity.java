package com.example.a2smd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Gson gson = new Gson();
    private Adapter adapter;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("com.example.a2smd.preferences", MODE_PRIVATE);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve the items from SharedPreferences
        items = getItemsFromSharedPreferences();
        adapter = new Adapter(getApplicationContext(), items);
        recyclerView.setAdapter(adapter);

        Button addButton = findViewById(R.id.btn_add);
        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivityForResult(intent, 1); // Use startActivityForResult to get result from RegisterActivity
        });

        Button deleteButton = findViewById(R.id.btn_delete);
        deleteButton.setOnClickListener(view -> {
            // Clear all data from SharedPreferences
            sharedPreferences.edit().clear().apply();

            // Clear the items list and notify the adapter of the data change
            items.clear();
            adapter.notifyDataSetChanged();
        });

        Button searchButton = findViewById(R.id.btn_search);
        EditText searchEditText = findViewById(R.id.txt_search);
        searchButton.setOnClickListener(view -> {
            String query = searchEditText.getText().toString().trim();
            if (!query.isEmpty()) {
                filterItems(query);
            }
        });

        Button refreshButton = findViewById(R.id.btn_refresh);
        refreshButton.setOnClickListener(view -> {
            // Reload the RecyclerView with the updated data
            items.clear();
            items.addAll(getItemsFromSharedPreferences());
            adapter.notifyDataSetChanged();
        });
    }

    private void filterItems(String query) {
        List<Item> filteredItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(query.toLowerCase()) ||
                    item.getLocation().toLowerCase().contains(query.toLowerCase()) ||
                    item.getDescription().toLowerCase().contains(query.toLowerCase()) ||
                    item.getPhone().toLowerCase().contains(query.toLowerCase()) ||
                    item.getRating().toLowerCase().contains(query.toLowerCase())) {
                filteredItems.add(item);
            }
        }
        // Clear the existing items and display the filtered items
        items.clear();
        items.addAll(filteredItems);
        adapter.notifyDataSetChanged();
    }

    private void saveItemsToSharedPreferences(List<Item> items) {
        // Convert the list of items to JSON
        String json = gson.toJson(items);
        sharedPreferences.edit().putString("items", json).apply();
    }

    private List<Item> getItemsFromSharedPreferences() {
        // Retrieve the JSON string of items
        String json = sharedPreferences.getString("items", null);
        Type type = new TypeToken<ArrayList<Item>>() {}.getType();
        List<Item> items = gson.fromJson(json, type);

        // If no items were stored, initialize a new list
        if (items == null) {
            items = new ArrayList<>();
        }

        return items;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the list from SharedPreferences in case it was updated in another activity
        items.clear();
        items.addAll(getItemsFromSharedPreferences());
        adapter.notifyDataSetChanged();
    }

    // This method is called when returning from RegisterActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) { // Check if the request code matches the one used in startActivityForResult
            if (resultCode == RESULT_OK) { // Check if the result is OK
                // Retrieve the newly added item from SharedPreferences
                String name = sharedPreferences.getString("name", "");
                String location = sharedPreferences.getString("location", "");
                String phone = sharedPreferences.getString("phone", "");
                String description = sharedPreferences.getString("description", "");
                String rating = sharedPreferences.getString("rating", "");

                // Create a new Item object with the retrieved data
                Item newItem = new Item(name, location, description, phone ,rating);

                // Add the new item to the existing list of items
                items.add(newItem);

                // Save the updated list of items to SharedPreferences
                saveItemsToSharedPreferences(items);

                // Refresh the list of items from SharedPreferences
                adapter.notifyDataSetChanged();
            }
        }
    }
}
