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
public class csvControllerTest {

  @Autowired
  csvController csvController;

  private static final String INPUT = "\"Patient Name\",\"SSN\",\"Age\",\"Phone Number\",\"Status\"\n" +
      "\"Prescott, Zeke\",\"542-51-6641\",21,\"801-555-2134\",\"Opratory=2,PCP=1\"\n" +
      "\"Goldstein, Bucky\",\"635-45-1254\",42,\"435-555-1541\",\"Opratory=1,PCP=1\"\n" +
      "\"Vox, Bono\",\"414-45-1475\",51,\"801-555-2100\",\"Opratory=3,PCP=2\"\n";

  private static final String OUTPUT = "[Patient Name] [SSN] [Age] [Phone Number] [Status]\n" +
      "[Prescott, Zeke] [542-51-6641] [21] [801-555-2134] [Opratory=2,PCP=1]\n" +
      "[Goldstein, Bucky] [635-45-1254] [42] [435-555-1541] [Opratory=1,PCP=1]\n" +
      "[Vox, Bono] [414-45-1475] [51] [801-555-2100] [Opratory=3,PCP=2]\n";

  @Test
  public void simpleTest() {
    String localOutput = csvController.formatCsv(INPUT);
    Assert.assertEquals(OUTPUT, localOutput);
  }
}
