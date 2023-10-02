import java.util.ArrayList;

public class Jangada {

    private Lado ladoAtual;
    private final ArrayList<Pessoa> passageiros = new ArrayList<>();
    private final int capacidade = 2;


    public Jangada(Lado ladoAtual){
        this.ladoAtual = ladoAtual;
    }

    public String mostrarPassageiros(){
        String passageirosString = "";
        for (Pessoa pessoa : passageiros){
            passageirosString += "\n" + pessoa;
        }
        return passageirosString;
    }

    public void atravessarRio() throws JangadaSemPilotoException {
        for(Pessoa pessoa : passageiros){
            if (pessoa.getManobraJangada()){
                return;
            }
        }
        throw new JangadaSemPilotoException();
    }

    public void verificaPassageiros() throws MaeEFilhoException, PaiEFilhaException, LadraException {
        boolean possuiMae = false;
        boolean possuiPai = false;
        boolean possuiFilho = false;
        boolean possuiFilha = false;
        boolean possuiLadra = false;

        for(Pessoa pessoaFor : passageiros){
            if(pessoaFor instanceof Mae){
                possuiMae = true;
            } else if(pessoaFor instanceof Pai){
                possuiPai = true;
            } else if(pessoaFor instanceof Filho){
                possuiFilho = true;
            } else if(pessoaFor instanceof Filha){
                possuiFilha = true;
            } else if (pessoaFor instanceof  Ladra) {
                possuiLadra = true;
            }
        }

        if(possuiMae && possuiFilho){
            throw new MaeEFilhoException();
        }
        if(possuiPai && possuiFilha){
            throw new PaiEFilhaException();
        }
        System.out.println(possuiLadra);
        if(possuiLadra && (possuiMae || possuiFilha || possuiFilho || possuiPai)){
            throw new LadraException();
        }
    }

    public ArrayList<Pessoa> getPassageiros() {
        return passageiros;
    }

    public Lado getLadoAtual() {
        return ladoAtual;
    }

    public void setLadoAtual(Lado ladoAtual) {
        this.ladoAtual = ladoAtual;
    }

    public int getCapacidade() {
        return capacidade;
    }

}
