import java.util.ArrayList;

public class Pessoa {

    private boolean manobraJangada = false;

    private static final ArrayList<Pessoa> pessoas = new ArrayList<>();

    public Pessoa(){
        Pessoa.getPessoas().add(this);
    }

    public void embarcar(Jangada jangada) throws JangadaLotadaException, PaiEFilhaException, MaeEFilhoException, LadraException {
        if(jangada.getPassageiros().size() == jangada.getCapacidade()){
            throw new JangadaLotadaException();
        }
        try{
            jangada.getLadoAtual().removerPessoa(this);
        } catch (PaiEFilhaException e){
            jangada.getLadoAtual().getPessoas().add(this);
            throw new PaiEFilhaException();
        } catch (MaeEFilhoException e){
            jangada.getLadoAtual().getPessoas().add(this);
            throw new MaeEFilhoException();
        } catch (LadraException e){
            jangada.getLadoAtual().getPessoas().add(this);
            throw new LadraException();
        }
        jangada.getPassageiros().add(this);
        try{
            jangada.verificaPassageiros();
        }catch (PaiEFilhaException e){
            jangada.getPassageiros().remove(this);
            jangada.getLadoAtual().getPessoas().add(this);
            throw new PaiEFilhaException();
        } catch (MaeEFilhoException e){
            jangada.getPassageiros().remove(this);
            jangada.getLadoAtual().getPessoas().add(this);
            throw new MaeEFilhoException();
        } catch (LadraException e){
            jangada.getPassageiros().remove(this);
            jangada.getLadoAtual().getPessoas().add(this);
            throw new LadraException();
        }
    }

    public boolean desembarcar(Jangada jangada) throws PaiEFilhaException, MaeEFilhoException, LadraException {
        try{
            jangada.getLadoAtual().adicionarPessoa(this);
        }catch (PaiEFilhaException e){
            jangada.getLadoAtual().getPessoas().remove(this);
            throw new PaiEFilhaException();
        }catch (MaeEFilhoException e){
            jangada.getLadoAtual().getPessoas().remove(this);
            throw new MaeEFilhoException();
        } catch (LadraException e){
            jangada.getLadoAtual().getPessoas().remove(this);
            throw new LadraException();
        }
        return jangada.getPassageiros().remove(this);
    }

    public boolean getManobraJangada(){
        return manobraJangada;
    }

    public void setManobraJangada(boolean manobraJangada) {
        this.manobraJangada = manobraJangada;
    }

    public static ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }
}
