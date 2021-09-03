package view;

import java.util.concurrent.Semaphore;
import Controller.ThreadP;

public class Pricipal {

	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);

		for (int idPessoa = 1; idPessoa < 5; idPessoa++) {
			Thread pessoa = new ThreadP(idPessoa, semaforo);
			pessoa.start();
		}
	}

}