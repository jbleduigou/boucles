package org.github.jbleduigou;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.util.List;

public class Manager {

  private static final StringBuilder PROCESSING_LOG = new StringBuilder();
  private static JFrame frame;

  private final Reader reader;
  private final Writer writer;
  private final ErrorHandler errorHandler;
  private final TimeCodeParser timeCodeParser;

  public Manager(Reader reader, Writer writer, ErrorHandler errorHandler, TimeCodeParser timeCodeParser) {
    this.reader = reader;
    this.writer = writer;
    this.errorHandler = errorHandler;
    this.timeCodeParser = timeCodeParser;
  }

  public static void main(String[] args) {
    frame = new JFrame("Boucles");

    CommandLineInterface cli = new CommandLineInterface(args);

    Manager manager = new Manager(new Reader(), new Writer(cli.includeHeader()), new ErrorHandler(frame), new TimeCodeParser());
    File[] files = manager.selectInputFiles();

    for (File file : files) {
      manager.processFile(file.getAbsolutePath());
    }

    JOptionPane.showMessageDialog(frame, PROCESSING_LOG.toString());
    System.exit(0);
  }

  protected void processFile(String inputFileName) {
    if (!inputFileName.endsWith(".pdf")) {
      errorHandler.displayErrorMessage("Input file should be a pdf file!");
      return;
    }

    try {
      String ouputFileName = inputFileName.replace(".pdf", ".csv");
      String text = reader.read(inputFileName);
      System.out.println("Processing file " + inputFileName);
      List<Boucle> boucles = timeCodeParser.parse(text);
      writer.write(ouputFileName, boucles);
      System.out.println("Processed file " + inputFileName + ", found " + boucles.size() + " timecodes.");
      PROCESSING_LOG.append("Processed file ").append(inputFileName).append(", found ").append(boucles.size()).append(" timecodes.").append('\n');
      System.out.println("Output written to " + ouputFileName);
      PROCESSING_LOG.append("Output written to ").append(ouputFileName).append('\n');
    } catch (Exception exception) {
      errorHandler.displayErrorMessage(exception.getMessage());
    }
  }

  private File[] selectInputFiles() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception exception) {
      errorHandler.displayErrorMessage("Input file should be a pdf file!");
    }

    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    JFileChooser chooser = new JFileChooser();
    chooser.setMultiSelectionEnabled(true);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files", "pdf");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(frame);
    if (returnVal == 0) {
      return chooser.getSelectedFiles();
    } else {
      errorHandler.displayErrorMessage("One input file should be selected");
      return new File[0];
    }
  }

}
