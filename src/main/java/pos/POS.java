package pos;

import com.sun.net.httpserver.Authenticator;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

class ModemDidNotConnectException extends Exception {}

class ModemLibrary {
  public static void dialModem(Object number)
      throws ModemDidNotConnectException {
    // null number represents implementation error... shutdown
    // assert or throw NPE??
    // enable assertions with JVM argument -ea or -enableassertions
    assert number != null : "argument must never be null";
    // attempt to dial, lots of serial port activity (AT codes!)
    // fail...
    throw new ModemDidNotConnectException();
    // succeed
//    return;
  }

}

// ---------------------------------------------------------
// support for buying

class RetryCCLaterException extends Exception {
  public RetryCCLaterException(String message, Throwable cause) {
    super(message, cause);
  }
}
class NoMoneyCCException extends Exception {}

public class POS {
  public static boolean getMoneyFromCard(int ccnum)
//      throws IOException, ModemDidNotConnectException{ // IMPLEMENTATION DETAIL!!!
      throws RetryCCLaterException, NoMoneyCCException {
    //
    int count = 0;
    boolean useModem = false;
    try {
      if (useModem) {
        ModemLibrary.dialModem(123456789);
      } else {
        new Socket("127.0.0.1", 80);
      }
      // skipped if exception arises...
    } catch (IOException | ModemDidNotConnectException mdnce) {
      // loop and retry three times...
      if (count >= 2) // after three times...
        throw new RetryCCLaterException("connection failure", mdnce);
    }
    return true;
  }
  public void buyStuff() {
    // calculate cost
    // get paid
    try {
      boolean success = getMoneyFromCard(1234);
    } catch (NoMoneyCCException e) {
      e.printStackTrace();
    } catch (RetryCCLaterException e) {
      e.printStackTrace();
    }
  }

}
