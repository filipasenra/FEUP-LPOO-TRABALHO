package com.pac_xon;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Score{

    private int score;
    private String HighScoresFile = "HighScores.txt";
    private List<Integer> highScores;

    public Score() {
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void loadHighScores() throws IOException {

        File f = new File(HighScoresFile);
        if(!f.exists()) {
            f.createNewFile();
        }

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
        if(highScores.size() == 0)
        {
            highScores.add(score.getScore());
            return;
        }

         int min = highScores.get(0);

            for (int i : highScores) {
                min = min < i ? min : i;
            }

            int count = -1;
            for (int i : highScores) {
                count++;
                if (i == min) {
                    highScores.set(count, score.getScore());
                    return;
                }
            }
    }
}

