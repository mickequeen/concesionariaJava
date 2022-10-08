package concesionaria;

import javax.swing.*;
import java.util.Stack;

public class Vehiculo {
  private static int counter = 0; // contador en 0
  private int ID = counter++; // suma al contador cada vez q se instancie la clase
  int cont = ID + 1;
  String codigo_veh = "veh_" + cont; // codigo de vehiculo correlativo al contador
  String marca_veh;
  String tipo_veh;
  String modelo_veh;
  int anho_veh;
  Double km_veh;
  String patente_veh;

  public int getID() {
    return ID;
  }

  public String getCodigo() {
    return codigo_veh;
  }

  public String getMarca() {
    return marca_veh;
  }

  public String getModelo() {
    return modelo_veh;
  }

  public Double getKM() {
    return km_veh;
  }

  public String getTipo() {
    return tipo_veh;
  }

  public String getPatente() {
    return patente_veh;
  }

  public int getAnho() {
    return anho_veh;
  }

  public String toString() {
    String datos = "código " + codigo_veh + "; Marca: " + marca_veh + "; Modelo: " +  modelo_veh + "; Km: " +  km_veh;
    return datos;
  }

  // Setter datos obligatorios
  public void setVehiculo ( String marca, String patente ){
    this.marca_veh = marca;
    this.patente_veh = patente;
  }

  // setter datos basicos
  public void setVehiculo ( String marca, Double km, String modelo){
    this.marca_veh = marca;
    this.km_veh = km;
    this.modelo_veh = modelo;
  }

  // setter datos basicos
  public void setVehiculo ( String marca, Double km, String modelo,  String tipo, String patente){
    this.marca_veh = marca;
    this.km_veh = km;
    this.modelo_veh = modelo;
    this.tipo_veh = tipo;
    this.patente_veh = patente;
  }

  // Setter con toda info vehículo
  public void setVehiculo ( String marca, Double km, String modelo,  String tipo, String patente, int anho){
    this.marca_veh = marca;
    this.patente_veh = patente;
    this.tipo_veh = tipo;
    this.modelo_veh = modelo;
    this.anho_veh = anho;
    this.km_veh = km;
  }

  // refactor ingreso inventario
  public static String[][] ingresoInventario(){
    String[][] vacio = new String[0][0];
    Stack<String> codigosDisponibles = new Stack<String>();
    Stack<String> marcasDisponibles = new Stack<String>();
    Stack<String> modelosDisponibles = new Stack<String>();
    Stack<Double> kmVehiculo = new Stack<Double>();
    Stack<String> tiposDisponibles = new Stack<String>();
    Stack<String> patentesDisponibles = new Stack<String>();
    Stack<String> anhoDisponible = new Stack<String>();

    boolean ingresoInventario = true;

    while (ingresoInventario == true) {
      String marcaVeh = JOptionPane.showInputDialog(null, "Ingresar marca del vehículo");
      if(marcaVeh == null){
        ingresoInventario();
      }
      String modelo = "";
      try {
        modelo = JOptionPane.showInputDialog(null, "Ingresar modelo a inventario, máximo 15 carácteres, mínimo 3");
        if(modelo == null){
          ingresoInventario();
        }
        if (modelo.length() <= 15 && modelo.length() >= 3 ) {
          modelo = modelo;
        }
        else {
          throw new Vehiculo.codigoLargo();
        }
      } catch (Vehiculo.codigoLargo e) {
        JOptionPane.showMessageDialog(null, "máximo de 15 carácteres, mínimo 3. vuelve a comenzar");
        ingresoInventario();
      }

      Double km = 0.00;
      try {
        km = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese kilometraje"));
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: debe ingresar un número entero o decimal, ej: 100.10, vuelve a comenzar",
                "Error",  JOptionPane.ERROR_MESSAGE);
        ingresoInventario();
      }

      String tipoString = "";
      // refactor tipo de vehículo
      Object[] selectionValues = { "Auto", "Camioneta", "Motocicleta" };
      String initialSelection = "Auto";
      Object selection = JOptionPane.showInputDialog(null, "Escoger tipo de vehículo",
              "Selección tipo", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);

      if(selection == "Auto"){
        tipoString = "Auto";
      }
      if(selection == "Camioneta"){
        tipoString = "Camioneta";
      }
      if(selection == "Motocicleta"){
        tipoString = "Motocicleta";
      }
      String patente = "";
      try {
        patente = JOptionPane.showInputDialog(null, "Ingresar patente (7 carácteres, incluya guión)");
        if(patente == null){
          ingresoInventario();
        }
        if (patente.length() == 7) {
          patente = patente;
        }
        else {
          throw new Vehiculo.codigoLargo();
        }
      } catch (Vehiculo.codigoLargo e) {
        JOptionPane.showMessageDialog(null, "Favor ingresar 6 carácteres");
        ingresoInventario();
      }
      // refactor tipo de vehículo
      Object[] anhoVehiculo = { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022" };
      String inicialAnho = "2022";
      Object selectionAnho = JOptionPane.showInputDialog(null, "Escoger año del vehículo",
              "Selección año", JOptionPane.QUESTION_MESSAGE, null, anhoVehiculo, inicialAnho);

      // push a stack de string's -y double- que contiene todos los ingresos realizados
      modelosDisponibles.push(modelo);
      kmVehiculo.push(km);
      marcasDisponibles.push(marcaVeh);
      tiposDisponibles.push(tipoString);
      patentesDisponibles.push(patente);
      anhoDisponible.push(String.valueOf(selectionAnho));

      if (JOptionPane.showConfirmDialog(null, "Desea hacer otro ingreso?", "Continuar",
              JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
        ingresoInventario = true;
      } else {
        ingresoInventario = false;
        int largoIngreso = modelosDisponibles.size();

        String[][] ingresoVehiculos = new String[largoIngreso][7];
        for (int i = 0; i < largoIngreso; i++) {
          Vehiculo vehiculo = new Vehiculo();
          ingresoVehiculos[i][0] = vehiculo.getCodigo();
          ingresoVehiculos[i][1] = marcasDisponibles.get(i);
          ingresoVehiculos[i][2] = modelosDisponibles.get(i);
          ingresoVehiculos[i][3] = String.valueOf(kmVehiculo.get(i));
          ingresoVehiculos[i][4] = tiposDisponibles.get(i);
          ingresoVehiculos[i][5] = patentesDisponibles.get(i);
          ingresoVehiculos[i][6] = anhoDisponible.get(i);
        }
        return ingresoVehiculos;
      }
    }
    return vacio;
    // cuando empecé este refactor solo diosito y yo sabíamos lo q estaba haciendo.
    // ahora ninguno de los dos lo sabe.
  }

  private static class codigoLargo extends Throwable {
  }
}
