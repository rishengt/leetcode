public class Manager extends Employee {
    private Double bonus;

    public Manager(){
        bonus = 1.0;
    }
    public Manager(String name, Double salary){
        super(name,salary);
    }

    public Double getSalary(){
        return super.getSalary()+bonus;
    }

}
