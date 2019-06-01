package com.pac_xon;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Score extends Item {

    private int width;
    private int score;
    private String HighScoresFile = "HighScores.txt";
    private List<Integer> highScores;

    public Score(String symbol, String color, int width) {
        super(symbol, color);
        this.width = width;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(this.width/2 - 5, 0), this.symbol + this.score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void loadHighScores() throws IOException {
        List<Integer> ints = Files.lines(Paths.get(HighScoresFile))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        this.highScores = ints;
    }

    public void saveHighScores() throws IOException {
            BufferedWriter out = new BufferedWriter(new FileWriter(HighScoresFile));
            for (int number : highScores) {
                out.write(number + "\n");
            }
            out.flush();

    }

    public List<Integer> getHighScores() {
        return highScores;
    }

    public void isHighScore(Score score) {
        int min = highScores.get(0);
        for (int i : highScores){
            min = min < i ? min : i;
        }

        int count = -1;
        for (int i : highScores){
            count ++;
            if (i == min) {
                System.out.println(count);
                highScores.set(count, score.getScore());
                return;
            }
        }
    }
}

