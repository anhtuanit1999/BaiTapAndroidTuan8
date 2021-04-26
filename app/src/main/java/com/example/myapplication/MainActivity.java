package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnDelete;
    EditText txtName;
    DataUser dataUser;
    ListView lv;
    ArrayAdapter adapter;
    ArrayList nameList, idList;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataUser = new DataUser(this, "userdb.sqlite", null, 1);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        txtName = findViewById(R.id.txtName);
        lv = findViewById(R.id.listV);

        nameList = new ArrayList();
        idList = new ArrayList();

        getNameList();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nameList);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser.addUser(new User(txtName.getText().toString()));
                getNameList();
                adapter.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataUser.deleteUser((int)idList.get(index));
                getNameList();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,
                        "Succesful", Toast.LENGTH_SHORT).show();

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }
        });

    }

    private ArrayList getNameList(){

        nameList.clear();
        idList.clear();
        for (Iterator iterator = dataUser.getUsers().iterator(); iterator.hasNext(); ) {
            User user = (User)  iterator.next();
            nameList.add(user.getName());
            idList.add(user.getId());

        }
        return nameList;
    }
}