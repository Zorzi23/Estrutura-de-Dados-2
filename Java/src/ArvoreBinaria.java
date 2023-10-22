import java.util.function.Consumer;

public class ArvoreBinaria extends Arvore<Integer> {

    // #region Atributos/Constantes

    public static final int RAMO_ESQUERDA = 0;
    public static final int RAMO_DIREITA = 1;

    private int iAlturaEsquerda = 0;
    private int iAlturaDireita = 0;

    // #endregion

    // #region Constructor

    public ArvoreBinaria(NodoArvore<Integer> oRaiz) {
        super(oRaiz
                .addRamo(new RamoArvore<Integer>(null))
                .addRamo(new RamoArvore<Integer>(null)));
    }

    // #endregion

    // #region Getters/Setters

    public RamoArvore<Integer> getRamoEsquerda(NodoArvore<Integer> oNodo) {
        return oNodo.getRamo(ArvoreBinaria.RAMO_ESQUERDA);
    }

    public RamoArvore<Integer> getRamoDireita(NodoArvore<Integer> oNodo) {
        return oNodo.getRamo(ArvoreBinaria.RAMO_DIREITA);
    }

    public ArvoreBinaria addAlturaEsquerda() {
        this.iAlturaEsquerda++;
        return this;
    }

    public int getAlturaEsquerda() {
        return this.iAlturaEsquerda;
    }

    public ArvoreBinaria addAlturaDireita() {
        this.iAlturaDireita++;
        return this;
    }

    public int getAlturaDireita() {
        return this.iAlturaDireita;
    }

    // #endregion

    // #region Métodos de Manipulação

    public ArvoreBinaria inserir(int iDado) {
        this.setNodoAtual(this.getRaiz());
        super.inserir(iDado, oNovoNodo -> {
            this.inserirNodoBinario(oNovoNodo);
        });
        return this;
    }

    public ArvoreBinaria inserirNodoBinario(NodoArvore<Integer> oNovoNodo) {
        NodoArvore<Integer> nodoAtual = this.getRaiz();
        
        while (true) {
            RamoArvore<Integer> oRamoInsercao = nodoAtual.getRamo(
                    this.getOritentacaoInsercaoRemocao(oNovoNodo.getDado()));
    
            if (oRamoInsercao.getNodo() == null) {
                oRamoInsercao.setNodo(oNovoNodo);
                break;
            } else {
                nodoAtual = oRamoInsercao.getNodo();
            }
        }
    
        return this;
    }

    public Integer getOritentacaoInsercaoRemocao(Integer iDado) {
        return this.getNodoAtual().getDado() > iDado ? ArvoreBinaria.RAMO_ESQUERDA : ArvoreBinaria.RAMO_DIREITA;
    }

    protected NodoArvore<Integer> criarNodo(Integer iDado) {
        return (new NodoArvore<Integer>(iDado))
            .addRamo(new RamoArvore<Integer>(null))
            .addRamo(new RamoArvore<Integer>(null));
    }

    public ArvoreBinaria remover(int iDado) {
        this.setNodoAtual(this.getRaiz());
        return this.removerNodoBinario(iDado);
    }

    private ArvoreBinaria removerNodoBinario(int iDado) {
        this.setNodoAtual(this.getRaiz());
        this.removerNodo(this.getRaiz(), iDado);
        return this;
    }
    
    protected NodoArvore<Integer> removerNodo(NodoArvore<Integer> oNodo, int iDado) {
        
        if(oNodo == null) {
            return null;
        }
    
        if (iDado < oNodo.getDado()) {
            oNodo.getRamo(ArvoreBinaria.RAMO_ESQUERDA).setNodo(removerNodo(oNodo.getRamo(ArvoreBinaria.RAMO_ESQUERDA).getNodo(), iDado));
        }
        else if (iDado > oNodo.getDado()) {
            oNodo.getRamo(ArvoreBinaria.RAMO_DIREITA).setNodo(removerNodo(oNodo.getRamo(ArvoreBinaria.RAMO_DIREITA).getNodo(), iDado));
        } 
        else {
    
            if (oNodo.getRamo(ArvoreBinaria.RAMO_ESQUERDA).getNodo() == null) {
                return oNodo.getRamo(ArvoreBinaria.RAMO_DIREITA).getNodo();
            } 
            else if (oNodo.getRamo(ArvoreBinaria.RAMO_DIREITA).getNodo() == null) {
                return oNodo.getRamo(ArvoreBinaria.RAMO_ESQUERDA).getNodo();
            }
            else {
                NodoArvore<Integer> oNodoSucessor = encontrarMenorNodo(oNodo.getRamo(ArvoreBinaria.RAMO_DIREITA).getNodo());
                oNodo.setDado(oNodoSucessor.getDado());
                oNodo.getRamo(ArvoreBinaria.RAMO_DIREITA).setNodo(removerNodo(oNodo.getRamo(ArvoreBinaria.RAMO_DIREITA).getNodo(), oNodoSucessor.getDado()));
            }
        }
        return oNodo;
    }
    
    private NodoArvore<Integer> encontrarMenorNodo(NodoArvore<Integer> nodo) {
        while (nodo.getRamo(ArvoreBinaria.RAMO_ESQUERDA).getNodo() != null) {
            nodo = nodo.getRamo(ArvoreBinaria.RAMO_ESQUERDA).getNodo();
        }
        return nodo;
    }

    public NodoArvore<Integer> buscar(int iDado) {
        NodoArvore<Integer> nodoAtual = this.getRaiz();
        
        while (nodoAtual != null) {
            if (iDado == nodoAtual.getDado()) {
                return nodoAtual;
            } else if (iDado < nodoAtual.getDado()) {
                nodoAtual = nodoAtual.getRamo(ArvoreBinaria.RAMO_ESQUERDA).getNodo();
            } else {
                nodoAtual = nodoAtual.getRamo(ArvoreBinaria.RAMO_DIREITA).getNodo();
            }
        }
        
        return null;
    }
    
    // #endregion

    // #region toString

    @Override
    public String toString() {
        StringBuilder oString = new StringBuilder();
        oString.append(super.toString());
        oString.append("Nodos Esquerda: ").append(this.getRamoEsquerda(this.getRaiz()).toString()).append("\n");
        oString.append("Nodos Direita: ").append(this.getRamoDireita(this.getRaiz()).toString()).append("\n");
        return oString.toString();
    }

    // #endregion
}