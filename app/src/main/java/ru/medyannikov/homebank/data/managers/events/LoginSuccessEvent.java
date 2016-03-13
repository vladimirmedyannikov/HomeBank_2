package ru.medyannikov.homebank.data.managers.events;

import ru.medyannikov.homebank.data.storage.models.TokenModel;
import ru.medyannikov.homebank.data.storage.models.UserModel;

/**
 * Created by Vladimir on 13.03.2016.
 */
public class LoginSuccessEvent {
    private UserModel mUserModel;
    private TokenModel tokenModel;

    public LoginSuccessEvent(TokenModel tokenModel) {
        this.tokenModel = tokenModel;
    }

    public TokenModel getTokenModel(){
        return tokenModel;
    }
}
