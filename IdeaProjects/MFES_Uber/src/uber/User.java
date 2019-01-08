package uber;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  public String name;

  public User() {}

  public String toString() {

    return "User{" + "name := " + Utils.toString(name) + "}";
  }
}
