/*
 *Author:   Yuliia Topalova - 040981104
 *Project:  Assignment 1
 *Class:    Sprite
 *Purpose:  Responsible for declaring program variables, getters and setters. 
            That are also responsible for some functions used by sprite JSF pages.
 */
package cst8218.topa0005.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Sprite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer x;
    private Integer y;
    private Integer xSpeed;
    private Integer ySpeed;

    //method gets the value of x
    public Integer getX() {
        return x;
    }

    //method sets the value of x
    public void setX(Integer x) {
        this.x = x;
    }

    //method gets the value of y
    public Integer getY() {
        return y;
    }

    //method sets the value of y
    public void setY(Integer y) {
        this.y = y;
    }

    //method gets the value of xSpeed
    public Integer getxSpeed() {
        return xSpeed;
    }

    //method sets the value of xSpeed
    public void setxSpeed(Integer xSpeed) {
        this.xSpeed = xSpeed;
    }

    //method gets the value of ySpeed
    public Integer getySpeed() {
        return ySpeed;
    }

    //method gets the value of ySpeed
    public void setySpeed(Integer ySpeed) {
        this.ySpeed = ySpeed;
    }

    //method gets the value of id
    public Long getId() {
        return id;
    }

    //method sets the value of id
    public void setId(Long id) {
        this.id = id;
    }

    //method describes the movement of the sprites
    public void move() {
        try {
            x += xSpeed;
            y += ySpeed;
        } catch (Exception exc) {
            
        }
        
    }

    //method modifies the ySpeed of the sprite to bounceTopWall
    public void bounceTopWall() {
        ySpeed = -ySpeed;
    }

    //method modifies the ySpeed of the sprite to bounceTopWall
    public void bounceBottomWall() {
        ySpeed = -ySpeed;
    }

    //method modifies the xSpeed of the sprite to bounceLeftWall
    public void bounceLeftWall() {
        xSpeed = -xSpeed;
    }

    //method modifies the xSpeed of the sprite to bounceRightWall
    public void bounceRightWall() {
        xSpeed = -xSpeed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    //method update the sprites
    public void updateSprite(Sprite newSprite) {
        if (newSprite.getX() != null) {
            setX(newSprite.getX());
        }
        if (newSprite.getY() != null) {
            setY(newSprite.getY());
        }
        if (newSprite.getxSpeed() != null) {
            setxSpeed(newSprite.getxSpeed());
        }
        if (newSprite.getySpeed() != null) {
            setySpeed(newSprite.getySpeed());
        }
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set

        if (!(object instanceof Sprite)) {
            return false;
        }
        Sprite other = (Sprite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst8218.topa0005.entity.Sprite[ id=" + id + " ]";
    }

}
