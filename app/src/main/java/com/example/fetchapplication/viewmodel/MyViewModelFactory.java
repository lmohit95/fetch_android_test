package com.example.fetchapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.fetchapplication.repository.DisplayItemsRepository;

import javax.inject.Singleton;

@Singleton
public class MyViewModelFactory implements ViewModelProvider.Factory {
    private final DisplayItemsRepository param;

    public MyViewModelFactory(DisplayItemsRepository param) {
        this.param = param;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <U extends ViewModel> U create(Class<U> modelClass) {
        return (U) new DisplayItemsViewModel(param);
    }
}
