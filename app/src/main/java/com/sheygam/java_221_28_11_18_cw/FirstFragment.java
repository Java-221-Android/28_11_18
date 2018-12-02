package com.sheygam.java_221_28_11_18_cw;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    private Interactor interactor;
    private Button clickBtn;
    private int count = 0;

    public FirstFragment() {
        // Required empty public constructor
    }

    public void setInteractor(Interactor interactor){
        this.interactor = interactor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_first, container, false);
        clickBtn = view.findViewById(R.id.clickBtn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interactor.emmitCount(++count);
            }
        });
    }
}
