package com.example.w_mvvm_okhttp;

//ViewModelProviders.of(FragmentActivity activity)://創建一個ViewModel提供者,相依於活在哪個Activity(要存活在哪個Activity)(回傳ViewModelProvider)

//ViewModelProvider.get(Class<T> modelClass):取得ViewModel物件實體(要取得的ViewModel.類別)((回傳<T extends ViewModel> T))

//MutableLiveData.setValue(T value)://傳送素質數據(你要設定的犯行的資料型態)
//MutableLiveData.postValue(T value)//傳送素質數據,可在後台中使用(你要設定的范行資料結構)
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private NameViewModel nameViewModel;
    private Button btnName,btnSetValue,btnRemove;
    private TextView txtMsg;
    private String TAG ="hank";

    private MutableLiveData<String> nameData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnName = findViewById(R.id.btn);
        btnName.setOnClickListener(this);

        btnSetValue = findViewById(R.id.btnSetValue);
        btnSetValue.setOnClickListener(this);

        btnRemove = findViewById(R.id.removeObserver);
        btnRemove.setOnClickListener(this);

        txtMsg = findViewById(R.id.txtMsg);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                initViewModelData();
                break;

            case R.id.btnSetValue:
                dataSetVlaue();
                break;

            case R.id.removeObserver:
                removeOb();
                break;
        }
    }




    //1.創建LiveData
    private void initViewModelData() {
        //A.取得ViewModel物件
        nameViewModel = ViewModelProviders
                .of(this)//創建一個ViewModel提供者,相依於活在哪個Activity(要存活在哪個Activity)
                .get(NameViewModel.class);//取得ViewModel物件實體(要取得的ViewModel.類別)((回傳<T extends ViewModel> T))


        //B.取得MutableData
        nameData = nameViewModel.getCurrentName(); //取得MutableData

        //C.設定一個	observe觀察者,當數據有變動時會更新UI
        nameData
                .observe( //創建一個生命週期觀察者取代一般生命流程,在STARTED或RESUMED時,只能接受事件,當Activity關掉時,將不會更新數據,在此開啟則可更新數據,如果DESTROYED,Observice自動被刪除
                        this,//1.控制這個Observe的生命週期環境
                        new Observer<String>() //2.當數據更新時Observer收到的event
                        {
            @Override
            public void onChanged(String s) {
                txtMsg.setText(s);
                Log.v(TAG, "observe => onChanged" + "s:" + s);
            }
        });
        Log.v(TAG,"initViewModelData建立完成");
    }

    //LiveData.getValue():取得現在的質(回傳T自己LiveData設定的犯行資料)
    //LiveData.hasObservers():此LiceData是否有obService觀察者(回傳boolean)

    /*LiveData.observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer)
     * 訂閱一個生命週期觀察者取代一般生命流程,在STARTED或RESUMED時,只能接受事件,當Activity關掉時,將不會更新數據,在此開啟則可更新數據,如果DESTROYED,Observice自動被刪除
     * 參數1://1.控制這個Observe的生命週期環境
     * 參數2: //2.當數據更新時Observer收到的event
    */


    //2.按下按鈕將liveData數據更新
    private void dataSetVlaue() {
        if(nameData.hasObservers()){
            nameData.setValue("123456"); //傳送素質數據(你要設定的犯行的資料型態)
            nameData.postValue("aaa");  //傳送素質數據,可在後台中使用(你要設定的范行資料結構
            String a = nameData.getValue(); //取得現在的質
            Log.v(TAG,"dataSetVlaue => value::" + a);
        }else{
            Log.v(TAG,"dataSetVlaue =>" +"noObserver");
        }
    }

    //3.刪除Observe
    private void removeOb() {
        nameData.removeObserver();
    }



}
