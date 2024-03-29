package seedu.realodex.model;

import static java.util.Objects.requireNonNull;
import javafx.collections.transformation.SortedList;
import static seedu.realodex.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.realodex.commons.core.GuiSettings;
import seedu.realodex.commons.core.LogsCenter;
import seedu.realodex.model.person.Person;

/**
 * Represents the in-memory model of the realodex data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static final Comparator<Person> COMPARATOR_DEFAULT = (x,y) -> 0;

    private final Realodex realodex;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final SortedList<Person> sortedPersons;
    private ObservableList<Person> lastFilteredOrSortedPersons;
    private boolean justFiltered = true;

    /**
     * Initializes a ModelManager with the given realodex and userPrefs.
     */
    public ModelManager(ReadOnlyRealodex realodex, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(realodex, userPrefs);

        logger.fine("Initializing with realodex: " + realodex + " and user prefs " + userPrefs);

        this.realodex = new Realodex(realodex);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.realodex.getPersonList());
        sortedPersons = new SortedList<>(this.realodex.getPersonList());
        lastFilteredOrSortedPersons = filteredPersons;
    }

    public ModelManager() {
        this(new Realodex(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getRealodexFilePath() {
        return userPrefs.getRealodexFilePath();
    }

    @Override
    public void setRealodexFilePath(Path realodexFilePath) {
        requireNonNull(realodexFilePath);
        userPrefs.setRealodexFilePath(realodexFilePath);
    }

    //=========== Realodex ================================================================================

    @Override
    public void setRealodex(ReadOnlyRealodex realodex) {
        this.realodex.resetData(realodex);
    }

    @Override
    public ReadOnlyRealodex getRealodex() {
        return realodex;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return realodex.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        realodex.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        realodex.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        updateSortedPersonList(COMPARATOR_DEFAULT);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        realodex.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public ObservableList<Person> getSortedPersonList() {
        return sortedPersons;
    }

    @Override
    public ObservableList<Person> getLastFilteredOrSortedPersonList() {
//        return lastFilteredOrSortedPersons;
        if (justFiltered) {
            return filteredPersons;
        } return sortedPersons;
    }
    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
        justFiltered = true;
        lastFilteredOrSortedPersons = getFilteredPersonList();
    }

    @Override
    public void updateSortedPersonList(Comparator<Person> comparator) {
        requireNonNull(comparator);
        sortedPersons.setComparator(comparator);
        justFiltered = false;
        lastFilteredOrSortedPersons = getSortedPersonList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return realodex.equals(otherModelManager.realodex)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && sortedPersons.equals(otherModelManager.sortedPersons);
    }

}
