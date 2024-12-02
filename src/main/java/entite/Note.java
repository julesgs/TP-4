package entite;

public class Note {

    private int score;
    private Image image;
    private User user;

    public Note(int score, Image image, User user) {
        this.score = score;
        this.image = image;
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public String getImage() {
        return image.getNom();
    }

    public String getUser() {
        return user.getName();
    }


}
