package ua.training;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

//IMPORTANT NOTE:
//there were a lot of different cases/experiments, so you can see a lot of commented blocks
	
/*
	public static void main(String[] args) {
        throw null;
    }
*/
	
/*
	RUNTIME ERROR: Exception in thread "main" java.lang.Error
	public static void main(String[] args) {
        Error ref = new Error(); // ������� ���������
        throw ref;               // "�������" ���
    }	
*/
	
/*
	RUNTIME ERROR: Exception in thread "main" java.lang.StackOverflowError
	public static void main(String[] args) {
        f(null);
    }
    public static void f(NullPointerException e) {
        try {
            throw e;
        } catch (NullPointerException npe) {
            f(npe);
        }
    }
*/
	
/*	
	public static void main(String[] args) {
        System.err.println("err");
        System.out.println("sout"); //��������� �� ������ "Exception in thread..." ������ �������� sout
        throw new Error();
    }
*/
	
/*	
	public static void main(String[] args) {
        System.out.println(sqr(15));
    }
    
	public static double sqr(double arg) { // ���� double
        int k = 1;                  // ���� int
        return k;                   // ����� ������ ������������� int � double
    }
*/
	
/*
	public static void main(String[] args) {
        System.out.println(sqr(15));
    }
	
	public static double sqr(double arg) {
        while (true); // �����������, �� �������������!
    }
    
    //�������� ���������� ��������� ������ �� ����������!
	public static double sqr(double arg) {
        throw new RuntimeException();
    }
    
	public static int area(int width, int height) {
	    if (width < 0 || height < 0) {
	        throw new IllegalArgumentException("Negative sizes: w = " + width + ", h = " + height);
	    }
	    return width * height;
	}
*/
	
/*
	public static void main(String[] args) {
        System.err.println("#1.in");
        try {
            f(); // ������� �����, �������� � ����, �������� � ���� ����������
        } catch (Error e) { // "�����������" "�������" ����������
            System.err.println("#1.CATCH");  // � ��������
        }
        System.err.println("#1.out");  // �������� ������
    }

    public static void f() {
        System.err.println(".   #2.in");
        try {
	        g(); // ������� �����, �������� � ����, �������� � ���� ����������
        } catch (Error e) { // "�����������" "�������" ����������
            System.err.println(".	#2.CATCH");  // � ��������
        }
        System.err.println(".   #2.out"); // ����������!
    }

    public static void g() {
        System.err.println(".   .   #3.in");
        h(); // ������� �����, �������� � ����, �������� � ���� ����������
        System.err.println(".   .   #3.out"); // ����������!
    }

    public static void h() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.THROW");
            throw new Error(); // ������� �� ���� ����� ������� ("��������� �����") �� 'throw'
        }
        System.err.println(".   .   .   #4.out"); // ����������!
    }
*/
	
/*
	public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                RuntimeException re = (RuntimeException) e;
                System.err.print("��� RuntimeException �� ����� ����!!!");              
            } else {
                System.err.print("� ����� ������ �� RuntimeException???");              
            }            
        }
    }	
*/
	
/*
	public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new Error();}
            System.err.print(" 1");
        } catch (Exception e) {
            System.err.print(" 2"); //it doesn't catch siblings             
        } catch (Error er) {
            System.err.print(" 3"); //this will work              
        }
        System.err.print(" 4");              
    }
*/

/*
	public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch (RuntimeException e) { // ����������� RuntimeException
            System.err.print(" 2.1");
            try {
                System.err.print(" 2.2");
                if (true) {throw new Error();} // � ������� ����� Error
                System.err.print(" 2.3");
            } catch (Throwable t) {            // ����������� Error
                System.err.print(" 2.4");                 
            }
            System.err.print(" 2.5");
        } catch (Error e) { // ���� ���� cath �� Error "����", �� �� � ���� �� ��������
            System.err.print(" 3");
        }
        System.err.println(" 4");
    }
*/
	
	/*		 
	 public static void main(String[] args) {
		 try {
	            throw new RuntimeException();
	        } finally {
	            //System.out.println("finally");
	            System.err.println("finally");
	        }
		 try {
	            Throwable t = new Exception(); // ������ ���� Throwable ��������� �� ������ ���� Exception
	            throw t;
	        } catch (RuntimeException e) {
	            System.err.println("catch RuntimeException");
	        } catch (Exception e) {
	            System.err.println("catch Exception");
	        } catch (Throwable e) {
	            System.err.println("catch Throwable");
	        }
	        System.err.println("next statement");
	    }
	*/
	
/*
	public static void main(String[] args) {
        try {
            System.err.println("finally-������ �� ���������� ������ ���� �� �������� JVM");
        	//System.exit(42);
        	return;
        } finally {
            System.err.println("finally-������ �������� ����������, ���� ���� try-���� ���������� ���������� ������ �� ������");
        }
    }	
*/

/*
	public static void main(String[] args) {
        try {
            System.err.println("try");
            if (true) {throw new RuntimeException();}
        //} catch (RuntimeException re) {
        //	System.err.println("it's caught");
        } finally {
            System.err.println("finally");
        }
        System.err.println("�� ���������, ���� ���������� �� �������");
    }
*/

/*
public static void main(String[] args) {
    try {
        System.err.print(" 0");
        try {
            System.err.print(" 1");
            if (true) {throw new RuntimeException();}
            System.err.print(" 2");
        } catch (RuntimeException e) {
            System.err.print(" 3"); // ������� - ���� ����������
        } finally {                 
            System.err.print(" 4"); // ������� ������
        }
        System.err.print(" 5");     // ������� - ���������� ��� � �����
    } catch (Exception e) {
        System.err.print(" 6");     // �� ������� - ��� ����������, ��� �����������
    } finally {
        System.err.print(" 7");     // ������� ������
    }
    System.err.print(" 8");         // ������� - ���������� ��� � �����
}
*/

/*
public static void main(String[] args) throws IOException {
    throw new Exception(); // ��� ������ ����������
}
	
public static void main(String[] args) throws Exception {
    throw new IOException(); // ��� ���
}
*/
	
/*	
	// EOFException ����������� catch-��, �� �� ������
    public static void main(String[] args) throws FileNotFoundException {
        try {
            if (System.currentTimeMillis() % 2 == 0) {
                throw new EOFException();
            } else {
                throw new FileNotFoundException();
            }
        } catch (EOFException e) {
            // ...
        }
    }
*/

/*
	public static void main(String[] args) throws Exception { 
        Throwable t = new Exception(); // � ������ ����� Exception
        throw t; // �� ��� ������ ���������� 
    }	
	public static void main(String[] args) throws FileNotFoundException { 
		Exception e = new FileNotFoundException(); // � ������ ����� Exception
        throw e; // � ��� ������ ���������� 
    }	
*/

/*
	public class Parent {
	    // ������ ������ IOException � InterruptedException
	    public void f() throws IOException, InterruptedException {}
	}

	class Child extends Parent {
	    // � ������� ������ ������ �������� IOException
	    @Override
	    //ok:
	    public void f() throws FileNotFoundException {}
	    //not ok:
	    //public void f() throws IllegalAccessException {}
	}	

*/

public static void main(String[] args) throws InterruptedException, IOException {
    f0();
    f1();
    f2();
}
public static void f0() throws EOFException {
	System.out.println("this part");
}
public static void f1() throws FileNotFoundException {
	System.out.println("of homework");
}
public static void f2() throws InterruptedException {
	System.out.println("is done");
}
	
}
