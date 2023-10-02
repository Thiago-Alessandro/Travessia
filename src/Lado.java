import java.util.ArrayList;

public class Lado {

    private ArrayList<Pessoa> pessoas = new ArrayList<>();

    public void removerPessoa(Pessoa pessoa) throws MaeEFilhoException, PaiEFilhaException, LadraException {
        pessoas.remove(pessoa);
        validaPessoas();
    }

    public void adicionarPessoa(Pessoa pessoa) throws PaiEFilhaException, MaeEFilhoException, LadraException {
        pessoas.add(pessoa);
        validaPessoas();
    }

    private void validaPessoas() throws MaeEFilhoException, PaiEFilhaException, LadraException {

        boolean possuiMae = false;
        boolean possuiPai = false;
        boolean possuiFilho = false;
        boolean possuiFilha = false;
        boolean possuiPolicial = false;
        boolean possuiLadra = false;

        for(Pessoa pessoaFor : pessoas){
            if(pessoaFor instanceof Mae){
                possuiMae = true;
            } else if(pessoaFor instanceof Pai){
                possuiPai = true;
            } else if(pessoaFor instanceof Filho){
                possuiFilho = true;
            } else if(pessoaFor instanceof Filha){
                possuiFilha = true;
            } else if (pessoaFor instanceof Policial) {
                possuiPolicial = true;
            } else if (pessoaFor instanceof  Ladra) {
                possuiLadra = true;
            }
        }

        if(possuiMae && possuiFilho && !possuiPai){
            throw new MaeEFilhoException();
        }
        if(possuiPai && possuiFilha && !possuiMae){
            throw new PaiEFilhaException();
        }
        System.out.println(possuiLadra);
        if(possuiLadra && !possuiPolicial && (possuiMae || possuiFilha || possuiFilho || possuiPai)){
            throw new LadraException();
        }
    }

    public String mostrarPessoas(){
        String pessoas = "";
        for(Pessoa pessoa : this.pessoas){
            pessoas += "\n" + pessoa;
        }
        return pessoas;
    }

    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(ArrayList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }


}
