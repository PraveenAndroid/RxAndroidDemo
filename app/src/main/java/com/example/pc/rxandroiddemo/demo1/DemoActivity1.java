package com.example.pc.rxandroiddemo.demo1;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.example.pc.rxandroiddemo.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 8/26/2018.
 */

public class DemoActivity1 extends AppCompatActivity {

    private Observable<String> observable;
    private Observer<String> observer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

        /*
        *
        */
        observable=getFirstObserable();

        /*
        *
        */

        observer=createFirstOvserver();

        /*
        *
        */

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }




    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private Observable<String> getFirstObserable() {

        return Observable.just("Item1","item2","Item3","Item4","Item5");
    }

    private Observer<String> createFirstOvserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG","OnSubcribe");
            }

            @Override
            public void onNext(String s) {
                Log.d("TAG",s);
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
}
