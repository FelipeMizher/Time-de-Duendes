import java.io.IOException;
import java.util.Scanner;

class Duende{
    String nome;
    int idade;

    public Duende(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }
}

class Pilha{
    private Duende[] array;
    private int topo;

    Pilha(int tamanho){
        array = new Duende[tamanho];
        topo = 0;
    }

    public void empilhar(Duende d){
        if(topo < array.length){
            array[topo++] = d;
        }
    }

    public Duende desempilhar(){
        if(topo == 0){
            throw new RuntimeException("Pilha vazia!");
        }
        return array[--topo];
    }

    public boolean isVazia(){
        return (topo == 0);
    }
}

public class Time_Duendes{
    public static boolean Comparar(Duende a, Duende b){
        if(a.idade != b.idade){
            return a.idade < b.idade;
        }
        return a.nome.compareTo(b.nome) > 0;
    }

    public static void sort(Duende[] duendes, int n){
        for(int i = 0; i < n - 1; i++){
            int maior = i;
            for(int j = i + 1; j < n; j++){
                if(Comparar(duendes[maior], duendes[j])){
                    maior = j;
                }
            }
            swap(duendes, maior, i);
        }
    }

    public static void swap(Duende[] duendes, int i, int j){
        Duende temp = duendes[i];
        duendes[i] = duendes[j];
        duendes[j] = temp;
    }

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a quantidade de duendes a serem cadastrados (Multiplo de 3): ");
        int N = 0;

        do{
            N = sc.nextInt();
            if(N % 3 != 0){
                System.out.println("A quantidade deve ser múltipla de 3. Tente novamente:");
            }
        } while(N % 3 != 0);

        Duende[] duendes = new Duende[N];

        for(int i = 0; i < N; i++){
            System.out.println("\nCadastro do duende " + (i + 1));
            System.out.print("Nome: ");
            sc.nextLine();
            String nome = sc.next();

            System.out.print("Idade: ");
            int idade = sc.nextInt();

            duendes[i] = new Duende(nome, idade);
        }

        sort(duendes, N);

        int times = N / 3;
        Pilha lideres = new Pilha(times);
        Pilha entregadores = new Pilha(times);
        Pilha pilotos = new Pilha(times);

        for(int i = times - 1; i >= 0; i--){
            lideres.empilhar(duendes[i]);
        }

        for(int i = 2 * times - 1; i >= times; i--){
            entregadores.empilhar(duendes[i]);
        }

        for(int i = 3 * times - 1; i >= 2 * times; i--){
            pilotos.empilhar(duendes[i]);
        }

        System.out.print("\n=== TIMES FORMADOS ===\n");

        for(int i = 1; i <= times; i++){
            System.out.println("Time " + i);

            Duende lider = lideres.desempilhar();
            Duende entregador = entregadores.desempilhar();
            Duende piloto = pilotos.desempilhar();

            System.out.println("Líder: " + lider.nome + " " + lider.idade);
            System.out.println("Entregador: " + entregador.nome + " " + entregador.idade);
            System.out.println("Piloto: " + piloto.nome + " " + piloto.idade);
            System.out.println();
        }

        sc.close();
    }
}
