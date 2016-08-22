package org.tinyradius;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tinyradius.util.RadiusClient;
import org.tinyradius.util.RadiusException;
import org.tinyradius.util.RadiusServer;

/**
 * Small smoke test with a client and server
 *
 * It doesn't cover much, but is a minimal barrier against breaking stuff.
 */
public class ClientServerSmokeTest extends RadiusServer {

  @Override
  public String getSharedSecret(InetSocketAddress client) {
    return "gahpheenguo7tet5eeGh";
  }

  @Override
  public String getUserPassword(String userName) {
    return "eiW5iGai4ateey3cuesh";
  }

  @Before
  public void startServer() {
    start(true, false);
  }

  @After
  public void stopServer() {
    stop();
  }

  @Test
  public void authenticateWrongPassword_ShouldReturnFalse() throws IOException, RadiusException {
    RadiusClient rc = new RadiusClient("localhost", "gahpheenguo7tet5eeGh");
    assertThat(rc.authenticate("testuser", "000000"), is(false));
  }

  @Test
  public void authenticateCorrectPassword_ShouldReturnTrue() throws IOException, RadiusException {
    RadiusClient rc = new RadiusClient("localhost", "gahpheenguo7tet5eeGh");
    assertThat(rc.authenticate("testuser", "eiW5iGai4ateey3cuesh"), is(true));
  }
}
