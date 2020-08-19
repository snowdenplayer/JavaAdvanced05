import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        UserService userService = new UserServiceImpl();
        userService.create(new User("root","root","root","root"));
    }
}
