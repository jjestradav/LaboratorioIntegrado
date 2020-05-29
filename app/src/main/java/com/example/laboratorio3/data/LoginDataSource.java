package com.example.laboratorio3.data;

import com.example.laboratorio3.data.model.LoggedInUser;
import com.example.laboratorio3.datasource.Database;
import com.example.laboratorio3.entity.Usuario;

import java.io.IOException;
import java.util.List;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private List<Usuario> Usuarios= Database.Usuarios();
    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            for(Usuario user: Usuarios){
                if(username.equals(user.getCedula())&&password.equals(user.getPassword())){
                    return new Result.Success<>(new LoggedInUser(username,username,user.getRol()));
                }
            }
            return new Result.Error(new Exception("No USER was found"));
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
