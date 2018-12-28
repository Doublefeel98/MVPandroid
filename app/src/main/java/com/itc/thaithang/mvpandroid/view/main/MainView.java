package com.itc.thaithang.mvpandroid.view.main;

import com.itc.thaithang.mvpandroid.model.entity.Student;

import java.util.List;


/**
 * - Lớp View định nghĩa các hàm hiển thị dữ liệu
 */

public interface MainView {
    void displayData(List<Student> student);
    void showLoadDataFailured(String message);
}
