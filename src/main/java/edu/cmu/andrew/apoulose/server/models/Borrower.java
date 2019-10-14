package edu.cmu.andrew.apoulose.server.models;



public class Borrower {

     String borrowerId;
     String borrowerName;
     String borrowerPhone;

    public Borrower(String id , String name ,String phone){
        this.borrowerId = id;
        this.borrowerName = name;
        this.borrowerPhone = phone;
    }

    void setBorrower(String id , String name ,String phone ){
        this.borrowerId = id;
        this.borrowerName = name;
        this.borrowerPhone = phone;
    }
    public String getBorrowerId(){

        return borrowerId ;
    }

    public String getBorrowerName(){
        return this.borrowerName ;
    }
    public String getBorrowerPhone(){
        return this.borrowerPhone ;
    }

}

