package org.github.jbleduigou;

import javax.swing.*;

public class ErrorHandler {

  private final JFrame frame;

  public ErrorHandler(JFrame frame) {
    this.frame = frame;
  }

  public void displayErrorMessage(String message) {
    JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    System.exit(-1);
  }
}
