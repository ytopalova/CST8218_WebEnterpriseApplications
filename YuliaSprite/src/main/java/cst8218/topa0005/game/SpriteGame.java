/*
 *Author:   Yuliia Topalova - 040981104
 *Project:  Assignment 1
 *Class:    SpriteGame
 *Purpose:  EJB contains the list of all sprites and handles the editing, 
            creation, and movement of them.
 */
package cst8218.topa0005.game;

import cst8218.topa0005.entity.Sprite;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class SpriteGame {

    private Integer x_size = 500;
    private Integer y_size = 500;

    private List<Sprite> spriteList;

    @Inject
    private GameSession session;

    //method runs after constuctor, sets up a thread
    //retrieve all sprites in order to move them   
    @PostConstruct
    public void go() {
        new Thread(new Runnable() {
            public void run() {

                while (true) {
                    spriteList = session.findAll();
                    //tell all sprite to move, determine which sprites should bounce
                    //ask them to bouncethen update the database
                    for (Sprite s : spriteList) {
                        s.move();
                        doBounce(s);
                        session.edit(s);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
        ).start();
    }

    //method implements the bouncing of the sprites
    public void doBounce(Sprite s) {
        try {
            //bounce off the left wall
            if (s.getX() < 0 && s.getxSpeed() < 0) {
                s.bounceLeftWall();
            }
            //bounce off the top wall
            if (s.getY() < 0 && s.getySpeed() < 0) {
                s.bounceTopWall();
            }
            //bounce off the right wall
            if (s.getX() > x_size && s.getxSpeed() > 0) {
                s.bounceRightWall();
            }
            //bounce off the bottom wall
            if (s.getX() > y_size && s.getySpeed() > 0) {
                s.bounceBottomWall();
            }
        } catch (Exception e) {
            
        }
        
    }
}
