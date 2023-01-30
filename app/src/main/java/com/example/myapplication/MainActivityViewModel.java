package com.example.myapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    public MutableLiveData<Boolean> isSelected = new MutableLiveData<>(false);
}
