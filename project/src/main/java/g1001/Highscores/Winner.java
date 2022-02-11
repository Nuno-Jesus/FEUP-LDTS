package g1001.Highscores;

import java.util.Objects;

public class Winner implements Comparable<Winner>{
    public Long score;
    public String name;

    public Winner(String name, Long score){
        this.name = name;
        this.score = score;
    }

    public Long getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setScore(Long score){
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)
            return false;
        if(!(o instanceof Winner))
            return false;
        Winner winner = (Winner) o;
        return Objects.equals(name, winner.name);
    }


    @Override
    public int compareTo(Winner o) {
        return this.getScore().compareTo(o.getScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
