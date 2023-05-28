package domain.handlers;

import domain.User;
import domain.UserCatalog;

public class LogoutHandler implements ILogoutHandler {
    private UserCatalog userCatalog;

    public LogoutHandler(UserCatalog userCatalog) {
        this.userCatalog = userCatalog;
    }

    @Override
    public void logout(String username) {
        User user = userCatalog.getUserByName(username);
        if (user != null) {
            user.setLoggedIn(false);
        }
    }
}
