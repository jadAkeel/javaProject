// Chain of Responsability
// Objects are connected (like a chain)
// An object either handle a request (if the request is in its capacity) or
// delegate the handling too the next Object.

package chain;

public class Chain {

    public static void main(String[] args) {
        Petition pet = new Petition("Change of Major", 2);
        
        Faculty vp = new Faculty("Vice President", 3, null);
        Faculty de = new Faculty("Dean", 2, vp);
        Faculty ch = new Faculty("Chair", 1, de);
        Faculty ad = new Faculty("Advisor", 0, ch);
        
        ad.handle(pet);
        
    }
}

// Example: faculties of a university handling students petitions

class Petition{
    String description;
    int level;

    public Petition(String description, int level) {
        this.description = description;
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public int getLevel() {
        return level;
    }
    
    
}

class Faculty{
    private String position;
    private int level;
    private Faculty sup;

    public Faculty(String position, int level, Faculty sup) {
        this.position = position;
        this.level = level;
        this.sup = sup;
    }
    
    void handle(Petition pet){
        if(pet.getLevel()<=level){
            process(pet);
        }else{
            sup.handle(pet);
        }
        
    }
    void process(Petition pet){
        System.out.println(position+" handling petition "+pet.getDescription());
    }
}

