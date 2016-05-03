package ru.medyannikov.homebank.data.managers.events;

/**
 * Created by Vladimir on 14.03.2016.
 */
public class LoginFailedEvent {
    private String message;
    public LoginFailedEvent(String message){
        this.message = message;
    }
    public String getError(){
        return message;
    }
}
