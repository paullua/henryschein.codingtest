package com.paulland.henryschein.codingtest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Paul Land
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvControllerTest {

  @Autowired
  CsvController csvController;

  private static final String INPUT = "\"Patient Name\",\"SSN\",\"Age\",\"Phone Number\",\"Status\"\n" +
      "\"Prescott, Zeke\",\"542-51-6641\",21,\"801-555-2134\",\"Opratory=2,PCP=1\"\n" +
      "\"Goldstein, Bucky\",\"635-45-1254\",42,\"435-555-1541\",\"Opratory=1,PCP=1\"\n" +
      "\"Vox, Bono\",\"414-45-1475\",51,\"801-555-2100\",\"Opratory=3,PCP=2\"\n";

  private static final String OUTPUT = "[Patient Name] [SSN] [Age] [Phone Number] [Status]\n" +
      "[Prescott, Zeke] [542-51-6641] [21] [801-555-2134] [Opratory=2,PCP=1]\n" +
      "[Goldstein, Bucky] [635-45-1254] [42] [435-555-1541] [Opratory=1,PCP=1]\n" +
      "[Vox, Bono] [414-45-1475] [51] [801-555-2100] [Opratory=3,PCP=2]\n";

  private static final String CARRIAGE_RETURN_INPUT = "\"Patient Name\",\"SSN\",\"Age\",\"Phone Number\",\"Status\"\r\n" +
      "\"Prescott, Zeke\",\"542-51-6641\",21,\"801-555-2134\",\"Opratory=2,PCP=1\"\r\n" +
      "\"Goldstein, Bucky\",\"635-45-1254\",42,\"435-555-1541\",\"Opratory=1,PCP=1\"\r\n" +
      "\"Vox, Bono\",\"414-45-1475\",51,\"801-555-2100\",\"Opratory=3,PCP=2\"\r\n";

  private static final String CARRIAGE_RETURN_OUTPUT = "[Patient Name] [SSN] [Age] [Phone Number] [Status]\r\n" +
      "[Prescott, Zeke] [542-51-6641] [21] [801-555-2134] [Opratory=2,PCP=1]\r\n" +
      "[Goldstein, Bucky] [635-45-1254] [42] [435-555-1541] [Opratory=1,PCP=1]\r\n" +
      "[Vox, Bono] [414-45-1475] [51] [801-555-2100] [Opratory=3,PCP=2]\r\n";

  @Test
  public void simpleTest() {
    String localOutput = csvController.formatCsv(INPUT);
    Assert.assertEquals(OUTPUT, localOutput);
  }

  @Test
  public void carriageReturnTest() {
    String output = csvController.formatCsv(CARRIAGE_RETURN_INPUT);
    Assert.assertEquals(CARRIAGE_RETURN_OUTPUT, output);
  }

  @Test
  public void unclosedQuotesTest() {
    String input = "\"Land, Paul,555-555-1234";
    String output = csvController.formatCsv(input);
    Assert.assertEquals("[Land, Paul,555-555-1234]", output);
  }

  @Test
  public void noQuotesTest() {
    String input = "col1,col2,col3,col4\n1,2,3,4";
    String output = csvController.formatCsv(input);
    Assert.assertEquals("[col1] [col2] [col3] [col4]\n[1] [2] [3] [4]", output);
  }
}
