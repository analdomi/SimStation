package simstation;
import mvc.Utilities;

public enum Heading {

    NORTH, SOUTH, EAST, WEST;

    public static Heading parse(String heading){
        if(heading.equalsIgnoreCase("north")){
            return NORTH;
        }
        if(heading.equalsIgnoreCase("south")){
            return SOUTH;
        }
        if(heading.equalsIgnoreCase("east")){
            return EAST;
        }
        if(heading.equalsIgnoreCase("west")){
            return WEST;
        }
        return null;
    }

    public static Heading random() {
        int ran = Utilities.rng.nextInt(4);
        if(ran == 1){
            return NORTH;
        }
        else if(ran == 2){
            return SOUTH;
        }
        else if(ran == 3){
            return EAST;
        }
        else{
            return WEST;
        }
    }


}
