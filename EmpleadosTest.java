import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Donatien Omar
 * 
 */

public class EmpleadosTest {

    public static void main(String[] arg) {

        Scanner input = new Scanner(System.in);
        int opcionUno = 0;
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
            
            Empleados empleados = objectMapper.readValue(new File("empleados.json"), Empleados.class);
            
            do {

                System.out.println("----------------------------------");
                System.out.println("====[¿Qué deseas hacer?]====");
                System.out.println(" ");
                System.out.println("1. Consultar datos");
                System.out.println("2. Sacar Salario de quincena");
                System.out.println("0. Salir");
                opcionUno = input.nextInt();
                System.out.println("----------------------------------");

                switch (opcionUno) {

                    case 1:
                    for (Empleado empleado : empleados.getEmpleados()) {
                        System.out.printf("* ID: %d \n", empleado.getID());
                        System.out.printf("* Nombre: %s \n", empleado.getNombre());
                        System.out.printf("* Salario: $%.2f \n", empleado.getSalario());
                        System.out.printf("* Fecha de Contratación: %s \n", empleado.getFechaContratacion());
                        System.out.println("----------------------------------------");
                    }
                        break;

                    case 2:
                        System.out.println("¿Cuál es el ID de la persona que quiere consultar su salario");
                        int idEmpleado = input.nextInt();
                        boolean encontrado = false;
                        for (Empleado emp : empleados.getEmpleados()) {
                            if (emp.getID() == idEmpleado) {
                                System.out.println("*-*-*-*-*-*-*-*-*-*--*--*-*-*-*-*-*-*-*");
                                System.out.printf("* El salario de %s es: $%.2f \n", emp.getNombre(), emp.getSalario());
                                encontrado = true;
                                System.out.println("¿Cuál es su número de faltas");
                                int numFaltas = input.nextInt();
                                    if (numFaltas == 0) {
                                        System.out.println("*-*-*-*-*-*-*-*-*-*--*--*-*-*-*-*-*-*-*");
                                        System.out.println("* No hay Modificación de salario ");
                                        System.out.printf("* Total: $%.2f \n", emp.getSalario());
                                        System.out.println("*-*-*-*-*-*-*-*-*-*--*--*-*-*-*-*-*-*-*");
                                    } else {
                                        System.out.println("*-*-*-*-*-*-*-*-*-*--*--*-*-*-*-*-*-*-*");
                                        float descuento = 1 - (numFaltas / 100.0f);
                                        float salarioTotal = emp.getSalario() * descuento;
                                        System.out.printf("* El descuento es de: %.2f \n", descuento);
                                        System.out.printf("* El salario total de %s es: $%.2f \n", emp.getNombre(), salarioTotal);
                                        System.out.println("*-*-*-*-*-*-*-*-*-*--*--*-*-*-*-*-*-*-*");
                                    }
                                break;
                            }
                        }
                        if (!encontrado) {
                            System.out.println("Empleado no encontrado.");
                        }

                    default:
                        break;
                }
                
            } while (opcionUno != 0);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Empleados {

    private List<Empleado> empleados;

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
}

class Empleado {

    private int id;
    private String nombre;
    private float salario;
    private String fechaContratacion;

    public int getID() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getSalario() {
        return salario;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }
}