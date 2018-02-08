package ru.isu.compmodels.imitation;

import java.util.Queue;
import java.util.concurrent.*;

public class DummyServer extends Thread implements Server {

    BlockingQueue<Request> requests = new LinkedBlockingQueue<Request>();
    private int unitsPerSecond;
    private boolean shouldStop=false;

    @Override
    public void setPerformance(int unitsPerSecond) {
        this.unitsPerSecond = unitsPerSecond;
    }

    @Override
    public int getPerformance() {
        return unitsPerSecond;
    }

    @Override
    public void process(Request r) {
        try {
            requests.put(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCurrentLoadUnits() {
        int sum  = 0;
        for (Request request : requests) {
            sum+=request.getLoad();
        }
        return sum;
    }

    @Override
    public float getCurrentLoad() {
        return Math.min(actualLoad(), 100);
    }


    /**
     * Реальная нагрузка измеряется в объеме запросов в юнитах, деленных на производительность. Может быть Чуровских 136%
     * @return
     */
    private float actualLoad() {
        return getCurrentLoadUnits() / getPerformance() * 100;
    }

    @Override
    public void run() {
        System.out.println("Запуск сервера " +this);
        while(!shouldStop){
            try {
                Request r = requests.take();
                //симуляция обработки запроса, просто спим некоторое время
                Thread.sleep(1000* r.getLoad() / getPerformance());

                //todo убрать, чтобы не тормозило выводом в консоль.
                System.out.println("Запрос обработан: "+ r.toString());
            } catch (InterruptedException e) {
                System.out.println("Разбудили ");
            }
        }
        System.out.println("Завершение работы сервера " +this);
    }

    @Override
    public void shutDown() {
        shouldStop = true;
        //Будим, если он уснул по take на пустой очереди
        this.interrupt();
    }

}
