package service;

import Util.FileManager;
import entite.Image;
import entite.Note;
import entite.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Modele {


    Image paris = new Image("/Users/jules/Downloads/TP-4/src/main/resources/tps/tp4/paris.jpg", "Paris");
    Image nyc = new Image("/Users/jules/Downloads/TP-4/src/main/resources/tps/tp4/nyc.jpg", "New York");
    Image singapour = new Image("/Users/jules/Downloads/TP-4/src/main/resources/tps/tp4/singapour.jpg", "Singapour");
    Image tokyo = new Image("/Users/jules/Downloads/TP-4/src/main/resources/tps/tp4/tokyo.jpg", "Tokyo");

    public List<Image> getImages() {
        List<Image> images = new ArrayList<>();
        images.add(paris);
        images.add(nyc);
        images.add(singapour);
        images.add(tokyo);
        return images;
    }

    public Image getImageByName(String name) {
        for (Image image : getImages()) {
            if (image.getNom().equalsIgnoreCase(name)) {
                return image;
            }
        }
        return null;
    }

    User admin = new User("admin", "admin");
    User root = new User("root", "root");
    User jules = new User("jules", "jules");

    User userLoged;

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(admin);
        users.add(root);
        users.add(jules);
        return users;
    }

    public User getUserByName(String name) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public List<Note> getNotes(){

        List<Note> notes = new ArrayList<>();

        FileManager fm = new FileManager();
        String content = fm.read();

        String[] elements = content.split("\n");

        for (String element : elements) {
            String[] values = element.split(";");

            Note n = new Note(Integer.parseInt(values[0]), getImageByName(values[1]), getUserByName(values[2]));

            notes.add(n);
        }

        return notes;
    }

    public Integer getNotebyCity(String cityName, String userName) {
        List<Note> notes = getNotes();

        for (Note note : notes) {
            if (note.getUser().equals(userName)) {
                if (note.getImage().equals(cityName)) {
                    return note.getScore();
                }
            }
        }
        return null;
    }

    public User login (String login, String password) {
        for (User user : getUsers()) {
            if (user.getName().equals(login) && user.getPassword().equals(password)) {
                System.out.println(user);
                userLoged = user;
                return user;
            }
        }
        return null;
    }

    public User getUserLoged(){
        return userLoged;
    }

    public void addNote(Integer score, Image image, User user) {

        String texte = score + ";" + image.getNom() + ";" + user.getName() + ";" + "\n";

        FileManager fm = new FileManager();

        fm.write(texte);
    }
}
