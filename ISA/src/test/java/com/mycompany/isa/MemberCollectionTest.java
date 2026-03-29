/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.isa;

import java.util.ArrayList;
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
public class MemberCollectionTest {

    private MemberCollection memberCollection;

    public MemberCollectionTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        memberCollection = new MemberCollection();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * test of addMember method, of class MemberCollection
     * collection size should increase after adding a member
     */
    @Test
    public void testAddMember() {
        System.out.println("addMember");
        memberCollection.addMember("Alice Smith", "1 High St", "alice@example.com", 0);
        assertEquals(1, memberCollection.getMembers().size());
    }

    /**
     * test of removeMember method, of class MemberCollection
     * collection size should decrease after removing a member
     */
    @Test
    public void testRemoveMember() {
        System.out.println("removeMember");
        memberCollection.addMember("Alice Smith", "1 High St", "alice@example.com", 0);
        Member m = memberCollection.getMembers().get(0);
        memberCollection.removeMember(m);
        assertEquals(0, memberCollection.getMembers().size());
    }

    /**
     * test of searchMember method, of class MemberCollection
     * should find a member by partial name match
     */
    @Test
    public void testSearchMember() {
        System.out.println("searchMember");
        memberCollection.addMember("Alice Smith", "1 High St", "alice@example.com", 0);
        memberCollection.addMember("Bob Jones", "2 Low Rd", "bob@example.com", 0);
        ArrayList<Member> results = memberCollection.searchMember("Alice");
        assertEquals(1, results.size());
        assertEquals("Alice Smith", results.get(0).getName());
    }

    /**
     * test of searchMember method, of class MemberCollection
     * search should be case insensitive
     */
    @Test
    public void testSearchMemberCaseInsensitive() {
        System.out.println("searchMember - case insensitive");
        memberCollection.addMember("Alice Smith", "1 High St", "alice@example.com", 0);
        assertEquals(1, memberCollection.searchMember("alice").size());
    }

}