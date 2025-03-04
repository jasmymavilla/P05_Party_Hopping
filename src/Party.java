//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PO5 Team Party Hopping
// Course:   CS 300 Spring 2025
//
// Author:   Jasmy Mavilla
// Email:    jmavilla@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////


import processing.core.PImage;


/**
 * Represents a party that can be selected and added to a team.
 */

public class Party extends Object implements Clickable {

  private static TeamManagementSystem tms;
  private float x, y;
  private PImage image;


  public Party(int x, int y, PImage image) {

    this.x = x;
    this.y = y;
    this.image = image;

  }


  /**
   * Initializes the TeamManagementSystem reference to the provided value.
   *
   * @param processing the TeamManagementSystem object representing this program's application window
   */

  public static void setProcessing(TeamManagementSystem processing) {

    tms = processing;

  }


  /**
   * Accessor method for the current x-coordinate of this Party.
   *
   * @return the current x-coordinate of this Party
   */

  public float getX() {

    return x;

  }


  /**
   * Accessor method for the current y-coordinate of this Party.
   *
   * @return the current y-coordinate of this Party
   */

  public float getY() {

    return y;

  }


  /**
   * Draws the image associated with this party to its (x, y) location.
   */

  @Override
  public void draw() {

    tms.image(image, x, y);

  }


  /**
   * This method is intentionally left empty because nothing happens
   * when the mouse is pressed on the Party.
   */

  @Override
  public void mousePressed() {

  }


  /**
   * Defines the behavior of this Party when the mouse is released.
   * If the mouse is over this party, the Party gets the active team from
   * the TeamManagementSystem and sends them to this party.
   */

  @Override
  public void mouseReleased() {

    if (isMouseOver() && tms != null) {
      Team activeTeam = tms.getActiveTeam();
      if (activeTeam != null) {
        activeTeam.sendToParty(this);
      }
    }

  }


  /**
   * Determines whether the mouse is over this party.
   *
   * @return true if the mouse is over the Party, false otherwise
   */

  @Override
  public boolean isMouseOver() {

    float mouseX = tms.mouseX;
    float mouseY = tms.mouseY;

    return mouseX >= x && mouseX <= x + image.width &&
        mouseY >= y && mouseY <= y + image.height;

  }


}
