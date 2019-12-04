
package Modele;

/**
 *
 * @author Fazul Nazar 
 * @author Askia Abdel Kader
 */
public class Case {
    private Position position;
    private boolean isLocked;
    
    public Case(){}
    public Case(int posX , int posY){
        position = new Position(posX,posY);
    }
    public Position getPosition(){
        return position;
    }
    
    public int getPositionX(){
        return position.getPositionX();
    }
    
    public int getPositionY(){
        return position.getPositionY();
    }
    
    public void setPosition(int posX, int posY){
        position.setPositionX(posX);
        position.setPositionY(posY);
    }

    public boolean getIsLocked(){
        return  this.isLocked;
    }

    public void setIsLocked(boolean newvalue){
        this.isLocked =newvalue;
    }
}
