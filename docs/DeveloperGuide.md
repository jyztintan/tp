---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# Realodex Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**



--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `LogicManager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

Realodex has implemented a dynamic delete function that either deletes user by index or by their name. Here we illustrate
deletion by index for brevity.

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `RealodexParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `RealodexParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `RealodexParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)


<puml src="diagrams/PersonClass.puml" width="800" />

<br>
<br>

In the context of our developer guide, the provided class diagram illustrates the structure of the `Person` class,
encompassing essential attributes that real-estate agents would require from their clients for official documents and for better understanding of their requirements.  
This detailed depiction allows developers
to grasp the internal composition of the `Person` entity
without needing to replicate `Person` in higher-level model interactions,
streamlining their understanding and facilitating focused development efforts.

<puml src="diagrams/ModelClassUpdated.puml" width="500"></puml>

The `Model` component,

* stores the Realodex contact data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the Realodex, which `Person` references. This allows Realodex to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassUpdated.puml" width="800" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both Realodex data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `RealodexStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.realodex.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Overall Add feature

### Implementation of `AddCommand`
The `add` feature, that was morphed from the original AddressBook3, allows users to add clients along with their critical personal information, as well as optional remarks and birthday information.

#### Details
1. The user executes the command `add n/John Doe p/98765432 i/20000 e/johnd@example.com a/311, Clementi Ave 2, #02-25 f/4 t/buyer t/seller h/HDB r/Has 3 cats b/01May2009`, intending to add a person with the specified details.
2. The AddCommandParser interprets the input
3. An AddCommand object is created.
4. The LogicManager invokes the execute method of AddCommand.
5. The execute method of AddCommand invokes the addPerson method in Model property to create new contact with the new Person object.
6. The execute method of AddCommand returns a CommandResult object which stores the data regarding the completion of the Add command.
4. The UI reflects this new list with added person.

#### Example Usage Scenario
1. The user launches the application. 
2. The user inputs `add n/John Doe p/98765432 i/20000 e/johnd@example.com a/311, Clementi Ave 2, #02-25 f/4 t/buyer t/seller h/HDB r/Has 3 cats b/01May2009`, intending to add a person with the specified details.
   3. The UI reflects this new list with added person John Doe.

#### Design Considerations

**Compulsory fields** include: Name, Phone Number, Income, Email, Address, Family Size, Buyer / Seller Tag, Housing Type 
We are cautious to choose compulsory fields that are important for real-estate agents, making them compulsory fields.
While they may not be **absolutely necessary** for all clients, we believe that the cost of missing out on these fields outweighs the hassle of making them compulsory.
For example, missing out on the family size may not be critical for a buyer who is single and is searching for a bachelor pad, but is critical for a family of 7 who needs a large enough house for all 7 of them.
These fields are also made necessary for both buyers and sellers, even though they may be intuitively be more suitable for either seller or buyer. For example, while the income may not be absolutely necessary for a seller,
it is important information if the agent would like to make recommendations for future properties for own-stay or investment purposes.

**Optional fields** include: Remark, Birthday
A real estate agent may not have any remark for a client yet, and wishes to leave Remark empty.
A real estate agent may also only want to track birthdays of their esteemed clients, and wishes to not include Birthday for the rest. 

**Field Constraints**
Specific field constraints are described below. They are designed with the users in mind.
* NAME:
  * Must be an alphanumeric string
* PHONE:
  * Should only contain numbers, and should be at least 3 digits long. This is to account for phone numbers of all types and need not necessarily be from Singapore. We avoid the '+' sign as it is not necessary and can be replaced with the international prefix
* INCOME:
  * Income should be an integer and should be at least 0. Decimals provide an unnecessary level of precision.
* EMAIL:
  * Emails should be of the format local-part@domain and adhere to the following constraints:
  1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-).
  2. The local-part may not start or end with any special characters.
  3. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods.\
     The domain name must:
    * end with a domain label at least 2 characters long
    * have each domain label start and end with alphanumeric characters
    * have each domain label consist of alphanumeric characters, separated only by hyphens, if any.
* ADDRESS:
  * String
* FAMILY:
  * Should be an integer greater than 1.
* TAG:
  * Should be restricted to case-insensitive "buyer" or "seller" using enums.
* HOUSINGTYPE: housing type a buyer wants or housing type a seller is selling
  * Should be restricted to "HDB", "CONDOMINIUM", "LANDED PROPERTY", "GOOD CLASS BUNGALOW" (case-insensitive) using enums. 
  * Only one housing type is allowed.
* REMARK:
  * Represented as a String
  * If remark is not specified, an empty string is used for representation 
* BIRTHDAY:
  * Implemented as an Optional Date, making use of Java SimpleDateFormat and Calendar classes for input and output validation.

The sequence diagram below illustrates the process of adding a person into Realodex.
<puml src="diagrams/add/AddCommandSequenceDiagram.puml" width="800" />

### Overall Sort feature

#### Initialization of `SortCommand`

The `sort` feature, introduced in version 1.4, allows users to arrange clients based on their upcoming birthday proximity, which is determined by the number of days until their next birthday relative to the current date.

To implement the sorting functionality, the `LogicManager` component parses the user's input command. Subsequently, it forwards the parsed command text to the `RealodexParser`. The RealodexParser is responsible for creating an instance of the `SortCommand`, encapsulating the logic for sorting clients based on their upcoming birthdays.

The sequence diagram below illustrates the process of creating a sort operation through the `Logic` component:
<puml src="diagrams/sort/SortSequenceDiagram-Logic.puml" width="800" />






#### Implementation of `SortCommand`


1. **Model Retrieval**: The method begins by retrieving the `Realodex` component from the provided `Model` object using the `getRealodex` method.

2. **List Copying**: Next, the method obtains a duplicate of the internal list of unique persons stored within the `Realodex` component. This is achieved by calling the `getCopyOfInternalListOfUniquePersonsList` method.

3. **Sorting**: The method proceeds to sort the copied list of persons using a `BirthdayComparator` object. This comparator compares the birthdays of two persons, ensuring that the list is arranged in ascending order based on upcoming birthdays.

4. **List Update**: After sorting the copied list, the method updates the internal list of persons within the `Realodex` component with the sorted list. This is accomplished by calling the `setPersons` method of the `Realodex` component.

5. **Command Result Creation**: Finally, the method returns a `CommandResult` object with a success message indicating the completion of the sorting operation. The success message is defined by the constant `MESSAGE_SUCCESS`.

6. **Exception Handling**: The method declares a `throws CommandException`, indicating that it may throw a `CommandException` if an error occurs during execution. However, the method implementation does not contain explicit error handling logic.
<puml src="diagrams/sort/SortSequenceDiagram-Model.puml" width="1000" />

#### Implementation of `BirthdayComparator`
The provided comparator compares two `Person` objects based on their birthdays.

1. If `o1` has an unspecified birthday (i.e., its birthday is blank), it is considered to come after `o2`.
2. If `o2` has an unspecified birthday (i.e., its birthday is blank), it is considered to come before `o1`.
3. If both `o1` and `o2` have specified birthdays, the comparator compares them based on the number of days until their next birthday.

    - If `o1`'s birthday is closer (fewer days until the next birthday) than `o2`'s birthday, `o1` is considered to come before `o2`.
    - If `o2`'s birthday is closer (fewer days until the next birthday) than `o1`'s birthday, `o2` is considered to come before `o1`.
    - If both `o1` and `o2` have the same number of days until their next birthday, their order remains unchanged.



```
    public int compare(Person o1, Person o2) {
        if (o1.getBirthday().toString().isBlank()) {
            return 1; // o1 has an unspecified birthday, so it comes after o2
        }
        if (o2.getBirthday().toString().isBlank()) {
            return -1; // o2 has an unspecified birthday, so it comes before o1
        }
        return o1.getBirthday().getDaysUntilBirthday().compareTo(o2.getBirthday().getDaysUntilBirthday());
    }
```

#### `Model` Dependency

By now, you may have noticed that `SortCommand` extensively interacts with the `Model` component to facilitate list sorting during execution. Consequently, `SortCommand` depends on `ModelManager`, which is an implementation of the `Model` interface. This dependency arises because `ModelManager` instances are passed as arguments in the `public CommandResult execute(Model model) throws CommandException` method of `SortCommand`. For brevity, interactions beyond the `Model` layer are not detailed.
<puml src="diagrams/sort/SortCommandClassDiagram-Model.puml" width="300" />


#### [Proposed] Sort Features Beyond v1.4

The `sort` functionality is poised for exciting developments in the future. Although initially focused on sorting 
clients based on their birthdays to bolster client relationships in a **breadth-first development** approach, 
we have ambitious plans to extend this feature to other fields. With clients having diverse attributes 
such as income and housing preferences, implementing `sort` for these fields is definitely on our roadmap.

#### Initialization of new `SortCommand`

To enhance the sorting functionality, we're introducing the capability to sort based on various fields specified by the user. 
The proposed command format is `sort field`, where `field` represents the attribute by which the sorting will be performed. 
For instance, users can execute commands like `sort birthday`, `sort income`, or `sort housepref`.

The following sequence diagram illustrates the process
of introducing this new `sort` operation through the `Logic` component,
with user-specified fields.

The ref frame sequence diagram is omitted here,
as it's similar to the [sorting](#implementation-of-sortcommand) sequence illustrated earlier. 
Instead of using the `BirthdayComparator`,
we'll utilize different comparators based on the user's specified field, such as `IncomeComparator`.

<puml src="diagrams/sort/NewSortSequenceDiagram-Logic.puml" width="1000" />






--------------------------------------------------------------------------------------------------------------------

### Overall Filter feature

#### Implementation
This feature enables users to filter and view persons in Realodex based on specified criteria such as name, remark, tag, birthday, and housing type.
The key components involved are:

- `FilterCommand`: Executes the filtering operation based on a provided predicate that encapsulates the filtering criteria.
- `PrefixChecker`: Ensures the syntactic correctness of user inputs by verifying command prefixes. 
Detects command format violations, and facilitates clear error messaging.
- `FilterCommandParser`: Parses user input into a FilterCommand by identifying the filtering field and keyphrase. 
- `PredicateProducer`: Generates specific predicates based on the identified field and keyphrase. 
- `Predicates`: `NameContainsKeyphrasePredicate`, `RemarkContainsKeyphrasePredicate`, `TagsMatchPredicate`, `BirthdayIsInMonthPredicate`, and `HousingTypeMatchPredicate` 
that determine if a person's attributes match the user-defined criteria.

#### Filter Command Architecture
<puml src="diagrams/filter/FilterFeatureArchitecture.puml" width="1000" />

### Filter by Name feature

#### Description

The Filter by Name feature allows users to filter the list of persons in Realodex based on their names. 
This is implemented using the `NameContainsKeyphrasePredicate` that checks if a person's name contains the keyphrase provided by the user. 

#### Example Usage Scenario
1. The user executes the command `filter n/John`, intending to filter out persons whose names contain "John". 
2. The FilterCommandParser interprets the input, creating a FilterCommand with the `NameContainsKeyphrasePredicate`. 
3. The `FilterCommand` applies the `NameContainsKeyphrasePredicate` predicate, updating the filtered person list to only include those whose names contain "John".
4. The UI reflects this filtered list.

#### Design considerations

Aspect: Handling of partial names

**Alternative 1 (current choice): Allow partial matches of names.**

> For example, `filter n/Jo` returns persons with names "John", "Bonjovi", etc.

Pros: More flexible search.

Cons: May return too many results for very short keyphrases.

**Alternative 2: Require exact matches.**

> For example, `filter n/Jo` only returns persons with names "Jo"

Pros: Precise filtering.

Cons: Less flexible; users must remember exact names.

### Filter by Remark Feature

#### Description

The Filter by Remark feature allows users to filter the list of persons in Realodex based on the remarks associated with them.
This is implemented using the `RemarkContainsKeyphrasePredicate` that checks if a person's remarks includes the keyphrase provided by the user.

#### Example Usage Scenario

1. The user executes the command `filter r/Loves coding`, intending to filter out to filter out persons whose remarks include "Loves coding".
2. The `FilterCommandParser` interprets the input, creating a FilterCommand with a `RemarkContainsKeyphrasePredicate`.
3. The `FilterCommand` applies the `RemarkContainsKeyphrasePredicate` predicate, updating the filtered person list to only include those whose remarks contain "Loves coding".
4. The UI reflects this filtered list.

#### Design considerations

Aspect: Handling of partial names

**Alternative 1 (current choice): Allow partial matches for remarks.**

> For example, `filter r/love` returns persons with remarks like "Loves coding" and "Has a lovely dog".

Pros: More flexible search.

Cons: May return too many results for common keyphrases.

**Alternative 2: Require exact matches.**

> For example, `filter r/Loves Coding` only returns persons with the exact remark "Loves coding".

Pros: Ensures that only persons with specific remarks are listed, reducing clutter.

Cons: Extremely limiting. Users must remember exact remarks.

### Filter by Tag Feature

#### Description

The Filter by Tag feature allows users to filter the list of persons in Realodex based on their tags.
This is implemented using the `TagsMatchPredicate` that checks whether a person's tags match the tag(s) specified by the user. 

#### Example Usage Scenario

1. The user executes the command `filter t/Buyer`, intending to filter out to filter out persons who are tagged as "Buyer".
2. The `FilterCommandParser` interprets the input, creating a FilterCommand with a `TagsMatchPredicate`.
3. The `FilterCommand` applies the `TagsMatchPredicate` predicate, updating the filtered person list to only include those who are tagged as "Buyer".
4. The UI reflects this filtered list.

**Alternative 1 (current choice): Allow inclusion of persons with matching tags, irrespective of other tags.**

> For example, `filter t/Buyer` returns persons tagged as "Buyer", inclusing those tagged as "Buyer" and "Seller".

Pros: Inclusive Search that returns anyone with the "Buyer" tag, increasing breadth of search outcomes.

Cons: Reduced precision in cases where users want to find persons exclusively tagged with this specific tag.

**Alternative 2: All tag inputs must strictly match without.**

> For example, `filter t/Buyer` only returns persons tagged solely as "Buyer" and excludes persons tagged as both "Buyer" and "Seller".

Pros: Increase precision of search results for targeted searches.

Cons: Excludes potentially relevant persons who carry the specified tag alongside others.

### Filter by Birthday Feature

#### Implementation

The Filter by Birthday feature allows users to filter the list of persons in Realodex based on their birthday month.
This is implemented using the `BirthdayIsInMonthPredicate` that checks whether a person's birthday matches the month specified by the user.

#### Example Usage Scenario

1. The user executes the command `filter b/Jan`, intending to filter out to filter out persons with birthdays in January.
2. The `FilterCommandParser` interprets the input, creating a FilterCommand with a `BirthdayIsInMonthPredicate`.
3. The `FilterCommand` applies the `BirthdayIsInMonthPredicate` predicate, updating the filtered person list to only include those with birthday in January.
4. The UI reflects this filtered list.

**Alternative 1 (current choice): Filter by birthday month.**

> For example, `filter b/Jan` returns all persons born in January, regardless of the day.

Pros: Simplifies the birthday search process, making it easier to remember and use.

Cons: Less precise, might include unwanted results from the entire month.

**Alternative 2: Require exact birthday matches.**

> For example, `filter b/1Jan` only returns persons born on January 1st.

Pros: Increase precision of search results, finding persons with specific birth dates.

Cons: Requires exact date knowledge, which may not always be available or remembered by users.

### Filter by Housing Type Feature

#### Implementation

The Filter by Housing Type feature allows users to filter the list of persons in Realodex based on their preferred housing type.
This is implemented using the `HousingTypeMatchPredicate` that checks whether a person's preferred housing type matches the housing type specified by the user.

#### Example Usage Scenario
1. The user executes the command `filter h/Condominium`, intending to filter out persons with a "Condominium" housing type preference.
2. The `FilterCommandParser` interprets the input, creating a FilterCommand with a `HousingTypeMatchPredicate`.
3. The `FilterCommand` applies the `HousingTypeMatchPredicate` predicate, updating the filtered person list to only include those with a "Condominium" housing type preference.
4. The UI reflects this filtered list.


### Help feature

####  Implementation

The Help feature provides help to the user by giving details on how all commands are used in a new window. 
The core components for this feature are:
- HelpCommand: A command that, when executed, either shows a new window summarising help for all commands, or
prints the help message in the Main Window for the requested command, depending on user input.
- HelpCommandParser: Processes the user input to instantiate the HelpCommand object appropriately to perform the
correct action (the type of help to give, in this case help for all commands).

#### Example Usage Scenario
1. User launches the application.
2. User executes `help`, wanting to get the help for all commands.
3. LogicManager instantiates a RealodexParser, which parses the command into a HelpCommand.
4. The HelpCommand is executed, showing a new window with help for all the features in Realodex.
5. The GUI reflects that the help window is currently open.

#### Design Considerations

Aspect: Information to include in the Help Window

__Alternative 1 (current choice): Includes summary of ways to use all commands.__

Pros: User does not need to leave the app to get the appropriate help, and can visit the UG if he/she needs more information.

Cons: May be lengthy and hard to find when the set of commands added becomes larger in the future.

__Alternative 2: Only include link to User Guide in the help window.__

Pros: Help window does not have too much information.

Cons: User will need to leave the application and look at a website everytime they require help which can be inconvenient.

### Help by command feature

####  Implementation

The Help by command feature provides help to the user for an individual command specified by the user.
The core components for this feature are:
- HelpCommand: A command that, when executed, either shows a new window summarising help for all commands, or
  prints the help message in the Main Window for the requested command, depending on user input.
- HelpCommandParser: Processes the user input to instantiate the HelpCommand object appropriately to perform the
  correct action (the type of help to give, in this case for individual commands).
- Note that although the command format is `COMMAND help`, `clear help` is the command to get the help for the clearRealodex command instead of `clearRealodex help`.
We changed the `clear` command to `clearRealodex` to avoid confusion with the `delete` command, as both involve the removal of entries, and `clearRealodex`
encapsulates the functionality of clearing the entire app more clearly. However, we kept `clear help` as this syntax is more user-friendly when seeking help.

#### Example Usage Scenario 
1. User launches the application.
2. User executes `COMMAND help`, wanting to get the help for only specified `COMMAND`.
3. LogicManager instantiates a RealodexPraser, which parses the command into a HelpCommand with appropriate parameters.
4. The HelpCommand is executed, printing the help message for the specified `COMMAND` in the GUI.

#### Design Considerations

Aspect: Method to request for help

__Alternative 1 (current choice): Format is `COMMAND help`.__

Pros: Intuitive syntax for the user, and is consistent with other CLI-based applications.

Cons: Harder to implement and maintain as a Developer as awareness of how other commands are currently being parsed is needed to preserve functionality.

__Alternative 2: Format is `help COMMAND`.__

Pros: Easy to implement as all functionality can be contained within help-related classes only.

Cons: Syntax may not be as intuitive.


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* A real estate agent
* has a need to manage a significant number of contacts of their clients
* has to note down many details about each client
* has to frequently add, delete, and search for clients
* prefer desktop apps over other types
* can type fast
* is reasonably comfortable using CLI apps
* is a real estate agent that wants to store relevant information about clients
* able to store additional notes about contacts

**Value proposition**:
* manage contacts faster than a typical mouse/GUI driven applications.
* storing of information tailored to real-estate agents


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                    | I want to …​                                             | So that I can…​                                                                                   |
|----------|----------------------------|----------------------------------------------------------|---------------------------------------------------------------------------------------------------|
| `* * *`  | first-time user            | receive a simple tutorial on app usage                    | easily navigate Realodex                                                                          |
| `* * *`  | tech-savvy user            | use a command-line interface                              | navigate the app more efficiently due to my fast typing speed                                    |
| `* * *`  | fast typer                 | quickly input various commands in the text box           | perform actions like adding new clients, editing profiles, finding clients, without using GUI    |
| `* * *`  | real-estate agent user     | easily log personal notes after client interactions       | reference these in future conversations for more personalized communication                     |
| `* * *`  | user with inactive clients | delete inactive clients permanently                      | remove them from my database and free up space                                                   |
| `* *`    | real estate agent user     | search for clients interested in specific property listings | quickly match selling and buying clients                                                         |
| `* *`    | real estate agent user     | record and access clients' preferred house types          | filter and match clients with relevant property listings                                         |
| `* *`    | real estate agent user     | analyze trends in housing preferences                     | understand market demands and tailor my services                                                 |
| `* *`    | efficient user             | filter clients by tag                                    | organize and access client information more efficiently                                           |
| `* *`    | efficient user             | filter clients by categories                             | better categorize and manage client information based on personal attributes                     |
| `* *`    | first-time user            | be guided through setting up my user profile              | save my details for future use                                                                   |
| `* *`    | first-time user            | learn how to create and edit client profiles              | manage client information efficiently                                                            |
| `* *`    | first-time user            | understand how to navigate the app and use CLI commands   | effectively use Realodex's features                                                              |
| `* *`    | forgetful user             | get instructions on how to set up profiles and navigate  | refresh my memory on how to use Realodex when needed                                             |
| `* *`    | user with inactive clients | archive inactive clients                                 | hide them from my active list while keeping their information for future reference               |
| `* `     | real estate agent user     | be notified of upcoming client birthdays                  | send personalized greetings and strengthen my relationships                                      |
| `*`      | real estate agent user     | be notified of upcoming holidays                          | prepare gifts for my clients and enhance our relationship                                        |
| `*`      | real estate agent user     | be reminded of significant client milestones              | acknowledge these events and further personalize our relationship                                |
| `*`      | tech-savvy user            | use tab to autofill parts of my command                  | speed up my use of the command line                                                              |


### Use cases

(For all use cases below, the **System** is Realodex and the **Actor** is the user, unless specified otherwise)


**Use case: UC01 — Adding a user profile**

**Actor: User**

**MSS**

1. User Executes `add ...` Command:
2. System adds user profile to `Realodex` and replies to user with a success message.
    Use case ends.

**Extensions**

* 1a. `Name` does not contain fully alphanumeric characters.
    * 1a1. Realodex throws an error and highlights the format to user.
    * 1a2. User enters new data.
    * Use case resumes from step 1.

* 1b. `Name` contains erroneous whitespace at front or back.
    * 1b1. Realodex fixes this for user without errors.
    * Use case ends.

* 1c. `Name` is not capitalized.
    * 1c1. Realodex fixes this for user without errors.
    * Use case ends.

* 1d. `Name` is blank.
    * 1d1. Realodex throws an error and highlights the format to user.
    * 1d2. User enters new data.
    * Use case resumes from step 1.

* 1e. `Phone` contains non-integer characters.
    * 1e1. Realodex throws an error and highlights the format to user.
    * 1e2. User enters new data.
    * Use case resumes from step 1.

* 1f. `Phone` is less than three characters.
    * 1f1. Realodex throws an error and highlights the format to user.
    * 1f2. User enters new data.
    * Use case resumes from step 1.

* 1g. `Phone` is blank.
    * 1g1. Realodex throws an error and highlights the format to user.
    * 1g2. User enters new data.
    * Use case resumes from step 1.

* 1h. `Income` is negative
    * 1h1. Realodex throws an error and highlights the format to user.
    * 1h2. User enters new data.
    * Use case ends.

* 1i. `Income` contains non-integer characters.
    * 1i1. Realodex throws an error and highlights the format to user.
    * 1i2. User enters new data.
    * Use case resumes from step 1.

* 1j. `Income` is blank.
    * 1j1. Realodex throws an error and highlights the format to user.
    * 1j2. User enters new data.
    * Use case ends.

* 1k. `Email` is not in the valid format.
    * 1k1. Realodex throws an error and highlights the format to user.
    * 1k2. User enters new data.
    * Use case resumes from step 1.

* 1l. `Email` is blank.
    * 1l1. Realodex throws an error and highlights the format to user.
    * 1l2. User enters new data.
    * Use case resumes from step 1.

* 1m. `Address` is blank.
    * 1m1. Realodex throws an error and highlights the format to user.
    * 1m2. User enters new data.
    * Use case resumes from step 1.

* 1n. `Family` contains non-integer characters.
    * 1n1. Realodex throws an error and highlights the format to user.
    * 1n2. User enters new data.
    * Use case resumes from step 1.

* 1o. `Family` is negative or zero.
    * 1o1. Realodex throws an error and highlights the format to user.
    * 1o2. User enters new data.
    * Use case resumes from step 1.

* 1p. `Family` is blank.
    * 1p1. Realodex throws an error and highlights the format to user.
    * 1p2. User enters new data.
    * Use case resumes from step 1.

* 1q. `Tag` is not `buyer` or `seller`.
    * 1q1. Realodex throws an error and highlights the format to user.
    * 1q2. User enters new data.
    * Use case resumes from step 1.

* 1r. `Tag` is blank.
    * 1r1. Realodex throws an error and highlights the format to user.
    * 1r2. User enters new data.
    * Use case resumes from step 1.

* 1s. `HOUSING_TYPE` is not in any of 'HDB', 'CONDOMINIUM', 'LANDED PROPERTY' or 'GOOD CLASS BUNGALOW'.
    * 1s1. Realodex throws an error and highlights the format to user.
    * 1s2. User enters new data.
    * Use case resumes from step 1.

* 1t. `HOUSING_TYPE` is blank.
    * 1t1. Realodex throws an error and highlights the format to user.
    * 1t2. User enters new data.
    * Use case resumes from step 1.

* 1u. `Birthday` is not in the valid format.
    * 1u1. Realodex throws an error and highlights the format to user.
    * 1u2. User enters new data.
    * Use case resumes from step 1.

* 1v. `Birthday` is blank.
    * 1v1. Realodex throws an error and highlights the format to user.
    * 1v2. User enters new data.
    * Use case resumes from step 1.

* 1w. Some compulsory fields are missing.
    * 1w1. Realodex throws an error and highlights the format to user.
    * 1w2. User enters new data.
    * Use case resumes from step 1.


**Use case: UC02 — Editing a user profile**

**Actor: User**

**MSS**

1. User Executes `edit ...` Command:
2. System edits user profile of `Realodex` and replies to user with a success message.
   Use case ends.

**Extensions**

* 1a. `Name` does not contain fully alphanumeric characters.
    * 1a1. Realodex throws an error and highlights the format to user.
    * 1a2. User enters new data.
    * Use case resumes from step 1.

* 1b. `Name` contains erroneous whitespace at front or back.
    * 1b1. Realodex fixes this for user without errors.
    * Use case ends.

* 1c. `Name` is not capitalized.
    * 1c1. Realodex fixes this for user without errors.
    * Use case ends.

* 1d. `Name` is blank.
    * 1d1. Realodex throws an error and highlights the format to user.
    * 1d2. User enters new data.
    * Use case resumes from step 1.

* 1e. `Phone` contains non-integer characters.
    * 1e1. Realodex throws an error and highlights the format to user.
    * 1e2. User enters new data.
    * Use case resumes from step 1.

* 1f. `Phone` is less than three characters.
    * 1f1. Realodex throws an error and highlights the format to user.
    * 1f2. User enters new data.
    * Use case resumes from step 1.

* 1g. `Phone` is blank.
    * 1g1. Realodex throws an error and highlights the format to user.
    * 1g2. User enters new data.
    * Use case resumes from step 1.

* 1h. `Income` is negative
    * 1h1. Realodex throws an error and highlights the format to user.
    * 1h2. User enters new data.
    * Use case ends.

* 1i. `Income` contains non-integer characters.
    * 1i1. Realodex throws an error and highlights the format to user.
    * 1i2. User enters new data.
    * Use case resumes from step 1.

* 1j. `Income` is blank.
    * 1j1. Realodex throws an error and highlights the format to user.
    * 1j2. User enters new data.
    * Use case ends.

* 1k. `Email` is not in the valid format.
    * 1k1. Realodex throws an error and highlights the format to user.
    * 1k2. User enters new data.
    * Use case resumes from step 1.

* 1l. `Email` is blank.
    * 1l1. Realodex throws an error and highlights the format to user.
    * 1l2. User enters new data.
    * Use case resumes from step 1.

* 1m. `Address` is blank.
    * 1m1. Realodex throws an error and highlights the format to user.
    * 1m2. User enters new data.
    * Use case resumes from step 1.

* 1n. `Family` contains non-integer characters.
    * 1n1. Realodex throws an error and highlights the format to user.
    * 1n2. User enters new data.
    * Use case resumes from step 1.

* 1o. `Family` is negative or zero.
    * 1o1. Realodex throws an error and highlights the format to user.
    * 1o2. User enters new data.
    * Use case resumes from step 1.

* 1p. `Family` is blank.
    * 1p1. Realodex throws an error and highlights the format to user.
    * 1p2. User enters new data.
    * Use case resumes from step 1.

* 1q. `Tag` is not `buyer` or `seller`.
    * 1q1. Realodex throws an error and highlights the format to user.
    * 1q2. User enters new data.
    * Use case resumes from step 1.

* 1r. `Tag` is blank.
    * 1r1. Realodex throws an error and highlights the format to user.
    * 1r2. User enters new data.
    * Use case resumes from step 1.

* 1s. `HOUSING_TYPE` is not in any of 'HDB', 'CONDOMINIUM', 'LANDED PROPERTY' or 'GOOD CLASS BUNGALOW'.
    * 1s1. Realodex throws an error and highlights the format to user.
    * 1s2. User enters new data.
    * Use case resumes from step 1.

* 1t. `HOUSING_TYPE` is blank.
    * 1t1. Realodex throws an error and highlights the format to user.
    * 1t2. User enters new data.
    * Use case resumes from step 1.

* 1u. `Birthday` is not in the valid format.
    * 1u1. Realodex throws an error and highlights the format to user.
    * 1u2. User enters new data.
    * Use case resumes from step 1.

* 1v. `Birthday` is blank.
    * 1v1. Realodex throws an error and highlights the format to user.
    * 1v2. User enters new data.
    * Use case resumes from step 1.

* 1w. No fields input.
    * 1w1. Realodex throws an error and highlights the format to user.
    * 1w2. User enters new data.
    * Use case resumes from step 1.


**Use case: UC03 - Delete a person**

**MSS**

1.  User requests to delete user
2.  Realodex deletes the person with success message

    Use case ends.

**Extensions**

* 2a. The input name is not found
 * 2a1. Realodex shows an error message "<Name> is not found".
 * Use case ends.

**Use case: UC04 — Sort list by birthday**

**MSS**

1.  User requests to sort the list by the nearest upcoming birthday.
2.  Realodex sorts the list and returns the sorted list to screen.

    Use case ends.

**Use case: List**

**MSS**

1.  User requests to list
2.  Realodex shows the list of all clients

    Use case ends.

**Extensions**

* 2a. The list is empty
  * 2a1. Realodex shows an empty list.
  * Use case ends.

**Use case: Filter by Name**

**MSS**

1. User requests to filter clients by providing a name substring. 
2. Realodex filters and displays a list of all clients whose names include the input substring.

    Use case ends.

**Extensions**

* 1a. The input substring is empty.

    * 1a1. Realodex shows an error message indicating that the filter criteria cannot be empty.

        Use case ends.

* 1b. No clients' names match the input substring.

    * 1b1. Realodex displays an empty list and shows a message indicating that no matches were found.
        
      Use case ends.

**Use case: Filter by Remarks**

**MSS**

1. User requests to filter clients by providing a remark substring. 
2. Realodex filters and displays a list of all clients whose remarks include the input substring.
   Use case ends.

**Extensions**

* 1a. The input substring is empty.

    * 1a1. Realodex shows an error message indicating that the filter criteria cannot be empty.

      Use case ends.

* 1b. No clients' remarks match the input substring.

    * 1b1. Realodex displays an empty list and shows a message indicating that no matches were found.

      Use case ends.

**Use case: Getting help**

**MSS**

1. User requests for help.
2. Realodex displays a new window showing a summary of how all features are used with examples. 

   Use case ends.


**MSS**

1. User requests for help for a specific command.
2. A string summarising how that individual command is used with examples is displayed on the main window.

**Extensions**

* 1a. The requested command does not exist.

    * 1a1. Realodex shows an error message command does not exist.

      Use case ends.

  
### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A real estate agent with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should be able to have up to 500 client profiles.
5.  The response to any command should become visible within 5 seconds.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Client Profile**: Details of customer of the Real Esate Agent looking to buy / sell / rent a property
* **Command Line Interface (CLI)**: A text-based interface used to interact with the software by entering commands into a terminal or console window, typically preferred by users who prefer efficiency and automation.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar realodex.jar` command to run the application.
      The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. Exiting the app

   1. Users can type in exit to exit the app. All data is auto-saved.

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Editing a person

1. Editing a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `edit 1 n/John Doe`<br>
      Expected: First contact is updated with the new name. Details of the updated contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `edit 0 n/John Doe`<br>
      Expected: No person is updated. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect edit commands to try: `edit`, `edit x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Corrupted Data

1. Dealing with missing/corrupted data files

   1. Should you want to re-enter your contacts in a fresh JSON file in the event of file corruption or a bad edit
      causing the format to be incorrect,
      simply delete `realodex.json` in the `data` directory and restart the app.
      A new JSON file with sample contacts will be generated and you may proceed from there.


### Save Data

1. Data is auto-saved in the `json` file in real time.

   1. Open up the `realodex.json` in the `data` directory in a text editor.

   2. If there is an existing user, try `delete index` where index is of that user.
      <br>Expected: This user will no longer appear in the `json` file after command is executed.
   3. If there is no existing user, you may want to refer to above "Corrupted Data" section 
      to easily get a fresh `json` file with sample data and repeat from step 1.


## **Appendix: Appendix: Effort**

### Difficulty, challenges, efforts and achievements


## **Appendix: Planned Enhancements**

**Team size is five.**

1. Generic index error messages
   - Currently, `Realodex` has a generic error message for invalid index input by users.
     <br>e.g. if user inputs `edit -1 n/someName`, `Realodex` replies "Invalid command format! ..."
     <br>e.g. if user inputs `delete -1`, `Realodex` replies "Index is not a non-zero unsigned integer.".
   - In future enhancements, we aim to reply users with more descriptive error messages, such as<br>
     "Index is negative, please input a integer index value that is > 0"<br>
     "Index is out of bounds, please input a index value in range of the list indexes on screen"
