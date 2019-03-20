package org.github.jbleduigou;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReaderTest {

  private Reader reader = new Reader();

  @Test
  public void read() throws IOException {
    ClassLoader classLoader = getClass().getClassLoader();
    String path = new File(classLoader.getResource("junit.pdf").getFile()).getAbsolutePath();

    assertThat(reader.read(path), is("Junit \n"));
  }
}
