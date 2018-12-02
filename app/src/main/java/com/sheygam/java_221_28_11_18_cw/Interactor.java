package com.sheygam.java_221_28_11_18_cw;

import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;

public class Interactor {
    private PublishSubject<Integer> subject;

    public Interactor() {
        subject = PublishSubject.create();
    }

    public void emmitCount(int count){
        subject.onNext(count);
    }

    public void subscribe(Observer<Integer> observer){
        subject.subscribe(observer);
    }
}
