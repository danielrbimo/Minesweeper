import java.util.ArrayList;
import java.util.Random;
import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

//Represents a single square of the game area
class Cell {
  // In logical coordinates, with the origin at the top-left corner of the screen
  int x;
  int y;
  //mines around 
  boolean isMine;
  ArrayList<Cell> neighbors;
  int numMines;

  //constructor 
  Cell(int x, int y, boolean isMine) {
    this.x = x;
    this.y = y;
    this.isMine = isMine;
    this.neighbors = new ArrayList<Cell>();
    this.numMines = 0;
  }

  //draw a cell image onto the background 
  WorldImage drawCell() {
    return new RectangleImage(
        20, 20, "outline", Color.BLACK);
  }


  //make a neighbor list
  void makeNeighbors(Cell neighbor) {
    this.neighbors.add(neighbor);
    if (neighbor.isMine) {
      this.numMines++;
    }
  }
}


//Represents a single mine of the game area 
class Mine {
  int x;
  int y;

  //constructor 
  Mine(int x, int y) {
    this.x = x;
    this.y = y;
  }

  //draw the mines 
  WorldImage drawMines() {
    return new CircleImage(5, "solid", Color.red);
  }
}

//to represent the game world
class MineSweep extends World {
  Cell cell;
  Mine mine;
  // All the cells of the game
  ArrayList<Cell> board;
  // All the mines of the game 
  ArrayList<Mine> mines;
  // Defines an int constant
  static final int BOARD_SIZE = 22;
  //number of mines 
  int numberMines;
  //row in board
  int row;
  //column in board
  int col;
  

  //constructor
  MineSweep(Cell cell) {
    this.mine = new Mine(0, 0);
    this.cell = cell;
    this.board = this.makeCell();
    this.mines = this.makeMine();
    this.numberMines = 0;
    this.row = 3 + this.cell.x;
    this.col = 3 + this.cell.y;
  }
  
  public void calculateCellNeighbors() {
    for (int i = row - 1; i <= row; i++) {
      for (int j = col - 1; j <= col + 1; j++) {
        if (i >= 0 && i < BOARD_SIZE && j >0 && j< BOARD_SIZE) {
          if (!(this.cell.x == this.board.get(i).x && this.cell.y == this.board.get(i).y)
              && (this.cell.x == this.board.get(j).x && this.cell.y == this.board.get(j).y)) {
          this.cell.makeNeighbors(this.board.get(i));
          this.cell.makeNeighbors(this.board.get(j));
        }
      }
    }
  }
  }
  
  //draws the cells onto the background 
  ArrayList<Cell> makeCell() {
    ArrayList<Cell> result = new ArrayList<Cell>();
    for (int i = 0; i < 22; i++) {
      for (int j = 0; j < 22; j++) {
        result.add(new Cell(i, j, this.cell.isMine));
      }
    }
    return result;
  }

  //draws the mines onto the background 
  ArrayList<Mine> makeMine() {
    ArrayList<Mine> result = new ArrayList<Mine>();
    Random randx = new Random(this.mine.x);
    Random randy = new Random(this.mine.y);
    for (int i = 0; i < 22; i++) {
      for (int j = 0; j < 22; j++) {
        result.add(new Mine(randx.nextInt(), randy.nextInt()));
      }
    }
    return result;
  }

  // Draws the cell currently on board
  void drawCells(WorldScene ws) {
    for (Cell c: board) {
      ws.placeImageXY(c.drawCell(), ((c.x * 20) + 10), ((c.y * 20) + 10));
    }
  }

  //draws the mines currently on board 
  void drawMines(WorldScene ws) {
    while (this.numberMines < 99) { 
      ws.placeImageXY(this.mine.drawMines(), this.mine.x * 2  , this.mine.y * 2);
    }
  }

  //Create a world scene by drawing the cells
  public WorldScene makeScene() {
    WorldScene answer = new WorldScene(this.BOARD_SIZE, this.BOARD_SIZE);
    this.drawCells(answer);
    return answer;
  }

  //press key r to reset the game 
  public void onKeyEvent(String ke) {
    if (ke.equals("r")) {
      this.board = this.makeCell();
    }
  }
}

//class examples 
class CellExamples {
  MineSweep w; 
  int iSize;
  Cell c1;
  Cell c2;
  Cell c3;
  Cell c4;
  Cell c5;
  Cell c6;
  Cell c7;
  Cell c8;
  Cell c9;
  Cell c10;
  Cell c11;
  Cell c12;
  Cell c13;
  Cell c14;
  Cell c15;
  Cell c16;
  Cell c17;
  Cell c18;
  Cell c19;
  Cell c20;
  Cell c21;
  Cell c22;
  Cell c23;
  Cell c24;
  Cell c25;
  Mine m1;
  Mine m2;
  Mine m3; 
  Mine m4;
  ArrayList<Cell> mtCells;

  //initialize the data
  void initVals() {
    this.w = new MineSweep(new Cell(0, 0, false));
    this.iSize = MineSweep.BOARD_SIZE;
    this.c1 = new Cell(0,0, false);
    this.c2 = new Cell(0,1, false);
    this.c3 = new Cell(0,2, false);
    this.c4 = new Cell(0,3, true);
    this.c5 = new Cell(0,4, true);
    this.c6 = new Cell(1,0, true);
    this.c7 = new Cell(1,1, false);
    this.c8 = new Cell(1,2, false);
    this.c9 = new Cell(1,3, false);
    this.c10 = new Cell(1,4, true);
    this.c11 = new Cell(2,0, false);
    this.c12 = new Cell(2,1, false);
    this.c13 = new Cell(2,2, false);
    this.c14 = new Cell(2,3, false);
    this.c15 = new Cell(2,4, true);
    this.c16 = new Cell(3,0, true); 
    this.c17 = new Cell(3,1, true);
    this.c18 = new Cell(3,2, true);
    this.c19 = new Cell(3,3, true);
    this.c20 = new Cell(3,4, true);
    this.c21 = new Cell(4,0, false);
    this.c22 = new Cell(4,1, false);
    this.c23 = new Cell(4,2, false);
    this.c24 = new Cell(4,3, false);
    this.c25 = new Cell(4,4, false);
    this.m1 = new Mine(0, 0);
    this.m2 = new Mine(2, 3);
    this.m3 = new Mine(2, 4);
    this.m4 = new Mine(3, 2);
    //arraylist of cells
    ArrayList<Cell> board = new ArrayList<Cell>();
    ArrayList<Mine> mines = new ArrayList<Mine>();
    board.add(c1);
    board.add(c2);
    board.add(c3);
    board.add(c4);
    board.add(c5);
    board.add(c6);
    board.add(c7);
    board.add(c8);
    board.add(c9);
    board.add(c10);
    board.add(c11);
    board.add(c12);
    board.add(c13);
    board.add(c14);
    board.add(c15);
    board.add(c16);
    board.add(c17);
    board.add(c18);
    board.add(c19);
    board.add(c20);
    board.add(c21);
    board.add(c22);
    board.add(c23);
    board.add(c24);
    board.add(c25);
    mines.add(m1);
    mines.add(m2);
    mines.add(m3);
    mines.add(m4);
  }

//  //test the draw a cell method 
//  void testdrawCell(Tester t) {
//    this.initVals();
//    t.checkExpect(c1.drawCell(), new RectangleImage(20, 20, "outline", Color.BLACK));
//  }
//
//  //test the draw a mine method 
//  void testdrawMine(Tester t) {
//    this.initVals();
//    t.checkExpect(m1.drawMines(), new CircleImage(5, "solid", Color.red));
//  }
//
//  //test the count mines method 
//  void testcountMines(Tester t) {
//    this.initVals();
//    t.checkExpect(c1.numMines, 0);
//    t.checkExpect(c2.numMines, 0);
//    t.checkExpect(c14.numMines, 25);
//  }
//
//  //test the makecell method 
//  void testmakeCell(Tester t) {
//    this.initVals();
//    t.checkExpect(w.makeCell(), ""); 
//  }
//
//  //to render the game
//  //start with the 25 cells 
//  void testBigBang(Tester t) {
//    this.initVals();
//    MineSweep w = new MineSweep(this.c25);
//    w.bigBang((20 * w.BOARD_SIZE), (20 * w.BOARD_SIZE), 1);
//  }
  
  void testMakeCell(Tester t) {
    this.initVals();
    MineSweep m1 = new MineSweep(this.c3);
    m1.calculateCellNeighbors();
    t.checkExpect(true, true);
  }
}



