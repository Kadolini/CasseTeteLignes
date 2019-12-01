
package Modele;

/**
 *
 * @author Fazul Nazar
 */
public class SymbolCase extends Case {
    private Symbol symbol;
    
     public SymbolCase(Symbol symbol){
        this.symbol = symbol;
     }
    
    public Symbol getSymbol(){
        return symbol ;
    }
    
    public void setSymbol(Symbol symbol){
        this.symbol= symbol;
    }

}
