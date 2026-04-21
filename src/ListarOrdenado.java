import java.sql.*;

public class ListarOrdenado {
    // Datos de conexión 
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "RIBERA";
    private static final String PASS = "ribera";

    public static void main(String[] args) {
        // SQL para ordenar de mayor a menor (DESC)
        String sql = "SELECT id, nombre, salario FROM empleado ORDER BY salario DESC";

        System.out.println("--- EMPLEADOS ORDENADOS POR SUELDO (DESC) ---");

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.printf("%-5s | %-15s | %-10s%n", "ID", "NOMBRE", "SALARIO");
            System.out.println("---------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double sueldo = rs.getDouble("salario");

                System.out.printf("%-5d | %-15s | %-10.2f€%n", id, nombre, sueldo);
            }

        } catch (SQLException e) {
            System.err.println("Error de base de datos: " + e.getMessage());
        }
    }
}
