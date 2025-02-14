import java.time.LocalDate;

public class Score {
    private int score;
    private boolean win;
    private LocalDate date;

    public Score(int score, boolean win, LocalDate date) {
        this.score = score;
        this.win = win;
        this.date = date;
    }

    public Score(int score, boolean win) {
        this.score = score;
        this.win = win;
        this.date = LocalDate.now();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
