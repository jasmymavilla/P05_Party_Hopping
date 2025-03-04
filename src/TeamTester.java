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


import java.util.ArrayList;


/**
 * A short tester class for verifying some of the Agent and Team behaviors in P05.
 */

public class TeamTester {


  /**
   * Verifies that an Agent’s initial position is set correctly upon creation.
   *
   * @return true if both agents are created with correct coordinates; false otherwise
   */

  public static boolean testAgentInitialPosition() {

    Agent agent1 = new Lead(50, 100);
    Agent agent2 = new Lead(200, 300);

    return agent1.getX() == 50 && agent1.getY() == 100
        && agent2.getX() == 200 && agent2.getY() == 300;

  }


  /**
   * Verifies that an Agent moves correctly when given a destination.
   *
   * @return true if agent movement behavior is correct; false otherwise
   */

  public static boolean testAgentMovement() {

    Agent agent = new Lead(50, 50);
    agent.setDestination(100, 100);
    agent.move();

    int newX = (int) agent.getX();
    int newY = (int) agent.getY();

    boolean movedCloser = (Math.abs(newX - 100) < Math.abs(50 - 100)) &&
        (Math.abs(newY - 100) < Math.abs(50 - 100));

    return movedCloser && (newX != 100 || newY != 100);

  }


  /**
   * Verifies that an Agent without a destination remains stationary.
   *
   * @return true if agent remains stationary when no destination is set; false otherwise
   */

  public static boolean testAgentStationary() {

    Agent agent = new Lead(50, 50);
    int initialX = (int) agent.getX();
    int initialY = (int) agent.getY();
    agent.isMoving();

    return agent.getX() == initialX && agent.getY() == initialY;

  }


  /**
   * Verifies that creating a Team with multiple Leads throws an IllegalArgumentException.
   *
   * @return true if the correct exception is thrown; false otherwise
   */

  public static boolean testMultipleLeadsException() {

    try {
      ArrayList<Agent> agents = new ArrayList<>();
      agents.add(new Lead(50, 50));
      agents.add(new Lead(200, 300));

      new Team(1, agents);
      return false;
    } catch (IllegalStateException e) {
      return true;
    } catch (Exception e) {
      return false;
    }

  }


  /**
   * Verifies behavior around empty teams.
   *
   * @return true if team behaves as expected with empty teams; false otherwise
   */

  public static boolean testEmptyTeam() {

    try {
      ArrayList<Agent> agents = new ArrayList<>();
      new Team(1, agents);
      return false;
    } catch (IllegalArgumentException e) {
    }

    ArrayList<Agent> agents = new ArrayList<>();
    agents.add(new Lead(50, 50));
    Team team = new Team(1, agents);

    if (team.getTeamSize() != 1) {
      return false;
    }

    team.removeMember(agents.get(0));
    return team.getTeamSize() == 0;

  }


  /**
   * Verifies that a Team can be created successfully with exactly one Lead.
   *
   * @return true if Team creation succeeds with correct composition; false otherwise
   */

  public static boolean testValidTeamCreation() {

    ArrayList<Agent> agents = new ArrayList<>();
    agents.add(new Lead(50, 50));

    Team team = new Team(1, agents);
    if (team.getTeamSize() != agents.size()) {
      return false;
    }

    if (!team.hasLead()) {
      return false;
    }

    return true;

  }


  /**
   * Verifies that a new Agent can be added to an existing Team.
   *
   * @return true if Agent is successfully added to Team; false otherwise
   */

  public static boolean testAddAgentToTeam() {

    ArrayList<Agent> agents = new ArrayList<>();
    agents.add(new Lead(50, 50));
    Team team = new Team(1, agents);
    Agent newAgent = new Lead(200, 250);

    team.addMember(newAgent);

    if (team.getTeamSize() != 2) {
      return false;
    }

    if (!team.contains(newAgent)) {
      return false;
    }

    return true;

  }


  /**
   * Verifies that Team’s center coordinates are calculated correctly.
   *
   * @return true if center coordinates are calculated correctly; false otherwise
   */

  public static boolean testTeamCenter() {

    ArrayList<Agent> agents = new ArrayList<>();
    agents.add(new Lead(50, 50));
    agents.add(new Lead(100, 100));
    agents.add(new Lead(200, 200));

    Team team = new Team(1, agents);
    double expectedCenterX = (50 + 100 + 200) / 3.0;
    double expectedCenterY = (50 + 100 + 200) / 3.0;

    if (team.getCenterX() != expectedCenterX || team.getCenterY() != expectedCenterY) {
      return false;
    }

    Agent newAgent = new Lead(300, 300);
    team.addMember(newAgent);
    expectedCenterX = (50 + 100 + 200 + 300) / 4.0;
    expectedCenterY = (50 + 100 + 200 + 300) / 4.0;

    return team.getCenterX() == expectedCenterX && team.getCenterY() == expectedCenterY;

  }


  /**
   * Runs all tests and displays results
   *
   * @param args unused
   */

  public static void main(String[] args) {

    System.out.println("-----------------------------------------------------------");
    System.out.println("testAgentInitialPosition: " + (testAgentInitialPosition() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testAgentMovement: " + (testAgentMovement() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testAgentStationary: " + (testAgentStationary() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testMultipleLeadsException: " + (testMultipleLeadsException() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testEmptyTeam: " + (testEmptyTeam() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testValidTeamCreation: " + (testValidTeamCreation() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testAddAgentToTeam: " + (testAddAgentToTeam() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testTeamCenter: " + (testTeamCenter() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");

  }


}
