package org.github.jbleduigou;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WriterTest {

  @Test
  public void shouldWriteToCsvFileWithHeader() throws IOException {
    Writer writer = new Writer(true);

    ClassLoader classLoader = getClass().getClassLoader();
    String path = new File(classLoader.getResource("junit.pdf").getFile()).getAbsolutePath().replace(".pdf", System.nanoTime()+".csv");

    writer.write(path, singletonList(new Boucle(1, "01:02:22:16", "01:05:21:16")));

    BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path));
    assertThat(bufferedReader.ready(), is(true));
    assertThat(bufferedReader.readLine(), is("Boucle,Timecode In,Timecode Out"));

    assertThat(bufferedReader.ready(), is(true));
    assertThat(bufferedReader.readLine(), is("1,01:02:22:16,01:05:21:16"));

    assertThat(bufferedReader.ready(), is(false));

    bufferedReader.close();
  }

  @Test
  public void shouldWriteToCsvFileWithoutHeader() throws IOException {
    Writer writer = new Writer(false);

    ClassLoader classLoader = getClass().getClassLoader();
    String path = new File(classLoader.getResource("junit.pdf").getFile()).getAbsolutePath().replace(".pdf", System.nanoTime()+".csv");

    writer.write(path, singletonList(new Boucle(1, "01:02:22:16", "01:05:21:16")));

    BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path));

    assertThat(bufferedReader.ready(), is(true));
    assertThat(bufferedReader.readLine(), is("1,01:02:22:16,01:05:21:16"));

    assertThat(bufferedReader.ready(), is(false));

    bufferedReader.close();
  }
}
