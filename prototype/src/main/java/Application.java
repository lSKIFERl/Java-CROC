import menu.Menu;

import java.io.*;

public class Application {

    /** Название файла для сериализации */
    private static final String FILE_NAME = "task.dat";

    public static void main(String[] args) {

        //Пробуем десериализовать файл менюшки, если такого нет, создаём пока объект  menu.Menu
        Menu menu;
        try (
                FileInputStream fis = new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            menu = (Menu) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            menu = new Menu();
        }

        //вызываем менюшку
        menu.callMenu();

        //после всего, что сделали, сохраняемся, сериализуем
        try(
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(menu);
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
