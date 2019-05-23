package com.pac_xon;

import com.googlecode.lanterna.input.KeyStroke;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class Model {

    private Arena arena;
    private Menu menu;
    private int lives;
    private int no_monsters;

    boolean Play = true;

    public Model(int width, int height) {

        this.lives = 5;
        this.no_monsters = 2;

        this.arena = new Arena(width, height, lives, no_monsters, 0);
        this.menu = new Menu(width, height);
    }

    public void processKey(KeyStroke key) {
        arena.processKey(key);
        return;
    }

    public Arena getArena() {
        return arena;
    }

    public Menu getMenu() {
        return menu;
    }

    public void newLevel(){
        if (no_monsters < 10)
            no_monsters++;

        arena = new Arena(70, 20, arena.getLives().getLives() +1, no_monsters, arena.getScore().getScore());
    }

    public void installKeyHandler(){

        KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(final KeyEvent e) {

                if(e.getID() == KeyEvent.KEY_RELEASED )
                    return false;


                arena.processKeySwing(e);

                if(e.getKeyCode() == KeyEvent.VK_Q)
                    Play = false;

                return true;
            }
        };

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
    }
}
