package com.paulland.henryschein.codingtest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Paul Land
 */
@RestController
@RequestMapping("/csv")
public class csvController {

  @GetMapping("/{stringCsv}")
  public String formatCsv(@PathVariable("stringCsv") String csv) {
    String formattedString = "";

    while(!csv.isEmpty()) {
      // Handles values enclosed in quotes
      if (csv.charAt(0) == '"') {
        String[] temp = substringBetweenQuotes(csv);
        formattedString += "[" + temp[0] + "]";
        csv = temp[1];
      }

      // Handles new-line and carriage-return
      else if (csv.charAt(0) =='\r' || csv.charAt(0) == '\n') {
        while (!csv.isEmpty() && (csv.charAt(0) == '\r' || csv.charAt(0) == '\n')) {
          formattedString += csv.charAt(0);
          csv = csv.substring(1);
        }
      }

      // Handles values not enclosed with quotes
      else {
        formattedString += "[";

        while (csv.charAt(0) != ',') {
          formattedString += csv.charAt(0);
          csv = csv.substring(1);
        }

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

    String returnString = "";
    int i = 0;

    // Move past the first quote
    if (string.charAt(0) == '"') {
      string = string.substring(1);
    }

    while (i + 1 <= string.length() && !string.substring(i, i + 1).equals("\"")) {
      returnString += string.charAt(i);
      i++;
    }

    if (i + 1 <= string.length()) {
      return new String[]{returnString, string.substring(i + 1)};
    }

    return new String[]{returnString, string.substring(i)};
  }

}
