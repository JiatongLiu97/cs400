import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Filename: PackageManager.java 
 * Project: p4 
 * Authors: Ivan Wu, Jiatong Liu
 * 
 * PackageManager is used to process json package dependency files and provide function that make
 * that information available to other users.
 * 
 * Each package that depends upon other packages has its own entry in the json file.
 * 
 * Package dependencies are important when building software, as you must install packages in an
 * order such that each package is installed after all of the packages that it depends on have been
 * installed.
 * 
 * For example: package A depends upon package B, then package B must be installed before package A.
 * 
 * This program will read package information and provide information about the packages that must
 * be installed before any given package can be installed. all of the packages in
 * 
 * You may add a main method, but we will test all methods with our own Test classes.
 */

public class PackageManager {

  private Graph graph;

  /*
   * Package Manager default no-argument constructor.
   */
  public PackageManager() {
    graph = new Graph();
  }

  /**
   * Takes in a file path for a json file and builds the package dependency graph from it.
   * 
   * @param jsonFilepath the name of json data file with package dependency information
   * @throws FileNotFoundException if file path is incorrect
   * @throws IOException if the give file cannot be read
   * @throws ParseException if the given json cannot be parsed
   */
  public void constructGraph(String jsonFilepath)
      throws FileNotFoundException, IOException, ParseException {
    String name = null;
    // parsing file with filepath - jsonFilepath
    Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
    // typecasting obj to JSONOject
    JSONObject jo = (JSONObject) obj;
    // getting packages
    JSONArray ja = (JSONArray) jo.get("packages");

    // iterating packages
    Iterator itr1 = ja.iterator();
    while (itr1.hasNext()) {
      Iterator itr2 = ((Map) itr1.next()).entrySet().iterator();
      while (itr2.hasNext()) {
        Map.Entry pair = (Entry) itr2.next();
        if (pair.getKey().equals("name")) {
          graph.addVertex((String) pair.getValue());
          name = (String) pair.getValue();
        } else {
          ArrayList<String> temp = (ArrayList<String>) pair.getValue();
          for (int i = 0; i < temp.size(); i++) {
            graph.addEdge(name, temp.get(i));
          }
        }


      }

    }

  }

  /**
   * Helper method to get all packages in the graph.
   * 
   * @return Set<String> of all the packages
   */
  public Set<String> getAllPackages() {
    return graph.getAllVertices();
  }

  /**
   * Given a package name, returns a list of packages in a valid installation order.
   * 
   * Valid installation order means that each package is listed before any packages that depend upon
   * that package.
   * 
   * @return List<String>, order in which the packages have to be installed
   * 
   * @throws CycleException if you encounter a cycle in the graph while finding the installation
   *         order for a particular package. Tip: Cycles in some other part of the graph that do not
   *         affect the installation order for the specified package, should not throw this
   *         exception.
   * 
   * @throws PackageNotFoundException if the package passed does not exist in the dependency graph.
   */
  public List<String> getInstallationOrder(String pkg)
      throws CycleException, PackageNotFoundException {
    if (!this.getAllPackages().contains(pkg)) {
      throw new PackageNotFoundException();
    }
    List<String> temp = new ArrayList<String>(); // temp list to store
    Stack<String> stack = new Stack<String>(); // stack to store vertices
    stack.add(pkg);
    ArrayList<String> visited = new ArrayList<String>();
    visited.add(pkg);
    while (!stack.isEmpty()) {
      String element = stack.pop();
      temp.add(0, element);

      List<String> neighbours = graph.getAdjacentVerticesOf(element);
      for (int i = 0; i < neighbours.size(); i++) {
        String n = neighbours.get(i);
        if (n != null && !visited.contains(n)) {
          stack.add(n);
          visited.add(n);
        }
      }
    }
    return temp;
  }

  /**
   * Given two packages - one to be installed and the other installed, return a List of the packages
   * that need to be newly installed.
   * 
   * For example, refer to shared_dependecies.json - toInstall("A","B") If package A needs to be
   * installed and packageB is already installed, return the list ["A", "C"] since D will have been
   * installed when B was previously installed.
   * 
   * @return List<String>, packages that need to be newly installed.
   * 
   * @throws CycleException if you encounter a cycle in the graph while finding the dependencies of
   *         the given packages. If there is a cycle in some other part of the graph that doesn't
   *         affect the parsing of these dependencies, cycle exception should not be thrown.
   * 
   * @throws PackageNotFoundException if any of the packages passed do not exist in the dependency
   *         graph.
   */
  public List<String> toInstall(String newPkg, String installedPkg)
      throws CycleException, PackageNotFoundException {
    if (graph.contains(newPkg) == false || graph.contains(installedPkg) == false) {
      throw new PackageNotFoundException();
    }
    ArrayList<String> list = new ArrayList<String>();
    List<String> installedlist = getInstallationOrder(installedPkg);
    return doToInstall(newPkg, installedlist, list);
  }

  public List<String> doToInstall(String newPkg, List<String> installedPkg,
      ArrayList<String> orderList) throws CycleException {
    if (newPkg == null) {
      return orderList;
    }
    if (installedPkg.contains(newPkg)) {
      return orderList;
    }
    // check for cycle
    if (orderList.contains(newPkg)) {
      throw new CycleException();
    }
    if (orderList.contains(newPkg) == false) {
      orderList.add(newPkg);
      for (String str : graph.getAdjacentVerticesOf(newPkg)) {
        doToInstall(str, installedPkg, orderList);
      }
    }
    return orderList;
  }

  /**
   * Return a valid global installation order of all the packages in the dependency graph.
   * 
   * assumes: no package has been installed and you are required to install all the packages
   * 
   * returns a valid installation order that will not violate any dependencies
   * 
   * @return List<String>, order in which all the packages have to be installed
   * @throws CycleException if you encounter a cycle in the graph
   */
  public List<String> getInstallationOrderForAllPackages() throws CycleException {
    List<String> temp = new ArrayList<String>();
    ArrayList<String> visited = new ArrayList<String>();
    Set<String> set = graph.getAllVertices();
    Stack st = new Stack();
    String current = null;
    for (String vertex : set) {
      if (graph.getAdjacentVerticesOf(vertex).isEmpty()) {
        visited.add(vertex);
        st.push(vertex);
      }
    }
    while (!st.isEmpty()) {
      int x = 0;
      current = (String) st.peek();
      for (String vertex : set) {
        if (graph.getAdjacentVerticesOf(vertex).contains(current)) {
          if (!visited.contains(vertex)) {
            visited.add(vertex);
            st.push(vertex);
            x = 1;
            break;
          }
        }
      }

      if (x == 0) {
        temp.add(0, (String) st.pop());
      }
    }
    return temp;
  }

  /**
   * Find and return the name of the package with the maximum number of dependencies.
   * 
   * Tip: it's not just the number of dependencies given in the json file. The number of
   * dependencies includes the dependencies of its dependencies. But, if a package is listed in
   * multiple places, it is only counted once.
   * 
   * Example: if A depends on B and C, and B depends on C, and C depends on D. Then, A has 3
   * dependencies - B,C and D.
   * 
   * @return String, name of the package with most dependencies.
   * @throws CycleException if you encounter a cycle in the graph
   */
  public String getPackageWithMaxDependencies() throws CycleException {
    int numDep = 0;
    int currentSize = 0;
    String maxPkg = "";
    Set<String> set = this.getAllPackages();
    for (String vertex : set) {
      try {
        currentSize = this.getInstallationOrder(vertex).size();
        if (currentSize > numDep) {
          numDep = currentSize;
          maxPkg = vertex;
        }
      } catch (PackageNotFoundException e) {
        e.printStackTrace();
      }
    }
    return maxPkg;
  }

  public static void main(String[] args) {
    System.out.println("PackageManager.main()");
    // PackageManager pk = new PackageManager();
    // try {
    // pk.constructGraph("shared_dependencies.json");
    // } catch (IOException | ParseException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    //
    //
    //// System.out.print(pk.graph.getAllVertices());
    // try {
    // System.out.println(pk.getInstallationOrderForAllPackages());
    // } catch (CycleException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // try {
    // System.out.println(pk.getInstallationOrder("A"));
    // } catch (CycleException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // } catch (PackageNotFoundException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }

  }

}


