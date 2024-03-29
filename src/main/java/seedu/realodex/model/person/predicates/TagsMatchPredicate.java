package seedu.realodex.model.person.predicates;

import java.util.Set;
import java.util.function.Predicate;

import seedu.realodex.commons.util.ToStringBuilder;
import seedu.realodex.model.person.Person;
import seedu.realodex.model.tag.Tag;


/**
 * Tests that a {@code Person}'s {@code Name} contains the keyphrase given.
 */
public class TagsMatchPredicate implements Predicate<Person> {
    private final Set<Tag> tagSet;

    public TagsMatchPredicate(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public boolean test(Person person) {
        Set<Tag> personTags = person.getTags();
        return personTags.containsAll(tagSet);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagsMatchPredicate)) {
            return false;
        }

        TagsMatchPredicate otherTagsMatchPredicate = (TagsMatchPredicate) other;
        return tagSet.equals(otherTagsMatchPredicate.tagSet);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("Tag Set", tagSet).toString();
    }

}
