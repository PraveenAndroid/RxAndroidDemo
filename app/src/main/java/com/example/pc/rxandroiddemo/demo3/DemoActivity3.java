package com.example.pc.rxandroiddemo.demo3;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.example.pc.rxandroiddemo.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 8/26/2018.
 */

public class DemoActivity3 extends AppCompatActivity {


    private Disposable disposable;
    private Observable<String> observable;
    private Observer<String> observer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);


         /*
        *
        */

        observable=getObserable();

        observer=getObserver();


        /*
        *  we can apply multiple operators on one obserable
        */

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return !s.toLowerCase().startsWith("a");
                    }
                })
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return !s.toLowerCase().startsWith("b");
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {

                        return s.toUpperCase();
                    }
                })
                .subscribeWith(observer);
    }

    private Observable<String> getObserable() {

        return Observable.fromArray(
                "Ant", "Ape",
                "Bat", "Bee", "Bear", "Butterfly",
                "Cat", "Crab", "Cod",
                "Dog", "Dove",
                "Fox", "Frog");
    }

    private Observer<String> getObserver() {

        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

                disposable=d;
            }

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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
