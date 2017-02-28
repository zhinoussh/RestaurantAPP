package com.android.shahkar.andelosapp.network;

import java.util.List;

public interface ResultCallBackList<T> {
    void OnResultReady(List<T> return_list);
}

