package com.android.shahkar.andelosapp.network;

public interface ResultCallBackObject<T> {
    void OnResultReady(T return_object,String errorMessage);
}
