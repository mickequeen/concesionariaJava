package concesionaria;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Venta {
  Double monto_venta;
  String fecha_venta;
  String codigo_veh;
  String nombre_clte;
  String apellido_clte;
  int rut_sindv;

  private static int contador = 0; // contador en 0
  private int ID = contador++; // suma al contador cada vez q se instancie la clase
  int cont = ID + 1;
  private String num_boleta = "bol_" + cont;

  public String fechaHoy(){
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    String fechaFormateada = formatter.format(date);
    return fechaFormateada;
  }

  // function para retornar 3 datos
  class retornoBusqueda {
    String retornoString;
    String codigo;
    String marca;
    retornoBusqueda(String rString, String cod, String mar) {
      retornoString = rString;
      codigo = cod;
      marca = mar;
    }
  }

  // function para retornar 3 datos
  retornoBusqueda retornoTipos(String stringRetorno, String codigo, String marca) {
    return new retornoBusqueda(stringRetorno, codigo, marca);
  }

  public static boolean busquedaInventario(String[][]inventario, String busqueda) {
    boolean continuarVenta = false;
    for (int i = 0; i < inventario.length; i++) {
      String Buscar = busqueda;
      if (Buscar.equals(String.valueOf(inventario[i][0]))){
        continuarVenta = true;
      }
    }
    return continuarVenta;
  }

  public retornoBusqueda mostrarDatosBusqueda(String[][] inventario, String busqueda) {
    retornoBusqueda retorno = null;
    for (int i = 0; i < inventario.length; i++) {
      String Buscar = busqueda;
      if (Buscar.equals(String.valueOf(inventario[i][0]))){
        String stringRetorno = "Código seleccionado: " + inventario[i][0] + "; marca: "
                + inventario[i][1] + "; modelo: " + inventario[i][2] + "; KM: " + inventario[i][3]+ "; Tipo: "
                + inventario[i][4] + " ;Patente: "  + inventario[i][5] + " ;Año: "  + inventario[i][6];
        retorno = retornoTipos(stringRetorno, inventario[i][0], inventario[i][1]);
         return retorno;
      } else{
        retorno = retornoTipos("Error", "error", "error");
        return retorno;
      }
    }
    return retorno;
  }

  // función para ingresar la venta
  public String ingresoVenta(String codigo, String marca){
    String resumenVenta = "";
    monto_venta = 0.00;
    fecha_venta = fechaHoy();
    nombre_clte = JOptionPane.showInputDialog(null, "Nombre cliente");
    if(nombre_clte == null){
      JOptionPane.showMessageDialog(null, "Favor ingresar nombre cliente");
      ingresoVenta(codigo, marca);
    }
    if (nombre_clte.length() > 3 ) {
      this.nombre_clte = nombre_clte;
      this.apellido_clte = JOptionPane.showInputDialog(null, "Apellido cliente");
      if(apellido_clte == null){
        JOptionPane.showMessageDialog(null, "Favor ingresar apellido cliente");
        ingresoVenta(codigo, marca);
      }
      if (apellido_clte.length() > 3 ){
        this.apellido_clte = apellido_clte;
      } else {
        JOptionPane.showMessageDialog(null, "Favor ingresar apellido cliente");
        ingresoVenta(codigo, marca);
      }
    } else {
      JOptionPane.showMessageDialog(null, "Favor ingresar nombre cliente");
      ingresoVenta(codigo, marca);
    }
    try {
      this.monto_venta = Double.parseDouble(JOptionPane.showInputDialog(null, "Monto venta"));
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error: ingresar un número entero o decimal, ej: 100.10; vuelve a comenzar", "Error",  JOptionPane.ERROR_MESSAGE);
      ingresoVenta(codigo, marca);
    }
    try {
      String stringRut = "";
      do {
        this.rut_sindv = Integer.parseInt(JOptionPane.showInputDialog(null, "Rut sin DV (7 dígitos)"));
        stringRut = String.valueOf(rut_sindv);
        resumenVenta = "<html><body width='%1s'><h1>Boleta: " + num_boleta + "</h1>"
                  + "<p><b>Fecha transacción: </b> " + fecha_venta +  "</p>"
                  + "<p><b>Código Vehiculo: </b> " + codigo +  "</p>"
                  + "<p><b>Marca: </b> " + marca +  "</p>"
                  + "<p><b>Nombre y apellido clte: </b> " + nombre_clte + " " + apellido_clte +  "</p>"
                  + "<p><b>Rut Cliente (sin DV): </b> " + rut_sindv +  "</p>"
                  + "<p><b>Monto venta: </b> $" + monto_venta +  " CLP</p>";
      } 
      while (stringRut.length() < 7);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error: ingresar solo 7 dígitos", "Error",  JOptionPane.ERROR_MESSAGE);
      ingresoVenta(codigo, marca);
    }
    return resumenVenta;
  }
}

