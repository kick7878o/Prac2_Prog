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
		String[] dataset = llegirLiniesFitxer(numLinies);

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
	private static String[] llegirLiniesFitxer(int nLinies) throws FileNotFoundException {
		String[] result;
		if (nLinies < 0)
			nLinies = 0;
		if (nLinies > 78282)
			nLinies = 78282;
		result = new String[nLinies];
		Scanner f = new Scanner(new File("Quantitat_aigua_embassaments_20231025.csv"));

		String capcalera = f.nextLine();
		System.out.println("El format de les dades en cada línia és el següent\n" + capcalera);
		for (int i = 0; i < nLinies; i++) {
			//result[i] = f.nextLine();
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

			//result[i].afegirDadesMesura(nivell);
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

	public static void opcio1(String[] dataset) {
		for (int i = 0; i < dataset.length; i++) {
			System.out.println("Linia " + (i + 1) + " conté " + dataset[i]);
		}
	}

	public static void opcio2(String[] dataset) {
		System.out.println("\tIndica el nom de la provincia");
		String nomProvincia = teclat.nextLine();
		System.out.println("\tIndica data 1");
		String inputData1 = teclat.nextLine();
		String[] inputData1Split = inputData1.split("/");
		int dia1 = Integer.parseInt(inputData1Split[0]);
		int mes1 = Integer.parseInt(inputData1Split[1]);
		int any1 = Integer.parseInt(inputData1Split[2]);
		Data data1 = new Data(dia1, mes1, any1);
		System.out.println("\tIndica data 2");
		String inputData2 = teclat.nextLine();
		String[] inputData2Split = inputData2.split("/");
		int dia2 = Integer.parseInt(inputData2Split[0]);
		int mes2 = Integer.parseInt(inputData2Split[1]);
		int any2 = Integer.parseInt(inputData2Split[2]);
		Data data2 = new Data(dia2, mes2, any2);
		//dataset.consultaPorProvincia(nomProvincia);
	}

	public static void opcio3(String[] dataset) {
		System.out.println("\tIndica el nom de l'embassament");
		String nomEmbassament = teclat.nextLine();
		//dataset.primeraMesura(nomEmbassament);
	}

	public static void opcio4(String[] dataset) {
		System.out.println("\t");
	}

	public static void opcio5(String[] dataset) {

	}

	public static void opcio6(String[] dataset) {
		System.out.println("\tIndica el nom de la provincia");
		String nomProvincia = teclat.nextLine();
	}

	public static void opcio7(String[] dataset) {

	}

	public static void opcio8(String[] dataset) {

	}

	public static void opcio9(String[] dataset) {

	}

}