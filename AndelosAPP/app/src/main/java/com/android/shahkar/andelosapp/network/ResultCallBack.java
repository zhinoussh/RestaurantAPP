package com.android.shahkar.andelosapp.network;

import java.util.List;

/**
 * Created by User on 2/2/2017.
 */
public interface ResultCallBack<T> {
    void OnResultReady(List<T> return_list);
}
