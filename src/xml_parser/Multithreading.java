package xml_parser;

import xml_parser.CommonAddressCache;
import xml_parser.Xmlparsing;
import xml_parser.CommonAddressCache;

class MultithreadingDemo extends Thread
{
    public void run()
    {
        try
        {
        	Xmlparsing x= new Xmlparsing();
        	x.parse();
        	CommonAddressCache c = new CommonAddressCache();
        	c.SearchDB("33 s 3rd st", "San Jose", "CA", "95113", "US");
            // Displaying the thread that is running
            System.out.println ("Thread " +
                  Thread.currentThread().getId() +
                  " is running");
 
        }
        catch (Exception e)
        {
            // Throwing an exception
            System.out.println ("Exception is caught");
        }
    }
}
 
// Main Class
public class Multithreading
{
    public static void main(String[] args)
    {
        int n = 8; // Number of threads
        for (int i=0; i<2; i++)
        {
            MultithreadingDemo object = new MultithreadingDemo();
            object.start();
        }
    }
}