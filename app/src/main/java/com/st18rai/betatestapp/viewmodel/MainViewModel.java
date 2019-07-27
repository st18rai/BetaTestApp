package com.st18rai.betatestapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.st18rai.betatestapp.model.ItemData;
import com.st18rai.betatestapp.network.ApiClient;
import com.st18rai.betatestapp.utils.RxUtil;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<ItemData>> signals = new MutableLiveData<>();

    public LiveData<List<ItemData>> getSignals() {
        return signals;
    }

    private void setSignals(List<ItemData> data) {
        signals.setValue(data);
    }

    // Must be in repository. Made for simplicity
    public void loadSignals(String login, String tradingSystems, String pairs, String from, String to,
                            String passKey) {

        RxUtil.networkConsumer(ApiClient.getApiInterface().getSignalsList(login,
                tradingSystems, pairs, from, to, passKey),
                this::setSignals,
                Throwable::printStackTrace);
    }
}
