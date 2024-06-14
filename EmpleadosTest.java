import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 
 * @author Donatien Omar
 * 
 */

public class EmpleadosTest {

    public static void main(String[] arg) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            
            Empleados empleados = objectMapper.readValue(new File("empleados.json"), Empleados.class);

            for (Empleado empleado : empleados.getEmpleados()) {
                System.out.printf("* Nombre: %s \n", empleado.getNombre());
                System.out.printf("* Salario: $%.2f \n", empleado.getSalario());
                System.out.printf("* Fecha de Contratación: %s \n", empleado.getFechaContratacion());
                System.out.println("----------------------------------------");
            }

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

    private String nombre;
    private double salario;
    private String fechaContratacion;

    public String getNombre() {
        return nombre;
    }

    public double getSalario() {
        return salario;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }
}