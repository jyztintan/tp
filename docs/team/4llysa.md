---
  layout: default.md
  title: "Allysa Tan's Project Portfolio Page"
---

### Project: Realodex

Realodex is a desktop contact management application for real estate agents. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add Tags to clients.
    * What it does: allows the user to add 'Buyer' or 'Seller' tags.
    * Justification: This feature improves the product significantly because the real-estate agent user can clearly differentiate the buyer and sellers. 
    * Highlights: This enhancement affects existing commands and commands to be added in the future. It required the non-trivial use of Enums. The implementation required changes to existing commands.

* **New Feature**: Added the ability to add Birthdays to clients.
  * What it does: allows the user to optionally add Birthdays.
  * Justification: This feature improves the product significantly because the real-estate agent can increase his personal touch
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives for Birthday. The implementation required changes to existing commands.

* **New Feature**: Added the ability to filter clients by birth month.
  * What it does: allows the user to optionally add Birthdays.
  * Justification: This feature improves the product significantly because a user can remember
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives . The implementation required changes to existing commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=4llysa&tabRepo=AY2324S2-CS2103T-W10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Updated the GUI color scheme and layout (Pull requests [\#164](),[\#227]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `add` [\#213]()
        * Standardised input formats for fields that are required by multiple commands [\#236]()
    * Developer Guide:
        * Added implementation details of the `add` feature.

* **Community**:
    * Review comments: 49

* **Tools**:
  * MarkBind for team website