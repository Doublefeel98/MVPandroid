package com.itc.thaithang.mvpandroid.presenter.main;


import com.itc.thaithang.mvpandroid.model.main.StudentInterator;
import com.itc.thaithang.mvpandroid.model.entity.Student;
import com.itc.thaithang.mvpandroid.view.main.MainView;

import java.util.List;


public class MainPresenter implements LoadDataListener {
    private StudentInterator mainInterator;
    private MainView mainView;
    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        mainInterator = new StudentInterator(this);
    }

    public void loadData() {
        mainInterator.loadData();
    }

    @Override
    public void onLoadDataSuccessed(List<Student> listStudent) {
        mainView.displayData(listStudent);
    }

    @Override
    public void onLoadDataFailured(String message) {
        mainView.showLoadDataFailured(message);
    }
}
