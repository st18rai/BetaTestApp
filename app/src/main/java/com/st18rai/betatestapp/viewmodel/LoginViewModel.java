package com.st18rai.betatestapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.st18rai.betatestapp.model.LoginModel;
import com.st18rai.betatestapp.network.ApiClient;
import com.st18rai.betatestapp.utils.RxUtil;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<String> passKey = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<String> getPassKey() {
        return passKey;
    }
    private void setPassKey(String key) {
        passKey.setValue(key);
    }


    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
    private void setErrorMessage(String message) {
        errorMessage.setValue(message);
    }

    // Must be in repository. Made for simplicity
    public void loginUser(LoginModel loginModel) {
        RxUtil.networkConsumer(ApiClient.getApiInterface().login(loginModel),
                responseBody -> setPassKey(responseBody.string()),
                throwable -> {
                    throwable.printStackTrace();
                    setErrorMessage(throwable.getMessage());
                });
    }

}
