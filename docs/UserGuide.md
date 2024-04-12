---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# Realodex User Guide

Realodex is a **desktop app for managing client contacts, optimized for use via a
Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Realodex can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. **Check Java Installation** Ensure you have Java `11` or above installed in your computer.

<box type="info" light>
<tabs>
<tab header="Windows">

1. Open command-prompt, by typing cmd in your start menu.
2. Type `java --version` and enter to check if Java is installed and to the right version.
   Below image is the expected output if the correct version of Java is installed. 
   The red box indicates the expected Java version you should see.
   <br>
  <div style="display:flex; justify-content: center; align-items:center;">
    <img src="images/quick-start/java-version.png" alt="java-version" style=" width: 700px; margin-bottom: 16px;">
  </div>

3. If Java is not installed or the wrong version is installed,
   please install Java 11 from the official site
   or click [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).
</tab>
<tab header="Mac"> 

1. Open Terminal.
2. Type `java --version` and enter to check if Java is installed and to the right version.
   Below image is the expected output if the correct version of Java is installed.
   The red box indicates the expected Java version you should see. 
   <br>
  <div style="display:flex; justify-content: center; align-items:center;">
    <img src="images/quick-start/java-version-macs.png" alt="java-version-macs" style=" width: 900px; margin-bottom: 16px;">
  </div>

3. If Java is not installed or the wrong version is installed, please install Java 11 from the official site.
</tab>
</tabs>
</box>

<div style="page-break-after: always;"></div>

2. **Download Realodex** You can download the latest `realodex.jar` from [here](https://github.com/AY2324S2-CS2103T-W10-1/tp/releases).
   <br>
  <div style="display:flex; justify-content: center; align-items:center;">
    <img src="images/quick-start/release.png" alt="release" style=" width: 800px; margin-bottom: 16px;">
  </div>
<br>

3. **Set up Realodex** You should copy the file to the folder you want to use as the _home folder_ for your Realodex.

<box type="tip"><md>We highly recommend you running Realodex in an empty folder as pre-existing JSON files may cause bugs!</md></box>

4. **Navigate to Workspace** Open a command terminal, change directory into the folder by using change directory command,
   `cd`, and navigating to the folder you put the jar file in
   <br>
  <div style="display:flex; justify-content: center; align-items:center;">
    <img src="images/quick-start/cd.png" alt="cd" style=" width: 600px; margin-bottom: 16px;">
  </div>
<br>

5. **Run Realodex** Run `java -jar realodex.jar ` in the same terminal opened to start Realodex.
   <br>
  <div style="display:flex; justify-content: center; align-items:center;">
    <img src="images/quick-start/running.png" alt="running" style=" width: 600px; margin-bottom: 16px;">
  </div>
<br>
<box type="warning">While the screenshots are from Windows OS, the commands are the same for other OS as well!.</box>

5. You should see a GUI similar to image below pop up. Note how the app contains some pre-populated sample data!
   <br>
  <div style="display:flex; justify-content: center; align-items:center;">
    <img src="images/quick-start/ui.png" alt="ui" style=" width: 550px; margin-bottom: 16px;">
  </div>
<br>


5. **Try out some Realodex features!** Some example commands you can try:

   * `add n/John Doe p/98765432 i/20000 e/johnd@example.com a/311, Clementi Ave 2, #02-25 f/4 h/HDB t/buyer r/Owes money.`
   Adds a contact named `John Doe` together with his details to Realodex.

   * `delete n/john doe` : This will delete the client with name `John Doe` from Realodex.

6. Refer to the [Features](#features) below for details of each command.
----
# Using this Guide

To ensure you have a smooth and intuitive experience, this guide utilizes specific formatting conventions and icons. Familiarizing yourself with these will enhance your understanding and efficiency as you navigate through the functionalities of Realodex.

## Formatting Conventions
| Format        | Meaning                                                                                                                                                                                                                 |
|---------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Bold**      | Used to **draw attention** to key concepts and actions you need to perform. When you see text in bold, it emphasises **important information.**                                                                         |
| _Italics_     | Reserved for introducing new terms and phrases that are essential to understanding Realodex. Italicized text may also appear in subheadings beneath screenshots to succinctly describe what you're seeing in the image. |
| `Code blocks` | Actual commands that you may enter into the Realodex CLI will be referred to in these code blocks.                                                                                                                      |


## Icon Legend

| Icon | Meaning                                                                                                    |
|------|------------------------------------------------------------------------------------------------------------|
| 💡   | Tips, helpful suggestions and best practices to optimize the Realodex user experience experience.          |
| ⚠️   | Warning for potential pitfalls or important considerations that could impact the Realodex user experience. |
| 🗒️  | Additional Notes to keep track of in the features.                                                         |
| 👀   | Upcoming Features to look out for. Stay tuned for exciting new features and enhancements in Realodex.      |

## Glossary

| Abbreviation/Nomenclature      | Meaning                                                                                                                                                                                                  |
|--------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GUI (Graphical User Interface) | It is the digital interface which you interact with when using Realodex!                                                                                                                                 |
| GUI Components                 | A specific component of the GUI. For more information on specific GUI components, refer to this section.                                                                                                 |
| CLI (Command Line Interface)   | A text-based user interface to interact with the application.                                                                                                                                            |
| Command                        | An input from you that tells Realodex to execute an action. You can refer to the command summary [here](#command-summary)!                                                                               |
| Prefix                         | Prefixes are like fields in a form you are required to fill up. They are information needed to be passes together with the command so that it can be executed. View our Prefix Summary for more details! |
| Case-Sensitive                 | The casing of the alphabetic characters matters (e.g. “ReAlOdEx” is different from “realodex”                                                                                                            |
| Case-Insensitive               | The casing of the alphabetic characters does not matter (e.g. “ReAlOdEx” is taken to be same as “realodex”                                                                                               |

---

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Commands are generally in the format `COMMANDWORD PREFIX_ONE/UPPER_CASE PREFIX_TWO/UPPER_CASE ....`
* `COMMANDWORD` refers to the various commands user can input. <br>Examples include `add`, `delete`, `filter`. A summary of all commands and their usages can be found in the [Commands Summary](#command-summary) table.

* `COMMANDWORD` is case-insensitive, i.e.`add` and `ADD` both indicate the command word for `add`.
* `PREFIX/` refers to the prefix tag associated with each user-written input. <br>
   e.g. in `add n/NAME r/REMARK`, `NAME` is the name parameter and `REMARK` is the remark parameter.
* `PREFIX` is case-insensitive, i.e.`N/` and `n/` are the same and are used to indicate a `NAME` input.
* `PREFIX` must be preceded by a whitespace character.
   e.g. `a/6 College Avenue Westr/Has a dog` will only recognise the `a/` prefix as the `r/` prefix is preceded by `t` which is not a whitespace character.
* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME ....`, `NAME` is a parameter which can be used as `add n/John Doe ....`.
  <br>For more details on the parameters, do refer to the [Field Constraints](#field-constraints).

* Parameters enclosed in `[]` are optional to input.<br>e.g. `[r/REMARK]` indicates an optional input field for that command.

* You may input the parameters in any order (e.g. if the command specifies `n/NAME a/ADDRESS`, `a/ADDRESS n/NAME` is also acceptable).

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.


</box>

### Adding a client: `add`

Adds a client to Realodex.

<u>Format</u>: `add n/NAME p/PHONE i/INCOME e/EMAIL a/ADDRESS f/FAMILY t/TAG h/HOUSING_TYPE [r/REMARK] [b/BIRTHDAY]`

- `n/NAME`,`p/PHONE`,`i/INCOME`,`e/EMAIL`,`a/ADDRESS`,`f/FAMILY`,`t/TAG` and `h/HOUSING_TYPE` are compulsory fields.
  - If any of the above fields are missed out in the `add` command, you will receive an error message informing you of the compulsory fields that you missed.
  - For example, if only `n/NAME` and `i/INCOME` are present, you will be alerted that you are missing the fields `p/PHONE`,`e/EMAIL`,`a/ADDRESS`,`f/FAMILY`,`t/TAG`and`h/HOUSINGTYPE`.
 <a href="images/add-command/missing_fields_error.png">
   <img src="images/add-command/missing_fields_error.png" alt="missing compulsory fields" style="width:150%">
   </a>
- Except `t/TAG`, there can only be one of each field in the add command.
<a href="images/add-command/excessive_fields_error.png">
  <img src="images/add-command/excessive_fields_error.png" alt="excessive fields" style="width:100%">
  </a>
- For `t/TAG`, you may input both `t/BUYER` and/or `t/SELLER` (case insensitive).
  - If either tag is repeated more than once, for e.g. `t/BUYER t/BUYER`, the tag will only be recorded once and no error will be thrown.
- Note that the `r/REMARK` and `b/BIRTHDAY` fields are optional, enclosed in `[]`. You may choose to omit them.
  - If you include the prefix with a blank input, the birthday and remark fields will be taken as not specified.
    - Example: `add n/John Doe p/98765432 i/20000 e/johnd@example.com a/311, Clementi Ave 2, #02-25 f/4 t/Buyer h/HDB r/ b/` will successfully add John Doe but remarks and birthday will be not specified.
- Each field has constraints to the inputs it can accept. Do refer to the [Field Constraints](#field-constraints) for more information.
  - If any of the constraints are violated, you will receive an error message detailing the fields with invalid formats.
  - For example, if `n/NAME`,`p/PHONE` and `t/TAG` fields do not fulfil the contraints, you will receive 3 error messages as shown.
    <a href="parsing_errors.png">
    <img src="parsing_errors.png" alt="duplicate person" style="width:150%">
    </a>
- You **cannot** have duplicate persons with the same name in Realodex.
  - Names are case-insensitive as described in [Field Constraints](#field-constraints).
  - If you try to add duplicate persons, you will get the error message "This client already exists in Realodex".
    <a href="images/add-command/duplicate_person_error.png">
    <img src="images/add-command/duplicate_person_error.png" alt="duplicate person" style="width:100%">
    </a>

<u>Examples</u>:
* `add n/John Doe p/98765432 i/20000 e/johnd@example.com a/311, Clementi Ave 2, #02-25 f/4 t/Buyer h/HDB r/Owes $1000. b/27May2003`
* `add n/Betsy Crowe a/Newgate Prison i/0 f/1 p/94859694 e/betsyc@rocketmail.com t/Seller h/CONDOMINIUM t/Buyer`

### Deleting a client : `delete`

Deletes the specified client from Realodex. There are 2 ways to do so:

#### Delete By Name

<u>Format</u>: `delete n/NAME`

* Deletes the client with the specified `NAME` in Realodex.
* `NAME` is case-insensitive.
* If `NAME` is **not found**, error message will be shown "The client name provided is invalid".

<u>Example</u>:
* `delete n/Udhaya Shanmugam` deletes the client in Realodex with the name "Udhaya Shanmugam".

#### Delete By Index

<u>Format</u>: `delete INDEX`

* Deletes the client of the specified `INDEX` in Realodex.
* 💡 If you are currently filtered, the index will be based on the filtered list.
* If `INDEX` is **more than the number of clients in Realodex**, error message will be shown "The client index provided is invalid."
* If 'INDEX` is a non-zero unsigned integer, error message will be shown "Index is not a non-zero unsigned integer."

<u>Example</u>:
* `delete 4` deletes the 4th client listed in Realodex, provided there are 4 or more entries.

Errors:
- If neither index nor name is provided `delete` will show an error message "Please provide either an index or a name.".
- If both an index and name is provided `delete INDEX n/NAME` will show an error message "Please provide either an index or a name, not both.".
- If both an index and name is provided `delete n/NAME INDEX ` will show an error message "The client name provided is invalid" as INDEX is considered part of the NAME".
### Editing clients : `edit`

Edits specified details of the client.

<u>Format</u>: `edit INDEX [n/NAME] [p/PHONE] [i/INCOME] [e/EMAIL] [a/ADDRESS] [f/FAMILY] [t/TAG] [h/HOUSINGTYPE] [r/REMARK] [b/BIRTHDAY]`

- If `INDEX` is `3`, the 3rd client's information will be edited.
- 💡 If you are currently filtered, the index will be based on the filtered list.
- It is optional to edit any field (i.e, you can choose to edit any combination of fields so long there is at least 1).
- The current information will be overwritten with the input provided.
- When editing the `TAG`, all existing tags will be overwritten with the new tag(s) provided. If you want to edit the client to be both a buyer and seller, include both tags i.e. `t/Buyer t/Seller`.
- All fields must follow the respective [Field Constraints](#field-constraints).

<u>Examples</u>:

- `edit 1 p/999` will overwrite the 1st client's phone number to "999".
- `edit 2 n/Kylie  i/3333 f/5` will overwrite the 2nd client's name to "Kylie", income to "3333" and family size to "5".

### Filtering clients: `filter`

The filter command in Realodex allows you to narrow down your list of clients by specifying a keyphrase
related to one of the client's attributes: name, remark, tag, birthday month, or housing type.
This feature is particularly useful when you need to focus on a
subset of your client database that meets certain criteria.
Note that the search is **case-insensitive** for all input parameters.

>
> When performing multiple filter operations in sequence,
> each new filter is applied to the original, full list of clients, not the subset produced by the previous filter.
> This approach ensures clarity and consistency in search results.

#### Filter By Name
<u>Format:</u> `filter n/KEYPHRASE`

- Returns the list of clients whose names contain the specified keyphrase.
- Keyphrase input should be in valid format for names (alphanumeric) and non-empty.
  - `filter n/james` matches person with the name "James".
- **Partial fragments** of names will still be matched.
  - `filter n/Udh` matches a person with the name "Udhaya".
- **Comprehensive searching**, returning all persons with names containing the keyphrase.
  - `filter n/Al` returns persons named "Alicia", "Allysa", "Jamal".

<u>Example:</u>

<a href="images/filter/filterByNameScreenshot.png">
<img src="images/filter/filterByNameScreenshot.png" alt="filterByNameScreenshot" style="width:150%">
</a>
<p align="center">
  <em> <code>filter n/Li</code> returns persons with names like "Charlotte Oliveiro" and "David Li"</em>
</p>

#### Filter By Tag
<u>Format:</u> `filter t/TAG`

- Returns the list of clients with the specified tag(s).
- Tag input should be valid and non-empty - "Buyer" or "Seller".
  - `filter t/buyer` matches person with tag "Buyer".
- **Inclusive matching** of persons with multiple tags, as long as they possess the
  tag(s) specified in the input.
  - `filter t/buyer` matches person with tags "Buyer" and "Seller".
- Supports searching with **multiple tags**.
  - `filter t/Buyer t/Seller` only returns persons with both "Buyer" and "Seller" tags.
- **Comprehensive searching**, returning all persons' with the specified tag(s).

<u>Examples:</u>
<p align="center">
      <a href="images/filter/filterByTagSeller.png">
      <img src="images/filter/filterByTagSeller.png" alt="filterByTagSeller" style="width:150%">
      </a>
  <em> <code>filter t/seller</code> returns persons with "Seller" Tag</em>
</p>
      <a href="images/filter/filterByTagBuyerSeller.png">
      <img src="images/filter/filterByTagBuyerSeller.png" alt="filterByTagBuyerSeller" style="width:150%">
      </a>

<p align="center">
  <em> <code>filter t/buyer t/seller</code> only returns persons with "Buyer" and "Seller" Tag</em>
</p>

#### Filter By Housing Type
<u>Format:</u> `filter h/HOUSING_TYPE`

- Returns the list of clients with the specified housing type preference.
- Housing Type input should be valid and non-empty - "HDB", "Condominium", "Landed Property" or "Good Class Bungalow".
  - `filter h/hdb` matches person with housing type "HDB".
- **Comprehensive searching**, returning all persons with the specified housing type.
  - `filter h/Condominium` returns all persons with the "Condominium" preferred housing type.

<u>Example:</u>

  <a href="images/filter/filterByHousingType.png">
  <img src="images/filter/filterByHousingType.png" alt="filterByHousingType" style="width:150%">
  </a>
<p align="center">
  <em> <code>filter h/Good Class Bungalow</code> returns persons with "Good Class Bungalow" Housing Type preference</em>
</p>

#### Filter By Remark
<u>Format:</u> `filter r/KEYPHRASE`

- Returns the list of clients whose remarks include the specified keyphrase.
- Keyphrase input should be non-empty. 
This is an intentional design choice to ensure that the command is used for targeted searches, preventing the potential misinterpretation of an empty keyphrase as a request to list all clients.
- Important: The remarks for the `filter r/` command must not contain any other prefixes to prevent parsing errors. 
> The command `filter r/ my tag is t/buyer` would cause an error because the system interprets `t/` as the start of a new prefix.
> To avoid this, ensure that the remark does not contain any spaces followed by slashes that could be misconstrued as additional prefixes.
  - `filter r/FOOD` matches person with remark "He loves food."
- **Partial fragments** of remarks will still be matched.
  - `filter r/hand` matches person with remark "handsome".
- **Comprehensive searching**, returning all persons' names containing the keyword .
  - `filter r/love` returns persons with remarks "loves to travel", "has a lovely dog".
    
<u>Example:</u>

  <a href="images/filter/filterByRemarkScreenshot.png">
    <img src="images/filter/filterByRemarkScreenshot.png" alt="filterByRemarkScreenshot" style="width:150%">
    </a>
<p align="center">
  <em> <code>filter r/eat</code> returns persons with remarks like "Eats alot" and "Likes to eat nasi lemak ..."</em>
</p>

#### Filter By Birthday
<u>Format:</u> `filter b/MONTH`

- Returns the list of clients whose birthdays are in the specified month.
- Month input should be a valid month in `MMM` format and non-empty.
  - Filtering by month "September" should be `filter b/Sep`
    - `filter b/SEP` matches person with Birthday in September.
- **Comprehensive searching**, returning all persons with birthdays in the specified month.
    - `filter b/Jan` returns all persons with birthday in January.
- Persons who do not have a specified birthday will **not be included** in the search results.

<u>Example:</u>

<a href="images/filter/filterByBirthday.png">
  <img src="images/filter/filterByBirthday.png" alt="filterByBirthday" style="width:150%">
  </a>

<p align="center">
  <em> <code>filter b/Apr</code> returns persons with Birthday in April</em>
</p>

### Listing clients : `list`

Lists all clients in Realodex.

<u>Format:</u> `list`

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>


### Sort : `sort`

Sort clients based on their proximity to their upcoming birthday,
calculated by the number of days until their next birthday relative to the current date.

**Format:**
<box>sort</box>

<box type="info" header="Things to note:">

* The current date is based on the local system's time.
* If their birthday has already passed,
  the calculation is based on the number of days until their next birthday next year.
</box>

<box type="warning" header="Warning:">

* If the list presented is currently a filtered list after using [filter](#filtering-clients-filter),
  sort will work on the new filtered list.
</box>

**Example:**

* Upon `sort`, the list on the screen will be filtered to return a new sorted list by nearest birthday.

<div style="display:flex; justify-content: center; align-items:center;">
<img src="images/sort/sort-day_showcase.png" alt="sort" style=" width: 800px; margin-bottom: 16px;">
</div>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>


### Clearing Realodex : `clearRealodex`

Clears all clients in Realodex.

**Format:**
<box>clearRealodex</box>

<box type="tip">A longer command, <code>clearRealodex</code>
is used so that users understand that this command <b>clears all entries in Realodex</b>,
preventing potential confusion with the delete command and accidental clearing of all entries.
</box>

<box type="warning" header="Warning:">
Be careful with <code>clearRealodex</code>, you will be unable to undo this operation!
  </box>

**Example:**

* Upon `clearRealodex`, Realodex will clear and return an empty list.
<div style="display:flex; justify-content: center; align-items:center;">
<img src="images/misc_features/clear.png" alt="clear" style=" width: 900px; margin-bottom: 16px;">
</div>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Help : `help`

Generates a pop-up window, which is a summarized version of the User Guide and its features.
**Format:**
<box>help</box>


<box type="tip">This window can also be accessed by the "Help" button on the top menu.
</box>

**Example:**
* Upon `help`, Realodex will pop up a help window together with a success message.
<div style="display:flex; justify-content: center; align-items:center;">
<img src="images/misc_features/help_window.png" alt="help_window" style=" width: 900px; margin-bottom: 16px;">
</div>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Help (Individual Commands) : `COMMAND help`

Shows the help message for the specified command only.

**Format:**
<box>COMMAND help</box>

<box type="info" header="Things to note:">

* Note that this feature is only available for the `add`,`clearRealodex`,`delete`,`edit`,`filter`,`list` and `sort` commands.
* Although the format is `COMMAND help`, the exception is the help message for the clear command.
  Use `clear help` instead of `clearRealodex help`.
  </box>

**Example:**
1. User types in `add help`.
2. Realodex posts the help message for `add` command as shown below.
<p align="center">
      <a href="images/misc_features/command_help.png">
      <img src="images/misc_features/command_help.png" alt="command_help" style="width:120%">
      </a>
</p>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Exiting the program : `exit`

Exits the program and closes the window.

**Format:**
<box>exit</box>

<box type="info" header="Things to note:">

* Note that keying in `exit` followed by any random string, such as `exit wrelvwrvn` will also cause the app to exit.
  </box>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### File Data

The JSON file that stores the data of your contacts can be found in a folder named `data`, in the same folder/directory as the Realodex app. (e.g. if you
have Realodex installed in your Desktop, the `data` folder containing the file can be found in your Desktop as well.)

#### Saving Data

Realodex data is saved in the hard disk as a JSON file automatically after any command that modifies it. There is no need to save manually.

#### Editing Data

Realodex data is saved automatically as a JSON file `[JAR file location]/data/realodex.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your **manual changes to the data file makes its format invalid**, Realodex will **discard all data and start with an empty data file at the next run.**  Hence, it is recommended to make a backup of the file before editing it.<br>
Furthermore, certain edits can cause the Realodex to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.

</box>

#### Restarting with New Data
Should you want to re-enter your contacts in a fresh JSON file in the event of file corruption or a bad edit causing the format to be incorrect,
simply delete `realodex.json`, which can be found in the `data` folder, and restart the app. A new JSON file with sample contacts will be generated and you may proceed from there.

--------------------------------------------------------------------------------------------------------------------
## Field Constraints
* NAME:
    * Should only contain Alphanumeric characters and must be unique.
    1. Names are case-insensitive.
  2. Number of spaces between words in the name do not matter.
  3. Although names are displayed in full capitalisation, they are still recorded in a case-insensitive manner. Hence, an input with the same name but different capitalisation will be considered a duplicate entry.
    * Example: `n/John Doe` and `n/john   doe` are both considered the same valid name but both will be displayed as `JOHN DOE`.
* PHONE:
    * Should only contain numbers, and should be at least 3 digits long.
    * Example: `p/81234567`
* INCOME:
    * Income should be an integer and should be at least 0.
    * Example: `i/20000`
* EMAIL:
    * Emails should be of the format local-part@domain and adhere to the following constraints:
    1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-).
    2. The local-part may not start or end with any special characters.
    3. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods.\
       The domain name must:
        * end with a domain label at least 2 characters long
        * have each domain label start and end with alphanumeric characters
        * have each domain label consist of alphanumeric characters, separated only by hyphens, if any.
    * Example: `e/realodex-admin@gmail.com`
* ADDRESS: Current residential address
    * Must not include other command prefixes (`a/`,`b/`,`e/`,`f/`,`h/`,`i/`,`n/`,`p/`,`r/`,`t/`) to prevent parsing errors. For instance, `a/lemontree street t/1` may cause the command to fail, as the system will interpret `t/` as an unintended tag prefix.
    * Example: `a/6 College Ave West`
* FAMILY: Immediate family size
    * Should be an integer greater than 1.
    * Example: `f/4`
* TAG:
    * Only accept "buyer" or "seller" as the input (case-insensitive). Multiple tags are accepted.
    * Example: `t/buyer`, `t/seller` or both
* HOUSINGTYPE: housing type a buyer wants or housing type a seller is selling
    * Must be one of the following: "HDB", "CONDOMINIUM", "LANDED PROPERTY", "GOOD CLASS BUNGALOW" (case-insensitive). Only one housing type is allowed.
    * Example: `h/HDB`
* REMARK:
    * Can be empty if remark is not specified.
    * Must not include other command prefixes (`a/`, `b/`, `f/`, `h/`, `i/`, `n/`, `p/`, `r/`, `t/`) to prevent parsing errors. For instance, `r/Prefers block b/c` may cause the command to fail, as the system will interpret `b/` as an unintended birthday prefix.
    * Example: `r/Has a cat`
* BIRTHDAY:
    * Should be in the form "DDMMMYYYY", and can be empty if the birthday is not specified.
    * Example: `b/22Feb2002`
    1. The date must not be in the future.
    2. The date must exist in the Gregorian calendar. (`b/29Feb2023` is not allowed as it is not a valid day to begin with)
    3. The day "DD" must be numeric. For 1st-9th day of the month, the 0 need not be present. (Example: `2Feb2002`)
    4. The month "MMM" refers to the first 3 letters of the month (case-insensitive)
    5. The year "YYYY" must be in full and greater than or equal to 1000. (`b/29Feb02` is not allowed)

--------------------------------------------------------------------------------------------------------------------
## Command summary

| Action                         | Format, Examples                                                                                                                                                                                                                                |
|--------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**                        | `add n/NAME p/PHONE i/INCOME e/EMAIL a/ADDRESS f/FAMILY t/TAG h/HOUSINGTYPE [r/REMARK] [b/BIRTHDAY]` <br> e.g. `add n/John Doe p/98765432 i/20000 e/johnd@example.com a/311, Clementi Ave 2, #02-25 f/4 t/Buyer h/HDB r/Likes cats b/31Dec1982` |
| **Delete (by name)**           | `delete n/NAME`<br> e.g. `delete n/John`                                                                                                                                                                                                        |
| **Delete (by index)**          | `delete INDEX`<br> e.g. `delete 3`                                                                                                                                                                                                              |
| **Edit**                       | `edit INDEX [n/NAME] [p/PHONE] [i/INCOME] [e/EMAIL] [a/ADDRESS] [f/FAMILY] [t/TAG] [h/HOUSINGTYPE] [r/REMARK] [b/BIRTHDAY]` <br> e.g. `edit 2 n/Denzel i/100000`                                                                                |
| **Filter**                     | `filter [n/KEYPHRASE] [r/KEYPHRASE] [t/TAG] [b/MONTH] [h/HOUSING_TYPE]`<br> e.g. `filter n/David`,`filter b/Oct`                                                                                                                                |
| **List**                       | `list`                                                                                                                                                                                                                                          |
| **Sort**                       | `sort`                                                                                                                                                                                                                                          |
| **Help**                       | `help`                                                                                                                                                                                                                                          |
| **Help (individual commands)** | `COMMAND help`<br> e.g. `add help`,`edit help`                                                                                                                                                                                                  |
| **Clear**                      | `clearRealodex`                                                                                                                                                                                                                                 |
| **Exit**                       | `exit`                                                                                                                                                                                                                                          |


--------------------------------------------------------------------------------------------------------------------
## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty JSON file it creates with the JSON file that contains all of your data.

--------------------------------------------------------------------------------------------------------------------
## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
--------------------------------------------------------------------------------------------------------------------
