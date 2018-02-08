package ru.isu.compmodels.imitation;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Создаем сервер
        Server server1 = new DummyServer();
        server1.setPerformance(100);

        //Запускаем сервер
        Thread t1 = (Thread) server1;
        t1.start();

        // Делаем запрос к серверу напрямую
        server1.process(new SimpleRequest());

        //Убедимся, что поток сервера в состоянии ожидания на пустой очереди
        Thread.currentThread().sleep(2000);
        System.out.println(t1.getState());

        //Остановим сервер, дадим ему сигнал
        server1.shutDown();

        //Дождемся его остановки
        t1.join();
        //Убедимся, что поток завершен
        System.out.println(t1.getState());
    }
}
