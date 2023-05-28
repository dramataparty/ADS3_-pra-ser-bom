package domain;

import domain.User;

public class UserCatalog {
    private List<User> users;

    public UserCatalog() {
        users = new ArrayList<>();
        createUser("Mary", "123");
        createUser("Peter", "ABC");
        createUser("John Snow", "123ABC");
    }

    public void createUser(String username, String password) {
        User user = new User(username, password);
        users.add(user);
    }

    public User getUserByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }
}
