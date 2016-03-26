package ru.medyannikov.homebank.data.managers.events;

import ru.medyannikov.homebank.data.storage.models.TokenModel;
import ru.medyannikov.homebank.data.storage.models.Account;

/**
 * Created by Vladimir on 13.03.2016.
 */
public class LoginSuccessEvent {
    private Account mAccount;
    private TokenModel tokenModel;

    public LoginSuccessEvent(TokenModel tokenModel) {
        this.tokenModel = tokenModel;
    }

    public TokenModel getTokenModel(){
        return tokenModel;
    }
}
