import java.util.ArrayList;

public class PaginaChaves {
    
    private ArrayList<Integer> aChaves;

    public PaginaChaves(ArrayList<Integer> aChaves) {
        this.setChaves(aChaves);
    }

    public PaginaChaves setChaves(ArrayList<Integer> aChaves) {
        this.aChaves = aChaves;
        return this;
    }

    public ArrayList<Integer> getChaves() {
        return this.aChaves;
    }

    public PaginaChaves addChave(Integer iChave) {
        this.aChaves.add(iChave);
        return this;
    }

    public boolean isVazia() {
        return this.getChaves().isEmpty();
    }

    //#region toString

    @Override
    public String toString() {
        StringBuilder oStringBuilder = new StringBuilder();
        oStringBuilder
            .append("aChaves: ")
            .append(this.getChaves());
        return oStringBuilder.toString();
    }

    //#endregion
}
