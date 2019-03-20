package org.github.jbleduigou;

import java.util.Objects;

public class Boucle {

  private final int index;
  private final String timeCodeIn;
  private final String timeCodeOut;

  public Boucle(int index, String timeCodeIn, String timeCodeOut) {
    this.index = index;
    this.timeCodeIn = timeCodeIn;
    this.timeCodeOut = timeCodeOut;
  }

  public int getIndex() {
    return index;
  }

  public String getTimeCodeIn() {
    return timeCodeIn;
  }

  public String getTimeCodeOut() {
    return timeCodeOut;
  }


  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    Boucle boucle = (Boucle) other;
    return index == boucle.index &&
            Objects.equals(timeCodeIn, boucle.timeCodeIn) &&
            Objects.equals(timeCodeOut, boucle.timeCodeOut);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getIndex(), getTimeCodeIn(), getTimeCodeOut());
  }
}
