package com.zilker.multithreading;
		
	public class Multiprogram  {
		
		static int[] array1 = new int[]{2,4,6,8,10,12};
		static int[] array2 = new int[]{12,14,16,18,20,22};
		static int[] array3 = new int[]{22,24,26,28,30,32};
		static int i=0, j=0, k=0,l=0;
		static Object monitor = new Object();
		 static boolean one = true;
		 static boolean two = false;
		 static boolean three = false;

		 public static void main(String[] args) {

		  Thread t1 = new Thread(new MultiprogramImpl(1));
		  Thread t2 = new Thread(new MultiprogramImpl(2));
		  Thread t3 = new Thread(new MultiprogramImpl(3));
		  t1.start();
		  t2.start();
		  t3.start();
		 }

		 static class MultiprogramImpl implements Runnable {

		  int threadId;

		  MultiprogramImpl(int threadId) {
		   this.threadId = threadId;
		  }

		  public void run() {
		   print();
		  }

		  private void print() {
		   try {
		    while (true) {
		     Thread.sleep(500);
		     synchronized (monitor) {
		      if (1 == threadId) {
		       if (!one) {
		    	   monitor.wait();
		       } else {
		        System.out.println(array1[i++]);
		       
		        one = false;
		        two = true;
		        three = false;
		        monitor.notifyAll();
		       }
		      }
		      if (2 == threadId) {
		       if (!two) {
		    	   monitor. wait();
		       } else {
		    	   System.out.println(array2[j++]);
		        one = false;
		        two = false;
		        three = true;
		        monitor.notifyAll();
		       }
		      }
		      if (3 == threadId) {
		       if (!three) {
		    	   monitor.wait();
		       } else {
		    	   System.out.println(array3[k++]);
		        one = true;
		        two = false;
		        three = false;
		        monitor.notifyAll();
		        if(k == array3.length){
		        	System.exit(0);
		        }
		       }
		      }
		     }
		    }
		   } catch (InterruptedException e) {
		    e.printStackTrace();
		   }

		  }

		 }

		}
	

