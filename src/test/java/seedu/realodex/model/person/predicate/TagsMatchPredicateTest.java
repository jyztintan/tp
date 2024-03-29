package seedu.realodex.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.realodex.model.person.Person;
import seedu.realodex.model.person.predicates.NameContainsKeyphrasePredicate;
import seedu.realodex.model.person.predicates.TagsMatchPredicate;
import seedu.realodex.model.tag.Tag;
import seedu.realodex.testutil.PersonBuilder;


public class TagsMatchPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyphrase = "first";
        String secondPredicateKeyphrase = "first second";

        NameContainsKeyphrasePredicate firstPredicate = new NameContainsKeyphrasePredicate(firstPredicateKeyphrase);
        NameContainsKeyphrasePredicate secondPredicate = new NameContainsKeyphrasePredicate(secondPredicateKeyphrase);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeyphrasePredicate firstPredicateCopy = new NameContainsKeyphrasePredicate(firstPredicateKeyphrase);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    void test_personTagsMatchPredicateTags_returnsTrue() {
        Set<Tag> predicateTags = Set.of(new Tag("buyer"));
        TagsMatchPredicate predicate = new TagsMatchPredicate(predicateTags);

        Person person = new PersonBuilder().withTags("buyer").build();

        assertTrue(predicate.test(person));
    }

    @Test
    void test_personMultipleTagsIncludingPredicateTags_returnsTrue() {
        Set<Tag> predicateTags = Set.of(new Tag("buyer"));
        TagsMatchPredicate predicate = new TagsMatchPredicate(predicateTags);

        Person person = new PersonBuilder().withTags("buyer", "seller").build();

        assertTrue(predicate.test(person));
    }

    @Test
    void test_personMultipleTagsIncludingMultiplePredicateTags_returnsTrue() {
        Set<Tag> predicateTags = Set.of(new Tag("buyer"), new Tag("seller"));
        TagsMatchPredicate predicate = new TagsMatchPredicate(predicateTags);

        Person person = new PersonBuilder().withTags("buyer", "seller").build();

        assertTrue(predicate.test(person));
    }

    @Test
    void test_personNoTagsDontMatchPredicateTags_returnsFalse() {

        Set<Tag> predicateTags = Set.of(new Tag("buyer"));
        TagsMatchPredicate predicate = new TagsMatchPredicate(predicateTags);

        Person person = new PersonBuilder().withTags("").build();

        assertFalse(predicate.test(person));
    }

    @Test
    void test_personTagsDontMatchPredicateTags_returnsFalse() {

        Set<Tag> predicateTags = Set.of(new Tag("buyer"));
        TagsMatchPredicate predicate = new TagsMatchPredicate(predicateTags);

        Person person = new PersonBuilder().withTags("seller").build();

        assertFalse(predicate.test(person));
    }

    @Test
    void test_personTagsDontMatchMultiplePredicateTags_returnsFalse() {

        Set<Tag> predicateTags = Set.of(new Tag("buyer"), new Tag("seller"));
        TagsMatchPredicate predicate = new TagsMatchPredicate(predicateTags);

        Person person = new PersonBuilder().withTags("seller").build();

        assertFalse(predicate.test(person));
    }

    @Test
    public void toStringMethod() {
        Set<Tag> tagSet = Set.of(new Tag("buyer"), new Tag("seller"));
        TagsMatchPredicate predicate = new TagsMatchPredicate(tagSet);
        String expected = TagsMatchPredicate.class.getCanonicalName() + "{Tag Set=" + tagSet + "}";
        assertEquals(expected, predicate.toString());
    }
}
