package org.github.jbleduigou;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CommandLineInterfaceTest {

  @Test
  public void includeHeaderShouldBeFalse() {
    String[] arguments = {"--no-header"};
    CommandLineInterface cli = new CommandLineInterface(arguments);
    assertThat(cli.includeHeader(), is(false));
  }

  @Test
  public void includeHeaderShouldBeTrue() {
    String[] arguments = {"--verbose"};
    CommandLineInterface cli = new CommandLineInterface(arguments);
    assertThat(cli.includeHeader(), is(true));
  }
}
