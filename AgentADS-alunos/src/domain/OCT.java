import domain.User;
import domain.UserCatalog;
import domain.interfaces.IOCT;

public class OCT implements IOCT {

    private UserCatalog userCatalog;

    public OCT(UserCatalog userCatalog) {
        this.userCatalog = userCatalog;
    }

    @Override
    public User getUsersInfo(String name) {
        User user = UserCatalog.getUserByName(name);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

}
