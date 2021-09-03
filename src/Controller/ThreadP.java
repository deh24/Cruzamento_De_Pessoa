package Controller;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class ThreadP extends Thread {

	private int idPessoa;
	private static int SaidaDePessoa;
	private Semaphore semaforo;
	Random m = new Random();

	public ThreadP(int idPessoa, Semaphore semaforo) {
		this.idPessoa = idPessoa;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		PessoaAndando();
		try {
			semaforo.acquire();
			Pnp();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	      CruzamentoDePessoas();
	}

	private void PessoaAndando() {
		int tempo = 1000;
		int disPer = 0;

		while (disPer < 200) {
			disPer += (int) ((Math.random() * 3) + 4);
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Está Pessoa " + idPessoa + " já andou nete exato momento " + disPer + " metros");
		}
	}

	private void Pnp() {
		System.out.println("A Pessoa " + idPessoa + " chegou na porta");
		double tI = System.nanoTime();
		int tP = (m.nextInt(3 - 1) + 1) * 1000;
		try {
			Thread.sleep(tP);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		double tF = System.nanoTime();
		double tempototal = tF - tI;
		tempototal = tempototal / Math.pow(10, 9);
		System.out.println("Está Pessoa esta com o " + idPessoa + " Tempo parado: " + tempototal);
	}

	private void CruzamentoDePessoas() {
		SaidaDePessoa++;
		System.out.println("com isso está pessoa " + idPessoa + " foi a " + SaidaDePessoa + "ª. a cruzar a porta");
	}
}