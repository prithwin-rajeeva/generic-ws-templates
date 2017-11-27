package com.generic.project.dal.impl;

import com.generic.project.test.util.TestUtil;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test cases for data access components.
 */
public class DataAccessImplTest {

    /**
     * gets the image count
     */
    @Test
    public void getImageCount() throws Exception {
        String htmlPage = "<html><img src=\"something.jpg\"/><img src=\"something.jpg\"></img></html>";
        assertEquals(2,new DataAccessImpl().getImageCount(htmlPage));

        String nextPage = "<html><img src=\"something.jpg\"/>< img src=\"something.jpg\"/><" +
                "img src=\"something.jpg\"></img></html>";
        assertEquals(3, new DataAccessImpl().getImageCount(nextPage));

        String pageThree = "<html><img src=\"something.jpg\"/><img src=\"something.jpg\"/>" +
                "< img src=\"something.jpg\"/><img src=\"something.jpg\"></img></html>";
        assertEquals(4, new DataAccessImpl().getImageCount(pageThree));
        assertEquals(300, new DataAccessImpl().getImageCount(TestUtil.generateFile(300)));
        assertEquals(700, new DataAccessImpl().getImageCount(TestUtil.generateFile(700)));
        assertEquals(55, new DataAccessImpl().getImageCount(TestUtil.generateFile(55)));
        assertEquals(7, new DataAccessImpl().getImageCount(TestUtil.generateFile(7)));
    }

    /**
     * Tests with random number of autogenerated image tags.
     */
    public void devTest() throws Exception {
        long randomNumber = Math.round(500 *Math.random());
        assertEquals(randomNumber, new DataAccessImpl().getImageCount(TestUtil.generateFile(randomNumber)));
    }

}