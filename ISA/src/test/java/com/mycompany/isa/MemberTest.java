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
        alice = new Member("Alice Smith", "1 High St, Hatfield, AL10 1AB", "alice@example.com", 0);
        bob   = new Member("Bob Jones",  "2 Low Rd, Hertford, SG13 1AB",  "bob@example.com", 0);
        collection = new Collection();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * test of getName method
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        assertEquals("Alice Smith", alice.getName());
    }

    /**
     * test of getAddress method
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        assertEquals("1 High St, Hatfield, AL10 1AB", alice.getAddress());
    }

    /**
     * test of getEmail method
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        assertEquals("alice@example.com", alice.getEmail());
    }

    /**
     * test of setName method
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        alice.setName("Alicia Smith");
        assertEquals("Alicia Smith", alice.getName());
    }

    /**
     * test of setAddress method
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        alice.setAddress("99 New Road");
        assertEquals("99 New Road", alice.getAddress());
    }

    /**
     * test of setEmail method
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        alice.setEmail("alicia@example.com");
        assertEquals("alicia@example.com", alice.getEmail());
    }

    /**
     * test of getDonatedQty method
     * a new member should start with zero donations
     */
    @Test
    public void testGetDonatedQty() {
        System.out.println("getDonatedQty");
        assertEquals(0, alice.getDonatedQty());
    }

    /**
     * test of addDonation method
     * donating an item should increment donatedQty
     */
    @Test
    public void testAddDonation() {
        System.out.println("addDonation");
        Book book = new Book("1984", "Orwell", alice, "English", "978-0451524935");
        alice.addDonation(book);
        assertEquals(1, alice.getDonatedQty());
    }

    /**
     * test of borrowingQty method
     * a new member should be borrowing zero items
     */
    @Test
    public void testBorrowingQty() {
        System.out.println("borrowingQty");
        assertEquals(0, alice.borrowingQty());
    }

    /**
     * test of lend method
     * lending an item should add it to the borrowing list
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
     * test of returnItem method
     * returning an item should remove it from the borrowing list
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
     * test of toString method
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String result = alice.toString();
        assertTrue(result.startsWith("Member|Alice Smith|"));
        assertTrue(result.contains("alice@example.com"));
    }
    
    /**
     * test of clearDonator
     * should be null after clearDonator called
     */
    @Test
    public void testClearDonator(){
        System.out.println("clearDonator");
        Book book = new Book("1984", "Orwell", alice, "English", "978-0451524935");
        book.clearDonator();
        assertNull(book.getDonator());
    }
    
     /**
     * test of IsAvailable
     * should not be available after being loaned
     */
    @Test
    public void testIsAvailableFalseWhenOnLoan(){
        System.out.println("isAvailable - false on loan");
        Book book = new Book("1984", "Orwell", alice, "English", "978-0451524935");
        book.loanTo(bob);
        assertFalse(book.isAvailable()); 
    }
    

}