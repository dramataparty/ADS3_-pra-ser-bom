import domain.User;
import domain.UserCatalog;
import domain.interfaces.IOCT;

/**
 * Classe que representa uma implementação da interface IOCT.
 */
public class OCT implements IOCT {

    private UserCatalog userCatalog;

    /**
     * Construtor da classe OCT.
     *
     * @param userCatalog O catálogo de usuários a ser utilizado.
     */
    public OCT(UserCatalog userCatalog) {
        this.userCatalog = userCatalog;
    }

    /**
     * Obtém as informações de um usuário pelo nome de usuário.
     *
     * @param name O nome de usuário do usuário.
     * @return O objeto User correspondente ao nome de usuário especificado, ou null se não for encontrado.
     */
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
