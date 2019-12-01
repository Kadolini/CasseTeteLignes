
package Modele;

/**
 *
 * @author Fazul Nazar
 */
class Position {
    private int positionX;
    private int positionY;
    
    public Position(int x, int y){
        positionX = x;
        positionY = y;
    }
    
    public int getPositionX(){
        return positionX;
    }
    
    public int getPositionY(){
        return positionY;
    }
    
    public void setPositionX(int posX){
        positionX = posX;
    }
    
     public void setPositionY(int posY){
        positionY = posY;
    }
}
