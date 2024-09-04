import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // Crear un nuevo usuario
        User user1 = new User(0, "john_doe", "john@example.com", "password123");
        userService.addUser(user1);

        // Obtener todos los usuarios
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user.getUsername() + " - " + user.getEmail());
        }

        // Actualizar un usuario
        User userToUpdate = userService.getUserById(1);
        if (userToUpdate != null) {
            userToUpdate.setPassword("new_password");
            userService.updateUser(userToUpdate);
        }

        // Eliminar un usuario
        userService.deleteUser(1);
    }
}
