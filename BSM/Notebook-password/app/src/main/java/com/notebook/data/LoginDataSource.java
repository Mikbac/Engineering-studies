package com.notebook.data;

import com.notebook.data.model.LoggedInUser;

import java.io.IOException;

public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            LoggedInUser user =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "-------------");
            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
    }
}
