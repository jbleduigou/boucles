package org.github.jbleduigou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

class TimeCodeParser {

  List<Boucle> parse(String text) throws IOException {
    LinkedList<Boucle> boucles = new LinkedList<>();
    BufferedReader bufferedReader = new BufferedReader(new StringReader(text.trim()));
    int currentLoop = 1;
    String line = "";
    String currentTimeCode = null;
    String previousTimeCode = null;

    while (bufferedReader.ready() && line != null) {
      line = bufferedReader.readLine();
      if (line != null && line.startsWith(currentLoop + " ")) {
        if (currentLoop == 1) {
          previousTimeCode = line.substring((currentLoop + " ").length());
        } else {
          currentTimeCode = line.substring((currentLoop + " ").length());
          boucles.add(new Boucle(currentLoop - 1, previousTimeCode, currentTimeCode));
          previousTimeCode = currentTimeCode;
        }
        currentLoop++;
      }
    }

    boucles.add(new Boucle(currentLoop - 1, currentTimeCode, plusOneMinute(currentTimeCode)));
    return boucles;
  }


  static String plusOneMinute(String currentTimeCode) {
    if (currentTimeCode == null) {
      return null;
    }
    String[] values = currentTimeCode.split(":");
    if ("59".equals(values[1])) {
      return String.format("%02d", Integer.valueOf(values[0]) + 1) + ":" + "00" + ":" + values[2] + ":" + values[3];
    } else {
      return values[0] + ":" + String.format("%02d", Integer.valueOf(values[1]) + 1) + ":" + values[2] + ":" + values[3];
    }
  }
}
