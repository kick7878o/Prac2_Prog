package dades;

public class LlistaNivellsAigua {

   private NivellAigua[] listaNiveles;
   private int nElem;
   
   public LlistaNivellsAigua(int tamany) {
      this.listaNiveles = new NivellAigua[tamany];
      nElem = 0;
   }

   /** Getter que obtiene el numero de elementos de la lista
    * 
    * @return el numero de elementos
    */
   public int getElem() { return nElem; }


   
}