package com.eelok.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.eelok.contactmanager.data.DatabaseHandler;
import com.eelok.contactmanager.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        Contact jeremy = new Contact();
        jeremy.setName("Jeremy");
        jeremy.setPhoneNumber("985747374");

        Contact olga = new Contact();
        olga.setName("Olga");
        olga.setPhoneNumber("74747747374");

//        db.addContact(jeremy);
//        db.addContact(olga);

//        Contact cont = db.getContact(2);
//        Log.d("contact:", "" + cont.getName() + " " +  cont.getPhoneNumber());


//        cont.setPhoneNumber("555555555");
//        cont.setName("NewName");
//        int updatedID = db.updateContact(cont);
//        Log.d("Updated", " " + updatedID);
//        Log.d("ID Olga: ", " " + olga.getId() + " "+ olga.getPhoneNumber());
//        db.deleteContact(olga);
//        Contact forDelete = db.getContact(3);
//        Log.d("Get", " " + forDelete.getId() + " " + forDelete.getName());
//        db.deleteContact(forDelete);
        List<Contact> allContacts = db.getAllContacts();
        allContacts.forEach(contact ->
                Log.d("List:", ">>> " + contact.getId() + " " + contact.getName() + " " + contact.getPhoneNumber()));

        int numOfRecords = db.getCount();
        Log.d("Num of R: ", ">> " + numOfRecords);

    }
}