package com.company;

import com.company.controller.Controller;
import com.company.model.Model;
import com.company.view.View;

/*
Создание собственного исключения.

Использовать код программы из задания «Создание структуры для
ввода данных с командной строки с помощью регулярных выражений».
Симитировать ситуацию, когда запись, поступающая в записную книжку,
содержит поле логин, существующий в данной записной книжке.
Исключительная ситуация должна вернуть данные в контроллер (и,
например, опубликовать их) и попросить пользователя сменить логин на другой.
После соответствующего ввода, запись должна повторно поступить в модель.
*/

public class Main {

    public static void main(String[] args) {
	    // write your code here
        Controller controller = new Controller(new Model(), new View());
        // Run
        controller.processUser();
    }
}
