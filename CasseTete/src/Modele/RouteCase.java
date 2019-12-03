
package Modele;

/**
 *
 * @author Fazul Nazar
 * @author Askia Abdel Kader
 */
public class RouteCase extends Case {
    private Route route;
    
    public RouteCase(){
        super();
    }
    public RouteCase(Route route, int posx, int posy){
        super(posx,posx);
        this.route = route;
    }
    
    public Route getRoute(){
        return route;
    }
    
    public void setRoute(Route route){
        this.route= route;
    }
}
