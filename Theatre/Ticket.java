package Theatre;

public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;
    public Ticket(int row, int seat, double price, Person person){
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.person=person;
    }
    public void print(){
        System.out.print("Ticket Details:\n"+"Name: "+person.getName()+"\nSurname: "+person.getSurname()+"\nEmail: "+person.getEmail()+"\nRow: "+row+"\nSeat: "+seat+"\nPrice: £"+price+"\n");
    }
    public int getRow(){
        return row;
    }
    public void setRow(int row){
        this.row=row;
    }
    public int getSeat(){
        return seat;
    }
    public void setSeat(int Seat){
        this.seat=seat;
    }
    public double getPrice(){
        return price;
    }

    public void setPrice(double Price){
        this.price=price;
    }
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

