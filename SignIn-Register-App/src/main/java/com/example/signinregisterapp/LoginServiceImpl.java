package com.example.signinregisterapp;

public class LoginServiceImpl implements LoginService {
    private LoginRepository loginRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public boolean isExist(String login, String password) {
        System.out.println("isExist: "+login);
        return loginRepository.isPresent(login,password);
    }
}
