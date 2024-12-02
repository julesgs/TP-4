package Util;

import java.io.*;

public class FileManager {

    private String filePath;

    public FileManager() {
        this.filePath = "notes.txt";
    }

    public void write(String content) {

        File nomFichier = new File(filePath);

        try {

            FileOutputStream flux = new FileOutputStream(nomFichier, true);
            flux.write(content.getBytes());
            flux.close();

        } catch ( FileNotFoundException e ){

            System.out.println(e.getMessage());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String read() {
        File nomFichier = new File(filePath);
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return content.toString();
    }
}




