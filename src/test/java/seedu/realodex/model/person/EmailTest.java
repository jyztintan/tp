package seedu.realodex.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // blank email
        assertFalse(Email.isValidEmail("")); // empty string
        assertFalse(Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Email.isValidEmail("@example.com")); // missing local part
        assertFalse(Email.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(Email.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Email.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(Email.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Email.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(Email.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Email.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(Email.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(Email.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(Email.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Email.isValidEmail("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(Email.isValidEmail("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(Email.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Email.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Email.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Email.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Email.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(Email.isValidEmail("peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(Email.isValidEmail("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(Email.isValidEmail("PeterJack.1190@example.com")); // period in local part
        assertTrue(Email.isValidEmail("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(Email.isValidEmail("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(Email.isValidEmail("a@bc")); // minimal
        assertTrue(Email.isValidEmail("test@localhost")); // alphabets only
        assertTrue(Email.isValidEmail("123@145")); // numeric local part and domain name
        assertTrue(Email.isValidEmail("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(Email.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Email.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(Email.isValidEmail("e1234567@u.nus.edu")); // more than one period in domain


        assertTrue(Email.isValidEmail("u+++u@gmail.com")); // multiple consecutive special characters
        assertTrue(Email.isValidEmail("test..email@example.com")); // consecutive periods
        assertTrue(Email.isValidEmail("john.doe+test@example.com")); // consecutive special characters with alphanumeric characters

        assertTrue(Email.isValidEmail("a+b@example.com")); // '+' symbol in local-part
        assertTrue(Email.isValidEmail("a_b@example.com")); // '_' symbol in local-part
        assertTrue(Email.isValidEmail("a-b@example.com")); // '-' symbol in local-part
        assertTrue(Email.isValidEmail("a.b@example.com")); // '.' symbol in local-part

        assertTrue(Email.isValidEmail("user@localhost")); // localhost domain
        assertTrue(Email.isValidEmail("user@sub.domain.example.com")); // subdomains

        assertTrue(Email.isValidEmail("user@example.co.uk")); // UK top-level domain
        assertTrue(Email.isValidEmail("user@example.travel")); // .travel top-level domain
        assertTrue(Email.isValidEmail("user@example.museum")); // .museum top-level domain

        assertTrue(Email.isValidEmail("peter_jack+1190@example.com")); // mixture of alphanumeric and special characters
        assertTrue(Email.isValidEmail("user1@example.com")); // numeric local-part
        assertTrue(Email.isValidEmail("user1@example1.com")); // numeric domain name


        // additional symbols that are not supported
        assertFalse(Email.isValidEmail("user%name@example.com")); // '%'
        assertFalse(Email.isValidEmail("user!name@example.com")); // '!'
        assertFalse(Email.isValidEmail("user#name@example.com")); // '#'
        assertFalse(Email.isValidEmail("user$name@example.com")); // '$'
        assertFalse(Email.isValidEmail("user^name@example.com")); // '^'
        assertFalse(Email.isValidEmail("user&name@example.com")); // '&'
        assertFalse(Email.isValidEmail("user*name@example.com")); // '*'
        assertFalse(Email.isValidEmail("user=name@example.com")); // '='
        assertFalse(Email.isValidEmail("user?name@example.com")); // '?'

        // consecutive symbols
        assertTrue(Email.isValidEmail("user..name@example.com")); // consecutive periods
        assertTrue(Email.isValidEmail("user__name@example.com")); // consecutive underscores
        assertTrue(Email.isValidEmail("user--name@example.com")); // consecutive hyphens
        assertTrue(Email.isValidEmail("user++name@example.com")); // consecutive pluses

        // long email addresses
        assertTrue(Email.isValidEmail("a".repeat(64) + "@example.com")); // local-part of 64 characters
        assertTrue(Email.isValidEmail("user." + "example".repeat(63) + "@example.com")); // domain label of 63 characters
        assertTrue(Email.isValidEmail("user@example." + "com".repeat(63))); // top-level domain of 63 characters

        // mixed valid characters
        assertTrue(Email.isValidEmail("u_ser+name@example.com")); // mixed alphanumeric and '+'
        assertTrue(Email.isValidEmail("user_name@example.com")); // mixed alphanumeric and '_'
        assertTrue(Email.isValidEmail("use.rname@example.com")); // mixed alphanumeric and '.'
        assertTrue(Email.isValidEmail("user-name@example.com")); // mixed alphanumeric and '-'
        assertTrue(Email.isValidEmail("user@name.example.com")); // mixed alphanumeric and '.' (domain part)

        // consecutive mixed valid characters
        assertTrue(Email.isValidEmail("us..er+name@example.com")); // mixed consecutive periods and '+'
        assertTrue(Email.isValidEmail("use_.rname@example.com")); // mixed consecutive underscores and '_'
        assertTrue(Email.isValidEmail("us.er+-name@example.com")); // mixed consecutive period and hyphens and '-'
        assertTrue(Email.isValidEmail("user-.n+++++++++++-----------ame@example.com")); // mixed consecutive periods and
        // '.'
    }

    @Test
    public void equals() {
        Email email = new Email("valid@email");

        // same values -> returns true
        assertTrue(email.equals(new Email("valid@email")));

        // same object -> returns true
        assertTrue(email.equals(email));

        // null -> returns false
        assertFalse(email.equals(null));

        // different types -> returns false
        assertFalse(email.equals(5.0f));

        // different values -> returns false
        assertFalse(email.equals(new Email("other.valid@email")));
    }
}
