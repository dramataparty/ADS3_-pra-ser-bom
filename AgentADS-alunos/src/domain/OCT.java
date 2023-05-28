import domain.interfaces.IOCT;
import domain.User;

public class OCT implements IOCT {

    @Override
    public String getUsersInfo(String name) {
        User user = getUserByName(name);
        if (user != null) {
            return user.toString();
        } else {
            return "User not found.";
        }
    }

    private User getUserByName(String name) {

    }
}