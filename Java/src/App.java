
public class App {

    public static void main(String[] args) {
        
        ArvoreB oArvore = new ArvoreB(10, 2);

        oArvore.inserir(11);
        oArvore.inserir(12);
        oArvore.inserir(9);

        oArvore.remover(9);
        oArvore.inserir(100);

        System.out.println(oArvore.buscar(100));

    }
    
}
