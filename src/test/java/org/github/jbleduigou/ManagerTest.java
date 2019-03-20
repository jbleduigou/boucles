package org.github.jbleduigou;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ManagerTest {

  private Manager manager;

  @Mock
  private Reader reader;

  @Mock
  private Writer writer;

  @Mock
  private ErrorHandler errorHandler;

  @Mock
  private TimeCodeParser timeCodeParser;

  @Before
  public void setupMocks() throws Exception {
    manager = new Manager(reader, writer, errorHandler, timeCodeParser);

    when(reader.read("junit.pdf")).thenReturn("Croisillé de mon film datant du 28\n1 01:01:54:06");
    when(timeCodeParser.parse("Croisillé de mon film datant du 28\n1 01:01:54:06")).thenReturn(singletonList(new Boucle(1, "01:01:54:06", "01:01:57:39")));
  }

  @Test
  public void processFileShouldDisplayErrorGivenInputNotPdf() {
    manager.processFile("junit.doc");

    verify(errorHandler).displayErrorMessage("Input file should be a pdf file!");
    verifyNoMoreInteractions(reader, writer, errorHandler, timeCodeParser);
  }

  @Test
  public void processFileShouldDisplayErrorGivenReaderError() throws IOException {
    when(reader.read("junit.pdf")).thenThrow(new RuntimeException("Junit sample exception"));

    manager.processFile("junit.pdf");

    verify(reader).read("junit.pdf");
    verify(errorHandler).displayErrorMessage("Junit sample exception");
    verifyNoMoreInteractions(reader, writer, errorHandler, timeCodeParser);
  }

  @Test
  public void processFileShouldCallMocks() throws Exception {
    manager.processFile("junit.pdf");

    verify(reader).read("junit.pdf");
    verify(timeCodeParser).parse("Croisillé de mon film datant du 28\n1 01:01:54:06");
    verify(writer).write("junit.csv", singletonList(new Boucle(1, "01:01:54:06", "01:01:57:39")));

    verifyNoMoreInteractions(reader, writer, errorHandler, timeCodeParser);
  }
}
