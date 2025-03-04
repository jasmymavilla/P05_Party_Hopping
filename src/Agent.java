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


import processing.core.PApplet;


/**
 * The Agent class represents an agent in a team-based system. It includes
 * functionality for drawing the agent, moving it, and interacting with user input.
 * This class implements the Clickable interface to allow agents to be clicked and dragged
 * within the graphical window.
 */

public class Agent extends Object implements Clickable {

  private float destX, destY;
  protected static int diameter = 50;
  private boolean isActive = false;
  protected boolean isDragging = false;
  private int oldMouseX, oldMouseY;
  private float originalX, originalY;
  protected static PApplet processing;
  protected Team team;
  private float xPos, yPos;


  /**
   * Constructs an agent at the given x, y coordinates.
   *
   * @param x The x-coordinate of the agent.
   * @param y The y-coordinate of the agent.
   */

  public Agent(float x, float y) {

    this.xPos = x;
    this.yPos = y;

  }


  /**
   * Returns the diameter of the agent.
   *
   * @return The diameter of the agent.
   */

  public static int diameter() {

    return diameter;

  }


  /**
   * Draws the agent on the screen.
   */

  public void draw() {

    processing.fill(getColor());
    processing.circle(xPos, yPos, diameter);

  }


  /**
   * Returns the color of the agent.
   *
   * @return The color of the agent.
   */

  protected int getColor() {

    if (isActive) {
      return processing.color(0, 255, 0);
    } else if (team != null) {
      return team.getColor();
    } else {
      return processing.color(255, 255, 0);
    }

  }


  /**
   * Returns the team of the agent.
   *
   * @return The team of the agent.
   */

  public Team getTeam() {

    return team;

  }


  /**
   * Sets the agent's team.
   *
   * @param team The team to set for the agent.
   */

  public void setTeam(Team team) {

    this.team = team;

  }


  /**
   * Toggles the agent's active state.
   */

  public void toggleActive() {

    this.isActive = !isActive;

  }


  /**
   * Starts dragging the agent.
   */

  protected void startDragging() {

    isDragging = true;
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
    originalX = xPos;
    originalY = yPos;

  }


  /**
   * Updates the agent's position while dragging.
   */

  protected void drag() {

    if (isDragging) {
      float dx = processing.mouseX - oldMouseX;
      float dy = processing.mouseY - oldMouseY;
      xPos += dx;
      yPos += dy;
      oldMouseX = processing.mouseX;
      oldMouseY = processing.mouseY;
    }

  }


  /**
   * Stops dragging the agent.
   */

  protected void stopDragging() {

    isDragging = false;

  }


  /**
   * Moves the agent towards the destination.
   */

  protected void move() {

    if (destX == -1 || destY == -1) return;
    float dx = destX - xPos;
    float dy = destY - yPos;
    double distance = Math.sqrt(dx * dx + dy * dy);

    if (distance <= 3) {
      xPos = destX;
      yPos = destY;
      destX = -1;
      destY = -1;
    } else {
      xPos += dx * 3 / distance;
      yPos += dy * 3 / distance;
    }

  }


  /**
   * Sets the destination for the agent.
   *
   * @param x The x-coordinate of the destination.
   * @param y The y-coordinate of the destination.
   */

  public void setDestination(float x, float y) {

    this.destX = x;
    this.destY = y;
    this.isActive = false;

  }


  /**
   * Checks if the agent is moving.
   *
   * @return True if moving, false otherwise.
   */

  protected boolean isMoving() {

    return destX != -1 && destY != -1;

  }


  /**
   * Handles mouse press events.
   */

  public void mousePressed() {

    if(this.isMouseOver() == true) {
      if(this.isMoving() != true);
      this.isDragging = true;
      this.originalX = this.xPos;
      this.originalY = this.yPos;
    }

  }


  /**
   * Handles mouse release events.
   */

  public void mouseReleased() {

    stopDragging();
    if(this.xPos == this.originalX && this.yPos == this.originalY) {
      this.isActive = true;
    }

    this.originalX = -1;
    this.originalY = -1;

  }

  /**
   * Checks if the agent is active.
   *
   * @return True if active, false otherwise.
   */

  public boolean isActive() {

    return isActive;

  }


  /**
   * Checks if the mouse is over the agent.
   *
   * @return True if the mouse is over, false otherwise.
   */

  public boolean isMouseOver() {

    double distance = Math.sqrt(Math.pow(processing.mouseX - xPos, 2)
        + Math.pow(processing.mouseY - yPos, 2));
    return distance < diameter / 2;

  }


  /**
   * Gets the x-coordinate of the agent.
   *
   * @return The x-coordinate of the agent.
   */

  public float getX() {

    return xPos;

  }


  /**
   * Gets the y-coordinate of the agent.
   *
   * @return The y-coordinate of the agent.
   */

  public float getY() {

    return yPos;

  }


  /**
   * Initializes the Processing instance.
   *
   * @param processing The PApplet instance used for rendering.
   */

  public static void setProcessing(PApplet processing) {

    Agent.processing = processing;

  }


}

