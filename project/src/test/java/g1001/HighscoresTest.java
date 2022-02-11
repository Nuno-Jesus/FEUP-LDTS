package g1001;

import g1001.Highscores.Highscores;
import g1001.Highscores.Winner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

public class HighscoresTest {
    Highscores hs;
    String filePath;

    @BeforeEach
    public void setup(){
        hs = new Highscores();
        filePath = "src/main/resources/highscores/highscores.txt";
    }

    @Test
    public void addEntryTest() throws IOException{
        hs.addEntry("Carlos", Long.valueOf(15));

        Assertions.assertTrue(hs.entryExists(new Winner("Carlos", Long.valueOf(15))));
    }

    @Test
    public void readHighscoresTest() throws IOException {
        try {
            File f = new File (filePath);

            // If the file already exits, let´s delete it
            if (f.exists())
                f.delete();

            // Escreve no ficheiro uma string
            FileWriter fw = new FileWriter(f);

            fw.write("Carlos 20\n");
            fw.write("Miguel 60\n");
            fw.write("Nuno 90\n");
            fw.close();

        }catch (FileNotFoundException e) {
                e.printStackTrace();
        }

        // Lê da lista de winners e vai ordenar por ordem decrescente de score
        hs.readHighscores();


        Assertions.assertEquals(hs.getWinners().size(), 3);
        Assertions.assertEquals(hs.getWinners().get(0).getName(), "Nuno");
        Assertions.assertEquals(hs.getWinners().get(1).getName(), "Miguel");
        Assertions.assertEquals(hs.getWinners().get(2).getScore(), 20);
    }

    @Test
    public void writeHighscoresTest() throws IOException {
        String name;
        String[] aux = {};

        hs.addEntry("Carlos", Long.valueOf(99));
        hs.writeHighscores();

        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line;
            while((line = br.readLine()) != null){
                aux = line.split(" ");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        Assertions.assertEquals(aux[0], "Carlos");
        Assertions.assertEquals(Integer.parseInt(aux[1]), 99);
    }
}
