package simstation;


public enum Heading {

    NORTH, SOUTH, EAST, WEST;

    public static Heading random() {
        int ran = (int)(Math.random() * (3) + 1);
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
