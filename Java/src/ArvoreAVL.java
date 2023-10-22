public class ArvoreAVL extends ArvoreBinaria {

    //#region Constructor

    public ArvoreAVL(NodoArvore<Integer> oRaiz) {
        super(oRaiz);
    }

    //#endregion

    //#region Métodos de Manipulação

    @Override
    public ArvoreAVL inserir(int iDado) {
        super.inserir(iDado, oNovoNodo -> {
            super.inserir(iDado);
            this.balancear(oNovoNodo);
        });
        return this;
    }

    //#endregion

    //#region Getters/Setters

    public int getAltura(NodoArvore<Integer> oNodo) {
        if (oNodo == null) {
            return 0;
        }
        return Math.max(getAltura(oNodo.getRamo(this.RAMO_ESQUERDA).getNodo()), 
                        getAltura(oNodo.getRamo(this.RAMO_DIREITA).getNodo())) + 1;
    }

    public int getFatorBalanceamento(NodoArvore<Integer> oNodo) {
        if (oNodo == null) {
            return 0;
        }
        return getAltura(oNodo.getRamo(this.RAMO_ESQUERDA).getNodo()) - 
               getAltura(oNodo.getRamo(this.RAMO_DIREITA).getNodo());
    }

    //#endregion

    //#region Rotações

    protected NodoArvore<Integer> rotacaoSimplesEsquerda(NodoArvore<Integer> oNodo) {
        NodoArvore<Integer> oNovaRaiz = oNodo.getRamo(this.RAMO_DIREITA).getNodo();
        oNodo.getRamo(this.RAMO_DIREITA).setNodo(oNovaRaiz.getRamo(this.RAMO_ESQUERDA).getNodo());
        oNovaRaiz.getRamo(this.RAMO_ESQUERDA).setNodo(oNodo);
        return oNovaRaiz;
    }

    protected NodoArvore<Integer> rotacaoSimplesDireita(NodoArvore<Integer> oNodo) {
        NodoArvore<Integer> oNovaRaiz = oNodo.getRamo(this.RAMO_ESQUERDA).getNodo();
        oNodo.getRamo(this.RAMO_ESQUERDA).setNodo(oNovaRaiz.getRamo(this.RAMO_DIREITA).getNodo());
        oNovaRaiz.getRamo(this.RAMO_DIREITA).setNodo(oNodo);
        return oNovaRaiz;
    }

    protected NodoArvore<Integer> rotacaoDuplaEsquerda(NodoArvore<Integer> oNodo) {
        oNodo.getRamo(this.RAMO_DIREITA).setNodo(rotacaoSimplesDireita(oNodo.getRamo(this.RAMO_DIREITA).getNodo()));
        return rotacaoSimplesEsquerda(oNodo);
    }

    protected NodoArvore<Integer> rotacaoDuplaDireita(NodoArvore<Integer> oNodo) {
        oNodo.getRamo(this.RAMO_ESQUERDA).setNodo(rotacaoSimplesEsquerda(oNodo.getRamo(this.RAMO_ESQUERDA).getNodo()));
        return rotacaoSimplesDireita(oNodo);
    }

    //#endregion

    //#region Balanceamento

    private NodoArvore<Integer> balancear(NodoArvore<Integer> oNodo) {
        if (oNodo == null) {
            return null;
        }

        int fatorBalanceamento = this.getFatorBalanceamento(oNodo);

        // Rotação à esquerda
        if (fatorBalanceamento > 1 && this.getFatorBalanceamento(oNodo.getRamo(this.RAMO_ESQUERDA).getNodo()) >= 0) {
            return rotacaoSimplesDireita(oNodo);
        }

        // Rotação à direita
        if (fatorBalanceamento < -1 && this.getFatorBalanceamento(oNodo.getRamo(this.RAMO_DIREITA).getNodo()) <= 0) {
            return rotacaoSimplesEsquerda(oNodo);
        }

        // Rotação dupla à esquerda
        if (fatorBalanceamento > 1 && this.getFatorBalanceamento(oNodo.getRamo(this.RAMO_ESQUERDA).getNodo()) < 0) {
            oNodo.getRamo(this.RAMO_ESQUERDA).setNodo(rotacaoSimplesEsquerda(oNodo.getRamo(this.RAMO_ESQUERDA).getNodo()));
            return rotacaoSimplesDireita(oNodo);
        }

        // Rotação dupla à direita
        if (fatorBalanceamento < -1 && this.getFatorBalanceamento(oNodo.getRamo(this.RAMO_DIREITA).getNodo()) > 0) {
            oNodo.getRamo(this.RAMO_DIREITA).setNodo(rotacaoSimplesDireita(oNodo.getRamo(this.RAMO_DIREITA).getNodo()));
            return rotacaoSimplesEsquerda(oNodo);
        }

        return oNodo;
    }

    //#endregion

    //#region toString

    @Override
    public String toString() {
        StringBuilder oString = new StringBuilder();
        oString.append(super.toString());
        return oString.toString();
    }

    //#endregion
}
