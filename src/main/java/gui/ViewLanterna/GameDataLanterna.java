package gui.ViewLanterna;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.pac_xon.Model;
import com.pac_xon.Monster;
import com.pac_xon.Position;
import com.pac_xon.Wall;

public class GameDataLanterna {

    private Model model;

    public GameDataLanterna(Model model) {
        this.model = model;
    }

    public void draw(TextGraphics graphics){

        drawBackground(graphics);
        drawWall(graphics);

        drawPlayer(graphics);
        drawMonsters(graphics);


        drawLives(graphics);
        drawScore(graphics);
        drawPercentage(graphics);

    }

    private void drawLives(TextGraphics graphics){

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(0, 0), ("Lives: " +  this.model.getArena().getLives()));
    }

    private void drawScore(TextGraphics graphics){

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(this.model.getArena().getWidth()/2 - 5, 0), "Score: " + this.model.getArena().getScore().getScore());
    }

    private void drawPercentage(TextGraphics graphics){

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.enableModifiers(SGR.BOLD);
        String percentage_to_be_shown = ((int) this.model.getArena().getPercentage() + "%");
        graphics.putString(new TerminalPosition(this.model.getArena().getWidth() - percentage_to_be_shown.length(), 0), percentage_to_be_shown);
    }

    private void drawMonsters(TextGraphics graphics){


        graphics.setForegroundColor(TextColor.Factory.fromString( "#FFCCD5"));
        graphics.enableModifiers(SGR.BOLD);

        for (Monster monster: model.getArena().getMonsters()) {

            Position position = monster.getPosition();
            graphics.putString(new TerminalPosition(position.getX(), position.getY() + 1), "X");
        }
    }

    private void drawPlayer(TextGraphics graphics){

        graphics.setForegroundColor(TextColor.Factory.fromString( "#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);

        Position position = model.getArena().getPlayer().getPosition();
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 1), "C");
    }


    private void drawBackground(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#3f3832"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(model.getArena().getWidth(), model.getArena().getWidth()), ' ');
    }

    private void drawWall(TextGraphics graphics){

        int width = model.getArena().getWidth();
        int height = model.getArena().getHeight();

        Wall.TYPE walls_array[][] = model.getArena().getWall().getWalls_array();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (walls_array[i][j] == Wall.TYPE.Wall || walls_array[i][j] == Wall.TYPE.Construction) {

                    graphics.setBackgroundColor(TextColor.Factory.fromString("#000080"));
                    graphics.putString(new TerminalPosition(i, j + 1), " ");
                }
            }
        }
    }

    public void drawStartMenu(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        String name_to_be_shown = "PAC XON";
        graphics.putString(new TerminalPosition(this.model.getArena().getWidth()/2 - name_to_be_shown.length()/2, 5), name_to_be_shown);
        String to_be_shown = "New game";
        graphics.putString(new TerminalPosition(this.model.getArena().getWidth()/2 - to_be_shown.length()/2, this.model.getArena().getHeight()/2), to_be_shown);
        String _to_be_shown = "Press enter to start";
        graphics.putString(new TerminalPosition(this.model.getArena().getWidth()/2 - _to_be_shown.length()/2, this.model.getArena().getHeight()/2 +1), _to_be_shown);
    }

    public void drawNextLevelMenu(TextGraphics graphics){

        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.enableModifiers(SGR.BOLD);
        String to_be_shown = "Next Level!";
        graphics.putString(new TerminalPosition(this.model.getArena().getWidth()/2 - to_be_shown.length()/2, this.model.getArena().getHeight()/2), to_be_shown);
    }

    public void drawGameOverMenu(TextGraphics graphics){

        int width = this.model.getArena().getWidth();
        int height = this.model.getArena().getHeight();

        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.enableModifiers(SGR.BOLD);
        String to_be_shown = "Game Over";
        graphics.putString(new TerminalPosition(width/2 - to_be_shown.length()/2, height/2), to_be_shown);
        String score_to_be_shown = "Score: " + this.model.getArena().getScore().getScore();
        graphics.putString(new TerminalPosition(width/2 - score_to_be_shown.length()/2, height/2 +1), score_to_be_shown);
    }
}
