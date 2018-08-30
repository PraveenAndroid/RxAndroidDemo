package com.example.pc.rxandroiddemo.demo5;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.example.pc.rxandroiddemo.R;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 8/26/2018.
 */

public class DemoActivity5 extends AppCompatActivity {

    private Observable<CustomData> observable;
    private CompositeDisposable disposable=new CompositeDisposable();
    private DisposableObserver<CustomData> disposableObserver1;
    private DisposableObserver<CustomData> disposableObserver2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_activity);

        observable=getObserable();


        disposableObserver1=getDisposableOserver1();


        disposable.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .filter(new Predicate<CustomData>() {
                            @Override
                            public boolean test(CustomData customData) throws Exception {
                                return customData.getName().equalsIgnoreCase("praveen");
                            }
                        })
                        .map(new Function<CustomData, CustomData>() {
                            @Override
                            public CustomData apply(CustomData customData)throws Exception
                            {
                                customData.setName(customData.getName().toUpperCase());
                                return customData;
                            }
                        })
                        .subscribeWith(disposableObserver1)
        );
    }



    private Observable<CustomData> getObserable() {

        final List<CustomData> list=getList();

        return Observable.create(new ObservableOnSubscribe<CustomData>() {
            @Override
            public void subscribe(ObservableEmitter<CustomData> emitter) throws Exception {

                for(CustomData customData:list)
                {
                    if(!emitter.isDisposed())
                    {
                        emitter.onNext(customData);
                    }
                }

                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });
    }

    public List<CustomData> getList()
    {
        List<CustomData> customData=new ArrayList<>();

        customData.add(new CustomData(1,"praveen","8586001230"));
        customData.add(new CustomData(2,"praveen1","8586001231"));
        customData.add(new CustomData(3,"praveen2","8586001232"));
        customData.add(new CustomData(4,"praveen3","8586001233"));
        customData.add(new CustomData(5,"praveen4","8586001234"));
        return customData;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    private DisposableObserver<CustomData> getDisposableOserver1() {

        return new DisposableObserver<CustomData>() {
            @Override
            public void onNext(CustomData customData) {

                Log.d("TAG",customData.getName());
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
