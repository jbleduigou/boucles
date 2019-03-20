package org.github.jbleduigou;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BoucleTest {

  @Test
  public void equalsShouldReturnTrueGivenSameInstance() {
    Boucle boucle = new Boucle(1, "01:01:54:06", "01:01:57:39");
    assertThat(boucle.equals(boucle), is(true));
  }

  @Test
  public void equalsShouldReturnTrueGivenAllFieldsSame() {
    Boucle boucle1 = new Boucle(1, "01:01:54:06", "01:01:57:39");
    Boucle boucle2 = new Boucle(1, "01:01:54:06", "01:01:57:39");
    assertThat(boucle1.equals(boucle2), is(true));
  }

  @Test
  public void equalsShouldReturnFalseGivenNull() {
    Boucle boucle = new Boucle(1, "01:01:54:06", "01:01:57:39");
    assertThat(boucle.equals(null), is(false));
  }

  @Test
  public void equalsShouldReturnFalseGivenDifferentClass() {
    Boucle boucle = new Boucle(1, "01:01:54:06", "01:01:57:39");
    assertThat(boucle.equals(new Integer(1337)), is(false));
  }

  @Test
  public void hashCodeShouldBeTheSame() {
    Boucle boucle1 = new Boucle(1, "01:01:54:06", "01:01:57:39");
    assertThat(boucle1.hashCode(), is(-1762475587));

    Boucle boucle2 = new Boucle(1, "01:01:54:06", "01:01:57:39");
    assertThat(boucle2.hashCode(), is(-1762475587));

    assertThat(boucle1, is(boucle2));
    assertThat(boucle2, is(boucle1));
  }
}
