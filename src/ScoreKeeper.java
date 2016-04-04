public class ScoreKeeper {

    Handler handler;
    Snake snake;

    // score = actual score (+1 if eat fruit, -2 if eat rotten fruit)
    // scoreKeep = used to determine level. Only +1 if eat fruit. Never decreased.
    int score = 0;
    int scoreKeep = 0;

    // Current game level
    int level = 1;

    ScoreKeeper(Handler handler) {

        this.handler = handler;

    }

    public void incrementScore() {
        score++;
        scoreKeep++;

        if (scoreKeep % 5 == 0) {
            level++;
            handler.addFruitRotten();
        }
    }

    public void decrementScore() {
        score -= 2;
    }

    public void reset() {
        score = 0;
        scoreKeep = 0;
        level = 1;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }
}
