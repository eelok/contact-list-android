package com.eelok.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.eelok.contactmanager.data.DatabaseHandler;
import com.eelok.contactmanager.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        contactArrayList = new ArrayList<>();

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        db.deleteAll();
        db.addContact(new Contact("James", "91645"));
        db.addContact(new Contact("Greg", "098765"));
        db.addContact(new Contact("Helena", "40678765"));
        db.addContact(new Contact("Carimo", "768345"));
        db.addContact(new Contact("Greg", "91645"));

        db.addContact(new Contact("Silo", "3445"));
        db.addContact(new Contact("Santos", "6665"));
        db.addContact(new Contact("Lotios", "5344"));
        db.addContact(new Contact("Karate", "96534"));
        db.addContact(new Contact("Guerra", "158285"));
        db.addContact(new Contact("Gema", "78130"));

        List<Contact> allContacts = db.getAllContacts();

        allContacts.forEach(contact -> {
                    Log.d("List:", ">>> " + contact.getId() + " " + contact.getName() + " " + contact.getPhoneNumber());
                    contactArrayList.add(contact.getName());
                }
        );

        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                contactArrayList
        );

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("AD", "onItemClick " + contactArrayList.get(i));
            }
        });

    }

}