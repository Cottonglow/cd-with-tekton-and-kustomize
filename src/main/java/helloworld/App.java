package helloworld;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("Hello " + args[0]);
        
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
