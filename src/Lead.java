//////////////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
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


/**
 * Models a Team Lead agent for the CS300 P05 Team Party Hopping project.
 * Every Team can have at most one Lead, and clicking on that Team's Lead
 * selects all members of the Team at the same time.
 */

public class Lead extends Agent {


  /**
   * Constructs a new Lead at the given x, y coordinates.
   *
   * @param x The x-coordinate of the lead.
   * @param y The y-coordinate of the lead.
   */

  public Lead(int x, int y) {

    super(x, y);

  }


  /**
   * Draws the team lead on the screen. This method draws an inverted black triangle
   * above the circle representing the agent to indicate that the agent is a team lead.
   * The color corresponds to this Lead's selection/team status.
   */

  @Override
  public void draw() {
    super.draw();

    processing.fill(0);

    float triangleX1 = xPos - diameter / 3;
    float triangleX2 = xPos + diameter / 3;
    float triangleY = yPos - diameter / 5;

    processing.triangle(triangleX1, triangleY, triangleX2, triangleY, xPos, yPos +
        diameter / 3);
  }


  /**
   * Defines the specific behavior of this team lead when the mouse is released.
   * If this lead is clicked, it activates all the agents in the lead's team.
   * If the team exists, all team members are selected upon release.
   */

  @Override
  public void mouseReleased() {

    if (isMouseOver()) {
      if (team != null) {
        team.selectAll();
      }
    }

  }

}
