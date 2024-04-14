---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# 🚀 Welcome to Realodex! 🚀

<div style="text-align: center;">
    <a href="images/realodex_icon.png">
        <img src="images/realodex_icon.png" alt='Realodex Icon' style="width:60%;" />
    </a>
</div>

**Realodex** is your ultimate client management tool tailored specifically for the **dynamic needs of real estate professionals**. 
Realodex offers seamless functionality to ***add***, ***edit***, ***filter***, and ***delete*** client information efficiently,
thereby **optimising and streamlining your day-to-day operations** of real estate agents. 


--------------------------------------------------------------------------------------------------------------------
## Why Choose Realodex?
Realodex is **tailor-made for the real estate industry**, offering **custom tools and features** that align with the everyday workflows of real estate agents. 

- **Enhanced Productivity**: Access and manage your client database efficiently. 
- **Intuitive Interface & Features**: Our user-friendly interface is designed for quick learning, enabling you to navigate and utilize Realodex with ease.
- **Focus on Core Goals**: Allows you to dedicate more time to closing deals and fostering client relationships.

<box type="tip" header = "**New to Realodex?**">
This guide provides detailed documentation on Realodex's features, FAQs, and upcoming updates. Start enhancing your real estate operations today by exploring the <a href="#quick-start">Quick Start</a> section.
</box>

--------------------------------------------------------------------------------------------------------------------

## Table of Contents

- [Using this Guide](#using-this-guide)
    - [Formatting Conventions](#formatting-conventions)
    - [Icon Legend](#icon-legend)
- [Getting Started](#getting-started)
  - [Downloading Realodex](#downloading-realodex)
  - [Introduction to Realodex GUI](#introduction-to-realodex-gui)
  - [Realodex Tutorial](#realodex-tutorial)
- [Features](#features)
    - [Adding a Client](#adding-a-client-add)
    - [Deleting a Client](#deleting-a-client-delete)
    - [Editing Clients](#editing-clients-edit)
    - [Filtering Clients](#filtering-clients-filter)
    - [Listing Clients](#listing-clients-list)
    - [Sorting](#sorting-sort)
    - [Clearing Realodex](#clearing-realodex-clearrealodex)
    - [Help](#help-help)
    - [Help (Individual Commands)](#help-individual-commands-command-help)
    - [Exiting the Program](#exiting-the-program-exit)
    - [File Data](#file-data)
- [Field Constraints](#field-constraints)
- [Command Summary](#command-summary)
- [FAQ](#faq)
- [Known Issues](#known-issues)

--------------------------------------------------------------------------------------------------------------------
## Navigating this User Guide
Welcome to the Realodex User Guide! 
We hope this user guide empowers you with the knowledge and confidence to **unleash the full potential of Realodex**.
To ensure you have a **smooth and intuitive experience**, this guide utilizes **specific formatting conventions and icons**. 
Familiarizing yourself with these will enhance your understanding and efficiency as you navigate through the functionalities of Realodex.

#### Formatting Conventions
| Format        | Meaning                                                                                                                                                                                                                 |
|---------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Bold**      | Used to **draw attention** to key concepts and actions you need to perform. When you see text in bold, it emphasises **important information.**                                                                         |
| _Italics_     | Reserved for introducing new terms and phrases that are essential to understanding Realodex. Italicized text may also appear in subheadings beneath screenshots to succinctly describe what you're seeing in the image. |
| `Code blocks` | Actual commands that you may enter into the Realodex CLI will be referred to in these code blocks.                                                                                                                      |


#### Icon Legend
<box type="tip">These boxes provide useful tips and tricks on how to optimise Realodex</box>
<box type="info">These boxes provide important things to note when using Realodex</box>
<box type="warning">These boxes provide precautionary warnings to avoid potential pitfalls</box>
<box type="wrong">These boxes highlight error messages and critical failure information</box>

#### Key Sections
This guide also includes:
- **Effortless Navigation**: Seamlessly navigate between sections using the [Table of Contents](#table-of-contents).
- **Quick Start for New Users**: If you are new to Realodex, begin your journey by exploring our [Quick Start Guide](#quick-start), which provides a comprehensive introduction to setting up Realodex.
- **Tutorial**: Dive into a detailed, step-by-step walkthrough of Realodex’s features in our [Tutorial](#realodex-tutorial) section.
- **Features**: Discover and learn how to utilize all the functionalities of Realodex in our detailed [Features](#features) section.
- **FAQ**: Find answers to common questions and helpful tips in the [FAQ](#faq) section.


#### Glossary

This table provides definitions of key terms and abbreviations used throughout the Realodex user guide:

| Abbreviation/Nomenclature      | Meaning                                                                                                                                                                 |
|--------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GUI (Graphical User Interface) | The digital interface through which you interact with Realodex.                                                                                                         |
| CLI (Command Line Interface)   | A text-based interface that allows users to interact with the application using commands typed into a command prompt or terminal.                                       |
| Command                        | An instruction given by the user that prompts Realodex to perform a specific action. See the [Command Summary](#command-summary) for details.                           |
| Prefix                         | Initial parts of commands in Realodex that specify the type of information to follow (e.g., `n/` for name). View our [Prefix Summary](#prefix-summary) for more details. |
| Case-Sensitive                 | Indicates that the casing of letters matters; for example, “ReAlOdEx” would be recognized differently from “realodex”.                                                  |
| Case-Insensitive               | Indicates that the casing of letters does not affect how they are interpreted; “ReAlOdEx” and “realodex” would be treated as the same.                                  |
| Command Prompt                 | A command line interpreter application available in most Windows operating systems. It allows users to execute commands and scripts.                                    |
| Terminal                       | The command line interface used in Unix and MacOS systems, allowing users to execute commands and scripts similar to Command Prompt in Windows.                         |
| JSON File                      | The file format Realodex uses to store client data.                                                                                                          |


--------------------------------------------------------------------------------------------------------------------

## Getting Started

### Downloading Realodex

<tabs>
<tab header="Windows">

1. **Check Java Installation**:

   a. Open _command-prompt_ by typing `cmd` in your Windows start menu.

   b. Type `java --version` in the command-prompt and press enter to check if Java is installed and to the correct version.
   Please see below for the expected output if the correct version of Java is installed.
   The red box indicates the expected Java version you should see.
   <br>
      <div style="display:flex; justify-content: center; align-items:center;">
        <img src="images/quick-start/java-version.png" alt="java-version" style="width: 700px; margin-bottom: 16px;">
      </div>
   
   c. If Java is not installed or the wrong version is installed,
      please install Java 11 from the official site or click [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).

</tab>
<tab header="Mac/Linux"> 

1. **Check Java Installation**:

   a. Open _Terminal_.

   b. Type `java --version` and press enter to check if Java is installed and to the right version.
   Below image is the expected output if the correct version of Java is installed.
   The red box indicates the expected Java version you should see.
   <br>
      <div style="display:flex; justify-content: center; align-items:center;">
        <img src="images/quick-start/java-version-macs.png" alt="java-version-macs" style="width: 900px; margin-bottom: 16px;">
      </div>

   c. If Java is not installed or the wrong version is installed, 
        please install Java 11 from the official site or click [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).

</tab>
</tabs>

<div style="page-break-after: always;"></div>

2. **Create a New Folder**: On your desktop, create a new folder and title it "Realodex". This will be the dedicated location for storing the Realodex application and its associated files.

3. **Download Realodex**:
   Download the latest version of `realodex.jar` from the [official release page](https://github.com/AY2324S2-CS2103T-W10-1/tp/releases). Below is an image of what the download page looks like:
      <br>
      <div style="display:flex; justify-content: center; align-items:center;">
        <img src="images/quick-start/release.png" alt="Realodex Release" style="width: 800px; margin-bottom: 16px;">
      </div>
      <br>

4. **Set Up Realodex**:
   Copy the downloaded `realodex.jar` file to the newly created "Realodex" folder on your desktop. This helps to keep your application organized and prevents potential conflicts with files from other applications.


5. **Navigate to Workspace**: 

    a. Open a command terminal.
    
    b. Enter the following command: `cd Desktop/Realodex`. You may refer to the screenshot below.
      <br>
  <div style="display:flex; justify-content: center; align-items:center;">
    <img src="images/quick-start/cd.png" alt="cd" style=" width: 600px; margin-bottom: 16px;">
  </div>
<br>

6. **Run Realodex**: Now enter `java -jar realodex.jar` to run the application.
   <br>
  <div style="display:flex; justify-content: center; align-items:center;">
    <img src="images/quick-start/running.png" alt="running" style=" width: 600px; margin-bottom: 16px;">
  </div>
<br>

You should see a _GUI_ similar to image below pop up.
   <br>
  <div style="display:flex; justify-content: center; align-items:center;">
    <img src="images/quick-start/ui.png" alt="ui" style=" width: 550px; margin-bottom: 16px;">
  </div>
<br>


<div style="page-break-after: always;"></div>

---

### Introduction to Realodex GUI

Realodex features a Graphical User Interface (GUI) crafted to deliver a seamless user experience. 
The GUI is structured into several distinct components, each designed to enhance your interactions and efficiency.


<a href="images/navigating-gui/basic_orientation.png">
  <img src="images/navigating-gui/basic_orientation.png" alt="basic orientation" style="width:100%">
  </a>

#### Client Profile

<a href="images/navigating-gui/client_profile.png">
  <img src="images/navigating-gui/client_profile.png" alt="client profile" style="width:100%">
  </a>

<div style="page-break-after: always;"></div>

---

### Realodex Tutorial
Welcome to your first session with Realodex.
We've designed this tutorial to **help new users familiarize themselves** with the functionality and flow of the application. 
Follow along in this short tutorial to get a feel of how Realodex works. Let's begin!

1. **Starting Realodex.** Launch Realodex. The app will show up as follows:
   <a href="images/tutorial/Launch.png" style="display: block; margin: 0 auto; text-align: center;">
   <img src="images/tutorial/Launch.png" alt="duplicate person" style="width:70%">
   </a>

<box type="info" header="Notes">

- If this is your first time, some sample entries will be loaded for you.
</box>

2. **Adding a client.** Let us try to add a new client into Realodex. Run the following command:
`
add n/John Doe p/98765432 i/20000 e/johnd@example.com a/311, Clementi Ave 2, #02-25 f/4 t/buyer t/seller h/HDB r/Has 3 cats b/01May2009
`

   <a href="images/tutorial/AddJohnDoe.png" style="display: block; margin: 0 auto; text-align: center;">
   <img src="images/tutorial/AddJohnDoe.png" alt="duplicate person" style="width:70%">
   </a>
<div style="text-align: center; font-style: italic;">New client John Doe has now been added!</div>

<br>

Congratulations, you have just added your first ever client into Realodex!

<box type="tip" header="Tip">

  - Try adding new clients with different parameters to familiarise yourself with the add command.
</box>

3. **Filter clients.** Notice that the list may start to feel a bit crowded as more clients are added. 
We can utilise the filter feature to efficiently locate specific clients. 
Let's search for our new client John, by simply keying in the command: `filter n/John`.

   <a href="images/tutorial/FilterJohnDoe.png" style="display: block; margin: 0 auto; text-align: center;">
   <img src="images/tutorial/FilterJohnDoe.png" alt="duplicate person" style="width:70%">
   </a>
<div style="text-align: center; font-style: italic;">The filter command outputs our new client John Doe.</div>


<box type="tip" header="Tip">

- You may filter by name but also by remarks, housing type preference, and even birthdays.
  </box>

<br>

4. **Listing all clients.** We are now done reviewing John Doe's details. 
Let us view the entire client list again. To do this, simply enter the command: `list`.

<a href="images/tutorial/List.png" style="display: block; margin: 0 auto; text-align: center;">
   <img src="images/tutorial/List.png" alt="duplicate person" style="width:70%">
   </a>
<div style="text-align: center; font-style: italic;">The list command outputs the entire client list.</div>

<br>

5. **Editing clients.** Suppose our new client John Doe has gotten a pay raise! Let's edit his income to reflect this change. 
First, let's take note of his index number in the client list.
   <a href="images/tutorial/IndexJohnDoe.png" style="display: block; margin: 0 auto; text-align: center;">
   <img src="images/tutorial/IndexJohnDoe.png" alt="duplicate person" style="width:83.3%; margin-right: 130px;">
   </a>
   <br>

Since John's index number is 7, enter the command `edit 7 i/88888`. 
   <a href="images/tutorial/IncomeJohnDoe.png" style="display: block; margin: 0 auto; text-align: center;">
   <img src="images/tutorial/IncomeJohnDoe.png" alt="duplicate person" style="width:83.4%; margin-right: 130px;">
   </a>
<div style="text-align: center; font-style: italic;">The edit command modifies John Doe's income to 88888.</div>
   <br>


<box type="tip" header="Tip">

- You may also edit multiple fields of a client at once.
  </box>

6. **Deleting a client.** 
We can also remove client profiles from Realodex. 
Let us try deleting John Doe from the Realodex by entering `delete n/John Doe`. 
   <a href="images/tutorial/DeleteJohnDoe.png" style="display: block; margin: 0 auto; text-align: center;">
   <img src="images/tutorial/DeleteJohnDoe.png" alt="duplicate person" style="width:70%">
   </a>
<div style="text-align: center; font-style: italic;">The delete command deletes client John Doe with a successful deletion message.</div>
   <br>

<box type="info" header="Notes">

- Notice that the client list has been automatically modified after John's deletion, with the last client now being Roy.
</box>

  7. **Getting Help.** Should you need assistance on any of the commands at anytime while using Realodex, simply enter the `help` command.
     <a href="images/tutorial/HelpWindow.png" style="display: block; margin: 0 auto; text-align: center;">
     <img src="images/tutorial/HelpWindow.png" alt="duplicate person" style="width:70%">
     </a>
<div style="text-align: center; font-style: italic;">A help window is displayed explaining each command usage.</div>
     <br>

For specific guidance on individual commands, you can also enter `COMMAND help`.
Let's try it out with `delete help`. 
     <a href="images/tutorial/DeleteHelp.png" style="display: block; margin: 0 auto; text-align: center;">
     <img src="images/tutorial/DeleteHelp.png" alt="duplicate person" style="width:70%">
     </a>
<div style="text-align: center; font-style: italic;">A message on the delete command usage will be shown.</div>
     <br>

Congratulations! You have finished the Realodex tutorial and are now ready to use Realodex. 
Feel free to continue exploring the in-depth features of Realodex to fully leverage its capabilities in your real estate operations!

## Features



### Command Format and Usage

Understanding the command format in Realodex is crucial for effectively managing your client data. Here are the key aspects of our command structure:

**General Command Structure:**

Commands typically follow this format: `COMMANDWORD PREFIX_ONE/UPPER_CASE PREFIX_TWO/UPPER_CASE ....` This structure helps in executing specific actions within the application.

**Command Words:**

`COMMANDWORD` refers to the base commands that the user can input, such as `add`, `delete`, `filter`. These commands are _case-insensitive_, meaning both `add` and `ADD` will perform the addition operation.

**Prefix Usage:**

`PREFIX/` tags are used to specify the type of data being entered. For example, in `add n/John Doe p/98765432`, `n/` stands for name, and `p/` stands for phone number. Like command words, prefixes are also case-insensitive (`n/` and `N/` are treated the same).

**Parameter Input:**

Parameters must follow the prefix they are associated with and are typically in `UPPER_CASE` in documentation to indicate where user-supplied data should be inserted. For instance, in the command `add n/NAME`, `NAME` is where you would place the actual name of the client.


</box>

### Adding a client: `add`

Adds a client to Realodex.

**Format:**
<box>

`add n/NAME p/PHONE i/INCOME e/EMAIL a/ADDRESS f/FAMILY t/TAG h/HOUSING_TYPE [r/REMARK] [b/BIRTHDAY]`
</box>

<box type="info" header="Notes">

  * There can only be one of each field in the add command except for `t/TAG`, where up to 2 tags are accepted.
</box>

<box type="warning" header="Caution">

* Ensure that each field input adheres to their respective [constraints](#field-constraints).
</box>

<box type="wrong" header="Error">

* `n/NAME`,`p/PHONE`,`i/INCOME`,`e/EMAIL`,`a/ADDRESS`,`f/FAMILY`,`t/TAG` and `h/HOUSING_TYPE` are compulsory fields.
    * If you missed out any of the fields mentioned above, you will be notified with an error message indicating the overlooked fields.
</box>


[//]: # (- For `t/TAG`, you may input both `t/BUYER` and/or `t/SELLER` &#40;case insensitive&#41;.)

[//]: # (  - If either tag is repeated more than once, for e.g. `t/BUYER t/BUYER`, the tag will only be recorded once and no error will be thrown.)

[//]: # (- Note that the `r/REMARK` and `b/BIRTHDAY` fields are optional, enclosed in `[]`. You may choose to omit them.)

[//]: # (  - If you include the prefix with a blank input, the birthday and remark fields will be taken as not specified.)

[//]: # (    - Example: `add n/John Doe p/98765432 i/20000 e/johnd@example.com a/311, Clementi Ave 2, #02-25 f/4 t/Buyer h/HDB r/ b/` will successfully add John Doe but remarks and birthday will be not specified.)

[//]: # (- Each field has constraints to the inputs it can accept. Do refer to the [Field Constraints]&#40;#field-constraints&#41; for more information.)

[//]: # (  - If any of the constraints are violated, you will receive an error message detailing the fields with invalid formats.)

[//]: # (  - For example, if `n/NAME`,`p/PHONE` and `t/TAG` fields do not fulfil the contraints, you will receive 3 error messages as shown.)

[//]: # (    <a href="parsing_errors.png">)

[//]: # (    <img src="parsing_errors.png" alt="duplicate person" style="width:150%">)

[//]: # (    </a>)

[//]: # (- You **cannot** have duplicate persons with the same name in Realodex.)

[//]: # (  - Names are case-insensitive as described in [Field Constraints]&#40;#field-constraints&#41;.)

[//]: # (  - If you try to add duplicate persons, you will get the error message "This client already exists in Realodex".)

[//]: # (    <a href="images/add-command/duplicate_person_error.png">)

[//]: # (    <img src="images/add-command/duplicate_person_error.png" alt="duplicate person" style="width:100%">)

[//]: # (    </a>)

**Example**:

`add n/John Doe p/98765432 i/20000 e/johnd@example.com a/311, Clementi Ave 2, #02-25 f/4 t/Buyer h/HDB r/Owes $1000. b/27May2003`
![add-example.png](add-example.png)
  <div style="text-align: center; font-style: italic;">John Doe has been added into Realodex.</div>
<br>

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>
  
### Deleting a client : `delete`

Deletes the specified client from Realodex. There are 2 ways to do so:

#### Delete By Name

Deletes the client with the specified `NAME`.

**Format:**
<box>

`delete n/NAME`
</box>

<box type="wrong" header="Error">

If there is **no client with the specified** `NAME` in the current list, error message will be shown "The client name provided is invalid". <br>
</box>

**Example:**

`delete n/John Doe` deletes the client in Realodex with the name "John Doe".


<p align="center">
  <a href="images/delete/before_execute_deletename.png">
  <img src="images/delete/before_execute_deletename.png" alt="before execute delete name" style="width:100%">
  </a>
  <em> Client list before entering command <code>delete n/John Doe</code></em>
</p>


<p align="center">
  <a href="images/delete/after_execute_deletename.png">
  <img src="images/delete/after_execute_deletename.png" alt="after execute delete name" style="width:100%">
  </a>
  <em> Client list after entering command <code>delete n/John Doe</code></em>
</p>

#### Delete By Index

Deletes the client of the specified `INDEX`.

**Format:**
<box>

`delete INDEX`
</box>

</box>

<box type="wrong" header="Error">

If there is **no client with the specified** `INDEX` in the current list, error message will be shown "The client index provided is invalid". <br>
</box>

**Example:**

`delete 3` deletes the 3rd client listed in Realodex, provided there are 3 or more entries.

<p align="center">
  <a href="images/delete/before_execute_deleteindex.png">
  <img src="images/delete/before_execute_deleteindex.png" alt="before execute delete index" style="width:100%"> 
  </a>
  <em> Client list before entering command <code>delete 3</code></em>
</p>


<p align="center">
  <a href="images/delete/after_execute_deleteindex.png"> 
  <img src="images/delete/after_execute_deleteindex.png" alt="after execute delete index" style="width:100%"> 
  </a>
  <em> Client list after entering command <code>delete 3</code></em>
</p>

[//]: # (<box type="info" header="">)

[//]: # ()
[//]: # (* If `INDEX` is **more than the number of clients in Realodex**, error message will be shown "The client index provided is invalid." <br>)

[//]: # (* If 'INDEX` is a non-zero unsigned integer, error message will be shown "Index is not a non-zero unsigned integer." <br>)

[//]: # (</box>)

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

### Editing clients : `edit`

Edits specified details of the client.

**Format:**
<box>

`edit INDEX [n/NAME] [p/PHONE] [i/INCOME] [e/EMAIL] [a/ADDRESS] [f/FAMILY] [t/TAG] [h/HOUSINGTYPE] [r/REMARK] [b/BIRTHDAY]`
</box>

<box type="info" header="Notes">

- It is optional to edit any field, but you must have at least 1.<br>
- When editing the `TAG`, all existing tags will be overwritten with the new tag(s) provided. If you want to edit the client to be both a buyer and seller, include both tags i.e. `t/Buyer t/Seller`. <br>
</box>

**Example:**
`edit 2 n/Kylie  i/3333 f/5` will change the 2nd client's name to "Kylie", income to "3333" and family size to "5".


<p align="center">
  <a href="images/edit/before_execute_edit.png">
  <img src="images/edit/before_execute_edit.png" alt="before execute edit" style="width:100%">
  </a>
  <em> Client list before entering command <code>edit 2 n/Kylie  i/3333 f/5</code></em>
</p>


<p align="center">
  <a href="images/edit/after_execute_edit.png">
  <img src="images/edit/after_execute_edit.png" alt="after execute edit" style="width:100%">
  </a>
  <em> Client list after entering command <code>edit 2 n/Kylie  i/3333 f/5</code></em>
</p>


[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

### Filtering clients: `filter`

The filter command in Realodex allows you to narrow down your list of clients by **one** specified field. 
Currently, we support filtering by name, remark, tag, birthday month, or housing type.


#### Filter By Name
Returns the list of only the clients whose names contain the specified keyphrase.

**Format:**
<box>

`filter n/KEYPHRASE`
</box>

**Example:**

`filter n/Li` will return a list of people whose names contain "li".
 

<p align="center">
    <a href="images/filter/filterByNameScreenshot.png">
    <img src="images/filter/filterByNameScreenshot.png" alt="filterByNameScreenshot" style="width:150%">
    </a>
    <em> "Charlotte Oliveiro" and "David Li" are returned.</em>
</p>

<br>

#### Filter By Tag
Returns the list of clients with the specified tag(s).

**Format:**
<box>

`filter t/TAG`
</box>

<box type="info" header="Notes">

- Filtering with the "Buyer" or "Seller" tag retrieves all clients with the respective tag.
- Filtering by multiple tags is supported, `filter t/Buyer t/Seller`, returns clients who possess both buyer and seller tags.
</box>

**Examples:**

`filter t/seller`.

<p align="center">
    <a href="images/filter/filterByTagSeller.png">
    <img src="images/filter/filterByTagSeller.png" alt="filterByTagSeller" style="width:150%">
    </a>
    <em> Persons with "Seller" tag are returned.</em>
</p>

---

`filter t/buyer t/seller`. 

<p align="center">
    <a href="images/filter/filterByTagBuyerSeller.png">
    <img src="images/filter/filterByTagBuyerSeller.png" alt="filterByTagBuyerSeller" style="width:150%">
    </a>
    <em> Persons with both "Buyer" and "Seller" tags are returned.</em>
</p>

#### Filter By Housing Type
Returns the list of clients with the specified preferred housing type.

**Format:**
<box>

`filter h/HOUSING_TYPE`
</box>

**Example:** 

`filter h/Good Class Bungalow`.

<p align="center">
    <a href="images/filter/filterByHousingType.png">
    <img src="images/filter/filterByHousingType.png" alt="filterByHousingType" style="width:150%">
    </a>
    <em>Persons with both "Good Class Bungalow" housing preference are returned.</em>
</p>

#### Filter By Remark
Returns the list of clients whose remarks include the specified keyphrase.

**Format:**

<box>

`filter r/KEYPHRASE`
</box>

<box type="info" header="Notes">

- Partial fragments of remarks will still be matched.
  - `filter r/hand` matches person with remark "handsome".
</box>
   
<box type="warning" header="Warning">

- Input remark must be non-empty, preventing empty remark input as a request to list all clients.
- The remarks for the filter r/ command must not contain any other prefixes which could be misconstrued as additional prefixes.
</box>

<box type="wrong" header="Error">

- The command `filter r/ my tag is t/buyer` would cause an error because the system interprets t/ as the start of a new prefix.
</box>

**Example:**

`filter r/eat`.

<p align="center">
    <a href="images/filter/filterByRemarkScreenshot.png">
    <img src="images/filter/filterByRemarkScreenshot.png" alt="filterByRemarkScreenshot" style="width:150%">
    </a>
    <em>Persons with remarks like "Eats alot" and "Likes to eat nasi lemak ..." are returned.</em>
</p>

#### Filter By Birthday Month
Returns the list of clients whose birthdays fall in the specified month.

**Format:**

<box>

`filter b/MONTH`
</box>

<box type="info" header="Notes">

- Month input should be in 3-letter abbrieviation (MMM) format. 
  - "Jan" for January
  - "Feb" for February
</box>

**Example:**

`filter b/Apr`

<p align="center">
    <a href="images/filter/filterByBirthday.png">
    <img src="images/filter/filterByBirthday.png" alt="filterByBirthday" style="width:150%">
    </a>
    <em>Persons with birthday in April are returned.</em>
</p>

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

### Listing clients : `list`

Lists all clients in Realodex.

<u>Format:</u> `list`

[Back to Table of Contents](#table-of-contents)

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

[Back to Table of Contents](#table-of-contents)

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

[Back to Table of Contents](#table-of-contents)

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

[Back to Table of Contents](#table-of-contents)

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

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Exiting the program : `exit`

Exits the program and closes the window.

**Format:**
<box>exit</box>

<box type="info" header="Things to note:">

* Note that keying in `exit` followed by any random string, such as `exit wrelvwrvn` will also cause the app to exit.
  </box>

[Back to Table of Contents](#table-of-contents)

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
 be found in the `data` folder, and restart the app. A new JSON file with sample contacts will be generated and you may proceed from there.
</box>

#### Restarting with New Data
Should you want to re-enter your contacts in a fresh JSON file in the event of file corruption or a bad edit causing the format to be incorrect,
simply delete `realodex.json`, which can be found in the data folder, and restart the app. A new JSON file with sample contacts will be generated and you may proceed from there.

--------------------------------------------------------------------------------------------------------------------

## Field Constraints
Summarized in the table below are the attributes of a client along with their constraints. These constraints are important and are used for the `add`, `edit`, `delete` and `filter` functions!

| Format          | Constraints                                                                                                                                       | Example                                                                                                  |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|
| `n/Name`        | Alphanumeric, unique, case-insensitive                                                                                                            | <ul><li>✅ <code>n/John Doe</code></li><li>✅ <code>n/JoJo Siwa 1</code></li><li>❌ <code>n/Han$el</code></li></ul> |
| `p/Phone`       | Only numbers, at least 3-digit long                                                                                                     | <ul><li>✅ <code>i/1234</code></li><li>❌ <code>i/123A</code></li></ul>                                    |
| `i/Income`      | Integer and should be at least 0.                                                                                                                 | <ul><li>✅ <code>i/10000</code></li><li>❌ <code>i/10,000</code></li><li>❌ <code>i/-1</code></li></ul>     |
| `e/Email`       | Format `local-part@domain`                                                                                                                        | <ul><li>✅ <code>e/admin@realodex</code></li><li>❌ <code>e/hello@gmail</code></li></ul>                   |
| `a/Address`     | No constraints                                                                                                                                    | <ul><li>✅ <code>a/6 College Avenue West</code></li>                                                      |
| `f/Family`      | Should be an integer greater than 1                                                                                                               | <ul><li>✅ <code>f/4</code></li><li>❌ <code>f/five</code></li></ul>                                       |
| `t/Tag`         | Only accept "buyer" or "seller" as the input                                                                                                      | <ul><li>✅ <code>t/buyer</code></li></ul>                                                                 |
| `h/HousingType` | Must be one of the following: "HDB", "CONDOMINIUM", "LANDED PROPERTY", "GOOD CLASS BUNGALOW" (case-insensitive). Only one housing type is allowed. | <ul><li>✅ <code>h/HDB</code></li></ul><ul><li>❌ <code>h/big house</code></li></ul>                       |
| `r/Remark`      | Can be empty if remark is not specified.                                                                                                          | <ul><li>✅ <code>r/Likes to eat cake</code></li></ul>                                                     |
| `b/Birthday`    | `ddMMMyyyy` format. Not in the future.<br/>Not earlier than year 1000                                                                             | <ul><li>✅ <code>b/23Apr1972</code></li></ul>                                                             |

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
2. If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

--------------------------------------------------------------------------------------------------------------------
