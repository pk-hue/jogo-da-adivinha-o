import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class JogoAdivinhacao{

    private static final int MIN_NUMERO = 1;
    private static final int MAX_NUMERO = 10;
    private static final int MAX_TENTATIVAS = 7;

    private static int gerarNumeroAleatorio(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    private static int lerChute(Scanner sc, int tentativasRestantes){
        while(true){
            System.out.print("Tentativa " + (MAX_TENTATIVAS - tentativasRestantes + 1) + " de " + MAX_TENTATIVAS + "Digite seu chute (" + MIN_NUMERO + " " + MAX_NUMERO + "): ");

            try{
                int chuteAcaso;
                String test;
                int chute = sc.nextInt();
                if(chute < MIN_NUMERO || chute > MAX_NUMERO){
                    System.out.println("Digite um numero valido entre " + MIN_NUMERO + " e " + MAX_NUMERO + ".");
                    continue;
                }
                sc.nextLine();
                return chute;
            }catch(InputMismatchException e){
                System.out.println("Entrada invalida digite apenas números inteiros!");
                sc.nextLine();
            }
        }
    }
    //metodo deve ser refatorado!
    private static void darDica(int chute, int tentativasRestantes){
        if(chute > tentativasRestantes){
            System.out.println("O número secreto é maior que " + chute);
        }else{
            System.out.println("O número secreto é menor que " + chute);
        }
    }

    private static void fecharJogo(Scanner sc){
        sc.close();
        System.out.println("Fechando Jogo, obrigado por jogar!");
    }

    public static void test(){
        System.out.println("teste");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===Jogo da Adivinhacao===");
        System.out.println("Digite seu nome: ");
        String nome = sc.nextLine().trim();

        if(nome.isEmpty()){
            nome = "Jogador aleatorio";
        }

        System.out.println( nome + "Estou pensando em um número entre " + MIN_NUMERO + " e " + MAX_NUMERO);
        System.out.println("Você tem " + MAX_TENTATIVAS + " tentativas!");

        int tentativasRestantes = MAX_TENTATIVAS;
        int numeroSecreto = gerarNumeroAleatorio(MIN_NUMERO, MAX_NUMERO + 1);

        while(tentativasRestantes > 0){

            int chute =  lerChute(sc, tentativasRestantes);

            if(chute == numeroSecreto){
                System.out.println("Parabêns " + nome + " você acertou!");
                fecharJogo(sc);
                return;
            }

            // tentativas restantes podem ser reduzidas em quantidades maiores
            tentativasRestantes--;
            darDica(chute, tentativasRestantes);
            System.out.println("Tentativas restantes " + tentativasRestantes);
        }

        System.out.println("Fim da Jogo!");
        fecharJogo(sc);
    }

}
