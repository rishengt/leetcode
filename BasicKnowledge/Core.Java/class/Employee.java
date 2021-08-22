import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee {
    private String name;
    private Double salary;
    private List<Manager> managers;

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    public Employee(){
        salary = 1.1;
    }

    public Employee(String name, Double salary){
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public static void main(String[] args) {
        Employee a = new Employee();
        Manager A = new Manager("A", 1.0);
        List<Manager> managers = new ArrayList<>();
        managers.add(A);
        a.setManagers(managers);
        List<Manager> managers1 = new ArrayList<>();
        managers1.add(new Manager("B", 2.0));
        a.getManagers().addAll(managers1);
        for(Manager m: a.getManagers()){
            System.out.println(m.getName());
        }
    }
}
