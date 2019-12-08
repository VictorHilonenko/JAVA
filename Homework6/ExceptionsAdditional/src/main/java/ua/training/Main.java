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
        Error ref = new Error(); // создаем экземпл€р
        throw ref;               // "бросаем" его
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
        System.out.println("sout"); //сообщение об ошибке "Exception in thread..." иногда обгон€ет sout
        throw new Error();
    }
*/
	
/*	
	public static void main(String[] args) {
        System.out.println(sqr(15));
    }
    
	public static double sqr(double arg) { // надо double
        int k = 1;                  // есть int
        return k;                   // можно не€вно преобразовать int в double
    }
*/
	
/*
	public static void main(String[] args) {
        System.out.println(sqr(15));
    }
	
	public static double sqr(double arg) {
        while (true); // ”дивительно, но  ќћѕ»Ћ»–”≈“—я!
    }
    
    //механизм исключений позвол€ет Ќ»„≈√ќ Ќ≈ ¬ќ«¬–јўј“№!
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
            f(); // создаем фрейм, помещаем в стек, передаем в него управление
        } catch (Error e) { // "перехватили" "лет€щее" исключение
            System.err.println("#1.CATCH");  // и работаем
        }
        System.err.println("#1.out");  // работаем дальше
    }

    public static void f() {
        System.err.println(".   #2.in");
        try {
	        g(); // создаем фрейм, помещаем в стек, передаем в него управление
        } catch (Error e) { // "перехватили" "лет€щее" исключение
            System.err.println(".	#2.CATCH");  // и работаем
        }
        System.err.println(".   #2.out"); // ѕ–ќѕ”—“»Ћ»!
    }

    public static void g() {
        System.err.println(".   .   #3.in");
        h(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   .   #3.out"); // ѕ–ќѕ”—“»Ћ»!
    }

    public static void h() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.THROW");
            throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
        }
        System.err.println(".   .   .   #4.out"); // ѕ–ќѕ”—“»Ћ»!
    }
*/
	
/*
	public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                RuntimeException re = (RuntimeException) e;
                System.err.print("Ёто RuntimeException на самом деле!!!");              
            } else {
                System.err.print("¬ каком смысле не RuntimeException???");              
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
        } catch (RuntimeException e) { // перехватили RuntimeException
            System.err.print(" 2.1");
            try {
                System.err.print(" 2.2");
                if (true) {throw new Error();} // и бросили новый Error
                System.err.print(" 2.3");
            } catch (Throwable t) {            // перехватили Error
                System.err.print(" 2.4");                 
            }
            System.err.print(" 2.5");
        } catch (Error e) { // хот€ есть cath по Error "ниже", но мы в него не попадаем
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
	            Throwable t = new Exception(); // ссылка типа Throwable указывает на объект типа Exception
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
            System.err.println("finally-секци€ Ќ≈ вызываетс€ только если мы Ђприбилиї JVM");
        	//System.exit(42);
        	return;
        } finally {
            System.err.println("finally-секци€ получает управление, даже если try-блок завершилс€ директивой выхода из метода");
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
        System.err.println("не выведетс€, если исключение не поймано");
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
            System.err.print(" 3"); // «ј’ќƒ»ћ - есть исключение
        } finally {                 
            System.err.print(" 4"); // заходим всегда
        }
        System.err.print(" 5");     // заходим - выполнение ”∆≈ в норме
    } catch (Exception e) {
        System.err.print(" 6");     // не заходим - нет исключени€, ”∆≈ перехвачено
    } finally {
        System.err.print(" 7");     // заходим всегда
    }
    System.err.print(" 8");         // заходим - выполнение ”∆≈ в норме
}
*/

/*
public static void main(String[] args) throws IOException {
    throw new Exception(); // тут ошибка компил€ции
}
	
public static void main(String[] args) throws Exception {
    throw new IOException(); // тут нет
}
*/
	
/*	
	// EOFException перехватили catch-ом, им не пугаем
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
        Throwable t = new Exception(); // и лететь будет Exception
        throw t; // но тут ошибка компил€ции 
    }	
	public static void main(String[] args) throws FileNotFoundException { 
		Exception e = new FileNotFoundException(); // и лететь будет Exception
        throw e; // и тут ошибка компил€ции 
    }	
*/

/*
	public class Parent {
	    // предок пугает IOException и InterruptedException
	    public void f() throws IOException, InterruptedException {}
	}

	class Child extends Parent {
	    // а потомок пугает только потомком IOException
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
