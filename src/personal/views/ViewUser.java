package personal.views;

import personal.controllers.UserController;
import personal.model.User;

import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt("\n(Возможные варианты команд: NONE, HELP, CREATE, READ, LIST, " +
                    "UPDATE, DELETE, EXIT)\nДля подробной информации выберите HELP.\n" +
                    "Введите команду (регистр значения не имеет): ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            try {
                switch (com) {
                    case HELP:
                        System.out.println("NONE   - ничего не делать\nHELP   - описание команд\n" +
                                "CREATE - создание новой записи\nREAD   - просмотр конкретной записи (необходимо знать " +
                                "идентификатор)\nLIST   - вывод на экран всех записей\nUPDATE - обновление конкретной " +
                                "записи(необходимо знать идентификатор)\nDELETE - удаление конкретной записи(необходимо " +
                                "знать идентификатор)\nEXIT   - завершение работы программы");
                        break;
                    case CREATE:
                        String firstName = prompt("Имя: ");
                        String lastName = prompt("Фамилия: ");
                        String phone = prompt("Номер телефона: ");
                        userController.saveUser(new User(firstName, lastName, phone));
                        break;
                    case READ:
                        String id = prompt("Идентификатор пользователя: ");
                        User user = userController.readUser(id);
                        System.out.println(user);
                        break;
                    case LIST:
                        List<User> lst = userController.readList();
                        lst.forEach(i -> System.out.println(i + "\n"));
                        break;
                    case UPDATE:
                        String numId = prompt("Какой контакт редактировать? Введите номер ID: ");
                        userController.idPresenceValidation(numId);
                        userController.updateUser(numId, createAGuy());
                        break;
                    case DELETE:
                        String delId = prompt("Какой контакт удалить? Введите номер ID: ");
                        userController.idPresenceValidation(delId);
                        userController.deleteUser(delId);

                }
            } catch (Exception e) {
                System.out.println("Ой!\n"+ e.getMessage()); ;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User createAGuy() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        User newGuy = new User(firstName, lastName, phone);
        return newGuy;
    }
}
