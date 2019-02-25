package utils;

public class Utils {

  public static boolean isNumber(String pathDistance) {
    for (char c : pathDistance.toCharArray()) {
      if (!Character.isDigit(c)) return false;
    }

    return true;
  }
}
