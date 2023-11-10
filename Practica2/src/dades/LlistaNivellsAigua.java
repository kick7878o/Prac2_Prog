package dades;

public class LlistaNivellsAigua {

   private NivellAigua[] listaLvl;
   private int nElem;
   

   /** Constructor de la lista de medidas de agua
    * @param tamany
    */
   public LlistaNivellsAigua(int tamany) {
      listaLvl = new NivellAigua[tamany];
      nElem = 0;
   }

   /** Getter que obtiene el numero de elementos de la lista
    * 
    * @return el numero de elementos
    */
   public int getElem() { return nElem; }


   /** Metodo que añade una medida a la lista
    * 
    * @param dades datos de la nueva medida de agua
    */
   public void afegirDadesMesura(NivellAigua dades) {
      if (nElem < listaLvl.length){
         listaLvl[nElem] = dades.copia();
         nElem++;
      }
   }

   /** Metodo que devuelve la primera medida de una presa por parametro
    * @param nomPresa
    * @return los datos de la medida o un null si no se ha encontrado
    */
   public NivellAigua primeraMesura(String nomPresa) {
      for (int i=0; i < nElem; i++) {
         if (listaLvl[i].esAquestEmbassament(nomPresa))
            return listaLvl[i].copia();
      }
      return null;
   }

   /** Metodo que duplica el porcentaje mas alto de la lista
    * @return una copia de la medida con el porcentaje mas alto
    */
   public NivellAigua copiaPorcentajeMasAlto() {
      // Si no hay elementos en la lista, acabamos
      if (nElem == 0) return null;

      NivellAigua aux = listaLvl[0]; // Iniciamos con el primer elemento

      // Buscamos des de la posicion 1
      for (int i=1; i < nElem; i++) {
         if (listaLvl[i].getPercentVolum() > aux.getPercentVolum())
            aux = listaLvl[i];
      }

      return aux.copia();
   }


   /** Metodo que duplica el porcentaje mas bajo de la lista
    * @return una copia de la medida con el porcentaje mas bajo
    */
   public NivellAigua copiaPorcentajeMasBajo() {
      // Si no hay elementos en la lista, acabamos
      if (nElem == 0) return null;

      NivellAigua aux = listaLvl[0]; // Iniciamos con el primer elemento

      // Buscamos des de la posicion 1
      for (int i=1; i < nElem; i++) {
         if (listaLvl[i].getPercentVolum() < aux.getPercentVolum())
            aux = listaLvl[i];
      }

      return aux.copia();
   }

   /** Metodo que devuelve un String con todos los datos de la lista
    * 
    * @return cadena con todos los datos de la lista
    */
   public String toString() {
      String aux = "Lista medidas => Elementos: " +nElem;

      for (int i=0; i < nElem; i++) {
         aux += "\n\t[" +i+ "] " +listaLvl[i];
      }

      return aux;
   }

   /** Metodo que duplica el volumen mas alto de la lista
    * @return una copia de la medida con el volumen mas alto
    */
   public NivellAigua copiaVolumenMasAlto() {
      // Si no hay elementos en la lista, acabamos
      if (nElem == 0) return null;

      NivellAigua aux = listaLvl[0]; // Iniciamos con el primer elemento

      // Buscamos des de la posicion 1
      for (int i=1; i < nElem; i++) {
         if (listaLvl[i].getVolum() > aux.getVolum())
            aux = listaLvl[i];
      }

      return aux.copia();
   }


   /** Metodo que devuelve una lista con las medidas
    * en las que coinciden la provinica
    *
    * @param provincia nombre de la provinicia
    * @return una lista con las medidas de dicha provincia
    */
   public LlistaNivellsAigua consultaPorProvincia(String provincia) {
      LlistaNivellsAigua aux = new LlistaNivellsAigua(nElem); // Nueva lista

      // Iteramos buscando si coinciden las provincias
      for (int i=0; i < nElem; i++) {
         if (listaLvl[i].esTrobaEnAquestaProvincia(provincia))
            aux.afegirDadesMesura(listaLvl[i].copia());
      }

      return aux;
   }

   /** Metodo que devuelve una lista con las medidas
    * entre un período de tiempo
    * 
    * @param d1 fecha 1
    * @param d2 fecha 2
    * @return lista con los datos entre fechas
    */
   public LlistaNivellsAigua consultaPorFechas(Data d1, Data d2) {
      LlistaNivellsAigua aux = new LlistaNivellsAigua(nElem); // Nova llista

      // Iteramos buscando estan entre las fechas
      for (int i=0; i < nElem; i++) {
         if (listaLvl[i].esTrobaEnAquestPeriode(d1, d2))
            aux.afegirDadesMesura(listaLvl[i].copia());
      }

      return aux;
   }

   /** Metodo que elimina un conjunto segun el nombre de la presa
    * NOTA: no deja posiciones vacías
    * @param nomPresa
    */
   public void eliminarConjunt(String nomPresa) {
      int indiceAux = 0; // Indice que rastrea la nueva posicion en la lista

      // Iteramos la lista original
      for (int i=0; i < nElem; i++) {
         // Verificamos si el nombre del embalse NO coincide con el nombre proporcionado.
         //  Si coincide, la medida no está asociada al embalse a eliminar.
         if (!listaLvl[i].esAquestEmbassament(nomPresa)) {
            listaLvl[indiceAux] = listaLvl[i];
            indiceAux++; // Sirve para copiar la medida en la nueva posicion en la lista
         }
      }
      nElem = indiceAux; // Nos aseguramos que el nElem refleje bien las medidas restantes
   }
}