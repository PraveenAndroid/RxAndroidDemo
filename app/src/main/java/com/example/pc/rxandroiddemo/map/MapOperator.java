package com.example.pc.rxandroiddemo.map;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.example.pc.rxandroiddemo.R;
import com.example.pc.rxandroiddemo.concat.User1;
import java.util.ArrayList;
import java.util.List;
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

public class MapOperator extends AppCompatActivity {

    private Observable<User1> user2Observable;
    private Disposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

        user2Observable=getUser2Oseravle();

        user2Observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<User1, User1>() {
                    @Override
                    public User1 apply(User1 user1)throws Exception
                    {
                        user1.setName(user1.getName().toUpperCase());
                       return user1;
                    }
                }

               ).subscribeWith(new Observer<User1>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable=d;
            }

            @Override
            public void onNext(User1 user1) {

                Log.d("TAG",user1.getName());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        })
        ;
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

    private Observable<User1> getUser2Oseravle() {

        final List<User1> user2List=getUser2List();
        return Observable.create(new ObservableOnSubscribe<User1>() {
            @Override
            public void subscribe(ObservableEmitter<User1> emitter) throws Exception {

                for (User1 user2:user2List) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(user2);
                    }
                }

                if(!emitter.isDisposed())
                {
                    emitter.onComplete();
                }
            }
        });
    }


    private List<User1> getUser2List() {

        List<User1> user2List=new ArrayList<>();
        user2List.add(new User1("Soni","FeMale"));
        user2List.add(new User1("Pooja","FeMale"));
        user2List.add(new User1("Jyoti","FeMale"));
        user2List.add(new User1("Mannu","FeMale"));
        return  user2List;
    }
}
