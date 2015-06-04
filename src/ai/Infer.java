package ai;

import java.util.ArrayList;
import java.util.Scanner;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import java.util.HashMap;


public class Infer 
{
        HashMap nodes = new HashMap();
        HashMap goalNodes = new HashMap();
	public static void main(String args[])
	{
            int choice = 0;
            String animal;
            String SOURCE = "c:/Users/Shraddha/Desktop/Spring2015/AI/Project/ontology.owl";
            OntModel model = null;
	    
            model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_RULE_INF );
            model.read(SOURCE, "RDF/XML");
            
            ArrayList<Individual> ind = new ArrayList<Individual>();
            ExtendedIterator classes = model.listHierarchyRootClasses();
            OntClass root = null;
            Individual thisInstance = null;
            
            String sparql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                            "PREFIX x: <http://www.semanticweb.org/shraddha/ontologies/2015/4/untitled-ontology-8#>\n" +
                            "SELECT ?makes ?food ?ind\n" +
                            "WHERE {\n" +
                            "?ind rdf:type x:Terrestrial .\n" +
                            "?ind x:makesSound ?makes.\n" +
                            "?ind x:eatsFood ?food.\n" +
                            "}";
            Query q = QueryFactory.create(sparql);
            ResultSet rs = QueryExecutionFactory.create(q,model).execSelect();
            String instance = null;
            int index = 0;
            HashMap indiv = new HashMap();
            String sound = null;
            String food = null;
            while (rs.hasNext()) {
                QuerySolution row = rs.nextSolution();
                sound = row.getLiteral("makes").getString();
                food = row.getLiteral("food").getString();
                instance = row.getResource("ind").toString();
                index = instance.indexOf("#");
                instance = instance.substring(index + 1);
                indiv.put(sound,instance);
                indiv.put(food,instance);
            }
            String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                                "PREFIX x: <http://www.semanticweb.org/shraddha/ontologies/2015/4/untitled-ontology-8#>\n" +
                                "SELECT ?makes ?food ?ind\n" +
                                "WHERE {\n" +
                                "?ind rdf:type x:Marine. \n" +
                                "?ind x:makesSound ?makes.\n" +
                                "?ind x:eatsFood ?food.\n" +
                                "}";
            Query q1 = QueryFactory.create(query);
            ResultSet rs1 = QueryExecutionFactory.create(q1,model).execSelect();
            
            while (rs1.hasNext()) {
                QuerySolution row = rs1.nextSolution();
                sound = row.getLiteral("makes").getString();
                food = row.getLiteral("food").getString();
          
                instance = row.getResource("ind").toString();
                index = instance.indexOf("#");
                instance = instance.substring(index + 1);
                indiv.put(sound,instance);
                indiv.put(food,instance);
            }
            while(classes.hasNext())
            {
		root = (OntClass) classes.next();
		ExtendedIterator indi = root.listInstances();
		while (indi.hasNext())
		{
                    thisInstance = (Individual) indi.next();
                    ind.add(thisInstance);
		}
            }
            Infer i = new Infer();
            i.goalNodes.put(0,3);
            i.goalNodes.put(1,5);
            i.goalNodes.put(2,7);
            i.goalNodes.put(3,1);
            i.goalNodes.put(4,8);
            i.goalNodes.put(5,2);
            i.goalNodes.put(6,4);
            i.goalNodes.put(7,6);
    
    
            while(choice != 9){
                System.out.println("Recognize who am I:");
                System.out.println("1. I make the sound 'moo' ");
                System.out.println("2. I eat 'bugs' ");
                System.out.println("3. I make the sound 'whoop' ");
                System.out.println("4. I eat 'fish' ");
                System.out.println("5. I make the sound 'bow-bow' ");
                System.out.println("6. I eat 'meat' ");
                System.out.println("7. I eat 'carrot' ");
                System.out.println("8. I make the sound 'meow' ");
                System.out.println("9. Exit ");
                Scanner input = new Scanner(System.in);
                choice = input.nextInt();
                    
                    if(choice == 1){
                        animal = indiv.get("moo").toString();
                        System.out.println("You got me! I am a "+ animal);
                        System.out.println("The number associated with me is 3");
                        i.nodes.put(0, 3);
                    }
                    if(choice == 2){
                        
			animal = indiv.get("bugs").toString();
                        System.out.println("You got me! I am a "+ animal);
                        System.out.println("The number associated with me is 6");
                        i.nodes.put(1, 5);
                    }
                    if(choice == 3){
                        animal = indiv.get("whoop").toString();
                        System.out.println("You got me! I am a "+ animal);
                        System.out.println("The number associated with me is 8");
                        i.nodes.put(2, 4);
                    }
                    if(choice == 4){
                        animal = indiv.get("fish").toString();
                        System.out.println("You got me! I am a "+ animal);
                        System.out.println("The number associated with me is 2");
                        i.nodes.put(3, 2);
                    }
                    if(choice == 5){
                        animal = indiv.get("bow-bow").toString();
                        System.out.println("You got me! I am a "+ animal);
                        System.out.println("The number associated with me is 4");
                        i.nodes.put(4, 7);
                    }
                    if(choice == 6){
                        animal = indiv.get("meat").toString();
                        System.out.println("You got me! I am a "+ animal);
                        System.out.println("The number associated with me is 1");
                        i.nodes.put(5, 8);
                    }
                    if(choice == 7){
                        animal = indiv.get("carrot").toString();
                        System.out.println("You got me! I am a "+ animal);
                        System.out.println("The number associated with me is 5");
                        i.nodes.put(6, 1);
                    }
                    if(choice == 8){
                        animal = indiv.get("meow").toString();
                        System.out.println("You got me! I am a "+ animal);
                        System.out.println("The number associated with me is 7");
                        i.nodes.put(7, 6);
                    }
                }   
            
            System.out.println("The input to 8-puzzle problem is:");
            System.out.println("    "+i.nodes.get(0)+"    "+i.nodes.get(1)+"   ");
            System.out.println(i.nodes.get(2)+"   "+i.nodes.get(3)+"    "+i.nodes.get(4)+"   "+i.nodes.get(5));
            System.out.println("    "+i.nodes.get(6)+"    "+i.nodes.get(7)+"   ");
            int match = 0;
            System.out.println("The hueristic used for the problem is: Number of squares in the wrong position ");
            for(int j=0;j<i.goalNodes.size();j++){
                if(i.goalNodes.get(j) == i.nodes.get(j))
                    match++;
            }
            
            int count = 0;
            Node currentNode = null;
            Node returnedNode = new Node(i.nodes,count);
            
            currentNode = returnedNode;
            returnedNode = i.checkForGoal(currentNode);
            
            System.out.println("       |    ");
            System.out.println("       |    ");
            System.out.println("       |    ");
            System.out.println("      \\/    ");
            System.out.println("    "+returnedNode.node.get(0)+"    "+returnedNode.node.get(1)+"   ");
            System.out.println(returnedNode.node.get(2)+"   "+returnedNode.node.get(3)+"    "+returnedNode.node.get(4)+"   "+returnedNode.node.get(5));
            System.out.println("    "+returnedNode.node.get(6)+"    "+returnedNode.node.get(7)+"   ");
                
            while(currentNode.heuristic<i.goalNodes.size())
            {
                currentNode = returnedNode;
                returnedNode = i.checkForGoal(currentNode);
                               
                if(currentNode.heuristic<i.goalNodes.size())
                {
                    System.out.println("       |    ");
                    System.out.println("       |    ");
                    System.out.println("       |    ");
                    System.out.println("       \\/    ");
                    System.out.println("    "+returnedNode.node.get(0)+"    "+returnedNode.node.get(1)+"   ");
                    System.out.println(returnedNode.node.get(2)+"   "+returnedNode.node.get(3)+"    "+returnedNode.node.get(4)+"   "+returnedNode.node.get(5));
                    System.out.println("    "+returnedNode.node.get(6)+"    "+returnedNode.node.get(7)+"   ");
                }
            }
        }
        
        public Node checkForGoal(Node current){
            int i=0;
            current.heuristic=0;
            int end = goalNodes.size()-1;
            ArrayList<Node> arr = new ArrayList<Node>();
            outerloop : while(i<=end)
            {
                if(!(this.goalNodes.get(i).equals(current.node.get(i))))
                {
                    for(int j = i+1;j<=end;j++){
                        if(!(this.goalNodes.get(j).equals(current.node.get(j)))){
                            int temp = (int)current.node.get(j);
                            
                            current.node.put(j, current.node.get(i));
                            current.node.put(i, temp);
                            
                            if(this.goalNodes.get(i).equals(current.node.get(i))){
                                current.heuristic++; 
                                break outerloop;
                            }
                            else{
                                    int temp1 = (int)current.node.get(j);

                                    current.node.put(j, current.node.get(i));
                                    current.node.put(i, temp1);

                                    Node node = new Node(current.node, current.heuristic);
                                    arr.add(node);
                                }
                        }
                        else
                        {
                            current.heuristic++;
                        }
                    }
                }
                else
                {
                    current.heuristic++;
                    i++;
                }
            }
            Node maxNode = null;
            if(arr.size()>0)
            {
                int countMax=0;
                int indexVal=0;

                for(int k=0;k<arr.size();k++)
                {
                    if(countMax<=arr.get(k).heuristic)
                    {
                        countMax=arr.get(k).heuristic;
                        indexVal = k;
                    }

                }
                maxNode = arr.get(indexVal);
            }
            else
            {
                maxNode= current;
            }
            
            return maxNode;
        }

    private String nodes(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
