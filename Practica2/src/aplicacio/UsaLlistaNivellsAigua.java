package aplicacio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import dades.*;

public class UsaLlistaNivellsAigua {
	static Scanner teclat = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Indica el número de línies a llegir del fitxer (màxim 78282)");
		int numLinies = Integer.parseInt(teclat.next());
		LlistaNivellsAigua dataset = llegirLiniesFitxer(numLinies);

		mostraMenu();
		int opcio = Integer.parseInt(teclat.next());
		
		do {
			switch (opcio) {
				case 1: opcio1(dataset); break;
				case 2: opcio2(dataset); break;
				case 3: opcio3(dataset); break;
				case 4: opcio4(dataset); break;
				case 5: opcio5(dataset); break;
				case 6: opcio6(dataset); break;
				case 7: opcio7(dataset); break;
				case 8: opcio8(dataset); break;
				case 9: opcio9(dataset); break;
				case 10: exit(); break;
			}
			if (opcio != 10) { 
				mostraMenu();
				opcio = Integer.parseInt(teclat.next());
			}
		} while (opcio != 10);

		teclat.close();
	}

	// Metodo que indica que salidmos del programa.
	private static void exit() {
		System.out.println("\n\n===================== ¡PROGRAMA FINALIZADO! =====================\n");
	}

	/** Metodo privado que lee un determinado numero de lineas del fichero y las procesa.
	 * Si se pasa, se le asignara el numero maximo o minimo.
	 * 
	 * NOTA: suponemos que los datos son correctos siempre
	 * 
	 * @param nLinies
	 * @return Las lineas leídas y procesadas del fichero
	 * @throws FileNotFoundException si no se encuentra el fichero saltara este error
	 */
	private static LlistaNivellsAigua llegirLiniesFitxer(int nLinies) throws FileNotFoundException {
		LlistaNivellsAigua result = new LlistaNivellsAigua(nLinies); // Aqui guardamos las lineas procesadas

		// Si lo introducido no está dentro del rango, definimos el minimo o maximo automaticamente
		if (nLinies < 0) nLinies = 0;
		if (nLinies > 78282) nLinies = 78282;

		// Leemos el fichero
		Scanner f = new Scanner(new File("Quantitat_aigua_embassaments_20231025.csv"));

		String capcalera = f.nextLine(); // Saltamos la primera línea
		System.out.println("El format de les dades en cada línia és el següent:\n" +capcalera);

		for (int i = 0; i < nLinies; i++) {
			String linia = f.nextLine(); // Leemos línea a línea
			String[] camps = linia.split(","); // Separamos los campos con coma ','
			
			// Procesamos la fecha. FORMATO: DD/MM/AAAA
			String fechaNoProcesado = camps[0]; // Obtenemos el campo de la fecha sin separar
				String[] dataSplit = fechaNoProcesado.split("/"); // Separamos la fecha con barra '/'
				int dia = Integer.parseInt(dataSplit[0]); // Obtenemos el dia
				int mes = Integer.parseInt(dataSplit[1]); // Obtenemos el mes
				int any = Integer.parseInt(dataSplit[2]); // Obtenemos el año
				Data data = new Data(dia, mes, any); // Lo insertamos en la instancia de Data

			// Procesamos el nombre y la poblacion. FORMATO: 'nombre (poblacion)'
			String embalsePoblacion = camps[1]; // Obtenemos el campo del nombre de la presa + poblacion
				// Separamos el nombre y poblacion con el parentesis de apertura
				String[] embPoblSplit = embalsePoblacion.split("\\(");
				// obtenemos el nombre del embalse y eliminamos espacios al final
				String nomEmb = embPoblSplit[0].trim(); 
				// Obtenemos la poblacion eliminando el parentesis de cierre
				String poblacio = embPoblSplit[1].replace(")", ""); 

			// Procesamos el resto
			String provincia = camps[2]; // obtenemos la provincia
			double nivellAbs = Double.parseDouble(camps[3]); // obtenemos el nivel absoluto
			double percentatgeVolum = Double.parseDouble(camps[4]); // obtenemos el porcentaje volumetrico
			double volum = Double.parseDouble(camps[5]); // obtenemos el volumen

			// Insertamos los datos en la instancia de NivellAigua
			NivellAigua nivell = new NivellAigua(data, nomEmb, poblacio, provincia, 
															 nivellAbs, percentatgeVolum, volum);

			// Insertamos los datos en la lista
			result.afegirDadesMesura(nivell);
		}
		f.close(); // Cerramos el fichero .csv
		return result;
	}

	// Metodo que muestra el menú de opciones por pantalla
	private static void mostraMenu() {
		System.out.println("\n\nOpcions del menu:\n");
		System.out.println("  1. Mostrar conjunt de mesures de la llista.");
		System.out.println("  2. Mostrar mesures d'una provincia entre dates.");
		System.out.println("  3. Consultar la primera mesura de la llista d'un cert embassament.");
		System.out.println("  4. Consultar la mesura amb percentatge de volum mes alt dels embassaments.");
		System.out.println("  5. Consultar del percentatge de volum mes baix.");
		System.out.println("  6. Consultar de quants embassaments tenim dades en una certa provincia.");
		System.out.println("  7. Calcular en quina de les dues províncies té un volum d'aigua mes alt.");
		System.out.println("  8. Consultar dades de mesura en un periode de temps.");
		System.out.println("  9. Eliminar conjunt de mesures dels embassaments d'una província.");
		System.out.println("  10. Sortir");
		System.out.print("\n\tIndica opcio: ");
	}

	/** Metodo que convierte un string de fecha para insertar en la instancia
	 * FORMATO: DD/MM/AAAA
	 * 
	 * NOTA: suponemos que el String es correcto siempre
	 * @param dataString la fecha insertada por parametro
	 * @return instancia Data
	 */
	private static Data convertirAData(String dataString){
		String[] dataStringSplit = dataString.split("/");
		int dia = Integer.parseInt(dataStringSplit[0]);
		int mes = Integer.parseInt(dataStringSplit[1]);
		int any = Integer.parseInt(dataStringSplit[2]);

		return new Data(dia, mes, any);
	}

	/** Metodo que muestra la lista completa del dataset
	 * 
	 * @param dataset la lista
	 */
	private static void opcio1(LlistaNivellsAigua dataset) {
		System.out.println("\n\n" +dataset);
	}

	/** Metodo que muestra medidas entre unas fechas concretas de una provincia concreta
	 * 
	 * @param dataset la lista
	 */
	private static void opcio2(LlistaNivellsAigua dataset) {
		System.out.println("\n\n==== OPCION 2 SELECCIONADO ====\n");
		System.out.print("  Indica el nom de la provincia: ");
			String nomProvincia = teclat.next();

		System.out.print("  Indica data 1 (format: dia/mes/any): ");
			String inputData1 = teclat.next();
			Data data1 = convertirAData(inputData1);

		System.out.print("  Indica data 2 (format: dia/mes/any): ");
			String inputData2 = teclat.next();
			Data data2 = convertirAData(inputData2);

		// Obtenemos la lista segun la provincia y fechas dadas
		LlistaNivellsAigua medidasProvincia = dataset.consultaPorProvincia(nomProvincia);
		LlistaNivellsAigua medidasFechas = medidasProvincia.consultaPorFechas(data1, data2);

		System.out.println("Segun la provincia: " +nomProvincia+ " y las fechas Ini: " +data1+ " y Fin " 
								 +data2+ " tenemos esta lista: " +medidasFechas);
	}

	/** Metodo que muestra los datos de la primera medida
	 * indicando el nombre del embalse.
	 * 
	 * @param dataset la lista
	 */
	private static void opcio3(LlistaNivellsAigua dataset) {
		System.out.println("\n\n==== OPCION 3 SELECCIONADO ====\n");
		System.out.print("  Indica el nom de l'embassament: ");
		String nomEmbassament = teclat.next(); // NO LEE MAS DE 1 PALABRA

		System.out.println("   Primera mesura de " +nomEmbassament+ ": " 
												+dataset.primeraMesura(nomEmbassament));
	}

	/** Metodo que consulta la medida con el porcentaje de volumen mas alto
	 * de los embalses de cada una de las províncias. Muestra la info
	 * y comprueba si son del mismo año
	 * 
	 * @param dataset la lista
	 */
	private static void opcio4(LlistaNivellsAigua dataset) {
		System.out.println("\n\n==== OPCION 4 SELECCIONADO ====\n");
		String[] provincias = {"Tarragona", "Barcelona", "Lleida", "Girona"};

		for (String prov : provincias) {
			// Consultar la medida con el porcentaje de volumen mas alto de la provincia
			NivellAigua medidaMaxima = dataset.consultaPorProvincia(prov).copiaPorcentajeMasAlto();

			if (medidaMaxima != null) {
				// Mostramos la informacion
				System.out.println("  Medida con porcentaje mas alto de " +prov+ ": " +medidaMaxima);
				System.out.println("\n  Lista de datos: " +medidaMaxima);
			} else
				System.out.println("  No hay datos para la provincia de " +prov);
		}
	}

	/** Metodo que consulta los datos con el porcentaje mas bajo de la lista.
	 * NO separamos por provincias
	 * 
	 * @param dataset la lista
	 */
	private static void opcio5(LlistaNivellsAigua dataset) {
		System.out.println("\n\n==== OPCION 5 SELECCIONADO ====\n");
		NivellAigua percentMesBaix = dataset.copiaPorcentajeMasBajo();
		System.out.println("  Mesura amb percentatge de volum mes baix: " +percentMesBaix);
	}

	/** Metodo que muestra de cuantos embalses tenemos datos de una provincia.
	 * 
	 * @param dataset la lista
	 */
	private static void opcio6(LlistaNivellsAigua dataset) {
		System.out.println("\n\n==== OPCION 6 SELECCIONADO ====\n");
		System.out.print("  Indica el nom de la provincia: ");
		String nomProvincia = teclat.next();
		
		// Creamos una lista auxiliar filtrado por provincias
		LlistaNivellsAigua listaAux = dataset.consultaPorProvincia(nomProvincia);

		// Si hay elementos, tenemos datos
		if (listaAux.getElem() > 0) {
			System.out.println("  Hay datos conteniendo " +listaAux.getElem()+ " elements de " +nomProvincia);

			// Iterador que mostrará el nombre del embalse de cada posicion.
			for (int i=0; i < listaAux.getElem(); i++) {
				NivellAigua medida = listaAux.consultaPosicio(i);
				System.out.println("  Nombre del embalse: " +medida.getNomEmbassament());
			}
		} else	
			System.out.println("  No tenemos datos de embalses de " +nomProvincia);
	}

	/** Metodo que muestra en un año concreto que embalse tiene el
	 * volumen mas alto entre dos provincias.
	 * 
	 * @param dataset la lista
	*/
	private static void opcio7(LlistaNivellsAigua dataset) {
		System.out.println("\n\n==== OPCION 7 SELECCIONADO ====\n");
		System.out.print("  Indica l'any (format: any): ");
    	int any = Integer.parseInt(teclat.next());

    	System.out.print("  Indica el nom de la primera província: ");
    	String provincia1 = teclat.next();

    	System.out.print("  Indica el nom de la segona província: ");
    	String provincia2 = teclat.next();

		// Creamos fecha de inicio y fin para ese año
		Data dataInici = new Data(1, 1, any);
    	Data dataFi = new Data(31, 12, any);

		LlistaNivellsAigua mesuresProvincia1 = dataset.consultaPorFechas(dataInici, dataFi)
                                    					 .consultaPorProvincia(provincia1);

		LlistaNivellsAigua mesuresProvincia2 = dataset.consultaPorFechas(dataInici, dataFi)
                                    					 .consultaPorProvincia(provincia2);

    	double volumMaxProvincia1 = mesuresProvincia1.copiaVolumenMasAlto().getVolum();
    	double volumMaxProvincia2 = mesuresProvincia2.copiaVolumenMasAlto().getVolum();

		String provinciaAmbVolumMesAlt;
    	if (volumMaxProvincia1 > volumMaxProvincia2) 
			provinciaAmbVolumMesAlt = provincia1;
    	else 
			provinciaAmbVolumMesAlt = provincia2;
    	
		
		System.out.println(" Per l'any " +any+ ":");
    	System.out.println("  A " +provincia1+ ", volum màxim: " +volumMaxProvincia1);
    	System.out.println("  A " +provincia2+ ", volum màxim: " +volumMaxProvincia2);
    	System.out.println("   La província amb el volum més alt és: " +provinciaAmbVolumMesAlt);
	}

	/** Consultar datos de medida en una franja temporal
	 * 
	 * @param dataset la lista
	 */
	private static void opcio8(LlistaNivellsAigua dataset) {
		System.out.println("\n\n==== OPCION 8 SELECCIONADO ====\n");
		System.out.println("  Indica la primera fecha (format: dia/mes/any): ");
    	String inputdata1 = teclat.next();
    	Data data1 = convertirAData(inputdata1);

    	System.out.println("  Indica la segona fecha (format: dia/mes/any): ");
    	String inputdata2 = teclat.next();
    	Data data2 = convertirAData(inputdata2);

    	LlistaNivellsAigua mesuresPeriode = dataset.consultaPorFechas(data1, data2);
		System.out.println(mesuresPeriode);
	}

	/** Eliminamos un conjunto de medidas de una provincia.
	 * Primero: mostramos los datos de la provincia
	 * Segundo: llamamos al metodo de eliminar
	 * Tercero: mostramos los datos de la provincia (debe estar vacio)
	 * 
	 * @param dataset la lista
	 */
	private static void opcio9(LlistaNivellsAigua dataset) {
		System.out.println("\n\n==== OPCION 9 SELECCIONADO ====\n");
		System.out.print("  Indica el nom de la provincia: ");
		String nomProvincia = teclat.next();

		// Mostramos lista filtrada antes de eliminar
		System.out.println("\n  Datos de la provincia: " +nomProvincia);
		System.out.println(dataset.consultaPorProvincia(nomProvincia));

		// Eliminamos conjunto
		

		// Mostramos la lista despues de eliminar
		System.out.println("\n  Datos despues de eliminar la provincia: " +nomProvincia);
		
		System.out.println();
	}

}