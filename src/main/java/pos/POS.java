package pos;

class ModemDidNotConnectException extends Exception {}

class ModemLibrary {
  public static void dialModem(Object number) throws ModemDidNotConnectException {
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

public class POS {
  public static boolean getMoneyFromCard(int ccnum) throws ModemDidNotConnectException{
    //
    int count = 0;
    try {
      ModemLibrary.dialModem(123456789);
      // skipped if exception arises...
    } catch (ModemDidNotConnectException mdnce) {
      // loop and retry three times...
      if (count >= 2) // after three times...
        throw mdnce;
    }


    return true;
  }
  public void buyStuff() {
    // calculate cost
    // get paid
    try {
      boolean success = getMoneyFromCard(1234);
    } catch (ModemDidNotConnectException mdnce) {
      // tell user???
    }
  }

}
