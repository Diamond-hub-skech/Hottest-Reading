/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * hold alt and scroll with mouse wheel to zoom in/out
 */


/**
 *
 * @author iidiamondii
 */
public class HottestReading {
    
    static class Sensor extends Thread{
        private int id;
        int v;
        Sensor(int id){
            this.id=id;
        }
        public void run(){
            for(int j =1; j<=10;j++){
                v = (id * 19 + j * 6) % 53 + 1;
                mutex.acquire();
                checks++;
                if (v > best) {
                   // Thread.sleep(1);
                   try {
                    Thread.sleep(1);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            }
                    best = v;
                    bestBy = id;
                }
                mutex.release();
            }
        }
    }
    
    static public class MySemaphore {
        private int permits;
        public MySemaphore(int permits) {
        this.permits = permits;
            }
        public synchronized void acquire() {
        while (permits == 0) {
        try {
        wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
        }
        permits--;
        }
        public synchronized void release() {
        permits++;
        notifyAll();
        }
    }
    
        static int best = 0;
        static int bestBy = 0;
        static int checks = 0;
        static MySemaphore mutex = new MySemaphore(1);
        
        public static void main(String[] args) throws InterruptedException{
            Sensor s1 = new Sensor(1);
            Sensor s2 = new Sensor(2);
            Sensor s3 = new Sensor(3);
            
            s1.start();
            s2.start();
            s3.start();
            s1.join();
            s2.join();
            s3.join();
            
            System.out.println("Best temperature: " + best);
            System.out.println("Found by Sensor number: " + bestBy);
            System.out.println("Checks made: " + checks);
        }
  }  


