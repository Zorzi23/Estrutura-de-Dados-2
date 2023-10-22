import java.util.ArrayList;

public class NodoArvore<T> {
    
    //#region Atributos/Constantes

    private T xDado;

    private ArrayList<RamoArvore<T>> aRamos;

    //#endregion
    
    //#region Constructor

    public NodoArvore(T xDado) {
        this.setDado(xDado);
        this.setRamos();
    }

    //#endregion

    //#region Getters/Setters

    public NodoArvore<T> setDado(T xDado) {
        this.xDado = xDado;
        return this;
    }

    public NodoArvore<T> setRamos() {
        this.aRamos = new ArrayList<RamoArvore<T>>();
        return this;
    }

    public NodoArvore<T> setRamo(int iIndice, RamoArvore<T> oNovoRamo) {
        this.aRamos.set(iIndice, oNovoRamo);
        return this;
    }

    public NodoArvore<T> addRamo(RamoArvore<T> oRamoArvore) {
        this.aRamos.add(this.getRamos().size(), oRamoArvore);
        return this;
    }

    public ArrayList<RamoArvore<T>> getRamos() {
        return this.aRamos;
    }

    public RamoArvore<T> getRamo(int iIndice) {
        return this.aRamos.get(iIndice);
    }

    public T getDado() {
        return this.xDado;
    }

    public boolean isFolha() {
        for(RamoArvore<T> oRamo : this.getRamos()) {
            if(!oRamo.isVazio()) {
                return false;
            } 
        }
        return true;
    }

    //#endregion

    //#region toString

    @Override
    public String toString() {
        StringBuilder oStringBuilder = new StringBuilder();
        oStringBuilder
            .append("xDado: ")
            .append(this.getDado()).append(" ")
            .append("aRamos: ")
            .append(this.getRamos().toString()).append(" ");
        return oStringBuilder.toString();
    }

    //#endregion
} 