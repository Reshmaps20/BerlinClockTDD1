# Berlin Clock
## About this Kata
This short and simple Kata should be performed using Test Driven Development (TDD).
## Rules
The Berlin Clock (Mengenlehreclock or Berlin Uhr) is a clock that tells the time using a series of illuminated coloured blocks, as you can see in the picture for this project.

- The **top lamp** blinks to show **seconds**; it is illuminated on **even seconds** and off on **odd seconds**.

- The next two rows represent **hours**:
    - The **upper row** represents **5-hour blocks** and is made up of **4 red lamps**.
    - The **lower row** represents **1-hour blocks** and is also made up of **4 red lamps**.

- The final two rows represent **minutes**:
    - The **upper row** represents **5-minute blocks** and is made up of **11 lamps**—every third lamp is **red**, while the rest are **yellow**.
    - The **bottom row** represents **1-minute blocks** and is made up of **4 yellow lamps**.

## Sample Input and Output

### Sample Input

- File: `src/main/resources/sample-input.json`
- Example:
  ```json
  {
    "time": {
      "hours": "23",
      "minutes": "59",
      "seconds": "59"
    }
  }

### Sample Output

- File: `src/main/resources/sample-output.json`
- Example:
  ```json
  {
  "digitalTime": "23:59:59",
  "detailedBerlinTime": {
    "secondsLamp": "O",
    "topFiveHourLamps": "RRRR",
    "bottomOneHourLamps": "RRRO",
    "topFiveMinuteLamps": "YYRYYRYYRYY",
    "bottomOneMinuteLamps": "YYYY"
  },
  "berlinTime": "O RRRR RRRO YYRYYRYYRYY YYYY"
  }