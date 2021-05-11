package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private ListView listView;
    private CustomDiaDiemAdapter adt;
    private ArrayList nameList, idList;
    private AppDatabase db;
    private Button btnSave, btnXoa;
    private TextView tvDiaDiem;
    DiaDiemDao diaDiemDao;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvDiaDiem = findViewById(R.id.tvDiaDiem);
        btnSave = findViewById(R.id.btnSave);
        btnXoa = findViewById(R.id.btnXoa);
        listView = findViewById(R.id.lvDiaDiem);

        nameList = new ArrayList();
        idList = new ArrayList();



//        listView = new ListView(this);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "diadiemDB.appdatabase")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        diaDiemDao = db.diaDiemDao();
        getNameList();
        adt = new CustomDiaDiemAdapter(MainActivity2.this, R.layout.layout_main2, nameList);
        listView.setAdapter(adt);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaDiemDao.add(new DiaDiem(tvDiaDiem.getText().toString()));
                getNameList();
                adt.notifyDataSetChanged();
                Toast.makeText(MainActivity2.this, "hihi", Toast.LENGTH_SHORT).show();
            }
        });
        if(btnXoa != null)
            btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    diaDiemDao.delete(diaDiemDao.getDiaDiemById((Integer) idList.get(index)));
                    getNameList();
                    adt.notifyDataSetChanged();
                    Toast.makeText(MainActivity2.this,"hihi", Toast.LENGTH_SHORT).show();

                }
            });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
//                btnXoa = findViewById(R.id.btnXoa);
//                if(btnXoa != null) {
//                    btnXoa.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            diaDiemDao.delete(diaDiemDao.getDiaDiemById((Integer) idList.get(index)));
//                            getNameList();
//                            adt.notifyDataSetChanged();
//                            Toast.makeText(MainActivity2.this,"hihi", Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
//                }
                Toast.makeText(MainActivity2.this,"kkkk", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private ArrayList getNameList(){

        nameList.clear();
        idList.clear();
        for (Iterator iterator = diaDiemDao.getAll().iterator(); iterator.hasNext(); ) {
            DiaDiem diaDiem = (DiaDiem)  iterator.next();
            nameList.add(diaDiem.getName());
            idList.add(diaDiem.getId());

        }
        return nameList;
    }
}