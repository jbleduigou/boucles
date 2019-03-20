package org.github.jbleduigou;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.IOException;

class Reader {

  String read(String filename) throws IOException {
    PDDocument document = PDDocument.load(filename);
    String text = (new PDFTextStripper()).getText(document);
    document.close();
    return text;
  }
}
