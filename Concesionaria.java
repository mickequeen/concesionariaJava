package concesionaria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class Concesionaria {

  // función para mostrar tabla con datos ingresados de vehículos
  public static void mostrarTabla( String[][] inventario) {
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);
    // crear cabeceras de columna
    model.addColumn("Código");
    model.addColumn("Marca");
    model.addColumn("Modelo");
    model.addColumn("Km");
    model.addColumn("Tipo de Vehículo");
    model.addColumn("Patente");
    model.addColumn("Año");
    int largoInventario = 0;

    for (int i = 0; i < inventario.length; i++) {
      if(inventario[i][0] == null){
        largoInventario = i;
        break;
      }
    }

    String[][] inventarioSinVacios = new String[largoInventario][7];
    for (int i = 0; i < largoInventario; i++) {
      inventarioSinVacios[i][0]= inventario[i][0];
      inventarioSinVacios[i][1]= inventario[i][1];
      inventarioSinVacios[i][2]= inventario[i][2];
      inventarioSinVacios[i][3]= inventario[i][3];
      inventarioSinVacios[i][4]= inventario[i][4];
      inventarioSinVacios[i][5]= inventario[i][5];
      inventarioSinVacios[i][6]= inventario[i][6];
    }

    // SORT a inventario por año descendente antes de pintar tabla
    String[][] invOrdenado = Arrays.stream(inventarioSinVacios)
            .sorted(Comparator.comparing(x -> -Integer.parseInt(x[6])))
            .toArray(String[][]::new);

    for (int i = 0; i < largoInventario; i++) {
      model.addRow(new Object[]{ invOrdenado[i][0], invOrdenado[i][1], invOrdenado[i][2],invOrdenado[i][3],
              invOrdenado[i][4], invOrdenado[i][5], invOrdenado[i][6]});
    }
    JOptionPane.showMessageDialog(null, new JScrollPane(table));
  }

  // función para mostrar tabla filtrada por tipo
  public static void tablaTipo( String[][] inventario, String tipo) {
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);
    // crear cabeceras de columna
    model.addColumn("Código");
    model.addColumn("Marca");
    model.addColumn("Modelo");
    model.addColumn("Km");
    model.addColumn("Tipo de Vehículo");
    model.addColumn("Patente");
    model.addColumn("Año");
    int largoInventario = 0;

    for (int i = 0; i < inventario.length; i++) {
      if(inventario[i][0] == null){
        largoInventario = i;
        break;
      }
    }

    String[][] inventarioSinVacios = new String[largoInventario][7];
    for (int i = 0; i < largoInventario; i++) {
      inventarioSinVacios[i][0]= inventario[i][0];
      inventarioSinVacios[i][1]= inventario[i][1];
      inventarioSinVacios[i][2]= inventario[i][2];
      inventarioSinVacios[i][3]= inventario[i][3];
      inventarioSinVacios[i][4]= inventario[i][4];
      inventarioSinVacios[i][5]= inventario[i][5];
      inventarioSinVacios[i][6]= inventario[i][6];
    }

    // SORT a inventario por año descendente antes de pintar tabla
    String[][] invOrdenado = Arrays.stream(inventarioSinVacios)
            .sorted(Comparator.comparing(x -> -Integer.parseInt(x[6])))
            .toArray(String[][]::new);


    for (int i = 0; i < largoInventario; i++) {
      // solo si coincide lo buscado con lo que hay en el inventario se muestra en tabla
      if(invOrdenado[i][4].equals(tipo)){
        model.addRow(new Object[]{ invOrdenado[i][0], invOrdenado[i][1], invOrdenado[i][2],invOrdenado[i][3],
                invOrdenado[i][4], invOrdenado[i][5], invOrdenado[i][6]});
      }
    }
    JOptionPane.showMessageDialog(null, new JScrollPane(table));
  }

  // función para mostrar tabla con datos ingresados de vehículos
  public static void tablaPatente( String[][] inventario, String patente) {
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);
    // crear cabeceras de columna
    model.addColumn("Código");
    model.addColumn("Marca");
    model.addColumn("Modelo");
    model.addColumn("Km");
    model.addColumn("Tipo de Vehículo");
    model.addColumn("Patente");
    model.addColumn("Año");
    int largoInventario = 0;

    for (int i = 0; i < inventario.length; i++) {
      if(inventario[i][0] == null){
        largoInventario = i;
        break;
      }
    }

    String[][] inventarioSinVacios = new String[largoInventario][7];
    for (int i = 0; i < largoInventario; i++) {
      inventarioSinVacios[i][0]= inventario[i][0];
      inventarioSinVacios[i][1]= inventario[i][1];
      inventarioSinVacios[i][2]= inventario[i][2];
      inventarioSinVacios[i][3]= inventario[i][3];
      inventarioSinVacios[i][4]= inventario[i][4];
      inventarioSinVacios[i][5]= inventario[i][5];
      inventarioSinVacios[i][6]= inventario[i][6];
    }

    int encontradas = 0;
    for (int i = 0; i < largoInventario; i++) {
      // solo si coincide lo buscado con lo que hay en el inventario se muestra en tabla

      if(inventarioSinVacios[i][5].equals(patente)){
        encontradas++;
        model.addRow(new Object[]{ inventarioSinVacios[i][0], inventarioSinVacios[i][1], inventarioSinVacios[i][2],inventarioSinVacios[i][3],
                inventarioSinVacios[i][4], inventarioSinVacios[i][5], inventarioSinVacios[i][6]});
      }
    }
    if (encontradas>0){
      JOptionPane.showMessageDialog(null, new JScrollPane(table));
    } else {
      JOptionPane.showMessageDialog(null, "Patente no encontrada");
    }
  }

  public static void ingresoCod(String[][] inventario){
    if (inventario.length > 0) {
      String busquedaInv = JOptionPane.showInputDialog(null, "Ingresar código a vender (veh_x)");
      if (busquedaInv != null){
        ingresarVenta(inventario, busquedaInv);
      } else {
        menu(inventario);
      }
    } else {
      JOptionPane.showMessageDialog(null, "Proceso finalizado");
    }
  }

  public static void ingresarVenta(String[][] inventario, String busqueda) {
    Venta venta = new Venta();
    boolean continuar = venta.busquedaInventario(inventario, busqueda);
    if(continuar == true){
      String datosBusqueda = venta.mostrarDatosBusqueda(inventario, busqueda).retornoString;
      String codigo = venta.mostrarDatosBusqueda(inventario, busqueda).codigo;
      String marca = venta.mostrarDatosBusqueda(inventario, busqueda).marca;

      if(datosBusqueda.equals("Error")){
        JOptionPane.showMessageDialog(null ,"Ha ocurrido un error ", "Error",  JOptionPane.ERROR_MESSAGE );
        menu(inventario);
      } else{
        if (JOptionPane.showConfirmDialog(null, datosBusqueda + " , desea continuar con la venta?", "Continuar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
          String retornoIngreso = venta.ingresoVenta(codigo, marca);
          Runnable boleta = () -> {
            String html = retornoIngreso;
            // change to alter the width
            int w = 300;
            JOptionPane.showMessageDialog(null, String.format(html, w, w));
          };
          SwingUtilities.invokeLater(boleta);
      } else {
          menu(inventario);
        }
      }
    } else {
      JOptionPane.showMessageDialog(null ,"Código de vehículo no se encuentra en inventario ", "Error",  JOptionPane.ERROR_MESSAGE );
    }
  }
  public static String[][] ingresoInventario(String[][] inventario, String nuevosVehiculos[][]){
    int largoInventario = 0;
    for (int i = 0; i < inventario.length; i++) {
      if(inventario[i][0] == null){
        largoInventario = i;
        break;
      }
    }

    for (int i = 0; i < nuevosVehiculos.length; i++) {
      int idInventario = largoInventario;
      inventario[idInventario][0] = nuevosVehiculos[i][0];
      inventario[idInventario][1] = nuevosVehiculos[i][1];
      inventario[idInventario][2] = nuevosVehiculos[i][2];
      inventario[idInventario][3] = nuevosVehiculos[i][3];
      inventario[idInventario][4] = nuevosVehiculos[i][4];
      inventario[idInventario][5] = nuevosVehiculos[i][5];
      inventario[idInventario][6] = nuevosVehiculos[i][6];
      idInventario++;
    }
    return inventario;
  }

  public static String[][] menu(String[][] inventario){
    String[] buttons = { "Ingresar vehículo", "Ingresar Venta", "Listar inventario", "Búsquedas" };
    int opcionesInicio = JOptionPane.showOptionDialog(null, "¿Qué desea hacer?", "Elegir acción",
            JOptionPane.INFORMATION_MESSAGE, 3, null, buttons, buttons[3]);
    switch (opcionesInicio){
      case 0:{
        String[][] vehiculosNuevos = Vehiculo.ingresoInventario();
        return vehiculosNuevos;
      }
      case 1:{
        // ingresar venta
        ingresoCod(inventario);
        break;
      }
      case 2:{
        // listar
        mostrarTabla(inventario);
        String[][] menu = menu(inventario);
        while(menu!= null){
          String[][] nuevo = ingresoInventario(inventario, menu);
          inventario = nuevo;
          menu(inventario);
        }
        break;
      }
      case 3:{
        String[] busquedas = { "Buscar por tipo de vehículo", "Búsqueda por patente" };
        String Buscar;
        int tipoBusqueda = JOptionPane.showOptionDialog(null, "Selecciona tipo de búsqueda", "Elegir búsqueda",
                JOptionPane.INFORMATION_MESSAGE, 3, null, busquedas, busquedas[1]);
        switch (tipoBusqueda) {
          case 0: {
            String[] tipos = { "Auto", "Camioneta", "Motocicleta" };
            int tiposVeh = JOptionPane.showOptionDialog(null, "Selecciona tipo de Vehículo", "Elegir tipo vehículo",
                    JOptionPane.INFORMATION_MESSAGE, 3, null, tipos, tipos[2]);
            switch (tiposVeh){
              case 0: {
                Buscar = "Auto";
                tablaTipo(inventario,Buscar);
                break;
              }
              case 1: {
                Buscar = "Camioneta";
                tablaTipo(inventario,Buscar);
                break;
              }
              case 2: {
                Buscar = "Motocicleta";
                tablaTipo(inventario,Buscar);
                break;
              }
            }
            break;
          }
          case 1: {
            // ingresar venta
            String patente = "";
            try {
              patente = JOptionPane.showInputDialog(null, "Ingresar patente a buscar (7 carácteres, con guión)");
              if(patente == null){
                String[][] menu = menu(inventario);
                while(menu!= null) {
                  String[][] nuevo = ingresoInventario(inventario, menu);
                  inventario = nuevo;
                  menu(inventario);
                }
              }
              if (patente.length() == 7) {
                tablaPatente(inventario, patente);
              }
              else {
                throw new Concesionaria.codigoLargo();
              }
            } catch (Concesionaria.codigoLargo e) {
              JOptionPane.showMessageDialog(null, "Favor ingresar 7 carácteres");
              String[][] menu = menu(inventario);
              while (menu != null) {
                String[][] nuevo = ingresoInventario(inventario, menu);
                inventario = nuevo;
                menu(inventario);
              }
            }
            break;
          }
        }
        break;
      }
      case -1:{
        // error
        JOptionPane.showMessageDialog(null, "Proceso finalizado sin ingresos");
        break;
      }
    }
    return inventario;
  }
  public static void main(String[] args) {

    boolean ingresoInventario = false;

    String[][] inventarioVehiculos = new String[50][7];
    String[][] Ventas = new String[0][];

    Vehiculo MazdaCX3 = new Vehiculo();
    MazdaCX3.setVehiculo("Mazda", 530.0,"CX3", "Auto", "HX-1712", 2016);
    inventarioVehiculos[0][0] = MazdaCX3.getCodigo();
    inventarioVehiculos[0][1] = MazdaCX3.getMarca();
    inventarioVehiculos[0][2] = MazdaCX3.getModelo();
    inventarioVehiculos[0][3] = String.valueOf(MazdaCX3.getKM());
    inventarioVehiculos[0][4] = MazdaCX3.getTipo();
    inventarioVehiculos[0][5] = MazdaCX3.getPatente();
    inventarioVehiculos[0][6] = String.valueOf(MazdaCX3.getAnho());
    Vehiculo ToyotaHilux = new Vehiculo();
    ToyotaHilux.setVehiculo("Toyota", 641.0,"Hilux", "Camioneta", "RH-7512", 2020);
    inventarioVehiculos[1][0] = ToyotaHilux.getCodigo();
    inventarioVehiculos[1][1] = ToyotaHilux.getMarca();
    inventarioVehiculos[1][2] = ToyotaHilux.getModelo();
    inventarioVehiculos[1][3] = String.valueOf(ToyotaHilux.getKM());
    inventarioVehiculos[1][4] = ToyotaHilux.getTipo();
    inventarioVehiculos[1][5] = ToyotaHilux.getPatente();
    inventarioVehiculos[1][6] = String.valueOf(ToyotaHilux.getAnho());
    Vehiculo HyundaiElantra = new Vehiculo();
    HyundaiElantra.setVehiculo("Hyundai", 0.0,"Elantra", "Auto", "KL-7123", 2022);
    inventarioVehiculos[2][0] = HyundaiElantra.getCodigo();
    inventarioVehiculos[2][1] = HyundaiElantra.getMarca();
    inventarioVehiculos[2][2] = HyundaiElantra.getModelo();
    inventarioVehiculos[2][3] = String.valueOf(HyundaiElantra.getKM());
    inventarioVehiculos[2][4] = HyundaiElantra.getTipo();
    inventarioVehiculos[2][5] = HyundaiElantra.getPatente();
    inventarioVehiculos[2][6] = String.valueOf(HyundaiElantra.getAnho());
    Vehiculo YamahaMT = new Vehiculo();
    YamahaMT.setVehiculo("Yamaha", 9221.0,"MT-03", "Motocicleta", "LR-6814", 2005);
    inventarioVehiculos[3][0] = YamahaMT.getCodigo();
    inventarioVehiculos[3][1] = YamahaMT.getMarca();
    inventarioVehiculos[3][2] = YamahaMT.getModelo();
    inventarioVehiculos[3][3] = String.valueOf(YamahaMT.getKM());
    inventarioVehiculos[3][4] = YamahaMT.getTipo();
    inventarioVehiculos[3][5] = YamahaMT.getPatente();
    inventarioVehiculos[3][6] = String.valueOf(YamahaMT.getAnho());

    String[][] menu = menu(inventarioVehiculos);
    while(menu!= null){
      String[][] nuevo = ingresoInventario(inventarioVehiculos, menu);
      inventarioVehiculos = nuevo;
      menu(inventarioVehiculos);
    }
  }
  private static class codigoLargo extends Throwable {
  }
}


