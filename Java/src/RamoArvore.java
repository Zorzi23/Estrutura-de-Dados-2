public class RamoArvore<T> {
    
    private NodoArvore<T> oNodo;

    public RamoArvore(NodoArvore<T> oNodo) {
        this.setNodo(oNodo);
    }

    public RamoArvore<T> setNodo(NodoArvore<T> oNodo) {
        this.oNodo = oNodo;
        return this;
    }

    public NodoArvore<T> getNodo() {
        return this.oNodo;
    }

    public boolean isVazio() {
        return this.getNodo() == null;
    }

    @Override
    public String toString() {
        StringBuilder oString = new StringBuilder();
        if(this.getNodo() != null) {
            oString.append(this.getNodo().toString());
        }
        return oString.toString();
    }

}
