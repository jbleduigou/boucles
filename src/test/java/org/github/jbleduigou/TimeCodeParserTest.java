package org.github.jbleduigou;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class TimeCodeParserTest {

  private final String sampleText = "Croisillé de mon film datant du 28/11/1984\n" +
          "Page 1 de 1C:\\Users\\jbleduig\\monfilm.mos\n" +
          "1 01:01:54:06\n" +
          "2 01:02:22:16\n" +
          "3 01:02:29:05\n" +
          "4 01:02:33:15";

  @Test
  public void parse() throws IOException {
    List<Boucle> result = new TimeCodeParser().parse(sampleText);

    assertThat(result.size(), is(4));

    assertThat(result.get(0).getIndex(), is(1));
    assertThat(result.get(0).getTimeCodeIn(), is("01:01:54:06"));
    assertThat(result.get(0).getTimeCodeOut(), is("01:02:22:16"));

    assertThat(result.get(1).getIndex(), is(2));
    assertThat(result.get(1).getTimeCodeIn(), is("01:02:22:16"));
    assertThat(result.get(1).getTimeCodeOut(), is("01:02:29:05"));

    assertThat(result.get(2).getIndex(), is(3));
    assertThat(result.get(2).getTimeCodeIn(), is("01:02:29:05"));
    assertThat(result.get(2).getTimeCodeOut(), is("01:02:33:15"));

    assertThat(result.get(3).getIndex(), is(4));
    assertThat(result.get(3).getTimeCodeIn(), is("01:02:33:15"));
    assertThat(result.get(3).getTimeCodeOut(), is("01:03:33:15"));
  }

  @Test
  public void plusOneMinuteShouldReturnNull() {
    String fixture = null;

    assertThat(TimeCodeParser.plusOneMinute(fixture), nullValue());
  }

  @Test
  public void plusOneMinuteShouldHandle59Minutes() {
    String fixture = "01:59:54:06";

    assertThat(TimeCodeParser.plusOneMinute(fixture), is("02:00:54:06"));
  }
}
