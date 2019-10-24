package server.models;

public class Customer {
    private String name;
    private int balance;
    private int accountNo;

    public Customer(String name, int balance, int accountNo) {
        this.name = name;
        this.balance = balance;
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }
}
