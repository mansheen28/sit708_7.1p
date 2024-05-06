package com.example.lostfound;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ViewItem extends AppCompatActivity {

    TextView name, description, phone, location, date, lostFound;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_item);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        name = findViewById(R.id.textViewName);
        description = findViewById(R.id.textViewDescription);
        phone = findViewById(R.id.textViewPhone);
        location = findViewById(R.id.textViewLocation);
        date = findViewById(R.id.textViewDate);
        lostFound = findViewById(R.id.textViewLostFound);

        Intent i = getIntent();
        String[] arr = i.getStringArrayExtra("data");
        name.setText("Name: " + arr[1]);
        description.setText("Description: " + arr[2]);
        phone.setText("Phone: " + arr[3]);
        location.setText("Location: " + arr[4]);
        date.setText("Date: " + arr[5]);
        lostFound.setText("Type: " + arr[6].toUpperCase());

        findViewById(R.id.btn_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.delete("my_table", "id = ?", new String[]{String.valueOf(arr[0])});
                Toast.makeText(ViewItem.this, "Item Deleted Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}