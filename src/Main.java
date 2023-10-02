import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static final Lado inicio = new Lado();
    private static final Lado chegada = new Lado();

    private static final Jangada jangada = new Jangada(inicio);

    public static void main(String[] args) {

        new Pai();
        new Mae();
        new Filha();
        new Filha();
        new Filho();
        new Filho();
        new Policial();
        new Ladra();

        inicio.setPessoas(Pessoa.getPessoas());

        boolean venceu = false;

        do{

            try{

                switch (menu()){

                    case 1 -> embarcar(selecionarPessoa(jangada.getLadoAtual().getPessoas()));
                    case 2 -> desembarcar(selecionarPessoa(jangada.getPassageiros()));
                    case 3 -> System.out.println(inicio.mostrarPessoas());
                    case 4 -> System.out.println(chegada.mostrarPessoas());
                    case 5 -> System.out.println(jangada.mostrarPassageiros());
                    case 6 -> atravessarRio();
                }
                venceu = validaVitoria();

            } catch (OpcaoInvalidaException | SemPessoasParaSelecionarException e){
                System.out.println(e.getMessage());
            }
        }while(!venceu);
        System.out.println("Parabéns você venceu!");
    }

    public static int menu() throws OpcaoInvalidaException {
        System.out.println("""
                            
                            MENU
                1 - Colocar pessoa na jangada
                2 - Retirar pessoa da jangada
                3 - Ver lado inicial
                4 - Ver lado final
                5 - Ver jangada
                6 - Atravessar Rio
                """);
        int opcao = sc.nextInt();
        if(opcao < 1 || opcao > 6){
            throw new OpcaoInvalidaException(opcao);
        }
        return opcao;
    }

    public static Pessoa selecionarPessoa(ArrayList<Pessoa> pessoas) throws OpcaoInvalidaException, SemPessoasParaSelecionarException {
        if(pessoas.size() > 0){
            int cont = 1;
            for(Pessoa pessoa : pessoas){
                System.out.println(cont + " - " + pessoa);
                cont ++;
            }
            int opcao = sc.nextInt();
            if(opcao < 1 || opcao > pessoas.size()){
                throw new OpcaoInvalidaException(opcao);
            }
            System.out.println(pessoas.get(opcao-1));
            return pessoas.get(opcao-1);
        }
        throw new SemPessoasParaSelecionarException();
    }

    public static void embarcar(Pessoa pessoa){
        try{
            pessoa.embarcar(jangada);
        }catch (PaiEFilhaException | MaeEFilhoException | JangadaLotadaException | LadraException e){
            System.out.println(e.getMessage());
        }
    }

    public static void desembarcar(Pessoa pessoa){
        try {
            pessoa.desembarcar(jangada);
        } catch (PaiEFilhaException | MaeEFilhoException | LadraException e){
            System.out.println(e.getMessage());
        }
    }

    public static void atravessarRio(){
        try{
            jangada.atravessarRio();
            if(jangada.getLadoAtual() == inicio){
                jangada.setLadoAtual(chegada);
                System.out.println("\nLado atual: Chegada\n");
            } else {
                jangada.setLadoAtual(inicio);
                System.out.println("\nLado atual: Inicio\n");
            }
        } catch (JangadaSemPilotoException e){
            System.out.println(e.getMessage());
        }
    }

    public static boolean validaVitoria(){
        return chegada.getPessoas().size() == 8;
    }

}
