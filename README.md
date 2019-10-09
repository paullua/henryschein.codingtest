This is a simple Spring Boot web service written with Java 8.

The only endpoint is a GET http request that takes a string of CSV data as a query parameter.
When running the application locally, you can type `http://localhost:8080/csv/format?stringCsv={insert CSV string here}` to test this webservice.

To insert a space in the `stringCsv` parameter, use `%20`.

To insert a quotation mark (`"`), use `%22`.

To insert a new-line character (`\n`), use `%0A`.

To insert a carriage-return (`\r`), use `%0D`.

Example input CSV string: 
```
“Patient Name”,”SSN”,”Age”,”Phone Number”,”Status”
“Prescott, Zeke”,”542-51-6641”,21,”801-555-2134”,”Opratory=2,PCP=1”
“Goldstein, Bucky”,”635-45-1254”,42,”435-555-1541”,”Opratory=1,PCP=1”
“Vox, Bono”,”414-45-1475”,51,”801-555-2100”,”Opratory=3,PCP=2”
```

Example output string:
```
[Patient Name] [SSN] [Age] [Phone Number] [Status]
[Prescott, Zeke] [542-51-6641] [21] [801-555-2134] [Opratory=2,PCP=1]
[Goldstein, Bucky] [635-45-1254] [42] [435-555-1541] [Opratory=1,PCP=1]
[Vox, Bono] [414-45-1475] [51] [801-555-2100] [Opratory=3,PCP=2]
```

To send the GET request using the example input CSV string when running the application locally, use the following URL:
```
http://localhost:8080/csv/format?stringCsv=%22Patient%20Name%22,%22SSN%22,%22Age%22,%22Phone%20Number%22,%22Status%22%0A%22Prescott,%20Zeke%22,%22542-51-6641%22,21,%22801-555-2134%22,%22Opratory%3D2,PCP%3D1%22%0A%22Goldstein,%20Bucky%22,%22635-45-1254%22,42,%22435-555-1541%22,%22Opratory%3D1,PCP%3D1%22%0A%22Vox,%20Bono%22,%22414-45-1475%22,51,%22801-555-2100%22,%22Opratory%3D3,PCP%3D2%22
```

The previous URL uses the new-line character. To use the carriage-return followed by the new-line character (i.e. `"\r\n"`), use the following:
```
http://localhost:8080/csv/format?stringCsv=%22Patient%20Name%22,%22SSN%22,%22Age%22,%22Phone%20Number%22,%22Status%22%0D%0A%22Prescott,%20Zeke%22,%22542-51-6641%22,21,%22801-555-2134%22,%22Opratory%3D2,PCP%3D1%22%0D%0A%22Goldstein,%20Bucky%22,%22635-45-1254%22,42,%22435-555-1541%22,%22Opratory%3D1,PCP%3D1%22%0D%0A%22Vox,%20Bono%22,%22414-45-1475%22,51,%22801-555-2100%22,%22Opratory%3D3,PCP%3D2%22
```

The unit test class is in `src/test/java/com/paulland/henryschein/codingtest/CsvControllerTest.java`.

The controller code that handles the logic for parsing the CSV string is in `src/main/java/com/paulland/henryschein/codingtest/CsvController.java`
