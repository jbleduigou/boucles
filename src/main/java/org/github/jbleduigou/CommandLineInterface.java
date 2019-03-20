package org.github.jbleduigou;

import java.util.Arrays;

public class CommandLineInterface {

  private final boolean noHeader;

  public CommandLineInterface(String[] arguments) {
    noHeader = Arrays.asList(arguments).contains("--no-header");
  }

  public boolean includeHeader() {
    return !noHeader;
  }
}
