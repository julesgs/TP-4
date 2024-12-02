package entite;

public class Image {

    private String url;
    private String name;

    public Image(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getNom() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
