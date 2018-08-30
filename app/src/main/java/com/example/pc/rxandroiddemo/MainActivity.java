package com.example.pc.rxandroiddemo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private TextView text_view;
    public Observable<String> observable;
    public Observer<String> observer;
    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_view=findViewById(R.id.text_view);


        observable=getAnimalObserable();

        observer=getAnimalObserver();


    }




    public void getRxText(View view)
    {

        observable
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.toLowerCase().startsWith("b");
                    }
                })
                .subscribeWith(observer);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private Observable<String> getAnimalObserable() {

        return Observable.fromArray(
                "Ant", "Ape",
                "Bat", "Bee", "Bear", "Butterfly",
                "Cat", "Crab", "Cod",
                "Dog", "Dove",
                "Fox", "Frog");
    }

    private Observer<String> getAnimalObserver() {

        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

                Log.d("TAG","OnSuscribe");
                disposable=d;
            }

            @Override
            public void onNext(String s) {
                Log.d("TAG",s);
               // text_view.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG","OnError");
            }

            @Override
            public void onComplete() {
                Log.d("TAG","OnComplete");
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        disposable.dispose();
    }
}
