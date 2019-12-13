package ua.testing;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Student student = Student.builder()
                .name("Taras")
                .build();
        student.setGroup("TI");
        System.out.println( student );
    }
}
