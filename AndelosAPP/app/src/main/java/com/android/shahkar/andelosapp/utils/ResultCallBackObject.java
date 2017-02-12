package com.android.shahkar.andelosapp.utils;

public interface ResultCallBackObject<T> {
    void OnResultReady(T return_object,String errorMessage);
}
