package Uber;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Admin extends User {
  public void cg_init_Admin_1(final String nm) {

    name = nm;
    return;
  }

  public Admin(final String nm) {

    cg_init_Admin_1(nm);
  }

  public Admin() {}

  public String toString() {

    return "Admin{}";
  }
}
