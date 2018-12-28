package com.itc.thaithang.mvpandroid.presenter.main;

import com.itc.thaithang.mvpandroid.model.entity.Student;

import java.util.List;


public interface LoadDataListener {
    void onLoadDataSuccessed(List<Student> listStudent);
    void onLoadDataFailured(String message);
}