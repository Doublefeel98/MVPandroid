package com.itc.thaithang.mvpandroid.view.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.itc.thaithang.mvpandroid.R;
import com.itc.thaithang.mvpandroid.model.entity.Student;
import com.itc.thaithang.mvpandroid.presenter.main.MainPresenter;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainView {

    private ListView listView;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build();

    //Presenter
    private MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();

        FirebaseApp.initializeApp(this);
        firestore.setFirestoreSettings(settings);
        listView = findViewById(R.id.listView);
    }

    public void loadData(View view) {
        mainPresenter.loadData();
    }

    private void initPresenter() {
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public void displayData(List<Student> listStudent) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listStudent);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void showLoadDataFailured(String message) {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }
}