public class ArvoreDrawer {

    public static void desenharArvore(NodoArvore<Integer> raiz) {
        desenharArvoreRecursivamente(raiz, "", true);
    }

    private static void desenharArvoreRecursivamente(NodoArvore<Integer> nodo, String prefixo, boolean isEsquerda) {
        if (nodo != null) {
            System.out.println(prefixo + (isEsquerda ? "├── " : "└── ") + nodo.getDado());
            desenharArvoreRecursivamente(nodo.getRamo(ArvoreBinaria.RAMO_ESQUERDA).getNodo(), prefixo + (isEsquerda ? "│   " : "    "), true);
            desenharArvoreRecursivamente(nodo.getRamo(ArvoreBinaria.RAMO_DIREITA).getNodo(), prefixo + (isEsquerda ? "│   " : "    "), false);
        }
    }
}
