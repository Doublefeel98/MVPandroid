package com.itc.thaithang.mvpandroid.model.main;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.itc.thaithang.mvpandroid.model.entity.Student;
import com.itc.thaithang.mvpandroid.presenter.main.LoadDataListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nullable;

public class StudentInterator {

    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String TAG = StudentInterator.class.getName();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    private LoadDataListener listener;

    public StudentInterator(LoadDataListener listener) {
        this.listener = listener;
    }

    //Xử lý tạo dữ liệu
    public void loadData(){
        final List<Student> listStudent = new ArrayList<>();

        DocumentReference mDocRef = db.collection("DemoMVP").document("Studen");

        mDocRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(e != null || !documentSnapshot.exists() || documentSnapshot == null){
                    listener.onLoadDataFailured("Error getting documents.");
                }
                else{
                    for (Map.Entry<String, Object> entry : Objects.requireNonNull(documentSnapshot.getData()).entrySet()) {

                        Map<String, String> nestedData = (Map<String, String>) entry.getValue();
                        String id = entry.getKey();
                        String name = nestedData.get(NAME_KEY);
                        Student student = new Student(id, name);
                        listStudent.add(student);
                    }
                    listener.onLoadDataSuccessed(listStudent);
                }
            }
        });

    }
}