package com.example.memod;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        listView = findViewById(R.id.ListView);

        // Create data
        ArrayList<Person> arrayList = new ArrayList<>();

        arrayList.add(new Person(R.drawable.item1, "J", "A"));
        arrayList.add(new Person(R.drawable.item1, "M", "B."));
        arrayList.add(new Person(R.drawable.item1, "Brie", "C"));
        arrayList.add(new Person(R.drawable.item1, "Chris", ";D...."));
        arrayList.add(new Person(R.drawable.item1, "Jihee", "A"));
        arrayList.add(new Person(R.drawable.item1, "MSH", "B"));
        arrayList.add(new Person(R.drawable.item1, "Brie", "C"));
        arrayList.add(new Person(R.drawable.item1, "Chris", "D"));

        // Adapter
        PersonAdapter personAdapter = new PersonAdapter(this, R.layout.list_row, arrayList);

        listView.setAdapter(personAdapter);
    }
}