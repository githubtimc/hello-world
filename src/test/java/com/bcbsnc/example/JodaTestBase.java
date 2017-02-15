package com.bcbsnc.example;

import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaMapper;

import junit.framework.TestCase;

public abstract class JodaTestBase extends TestCase
{
    protected static ObjectMapper jodaMapper()
    {
        return new JodaMapper();
    }

    /*
    /**********************************************************
    /* Additional assert methods
    /**********************************************************
     */

    protected void assertEquals(int[] exp, int[] act) {
        assertArrayEquals(exp, act);
    }
    
    /*
    /**********************************************************
    /* Helper methods
    /**********************************************************
     */

    public String quote(String str) {
        return '"'+str+'"';
    }

    protected String aposToQuotes(String json) {
        return json.replace("'", "\"");
    }

    protected <T> T readAndMapFromString(ObjectMapper m, String input, Class<T> cls)
        throws IOException
    {
        return (T) m.readValue("\""+input+"\"", cls);
    }
}
