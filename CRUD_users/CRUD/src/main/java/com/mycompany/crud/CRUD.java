/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.crud;

/**
 *
 * @author pipel
 */
import java.util.List;

public class CRUD {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // Crear un nuevo usuario
        User user1 = new User(0, "andres", "prueba@example.com", "constrasena1234");
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

