package com.icloud.m.dimas.software.test;

import com.icloud.m.dimas.software.utils.CommonsUtils;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestBase64Codec extends TestCase {

    @Autowired
    private CommonsUtils utils;

    @Test
    public void testSamplePasswordHashSha256AndToBase64() {
        String passwordEncoded = "sDqnZ7zFOcr7HzC3qijc2/i4g5GTXcsPIJHfngz0954=";
        boolean passwordValid = utils.isPasswordValid(passwordEncoded, "Mandiri1", "Xm8yXzph");
        assertTrue("Username and password should match!", passwordValid);

        passwordValid = utils.isPasswordValid(passwordEncoded,"Mandiri", "Xm8yXzph");
        assertFalse("Username and password should be not match!", passwordValid);
    }
}
