package aplicacio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import dades.*;

public class UsaLlistaNivellsAigua {
	static Scanner teclat = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Indica el número de línies a llegir del fitxer (màxim 78282)");
		int numLinies = Integer.parseInt(teclat.nextLine());
		LlistaNivellsAigua dataset = llegirLiniesFitxer(numLinies);

		// mostrem el contingut que hem llegit. Això ho eliminarem en les
		// versions finals del codi

		// Completar el codi a partir d'aquí
		mostraMenu();
		int opcio = Integer.parseInt(teclat.nextLine());
		while (opcio != 10) {
			switch (opcio) {
			case 1:
				opcio1(dataset);
				break;
			case 2:
				opcio2(dataset);
				break;
			case 3:
				opcio3(dataset);
				break;
			case 4:
				opcio4(dataset);
				break;
			case 5:
				opcio5(dataset);
				break;
			case 6:
				opcio6(dataset);
				break;
			case 7:
				opcio7(dataset);
				break;
			case 8:
				opcio8(dataset);
				break;
			case 9:
				opcio9(dataset);
				break;
			}
		}

	}

	

	/** Metodo privado que lee un determinado numero de lineas del fichero.
	 * Si se pasa, se le asignara el numero maximo o minimo
	 * 
	 * @param nLinies
	 * @return Las lineas leídas del fichero
	 * @throws FileNotFoundException
	 */
	private static LlistaNivellsAigua llegirLiniesFitxer(int nLinies) throws FileNotFoundException {
		LlistaNivellsAigua result;
		if (nLinies < 0)
			nLinies = 0;
		if (nLinies > 78282)
			nLinies = 78282;
		result = new LlistaNivellsAigua(nLinies);
		Scanner f = new Scanner(new File("Quantitat_aigua_embassaments_20231025.csv"));

		String capcalera = f.nextLine();
		System.out.println("El format de les dades en cada línia és el següent\n" + capcalera);
		for (int i = 0; i < nLinies; i++) {
			String linia = f.nextLine();
			String[] camps = linia.split(",");
			
			//Procesar los campos
			String dataString = camps[0];
			String[] dataSplit = dataString.split("/");
			int dia = Integer.parseInt(dataSplit[0]);
			int mes = Integer.parseInt(dataSplit[1]);
			int any = Integer.parseInt(dataSplit[2]);
			Data data = new Data(dia, mes, any);
			String nomEmbPobl = camps[1];
			String[] EmbPoblSplit = nomEmbPobl.split("\\(");
			String nomEmb = EmbPoblSplit[0].trim();
			String poblacio = EmbPoblSplit[1].replace(")", "").trim();
			String provincia = camps[2];
			double nivellAbs = Double.parseDouble(camps[3]);
			double percentatgeVolum = Double.parseDouble(camps[4]);
			double volum = Double.parseDouble(camps[5]);

			NivellAigua nivell = new NivellAigua(data, nomEmb, poblacio, provincia, nivellAbs, percentatgeVolum, volum);

			result.afegirDadesMesura(nivell);
		}
		f.close();
		return result;
	}

	public static void mostraMenu() {
		System.out.println("\n\nOpcions del menu:");
		System.out.println("\n\t1. Mostrar conjunt de mesures de la llista.");
		System.out.println("\t2. Mostrar mesures d'una provincia entre dates.");
		System.out.println("\t3. Consultar la primera mesura de la llista d'un cert embassament.");
		System.out.println("\t4. Consultar la mesura amb percentatge de volum mes alt dels embassaments.");
		System.out.println("\t5. Consultar del percentatge de volum mes baix.");
		System.out.println("\t6. Consultar de quants embassaments tenim dades en una certa provincia.");
		System.out.println("\t7. Calcular en quina de les dues províncies té un volum d'aigua mes alt.");
		System.out.println("\t8. Consultar dades de mesura en un periode de temps.");
		System.out.println("\t9. Eliminar conjunt de mesures dels embassaments d'una província.");
		System.out.println("\t10. Sortir");
		System.out.println("\n\t\t\tIndica opcio:\n");
	}

	private static Data convertirAData(String dataString){
		String[] dataStringSplit = dataString.split("/");
		int dia = Integer.parseInt(dataStringSplit[0]);
		int mes = Integer.parseInt(dataStringSplit[1]);
		int any = Integer.parseInt(dataStringSplit[2]);

		return new Data(dia, mes, any);
	}

	public static void opcio1(LlistaNivellsAigua dataset) {
		System.out.println("Mesures de la llista:");
		System.out.println(dataset);
	}

	public static void opcio2(LlistaNivellsAigua dataset) {
		System.out.println("\tIndica el nom de la provincia");
		String nomProvincia = teclat.nextLine();
		System.out.println("\tIndica data 1 (format: dia/mes/any)");
		String inputData1 = teclat.nextLine();
		Data data1 = convertirAData(inputData1);
		System.out.println("\tIndica data 2 (format: dia/mes/any)");
		String inputData2 = teclat.nextLine();
		Data data2 = convertirAData(inputData2);
		LlistaNivellsAigua a;
		a = dataset.consultaPorProvincia(nomProvincia.trim())
				.consultaPorFechas(data1, data2);
		System.out.println(a);
	}

	public static void opcio3(LlistaNivellsAigua dataset) {
		System.out.println("\tIndica el nom de l'embassament");
		String nomEmbassament = teclat.nextLine();
		System.out.println("Primera mesura de" + nomEmbassament + ":" + dataset.primeraMesura(nomEmbassament.trim()));
	}

	public static void opcio4(LlistaNivellsAigua dataset) {
		NivellAigua tarragona = dataset.consultaPorProvincia("Tarragona")
					.copiaPorcentajeMasAlto();
    	NivellAigua barcelona = dataset.consultaPorProvincia("Barcelona")
					.copiaPorcentajeMasAlto();
    	NivellAigua lleida = dataset.consultaPorProvincia("Lleida")
					.copiaPorcentajeMasAlto();
    	NivellAigua girona = dataset.consultaPorProvincia("Girona")
					.copiaPorcentajeMasAlto();

    	System.out.println("Percentatge de volum més alt per provincia:");
    	System.out.println("Tarragona: " + tarragona);
    	System.out.println("Barcelona: " + barcelona);
    	System.out.println("Lleida: " + lleida);
    	System.out.println("Girona: " + girona);

		//falta ver si son del mismo año
	}

	public static void opcio5(LlistaNivellsAigua dataset) {
		NivellAigua percentMesBaix = dataset.copiaPorcentajeMasBajo();
		System.out.println("Mesura amb percentatge de volum mes baix:" + percentMesBaix);
	}

	public static void opcio6(LlistaNivellsAigua dataset) {
		System.out.println("\tIndica el nom de la provincia");
		String nomProvincia = teclat.nextLine();
		LlistaNivellsAigua opcioSis;
		opcioSis = dataset.consultaPorProvincia(nomProvincia.trim());
		System.out.println("Hi han dades de" + opcioSis.getElem() + "elements de" + nomProvincia);
		System.out.println("Nom dels embassaments:");
		for(int i = 0; i < opcioSis.getElem(); i++) {
			NivellAigua mesura;
			//System.out.println(.getNomEmbassament);
		}
	}

	public static void opcio7(LlistaNivellsAigua dataset) {
		System.out.println("\tIndica l'any (format: any)");
    	int any = Integer.parseInt(teclat.nextLine());

    	System.out.println("\tIndica el nom de la primera província");
    	String provincia1 = teclat.nextLine();
    	System.out.println("\tIndica el nom de la segona província");
    	String provincia2 = teclat.nextLine();

		Data dataInici = new Data(1, 1, any);
    	Data dataFi = new Data(31, 12, any);

		LlistaNivellsAigua mesuresProvincia1 = dataset.consultaPorFechas(dataInici, dataFi)
                                    .consultaPorProvincia(provincia1.trim());
		LlistaNivellsAigua mesuresProvincia2 = dataset.consultaPorFechas(dataInici, dataFi)
                                    .consultaPorProvincia(provincia2.trim());

    	double volumMaxProvincia1 = mesuresProvincia1.copiaVolumenMasAlto().getVolum();
    	double volumMaxProvincia2 = mesuresProvincia2.copiaVolumenMasAlto().getVolum();

		String provinciaAmbVolumMesAlt;
    	if (volumMaxProvincia1 > volumMaxProvincia2) {
        	provinciaAmbVolumMesAlt = provincia1;
    	} else {
        	provinciaAmbVolumMesAlt = provincia2;
    	}
		
		System.out.println("Per l'any " + any + ":");
    	System.out.println("A " + provincia1 + ", volum màxim: " + volumMaxProvincia1);
    	System.out.println("A " + provincia2 + ", volum màxim: " + volumMaxProvincia2);
    	System.out.println("La província amb el volum més alt és: " + provinciaAmbVolumMesAlt);
	}

	public static void opcio8(LlistaNivellsAigua dataset) {
		System.out.println("\tIndica la primera data (format: dia/mes/any)");
    	String inputdata1 = teclat.nextLine();
    	Data data1 = convertirAData(inputdata1);

    	System.out.println("\tIndica la segona data (format: dia/mes/any)");
    	String inputdata2 = teclat.nextLine();
    	Data data2 = convertirAData(inputdata2);
    	LlistaNivellsAigua mesuresPeriode = dataset.consultaPorFechas(data1, data2);
		System.out.println(mesuresPeriode);
	}

	public static void opcio9(LlistaNivellsAigua dataset) {

	}

}