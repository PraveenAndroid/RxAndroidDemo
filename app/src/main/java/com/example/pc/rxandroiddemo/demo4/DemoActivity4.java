package com.example.pc.rxandroiddemo.demo4;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.example.pc.rxandroiddemo.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 8/26/2018.
 */

public class DemoActivity4 extends AppCompatActivity {

    private Observable<String> observable;
    private CompositeDisposable disposable=new CompositeDisposable();
    private DisposableObserver<String> disposableObserver1;
    private DisposableObserver<String> disposableObserver2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

          /*
        *
        */

        observable=getObserable();

        disposableObserver1=getDisposableObserver1();
        disposableObserver2=getDisposableObserver2();


        disposable.add(
                observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                        .filter(new Predicate<String>() {
                            @Override
                            public boolean test(String s) throws Exception {
                                return s.toLowerCase().startsWith("c");
                            }
                        })
                .subscribeWith(disposableObserver1)
        );

        disposable.add(
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .filter(new Predicate<String>() {
                            @Override
                            public boolean test(String s) throws Exception {
                                return !s.toLowerCase().startsWith("c");
                            }
                        })
                        .map(new Function<String, String>() {
                            @Override
                            public String apply(String s ) throws Exception
                            {
                                return s.toUpperCase();
                            }
                        })
                        .subscribeWith(disposableObserver2)
        );

    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    private Observable<String> getObserable() {

        return Observable.fromArray(
                "Ant", "Ape",
                "Bat", "Bee", "Bear", "Butterfly",
                "Cat", "Crab", "Cod",
                "Dog", "Dove",
                "Fox", "Frog");
    }

    private DisposableObserver<String> getDisposableObserver1() {

        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {

                Log.d("TAG",s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG",e.toString());
            }

            @Override
            public void onComplete() {
                Log.d("TAG","OnComplete");
            }
        };
    }
    private DisposableObserver<String> getDisposableObserver2() {

        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.d("TAG",s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG",e.toString());
            }

            @Override
            public void onComplete() {
                Log.d("TAG","OnComplete");
            }
        };
    }

}
