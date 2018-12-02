package com.sheygam.java_221_28_11_18_cw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Interactor interactor = new Interactor();
        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        firstFragment.setInteractor(interactor);
        secondFragment.setInteractor(interactor);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fRoot,firstFragment)
                .replace(R.id.sRoot,secondFragment)
                .commit();
    }
}
