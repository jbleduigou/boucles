package org.github.jbleduigou;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class Writer {

  private final boolean includeHeader;
  private final char delimiter;

  public Writer(boolean includeHeader) {
    this(includeHeader, ',');
  }

  private Writer(boolean includeHeader, char delimiter) {
    this.includeHeader = includeHeader;
    this.delimiter = delimiter;
  }

  void write(String filename, Collection<Boucle> boucles) throws IOException {
    CSVFormat csvFormat = getCsvFormat();
    CSVPrinter printer = csvFormat.print(new FileWriter(new File(filename)));
    for (Boucle boucle : boucles) {
      printer.printRecord(boucle.getIndex(), boucle.getTimeCodeIn(), boucle.getTimeCodeOut());
    }
    printer.close();
  }

  private CSVFormat getCsvFormat() {
    CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(delimiter);
    if (includeHeader) {
      return csvFormat.withHeader("Boucle", "Timecode In", "Timecode Out");
    }
    return csvFormat;
  }
}
