/*-----------------------------------------------
   Programa :  WFConcepto.java
   Versi�n  :  0.5.0
   Fecha    :  05-05-2003
   Estado   :  OK
-----------------------------------------------*/
package totem.lib;

import  java.io.*;
import  java.util.*;
import  java.sql.*;


class WFConcepto extends Object
{
   private int 		cod_concepto;
   private String	des_concepto;
   
   
   //=======================
   //Constructor de la clase
   //=======================
   WFConcepto(Connection p_con, int p_cod_concepto)
   {
      cod_concepto=-1; des_concepto=null;
      
      Statement st1;
      ResultSet rs1;
      try
      {
	 st1 = p_con.createStatement();
	 rs1 = st1.executeQuery("SELECT * FROM DEF_M_CONCEPTOS WHERE cod_concepto="+p_cod_concepto+";");
	 if (rs1.next())
	 { 
	    //Cargar las variables de la tabla CONCEPTO en el objeto java
	    cod_concepto=p_cod_concepto;des_concepto=rs1.getString("DES_CONCEPTO");
	 }
	 rs1.close();
	 st1.close();
      }
      catch(Exception e)
      {  System.err.println("Exception WFTipoTicket("+p_cod_concepto+") REG_NOTFOUND: " + e);}
   }

   
   //===========
   //M�todos GET
   //===========
   public int	   leeCodConcepto()   { return cod_concepto;}
   public String   leeDesConcepto()   { return des_concepto;}
   
   
   //=========
   //verInfo()
   //=========
   public void verInfo()
   {
      System.out.println("WFConcepto.verInfo():");
      System.out.println("concepto = "+des_concepto+" ("+cod_concepto+")");
   }
}
//Fin de la clase WFConcepto.java
