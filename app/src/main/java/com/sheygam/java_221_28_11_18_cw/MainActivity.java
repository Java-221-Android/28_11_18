package com.sheygam.java_221_28_11_18_cw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MY_TAG";
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        example6();
    }

    private void example1(){
        ObservableOnSubscribe<String> onSubscribe = new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Vasya");
                emitter.onNext("Sofa");
                emitter.onNext("Alex");
                emitter.onComplete();
//                emitter.onError(new Exception("Error"));
            }
        };

        Observable<String> observable = Observable.create(onSubscribe);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe() called with: d = [" + d + "]");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext() called with: s = [" + s + "]");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError() called with: e = [" + e + "]");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete() called");
            }
        };

        observable.subscribe(observer);

        observable = Observable.just("Tanya","Vova","Dima");
        observable.subscribe(observer);

        String[] array = {"Moshe","David","Sara"};
        List<String> list = Arrays.asList(array);

        observable = Observable.fromArray(array);
        observable.subscribe(observer);

        observable = Observable.fromIterable(list);
        observable.subscribe(observer);

        observable = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Vasya";
            }
        });

        observable.subscribe(observer);

    }

    private void example2(){
        DisposableObserver<String> observer = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext() called with: s = [" + s + "]");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError() called with: e = [" + e + "]");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete() called");
            }
        };

        Observable<String> observable = Observable.just("Vasya","Petya","Vova");
        observable.subscribe(observer);
    }

    public void example3(){
        Single<String> single = Single.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
//                return "Vasya";
                throw new Exception("Error");
            }
        });

        DisposableSingleObserver<String> singleObserver = new DisposableSingleObserver<String>() {
            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "onSuccess() called with: s = [" + s + "]");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError() called with: e = [" + e + "]");
            }
        };
        single.subscribe(singleObserver);

        Completable completable = Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: called");
            }
        });

        DisposableCompletableObserver completableObserver = new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete() called");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError() called with: e = [" + e + "]");
            }
        };
        completable.subscribe(completableObserver);

        Maybe<String> maybe = Maybe.just("Vasya");

        DisposableMaybeObserver<String> maybeObserver = new DisposableMaybeObserver<String>() {
            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "onSuccess() called with: s = [" + s + "]");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError() called with: e = [" + e + "]");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete() called");
            }
        };

        maybe.subscribe(maybeObserver);
    }

    public void example4(){
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Thread.sleep(5000);
                emitter.onNext("Result ok");
                emitter.onComplete();
            }
        });

        DisposableObserver<String> observer = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError() called with: e = [" + e + "]");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete() called");
            }
        };

        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void example5(){
        Observable<String> observable = Observable.just("Vasya","Petya");
        DisposableObserver<String> observer = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext() called with: s = [" + s + "]");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError() called with: e = [" + e + "]");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete() called");
            }
        };

        Disposable disposable = observable.subscribeWith(observer);
        disposable.dispose();
    }

    public void example6(){
        Observable<String> observable = Observable.just("Vasya","Petya","Vova");
        Disposable disposable = observable.subscribe(
                s -> Log.d(TAG, "accept() called with: s = [" + s + "]"),
                throwable -> Log.d(TAG, "accept() called with: throwable = [" + throwable + "]"),
                () -> Log.d(TAG, "run() called"));
    }
}
