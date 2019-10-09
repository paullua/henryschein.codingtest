package com.paulland.henryschein.codingtest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Paul Land
 */
@RestController
@RequestMapping("/csv")
public class CsvController {

  @GetMapping("/format")
  public String formatCsv(@RequestParam("stringCsv") String csv) {
    String formattedString = "";

    while (!csv.isEmpty()) {
      // Handles values enclosed in quotes
      if (csv.charAt(0) == '"') {
        String[] temp = substringBetweenQuotes(csv);
        formattedString += "[" + temp[0] + "]";
        csv = temp[1];
      }

      // Handles new-line and carriage-return
      else if (csv.charAt(0) == '\r' || csv.charAt(0) == '\n') {
        int i = 0;
        while (i < csv.length() && (csv.charAt(i) == '\r' || csv.charAt(i) == '\n')) {
          formattedString += csv.charAt(i);
          i++;
        }
        csv = csv.substring(i);
      }

      // Handles values not enclosed with quotes
      else {
        formattedString += "[";

        int i = 0;
        while (i < csv.length() && csv.charAt(i) != ',' && csv.charAt(i) != '\r' && csv.charAt(i) != '\n') {

          formattedString += csv.charAt(i);
          i++;
        }
        csv = csv.substring(i);

        formattedString += "]";
      }

      // Replace the commas after values with spaces
      if (!csv.isEmpty() && csv.charAt(0) == ',') {
        formattedString += " ";
        csv = csv.substring(1);
      }
    }

    return formattedString;
  }

  private String[] substringBetweenQuotes(String string) {
    // Move past the first quote
    if (string.charAt(0) == '"') {
      string = string.substring(1);
    }

    String returnString = "";
    int i = 0;

    while (i < string.length() && !string.substring(i, i + 1).equals("\"")) {
      returnString += string.charAt(i);
      i++;
    }

    if (i < string.length()) {
      return new String[]{returnString, string.substring(i + 1)};
    }

    return new String[]{returnString, string.substring(i)};
  }

}
