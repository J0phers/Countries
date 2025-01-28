/*
Author: Jophie Chan
Date: 1/23/25
Description: This class sets up the next, quiz, and review button of the game
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Main {

  // Array to hold 10 Country objects
  private Country[] countryArray = new Country[10];  
  // Index to track the current country being displayed
  private int index = 0;

  // GUI elements
  private JFrame jFrame = new JFrame("Countries");  // The main window frame
  private ImageIcon img;  // To hold the image icon
  private JLabel imageLabel;  // Label to display the image
  private JLabel outputLabel;  // Label to display the output text
  private JTextField input;  // Text field for user input
  
  public static void main(String[] args) {
    // Create the GUI
    Main gui = new Main();
    gui.loadCountries();  // Load countries from the data file
    gui.showCountry();  // Show the first country's details
  }

  /* 
   * loadCountries() reads in the data from the countries-data.csv file and fills in the countryArray with data.
   * The data file should contain information about 10 countries (name, capital, image filename, and other data).
   */
  public void loadCountries() {
    // Open the data file - ensure the file path is correct
    File file = new File("/workspaces/Countries/workspace/countries-data.csv");
    Scanner scan = null;
    try {
      scan = new Scanner(file);  // Try to read the file
    } catch(FileNotFoundException e) {
      System.out.println("File not found");  // Error if file is not found
    }
    
    // Read data from the file and populate the countryArray
    for (int i = 0; i < countryArray.length; i++) {
      String input = scan.nextLine();  // Read each line of the file
      String[] data = input.split(",");  // Split the line by commas
      System.out.println("Read in " + data[0]);  // Print the country name being read
      // Create a new Country object with the data read from the file
      Country c = new Country(data[0], data[1], data[2], data[3]);
      // Store the created Country object in the array
      countryArray[i] = c;
    }
  }

  /* 
   * showCountry() displays the image of the country currently at the given index in the countryArray.
   * It updates the image in the GUI using the image file specified in the country object.
   */
  public void showCountry() {
    // Get the country at the current index from the countryArray
    Country currentCountry = countryArray[index];
    
    // Get the image file name from the current country object
    String imagefile = currentCountry.getImageFile();
    // Create a new ImageIcon using the image file
    img = new ImageIcon("/workspaces/Countries/workspace/" + imagefile);
    // Set the image icon to the imageLabel
    imageLabel.setIcon(img);
  }

  /* 
   * nextButtonClick() handles the click of the "Next" button.
   * It increments the index to show the next country. If the index exceeds 9, it wraps around to 0.
   * It clears the output label and calls showCountry() to update the country displayed.
   */
  public void nextButtonClick() {
    index++;  // Increment the index to show the next country

    // If index exceeds 9, reset it back to 0 (wrap around)
    if (index > 9) {
      index = 0;
    }

    outputLabel.setText("");  // Clear the output label
    showCountry();  // Display the country corresponding to the new index
    input.setText("");  // Clear the text input field

    // Get the current country and set the output label to ask for the capital. 
    Country currentCountry = countryArray[index];
    outputLabel.setText("What is the capital of " + currentCountry.getName() + "?");
  }

  /* 
   * reviewButtonClick() handles the click of the "Review" button.
   * It prints the current country's details (using toString()) to both the console and the outputLabel.
   */
  public void reviewButtonClick() {
    System.out.println(countryArray[index]);  // Print country details to the console
    outputLabel.setText(countryArray[index].toString());  // Display the country details in the outputLabel
  }

  /* 
   * quizButtonClick() handles the click of the "Quiz" button.
   * It asks a quiz question based on the current country and checks if the user's answer is correct.
   * It compares the user's input with the country's capital and provides feedback.
   */
  public void quizButtonClick() {
    Country currentCountry = countryArray[index];  // Get the current country
    String userAnswer = input.getText();  // Get the user's answer from the input field

    // Check if the user's answer matches the country's capital (case-insensitive)
    if (userAnswer.equalsIgnoreCase(currentCountry.getCapital())) {
      outputLabel.setText("Correct!");  // If correct, display "Correct!"
    } else {
      // If incorrect, display the correct answer
      outputLabel.setText("Incorrect. The correct answer is: " + currentCountry.getCapital());
    }
  }

  /* 
   * The Main() constructor initializes the GUI with buttons, labels, and input field.
   * It also sets up the layout and event listeners for button clicks.
   */
  public Main() {
    jFrame.setLayout(new FlowLayout());  // Set the layout of the frame to FlowLayout
    jFrame.setSize(500, 360);  // Set the size of the frame
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit the application when the window is closed

    // Create and add buttons for Review, Quiz, and Next actions
    JButton reviewButton = new JButton("Review");
    JButton quizButton = new JButton("Quiz");
    JButton newButton = new JButton("Next");
    jFrame.add(reviewButton);
    jFrame.add(quizButton);
    jFrame.add(newButton);

    // Create an ImageIcon for the world map (default image)
    img = new ImageIcon("worldmap.jpg");
    // Create a label to display the image
    imageLabel = new JLabel(img);
    // Create a label to display text output
    outputLabel = new JLabel();
    jFrame.add(imageLabel);  // Add the image label to the frame
    jFrame.add(outputLabel);  // Add the output label to the frame

    // Create a text field for user input (e.g., for quiz answers)
    input = new JTextField(30);
    jFrame.add(input);  // Add the input field to the frame

    // Add a KeyListener to trigger the quiz when the Enter key is pressed
    input.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {  // If Enter key is pressed
          quizButtonClick();  // Call the quizButtonClick() method
        }
      }
    });

    jFrame.setVisible(true);  // Make the frame visible

    // Add ActionListeners for the buttons to handle button clicks
    reviewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        reviewButtonClick();  // Call reviewButtonClick() when Review button is clicked
      }
    });
    quizButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        quizButtonClick();  // Call quizButtonClick() when Quiz button is clicked
      }
    });
    newButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        nextButtonClick();  // Call nextButtonClick() when Next button is clicked
      }
    });
  }

}
