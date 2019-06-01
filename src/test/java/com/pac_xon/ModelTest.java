package com.pac_xon;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static junit.framework.TestCase.assertEquals;

public class ModelTest {

    Model model;

    @Before
    public void setUp(){

        this.model = new Model(70, 20);
    }

    @Test
    public void testNextLevel(){

        int lives = model.getArena().getLives().getLives();
        int monsters = model.getArena().getMonsters().size();


        model.newLevel();

        assertEquals(lives +1 , model.getArena().getLives().getLives());
        assertEquals(monsters + 1, model.getArena().getMonsters().size());
    }

    @Test
    public void testKeyHandlerStartMenu() throws AWTException {

        model.installKeyHandlerStartMenu();


        JTextField inputField = new JTextField();

        KeyboardFocusManager.getCurrentKeyboardFocusManager().dispatchKeyEvent(new KeyEvent(inputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'Z'));

        //assertEquals(model. menu_OPTION, Model.MENU.GAMEOVER);
    }
}
