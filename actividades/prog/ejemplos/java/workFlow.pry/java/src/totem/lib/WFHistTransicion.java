/*-----------------------------------------------
   Programa :  WFHistTransicion.java
   Fecha    :  09-06-2003
-----------------------------------------------*/
package totem.lib;

import java.io.*;
import java.util.*;
import java.sql.*;


/**
 * Esta clase representa cada transici�n realizada en el pasado por el ticket.
 *
 * @author David Vargas Ruiz
 * @version 0.7.2
 */
public class WFHistTransicion extends Object
{
   private int		   cod_ticket,cod_ticket_padre;
   private WFTarea         tarea;
   private WFTransicion    transicion;
   private java.util.Date  fecha;
   private String	   cod_usuario, apunte;
   private Connection      con;
   
   //=======================
   //Constructor de la clase
   //=======================
   public WFHistTransicion(Connection p_con)   { con=p_con;}
   
   //====================
   //M�todos PON p�blicos
   //====================
   public void ponApunte(String s)      { apunte=s; }
   public void ponCodTicket(int i)      { cod_ticket=i; }
   public void ponCodTicketPadre(int i) { cod_ticket_padre=i; }
   public void ponCodUsuario(String s)  { cod_usuario=s; }
   public void ponCodTarea(int i)       { tarea = new WFTarea(con,i); }
   public void ponCodTransicion(int i)  { transicion = new WFTransicion(con,i); }
   public void ponFecha(java.util.Date f)  { fecha = f; }
   
   
   //====================
   //M�todos LEE p�blicos
   //====================
   public String         leeApunte()         { return apunte;}
   public int            leeCodTicket()      { return cod_ticket;}
   public int            leeCodTicketPadre() { return cod_ticket_padre;}
   public String         leeCodUsuario()     { return cod_usuario;}
   public String         leeDesTarea()       { return tarea.leeDesTarea();}
   public java.util.Date leeFecha()          { return fecha;}
   public WFTransicion   leeTransicion()     { return transicion;}
}
//Fin de la clase WFHistTransiciones.java
