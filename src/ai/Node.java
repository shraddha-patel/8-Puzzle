
package ai;

import java.util.HashMap;

/**
 *
 * @author Shraddha
 */
public class Node {
    public HashMap node;
    public int heuristic = 0;
    public Node(HashMap node,int heuristic ){
         this.node = node;
         this.heuristic = heuristic;
      }
}
