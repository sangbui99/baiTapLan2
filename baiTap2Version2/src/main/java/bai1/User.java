package bai1;

import java.util.ArrayList;
import java.util.List;

public class User {
    private boolean result;
    private int user;
    private float per_user;
    private int user_old_time;
    private List<Source> source = new ArrayList<>();


    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public float getPer_user() {
        return per_user;
    }

    public void setPer_user(float per_user) {
        this.per_user = per_user;
    }

    public int getUser_old_time() {
        return user_old_time;
    }

    public void setUser_old_time(int user_old_time) {
        this.user_old_time = user_old_time;
    }

    public List<Source> getSource() {
        return source;
    }

    public void setSource(List<Source> source) {
        this.source = source;
    }

    @Override
    public String toString() {
        for (Source s : source) {
            s.toString();
        }
        return "User{" +
                "result=" + result +
                ", user=" + user +
                ", per_user=" + per_user +
                ", user_old_time=" + user_old_time +
                ", source=" + source +
                '}';
    }
}
