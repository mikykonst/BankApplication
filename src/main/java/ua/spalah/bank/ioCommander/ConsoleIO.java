package ua.spalah.bank.ioCommander;

import java.util.Scanner;

public class ConsoleIO implements IO {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void write(String s) {
        System.out.println(s);
    }
}
