package com.vaani.leetcode.string;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/unique-email-addresses/
 * 929. Unique Email Addresses
 * Easy
 * <p>
 * Every email consists of a local name and a domain name, separated by the @ sign.
 * <p>
 * For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.
 * <p>
 * Besides lowercase letters, these emails may contain '.'s or '+'s.
 * <p>
 * If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)
 * <p>
 * If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)
 * <p>
 * It is possible to use both of these rules at the same time.
 * <p>
 * Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * Output: 2
 * Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= emails[i].length <= 100
 * 1 <= emails.length <= 100
 * Each emails[i] contains exactly one '@' character.
 * All local and domain names are non-empty.
 * Local names do not start with a '+' character.
 */
public class UniqueEmailAddresses {
    UniqueEmailAddresses underTest;

    @Before
    public void init() {
        underTest = new UniqueEmailAddresses();
    }

    @After
    public void tearDown() {
        underTest = null;
    }

    @Test
    public void testPlusSign() {
        String[] emails = {"test.email+alex@leetcode.com", "test.email@leetcode.com"};
        Assert.assertEquals(1, underTest.numUniqueEmails(emails));

        String[] emails2 = {"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
        Assert.assertEquals(2, underTest.numUniqueEmails(emails2));

        String[] emails3 = {"test.email+alex@leetcode.com", "test.email.leet+alex@code.com"};
        Assert.assertEquals(2, underTest.numUniqueEmails(emails3));
    }

    public int numUniqueEmails(String[] emails) {
        Set<String> uniqueEmails = new HashSet<>();

        for (String email : emails) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < email.length(); i++) {
                char c = email.charAt(i);
                if (c == '.') {
                    continue;
                } else if (c == '+') {

                    // skip all the characters
                    while (email.charAt(i) != '@') {
                        i++;
                    }

                    i--;// add the skipped alpha back to email

                } else if (c == '@') {
                    sb.append(email.substring(i)); // include rest of string and exit loop
                    break;
                } else {
                    sb.append(c);
                }

            }
            uniqueEmails.add(sb.toString());
        }

        return uniqueEmails.size();
    }
}
