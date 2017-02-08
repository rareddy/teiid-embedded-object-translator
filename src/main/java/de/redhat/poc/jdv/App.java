package de.redhat.poc.jdv;

import static de.redhat.poc.jdv.JDBCUtils.execute;

import java.sql.Connection;

import org.teiid.runtime.EmbeddedConfiguration;
import org.teiid.runtime.EmbeddedServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        // Teiid stuff
        EmbeddedServer embeddedServer = new EmbeddedServer();
        embeddedServer.start(new EmbeddedConfiguration());
        embeddedServer.deployVDB(App.class.getClassLoader().getResourceAsStream("object-vdb.xml"));

        Thread.sleep(1000);

        Connection connection = embeddedServer.getDriver().connect("jdbc:teiid:objectExampleVDB", null);

        execute(connection, "SELECT * from Team", false);
        
        execute(connection, "SELECT * from Player", true);
        
        embeddedServer.stop();

    }
}
