package com.example.fetchapplication.di.component;

import com.example.fetchapplication.repository.DisplayItemsRepository;
import com.example.fetchapplication.ui.DisplayItemsActivity;

import dagger.Component;

@Component
public interface DisplayItemsComponent {

    DisplayItemsRepository getDisplayItemsRepository();

    void inject(DisplayItemsActivity activity);
}
