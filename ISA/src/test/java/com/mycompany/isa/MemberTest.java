package com.mycompany.isa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author elean
 */
public class MemberTest {

    private Member alice;
    private Member bob;
    private Collection collection;

    public MemberTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        alice = new Member("Alice Smith", "1 High St, Hatfield, AL10 1AB", "alice@example.com");
        bob   = new Member("Bob Jones",  "2 Low Rd, Hertford, SG13 1AB",  "bob@example.com");
        collection = new Collection();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getName method
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        assertEquals("Alice Smith", alice.getName());
    }

    /**
     * Test of getAddress method
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        assertEquals("1 High St, Hatfield, AL10 1AB", alice.getAddress());
    }

    /**
     * Test of getEmail method
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        assertEquals("alice@example.com", alice.getEmail());
    }

    /**
     * Test of setName method
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        alice.setName("Alicia Smith");
        assertEquals("Alicia Smith", alice.getName());
    }

    /**
     * Test of setAddress method
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        alice.setAddress("99 New Road");
        assertEquals("99 New Road", alice.getAddress());
    }

    /**
     * Test of setEmail method
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        alice.setEmail("alicia@example.com");
        assertEquals("alicia@example.com", alice.getEmail());
    }

    /**
     * Test of getDonatedQty method
     * A new member should start with zero donations.
     */
    @Test
    public void testGetDonatedQty() {
        System.out.println("getDonatedQty");
        assertEquals(0, alice.getDonatedQty());
    }

    /**
     * Test of addDonation method
     * Donating an item should increment donatedQty.
     */
    @Test
    public void testAddDonation() {
        System.out.println("addDonation");
        collection.addBook("1984", "Orwell", alice, "English", "978-0451524935");
        assertEquals(1, alice.getDonatedQty());
    }

    /**
     * Test of borrowingQty method
     * A new member should be borrowing zero items.
     */
    @Test
    public void testBorrowingQty() {
        System.out.println("borrowingQty");
        assertEquals(0, alice.borrowingQty());
    }

    /**
     * Test of lend method
     * Lending an item should add it to the borrowing list.
     */
    @Test
    public void testLend() {
        System.out.println("lend");
        collection.addBook("The Hobbit", "Tolkien", alice, "English", "222");
        Item item = collection.getItem("The Hobbit");
        item.loanTo(bob);
        bob.lend(item);
        assertEquals(1, bob.borrowingQty());
    }

    /**
     * Test of returnItem method
     * Returning an item should remove it from the borrowing list.
     */
    @Test
    public void testReturnItem() {
        System.out.println("returnItem");
        collection.addBook("The Hobbit", "Tolkien", alice, "English", "222");
        Item item = collection.getItem("The Hobbit");
        item.loanTo(bob);
        bob.lend(item);
        bob.returnItem(item);
        item.returnLoan();
        assertEquals(0, bob.borrowingQty());
    }

    /**
     * Test of toString method
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String result = alice.toString();
        assertTrue(result.startsWith("Member|Alice Smith|"));
        assertTrue(result.contains("alice@example.com"));
    }

}