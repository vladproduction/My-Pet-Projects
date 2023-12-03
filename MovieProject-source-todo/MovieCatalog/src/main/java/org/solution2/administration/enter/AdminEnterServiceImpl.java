package org.solution2.administration.enter;

public class AdminEnterServiceImpl implements AdminEnterService {

    private AdminEnterInDB adminEnterInDB;

    public AdminEnterServiceImpl(AdminEnterInDB adminEnterInDB) {
        this.adminEnterInDB = adminEnterInDB;
    }

    @Override
    public boolean isExist(String login, String pass) {
        return adminEnterInDB.isPresent(login, pass);
    }
}
