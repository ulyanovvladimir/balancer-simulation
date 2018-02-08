package ru.isu.compmodels.imitation;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	    // write your code here

        // Создаем сервер
        Server server1 = new DummyServer();
        server1.setPerformance(100);

        //Запускаем сервер
        Thread t1 = new Thread(server1);
        t1.start();

        // Делаем запрос к серверу напрямую
        server1.process(new SimpleRequest());

    }
}
