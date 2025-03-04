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
 * Represents a team of agents, which can have multiple members, each with a unique role and position.
 * The team has a unique identifier and color for visual representation.
 */

public class Team {

  private static char idGenerator = 'A';
  private ArrayList<Agent> members;
  private final char TEAM_ID;
  private int color;


  /**
   * Constructs a Team using a list of agents and assigns it a unique identifier.
   *
   * @param color the color associated with this team
   * @param agents the list of agents that will be part of this team
   * @throws IllegalArgumentException if the agents list is empty or null
   * @throws IllegalStateException if the team already has a Lead member
   */

  public Team(int color, ArrayList<Agent> agents) {

    if (agents == null || agents.isEmpty()) {
      throw new IllegalArgumentException("Agent list cannot be empty");
    }

    int leadCount = 0;
    for (Agent agent : agents) {
      if (agent.isActive()) {
        leadCount++;
      }
    }
    if (leadCount > 1) {
      throw new IllegalStateException("A team can only have one Lead");
    }

    this.members = new ArrayList<>(agents);
    this.color = color;
    this.TEAM_ID = idGenerator++;

  }


  /**
   * Adds a new member to the team.
   *
   * @param a the agent to add to the team
   * @throws IllegalStateException if the team already has a Lead member and the agent is active
   */

  public void addMember(Agent a) throws IllegalStateException {

    if (a.isActive() && hasLead()) {
      throw new IllegalStateException("Team already has a Lead");
    }
    if (!members.contains(a)) {
      members.add(a);
    }

  }


  /**
   * Checks if the team contains the given agent.
   *
   * @param a the agent to check for in the team
   * @return true if the agent is part of this team, false otherwise
   */

  public boolean contains(Agent a) {

    return members.contains(a);

  }


  /**
   * Finds the center x-coordinate of the team,
   * defined as halfway between the leftmost and rightmost agents.
   *
   * @return the center x-coordinate of the team
   */

  public float getCenterX() {

    float leftMost = Float.MAX_VALUE;
    float rightMost = Float.MIN_VALUE;

    for (Agent agent : members) {
      float x = agent.getX();
      if (x < leftMost) {
        leftMost = x;
      }
      if (x > rightMost) {
        rightMost = x;
      }
    }
    return (leftMost + rightMost) / 2;

  }


  /**
   * Finds the center y-coordinate of the team,
   * defined as halfway between the topmost and bottommost agents.
   *
   * @return the center y-coordinate of the team
   */

  public float getCenterY() {

    float topMost = Float.MAX_VALUE;
    float bottomMost = Float.MIN_VALUE;

    for (Agent agent : members) {
      float y = agent.getY();
      if (y < topMost) {
        topMost = y;
      }
      if (y > bottomMost) {
        bottomMost = y;
      }
    }
    return (topMost + bottomMost) / 2;

  }


  /**
   * Returns the color associated with this team.
   *
   * @return the color of the team
   */

  public int getColor() {

    return color;

  }


  /**
   * Returns the unique identifier for this team.
   *
   * @return the team's unique ID character
   */

  public char getTeamID() {

    return TEAM_ID;

  }


  /**
   * Returns the total number of agents in the team.
   *
   * @return the size of the team
   */

  public int getTeamSize() {

    return members.size();

  }


  /**
   * Checks if this team currently has a Lead member.
   *
   * @return true if there is a Lead member, false otherwise
   */

  public boolean hasLead() {

    for (Agent agent : members) {
      if (agent.isActive()) {
        return true;
      }
    }
    return false;

  }


  /**
   * Checks if all members of the team are active (selected).
   *
   * @return true if all members are active, false otherwise
   */

  public boolean isActive() {

    for (Agent agent : members) {
      if (!agent.isMouseOver()) {
        return false;
      }
    }
    return true;

  }


  /**
   * Activates all members of the team.
   */

  public void selectAll() {

    for (Agent agent : members) {
      agent.toggleActive();
    }

  }


  /**
   * Sends all members of the team to a specific party location.
   *
   * @param p the Party to which the team should move
   */

  public void sendToParty(Party p) {

    float partyX = p.getX();
    float partyY = p.getY();

    for (Agent agent : members) {
      agent.setDestination((int) partyX, (int) partyY);
    }

  }


  /**
   * Removes the provided agent from the team.
   *
   * @param a the agent to remove from the team
   * @return true if the agent was successfully removed, false otherwise
   */

  public boolean removeMember(Agent a) {

    return members.remove(a);

  }


  /**
   * Updates the destination of all team members to form a line centered at the team's center.
   */

  public void lineUp() {

    if (members.size() == 1) {
      Agent agent = members.get(0);
      agent.setDestination((int) agent.getX(), (int) agent.getY());
      return;

    }

    float centerX = getCenterX();
    float centerY = getCenterY();
    int teamSize = getTeamSize();
    int diameter = 20;
    float spacing = diameter + 3;

    float totalWidth = (spacing * teamSize) - 3;
    float startX = centerX - (totalWidth / 2);

    for (int i = 0; i < teamSize; i++) {
      Agent agent = members.get(i);
      float newX = startX + (i * spacing);
      agent.setDestination((int) newX, (int) centerY);
    }

  }


}

