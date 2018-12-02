package com.sheygam.java_221_28_11_18_cw;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.reactivex.observers.DisposableObserver;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    private Interactor interactor;
    private TextView countTxt;

    public SecondFragment() {
        // Required empty public constructor
    }

    public void setInteractor(Interactor interactor){
        this.interactor = interactor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_second, container, false);
        countTxt = view.findViewById(R.id.countTxt);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        interactor.subscribe(new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                countTxt.setText(String.valueOf(integer));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
