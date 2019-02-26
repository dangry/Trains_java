package controllers;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertThat;

public class TextControllerTest {

  String fileName;
  TextController textController;

  @Before
  public void setupTestData() throws FileNotFoundException {
    fileName = "src/test/resources/testFile.txt";
    textController = new TextController(fileName);
  }

  @Test
  public void shouldProcessFileInstruction_1() {
    assertThat(
        textController.processFile(),
        CoreMatchers.hasItems("9", "5", "13", "22", "NO SUCH ROUTE", "2", "3", "9", "9", "7"));
  }
}
