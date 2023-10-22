import java.util.ArrayList;
import java.util.Collections;

public class ArvoreB extends Arvore<PaginaChaves> {

    private int iOrdem;

    public ArvoreB(Integer iRaiz, Integer iOrdem) {
        super();
        this.setOrdem(iOrdem);
        this.setRaiz(this.criarNodo(this.criarPaginaChaves(iRaiz)));
        this.setNodoAtual(this.getRaiz());
    }

    public ArvoreB setOrdem(int iOrdem) {
        this.iOrdem = iOrdem;
        return this;
    }

    public int getOrdem() {
        return this.iOrdem;
    }

    public ArvoreB inserir(Integer iChave) {
        super.inserir(this.criarPaginaChaves(iChave), this::inserirRecursivo);
        return this;
    }

    private ArvoreB inserirRecursivo(NodoArvore<PaginaChaves> oNovoNodo) {
    
        int iChaveInsercao = oNovoNodo.getDado().getChaves().get(0);

        if(this.getNodoAtual() == null) {

        }

        PaginaChaves oPaginaChaves = this.getNodoAtual().getDado();

        if(this.isPaginaOcupada(this.getNodoAtual().getDado())) {
            this.inserirPaginaOcupado(oNovoNodo);
            return this;
        }

        oPaginaChaves.addChave(iChaveInsercao);
        Collections.sort(oPaginaChaves.getChaves());

        return this;
    }

    protected ArvoreB inserirPaginaOcupado(NodoArvore<PaginaChaves> oNovoNodo) {
        
        int iChaveInsercao = oNovoNodo.getDado().getChaves().get(0);

        ArrayList<Integer> aChaves = this.getNodoAtual().getDado().getChaves();

        for(int iRamo = 0; iRamo <= aChaves.size(); iRamo++) {

            int iChave = aChaves.get(iRamo);

            if(!(iChaveInsercao < iChave || iChaveInsercao > iChave)) {
                continue;
            }

            RamoArvore<PaginaChaves> oProximoRamo = this.getNodoAtual().getRamo(iRamo);
            NodoArvore<PaginaChaves> oProximoNodo = oProximoRamo.getNodo(); 

            if(oProximoNodo == null) {
                oProximoRamo.setNodo(oNovoNodo);
                return this;
            }

            this.setNodoAtual(oProximoNodo);
            return this.inserirRecursivo(oNovoNodo);
        }

        return this;
    }

    public ArvoreB remover(Integer iChave) {
        removerRecursivo(this.getRaiz(), iChave);
        return this; 
    }
    
    private NodoArvore<PaginaChaves> removerRecursivo(NodoArvore<PaginaChaves> oNodoAtual, Integer iChave) {
        
        if(oNodoAtual == null) {
            return null;
        }

        ArrayList<Integer> aChaves = oNodoAtual.getDado().getChaves();

        for(Integer iChaveAtual: aChaves) {
            if(iChave == iChaveAtual) {
                return oNodoAtual;
            }
        }

        for(RamoArvore<PaginaChaves> oRamoAtual: oNodoAtual.getRamos()) {

            NodoArvore<PaginaChaves> oNodoRamo = oRamoAtual.getNodo();

            if(oNodoRamo == null) {
                continue;
            }

            oNodoAtual = removerRecursivo(oNodoRamo, iChave);

            if(oNodoAtual != null) {
                oRamoAtual.setNodo(null);
                return oNodoAtual;
            }
        }

        return null;

    }


    public boolean isPaginaOcupada(PaginaChaves oPagina) {
        return oPagina.getChaves().size() == (2 * this.getOrdem() - 1);
    }

    protected NodoArvore<PaginaChaves> criarNodo(PaginaChaves aPagina) {

        NodoArvore<PaginaChaves> oNodo = new NodoArvore<PaginaChaves>(aPagina);
        
        for(int iRamo = 0; iRamo <= (aPagina.getChaves().size() + 1); iRamo++) {
            oNodo.addRamo(new RamoArvore<PaginaChaves>(null));
        }

        return oNodo;
    }

    protected PaginaChaves criarPaginaChaves(Integer iChave) {
        ArrayList<Integer> aPagina = new ArrayList<Integer>(2 * this.getOrdem() - 1);
        aPagina.add(iChave);
        PaginaChaves oPagina = new PaginaChaves(aPagina);
        return oPagina;
    }

    public NodoArvore<PaginaChaves> buscar(Integer iChave) {
        return buscarRecursivo(this.getRaiz(), iChave);
    }
    
    private NodoArvore<PaginaChaves> buscarRecursivo(NodoArvore<PaginaChaves> oNodoAtual, Integer iChave) {
        
        if(oNodoAtual == null) {
            return null;
        }

        ArrayList<Integer> aChaves = oNodoAtual.getDado().getChaves();

        for(Integer iChaveAtual: aChaves) {
            if(iChave == iChaveAtual) {
                return oNodoAtual;
            }
        }

        for(RamoArvore<PaginaChaves> oRamoAtual: oNodoAtual.getRamos()) {

            NodoArvore<PaginaChaves> oNodoRamo = oRamoAtual.getNodo();

            if(oNodoRamo == null) {
                continue;
            }

            oNodoAtual = buscarRecursivo(oNodoRamo, iChave);

            if(oNodoAtual != null) {
                return oNodoAtual;
            }
        }

        return null;
    }

}
