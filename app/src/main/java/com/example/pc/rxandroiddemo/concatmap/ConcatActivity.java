package com.example.pc.rxandroiddemo.concatmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.pc.rxandroiddemo.R;
import com.example.pc.rxandroiddemo.flatmap.*;
import com.example.pc.rxandroiddemo.flatmap.User;
import com.example.pc.rxandroiddemo.flatmap.UserAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 8/28/2018.
 */

public class ConcatActivity extends AppCompatActivity {
    Disposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

        getUsersObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap(new Function<com.example.pc.rxandroiddemo.flatmap.User, Observable<com.example.pc.rxandroiddemo.flatmap.User>>() {

                    @Override
                    public Observable<com.example.pc.rxandroiddemo.flatmap.User> apply(com.example.pc.rxandroiddemo.flatmap.User user) throws Exception {

                        // getting each user address by making another network call
                        return getAddressObservable(user);
                    }
                })
                .subscribe(new Observer<com.example.pc.rxandroiddemo.flatmap.User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("TAG", "onSubscribe");
                        disposable = d;
                    }

                    @Override
                    public void onNext(User user) {
                        Log.d("TAG", "onNext: " + user.getName() + ", " + user.getGender() + ", " + user.getAddress().getAddress());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "All users emitted!");
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /*
    *
    */

    private Observable<com.example.pc.rxandroiddemo.flatmap.User> getUsersObservable() {
        String[] maleUsers = new String[]{"Mark", "John", "Trump", "Obama"};

        final List<com.example.pc.rxandroiddemo.flatmap.User> users = new ArrayList<>();
        for (String name : maleUsers) {
            com.example.pc.rxandroiddemo.flatmap.User user = new com.example.pc.rxandroiddemo.flatmap.User();
            user.setName(name);
            user.setGender("male");
            users.add(user);
        }

        return Observable
                .create(new ObservableOnSubscribe<com.example.pc.rxandroiddemo.flatmap.User>() {
                    @Override
                    public void subscribe(ObservableEmitter<com.example.pc.rxandroiddemo.flatmap.User> emitter) throws Exception {
                        for (com.example.pc.rxandroiddemo.flatmap.User user : users) {
                            if (!emitter.isDisposed()) {
                                emitter.onNext(user);
                            }
                        }

                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }

    private Observable<com.example.pc.rxandroiddemo.flatmap.User> getAddressObservable(final com.example.pc.rxandroiddemo.flatmap.User user) {

        final String[] addresses = new String[]{

                "1600 Amphitheatre Parkway, Mountain View, CA 94043",
                "2300 Traverwood Dr. Ann Arbor, MI 48105",
                "500 W 2nd St Suite 2900 Austin, TX 78701",
                "355 Main Street Cambridge, MA 02142"
        };

        return Observable
                .create(new ObservableOnSubscribe<com.example.pc.rxandroiddemo.flatmap.User>() {
                    @Override
                    public void subscribe(ObservableEmitter<com.example.pc.rxandroiddemo.flatmap.User> emitter) throws Exception {
                        com.example.pc.rxandroiddemo.flatmap.UserAddress address = new UserAddress();
                        address.setAddress(addresses[new Random().nextInt(2) + 0]);
                        if (!emitter.isDisposed()) {
                            user.setAddress(address);

                            // Generate network latency of random duration
                            int sleepTime = new Random().nextInt(1000) + 500;

                            Thread.sleep(sleepTime);
                            emitter.onNext(user);
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }
}
