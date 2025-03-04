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
import java.util.Random;
import processing.core.PApplet;


/**
 * Manages teams by allowing users to select agents and group them into teams.
 */

public class TeamManagementSystem extends PApplet {

  private Random randGen;
  private ArrayList<Clickable> objects;
  private ArrayList<Team> teams;
  private int bgColor;
  private int windowWidth = 800;
  private int windowHeight = 600;

  private boolean isSelecting;
  private int selectionStartX;
  private int selectionStartY;


  /**
   * Main method to launch the program using PApplet.
   */

  public static void main(String[] args) {

    PApplet.main("TeamManagementSystem");

  }


  /**
   * Configures the window size for the sketch.
   */

  @Override
  public void settings() {
    size(windowWidth, windowHeight);
  }


  /**
   * Sets up the initial state of the program, including the creation of agents and teams.
   */

  @Override
  public void setup() {

    smooth();
    imageMode(CENTER);
    randGen = new Random();
    objects = new ArrayList<Clickable>();
    teams = new ArrayList<Team>();
    bgColor = color(81, 125, 168);

    objects.add(new Party(200, 125, loadImage("cup.png")));
    objects.add(new Party(600, 150, loadImage("dice.png")));
    objects.add(new Party(400, 450, loadImage("ball.png")));

    Agent agent = new Lead(width / 2, height / 2);
    objects.add(agent);

  }


  /**
   * Draws the background, selection box (if selecting), all objects,
   * and the active teams with their team IDs.
   */

  @Override
  public void draw() {

    background(bgColor);

    if (isSelecting) {
      drawSelectionBox();
    }

    for (Clickable obj : objects) {
      obj.draw();
    }

    clearEmptyTeams();

    if (teams.size() > 0) {

      int yCoordinate = 20;
      textSize(16);
      for (Team team : teams) {
        if (team.isActive()) {
          fill(0, 255, 0);
        } else {
          fill(255);
        }
        text("Team " + team.getTeamID(), 10, yCoordinate);
        yCoordinate += 20;
      }
    }

  }


  /**
   * Clears any teams that no longer have any members.
   */

  public void clearEmptyTeams() {

    for (int i = teams.size() - 1; i >= 0; i--) {
      if (teams.get(i).members().isEmpty()) {
        teams.remove(i);
      }
    }

  }


  /**
   * Draws a selection box based on mouse drag.
   */

  public void drawSelectionBox() {

    fill(135, 185, 201);
    int x = min(selectionStartX, mouseX);
    int y = min(selectionStartY, mouseY);
    int width = abs(mouseX - selectionStartX);
    int height = abs(mouseY - selectionStartY);
    rect(x, y, width, height);

  }


  /**
   * Handles the mouse press event. Starts the selection process or selects objects.
   */

  @Override
  public void mousePressed() {

    for (Clickable obj : objects) {
      if (obj.isMouseOver()) {
        obj.mousePressed();
        return;
      }
    }
    isSelecting = true;
    selectionStartX = mouseX;
    selectionStartY = mouseY;

  }


  /**
   * Handles the mouse release event. Finalizes selection and team creation.
   */

  @Override
  public void mouseReleased() {

    if (isSelecting) {
      Team team = detectTeam();
      if (team == null) {
        createTeamFromSelection();
      }
      isSelecting = false;
    }
    clearEmptyTeams();

  }


  /**
   * Detects the team of the selected agents. If they belong to different teams, returns null.
   *
   * @return the detected team or null if no valid team is found.
   */

  public Team detectTeam() {

    ArrayList<Agent> selectedAgents = new ArrayList<>();
    for (Clickable obj : objects) {
      if (obj instanceof Agent) {
        Agent agent = (Agent) obj;
        if (isWithinSelectionBox(agent)) {
          selectedAgents.add(agent);
        }
      }
    }

    if (selectedAgents.isEmpty()) return null;

    Team team = selectedAgents.get(0).getTeam();
    for (Agent agent : selectedAgents) {
      if (agent.getTeam() != team) return null;
    }

    return team;

  }


  /**
   * Checks if the agent is within the selected selection box.
   *
   * @param agent the agent to check
   * @return true if the agent is within the selection box, otherwise false
   */

  public boolean isWithinSelectionBox(Agent agent) {

    return agent.getX() >= min(selectionStartX, mouseX)
        && agent.getX() <= max(selectionStartX, mouseX)
        && agent.getY() >= min(selectionStartY, mouseY)
        && agent.getY() <= max(selectionStartY, mouseY);

  }


  /**
   * Creates a new team from the selected agents.
   */

  public void createTeamFromSelection() {

    ArrayList<Agent> selectedAgents = getAllSelectedAgents();
    if (!selectedAgents.isEmpty()) {
      createTeam(selectedAgents);
    }

  }


  /**
   * Creates a team with a random color and a list of selected agents.
   *
   * @param selected the list of agents to be included in the team
   */

  public void createTeam(ArrayList<Agent> selected) {

    int r = randGen.nextInt(256);
    int g = randGen.nextInt(256);
    int b = randGen.nextInt(256);
    int color = color(r, g, b);
    Team newTeam = new Team(color, selected);
    teams.add(newTeam);

  }


  /**
   * Retrieves all selected agents within the selection box.
   *
   * @return a list of selected agents
   */

  public ArrayList<Agent> getAllSelectedAgents() {

    ArrayList<Agent> agents = new ArrayList<>();
    int left = min(selectionStartX, mouseX);
    int right = max(selectionStartX, mouseX);
    int top = min(selectionStartY, mouseY);
    int bottom = max(selectionStartY, mouseY);

    for (Clickable obj : objects) {
      if (obj instanceof Agent) {
        Agent agent = (Agent) obj;
        if (agent.getX() >= left && agent.getX() <= right
            && agent.getY() >= top && agent.getY() <= bottom) {
          agents.add(agent);
        }
      }
    }

    return agents;

  }


  /**
   * Retrieves the currently active team.
   *
   * @return the active team, or null if no team is active
   */

  public Team getActiveTeam() {

    for (Team team : teams) {
      if (team.isActive()) {
        return team;
      }
    }
    return null;

  }


}
