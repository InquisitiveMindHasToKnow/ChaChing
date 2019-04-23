package com.ohmstheresistance.chaching;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestsAreTheWorst {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void test_LocationID_greaterThan() {
        ChaChingLocation Angetu = new ChaChingLocation("ET", "Angetu", 343846);
        ChaChingLocation Baiersbronn = new ChaChingLocation("DE", "Baiersbronn", 6555717);
        int result = Angetu.compareTo(Baiersbronn);
        Assert.assertEquals(result, -1);
    }

    @Test
    public void test_LocationID_Equals() {
        ChaChingLocation Washington = new ChaChingLocation("US", "Washington", 6555717);
        ChaChingLocation Birim = new ChaChingLocation("NG", "Birim", 6555717);
        int result = Washington.compareTo(Birim);
        Assert.assertEquals(result, 0);
    }

    @After
    public void tearDown() throws Exception {

    }
}
