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

    return formattedString;
  }

}
