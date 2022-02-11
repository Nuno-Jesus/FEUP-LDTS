package g1001.Highscores;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Highscores {
    private List<Winner> winners;
    private String filePath;

    public Highscores(){
        winners = new ArrayList<>();
        filePath = "src/main/resources/highscores/highscores.txt";
    }

    public List<Winner> getWinners() {
        return winners;
    }

    /**
     * Will add to the list of highscores the new entry
     * @param name name of the player
     * @param score score of the player
     */
    public void addEntry(String name, Long score) throws IOException {
        Winner player = new Winner(name, score);

        winners.add(player);
        sortHighscores();
    }

    /**
     * Reads the info from the file
     * @throws IOException exception
     */
    public void readHighscores () throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {

            String curLine;      // Will read the line
            String[] parts;      // To split the string into:

            while ((curLine = br.readLine()) != null) {
                parts = curLine.split(" ");
                addEntry(parts[0], Long.parseLong(parts[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the info to the file
     * @throws IOException exception
     */
    public void writeHighscores() throws IOException {
        try {
            File f = new File(filePath);

            // If it exists already, lets delete it
            if (f.exists())
                f.delete();

            Writer fw = Files.newBufferedWriter(f.toPath(), StandardCharsets.UTF_8);

            for (Winner winner : winners){
                fw.write(winner.getName() + " " + winner.getScore());
                fw.write("\n");
            }
            fw.close();

        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    /**
     * Displays the scores in the screen
     */
    public void showHighscores(TextGraphics graphics, int row, int col){
        int max = 0;
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.enableModifiers(SGR.BOLD);

        for (Winner winner : winners){
            graphics.putString(new TerminalPosition(col + 15, row), "Name: " + winner.getName() + "Score: " + winner.getScore());
            row+=1;
            max++;
            if (max >= 10) break;
        }

    }

    /**
     * Sort the highscores by highest to lowest
     */
    public void sortHighscores(){
        Collections.sort(winners, Collections.reverseOrder());
    }

    /**
     * Method to check if a name already exists in the list of winners
     * @param w entry which existence will be checked
     * @return true if exists, false otherwise
     */
    public boolean entryExists(Winner w){
        for (Winner winner : winners)
            if (w.equals(winner))
                return true;

        return false;
    }

}
