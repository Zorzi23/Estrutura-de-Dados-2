import java.util.function.Consumer;

public class Arvore<T> {
    
    //#region Atributos/Constantes

    protected NodoArvore<T> oRaiz;

    protected NodoArvore<T> oNodoAtual;

    private Integer iAltura = 0;

    //#endregion

    //#region Constructor
    
    public Arvore(NodoArvore<T> oRaiz) {
        this.setRaiz(oRaiz);
        this.setNodoAtual(oRaiz);
    }

    public Arvore() {
        this.setRaiz(new NodoArvore<T>(null));
        this.setNodoAtual(oRaiz);
    }

    //#endregion

    //#region Getters/Setters

    public Arvore<T> setRaiz(NodoArvore<T> oRaiz) {
        this.oRaiz = oRaiz;
        return this;
    }

    public NodoArvore<T> getRaiz() {
        return this.oRaiz;
    }
    
    public Arvore<T> setNodoAtual(NodoArvore<T> oNodoAtual) {
        this.oNodoAtual = oNodoAtual;
        return this;
    }
    
    public NodoArvore<T> getNodoAtual() {
        return this.oNodoAtual;
    }
    
    public Arvore<T> addAltura() {
        this.iAltura++;
        return this;
    }
    
    public Integer getAltura() {
        return this.iAltura;
    }

    public RamoArvore<T> getProximoRamoNodo(NodoArvore<T> oNodo, Integer iIndiceRamo) {

        RamoArvore<T> oRamoAtual = oNodo.getRamo(iIndiceRamo); 

        if(oRamoAtual.getNodo() == null) {
            return oRamoAtual;
        }

        return oRamoAtual.getNodo().getRamo(iIndiceRamo);
    }
    
    //#endregion

    //#region Métodos de Manipulação

    public Arvore<T> inserir(T xDado, Consumer<NodoArvore<T>> fnInsercao) {
        fnInsercao.accept(this.criarNodo(xDado));
        return this.addAltura().setNodoAtual(this.getRaiz());
    }

    protected NodoArvore<T> criarNodo(T xDado) {
        return new NodoArvore<T>(xDado);
    }

    public Arvore<T> remover(Consumer<NodoArvore<T>> fnRemocao) {
        fnRemocao.accept(this.getNodoAtual());
        return this;
    }

    public Arvore<T> percorrer(Consumer<NodoArvore<T>> fnPercorrer) {
        fnPercorrer.accept(this.getNodoAtual());
        return this;
    } 

    //#endregion

    //#region toString

    @Override
    public String toString() {
        StringBuilder oStringBuilder = new StringBuilder();
        oStringBuilder.append("Raiz: ")
            .append(this.getRaiz().getDado())
            .append("\n")
            .append("Altura:")
            .append(this.getAltura())
            .append("\n");
        return oStringBuilder.toString();
    }

    //#endregion
}