/**
 * Filename: PackageManagerTest.java 
 * Project: p4 
 * Authors: Ivan Wu, Jiatong Liu
 * 
 */
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

/**
 * This class consists of test methods to test the functionality of the implementation of the
 * packageManager
 *
 */
class PackageManagerTest {
  private PackageManager pk;


  @Test
  void testIntstalationOrderForAll() {
    PackageManager pk = new PackageManager();

    try {
      pk.constructGraph("js.json");
    } catch (IOException e) {
      // Auto-generated catch block
      e.printStackTrace();
    } catch (org.json.simple.parser.ParseException e) {
      // Auto-generated catch block
      e.printStackTrace();
    }

    try {
      if (pk.getInstallationOrderForAllPackages().indexOf("docutils") != 0
          || pk.getInstallationOrderForAllPackages().indexOf("rsa") != 2) {
        fail("wrong order!");
      }
    } catch (CycleException e) {
      // Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  void testIntstalationOrder() {
    PackageManager pk = new PackageManager();

    try {
      pk.constructGraph("js.json");
    } catch (IOException e) {
      // Auto-generated catch block
      e.printStackTrace();
    } catch (org.json.simple.parser.ParseException e) {
      // Auto-generated catch block
      e.printStackTrace();
    }

    try {
      if (pk.getInstallationOrderForAllPackages().indexOf("docutils") != 0
          || pk.getInstallationOrderForAllPackages().indexOf("rsa") != 2) {
        fail("wrong order!");
      }
    } catch (CycleException e) {
      // Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  void testToInstall() {
    PackageManager pk = new PackageManager();
    ArrayList temp = new ArrayList<String>();
    temp.add("awscli");
    temp.add("docutils");
    temp.add("botocore");
    temp.add("urllib3");
    try {
      pk.constructGraph("js.json");
    } catch (IOException e) {
      // Auto-generated catch block
      e.printStackTrace();
    } catch (org.json.simple.parser.ParseException e) {
      // Auto-generated catch block
      e.printStackTrace();
    }

    try {
      System.out.print(pk.toInstall("awscli", "rsa"));
      for (int i = 0; i < temp.size(); i++) {
        if (!temp.get(i).equals(pk.toInstall("awscli", "rsa").get(i))) {
          fail("order to install is not correct");
        }
      }
    } catch (CycleException e) {
      // Auto-generated catch block
      e.printStackTrace();
    } catch (PackageNotFoundException e) {
      // Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  void testGetMaxmal() {
    PackageManager pk = new PackageManager();

    try {
      pk.constructGraph("js.json");
    } catch (IOException e) {
      // Auto-generated catch block
      e.printStackTrace();
    } catch (org.json.simple.parser.ParseException e) {
      // Auto-generated catch block
      e.printStackTrace();
    }

    try {
      if (!pk.getPackageWithMaxDependencies().equals("awscli")) {
        fail("wrong order!");
      }
    } catch (CycleException e) {
      // Auto-generated catch block
      e.printStackTrace();
    }
    // } catch (PackageNotFoundException e) {
    // // Auto-generated catch block
    // e.printStackTrace();
    // }
  }



}


