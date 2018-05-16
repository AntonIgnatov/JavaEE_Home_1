package net.bigmir.venzor;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private String login;
    private String password;
    private ArrayList<AtomicInteger> answers = new ArrayList<>();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.initAnswers();
    }

    public void incAnswer(int i){
        this.answers.get(i).getAndIncrement();
    }

    public AtomicInteger getAnswer (int i){
        return this.answers.get(i);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public void initAnswers (){
        for (int i = 0; i < 4; i++) {
            this.answers.add(new AtomicInteger(0));
        }
    }
}
