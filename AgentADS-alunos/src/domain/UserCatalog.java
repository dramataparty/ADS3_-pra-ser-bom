package domain;

import domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um catálogo de usuários.
 */
public class UserCatalog {
    private List<User> users;

    /**
     * Construtor da classe UserCatalog.
     * Inicializa a lista de usuários e cria alguns usuários iniciais.
     */
    public UserCatalog() {
        users = new ArrayList<>();
        createUser("Mary", "123");
        createUser("Peter", "ABC");
        createUser("John Snow", "123ABC");
    }

    /**
     * Cria um novo usuário com o nome de usuário e senha especificados.
     *
     * @param username O nome de usuário do novo usuário.
     * @param password A senha do novo usuário.
     */
    public void createUser(String username, String password) {
        User user = new User(username, password);
        users.add(user);
    }

    /**
     * Obtém um usuário pelo nome de usuário.
     *
     * @param name O nome de usuário do usuário a ser obtido.
     * @return O usuário correspondente ao nome de usuário especificado, ou null se não for encontrado.
     */
    public User getUserByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }
}
