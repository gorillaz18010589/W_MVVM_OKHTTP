package com.example.w_mvvm_okhttp;
//LiveData:
//1.繼承ViewModel
//2.設定private MutableLiveData<String> currentName; 宣告要玩的Data資料結構
//3.取得MutableLiveData物件實體


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NameViewModel extends ViewModel {//1.繼承ViewModel

    //2.設定private MutableLiveData<String> currentName; 宣告要玩的Data資料結構
    private MutableLiveData<String> currentName;

    //3.取得MutableLiveData物件實體
    public MutableLiveData<String> getCurrentName(){
        if(currentName == null){
            currentName = new MutableLiveData<>();
        }
        return  currentName;
    }
}
