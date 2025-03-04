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


import java.io.File;


/**
 * The Clickable interface represents an object that can be interacted with
 * using the mouse in a graphical application.
 */

public interface Clickable {


  /**
   * Renders the clickable object to the application window.
   */

  void draw();


  /**
   * Implements the behavior to be run each time the mouse is pressed on the object.
   */

  void mousePressed();


  /**
   * Implements the behavior to be run each time the mouse is released on the object.
   */

  void mouseReleased();


  /**
   * Determines whether the cursor is currently over this object.
   *
   * @return true if the cursor is over the object, false otherwise.
   */

  boolean isMouseOver();


}
