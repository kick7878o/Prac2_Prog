package dades;

public class NivellAigua {
	private Data dataMesura;
	private String nomEmbassament;
	private String poblacio;
	private String provincia;
	private double nivell;
	private double percentatgeVolum;
	private double volum;
	
	/** Constructor principal que contiene los datos del nivel del agua
	 * de una presa.
	 * 
	 * @param dataMesura
	 * @param nomEmbassament
	 * @param poblacio
	 * @param provincia
	 * @param nivell
	 * @param percent
	 * @param volum
	 */
	public NivellAigua(Data dataMesura, String nomEmbassament, String poblacio, String provincia, double nivell, double percent, double volum) {
		this.dataMesura=dataMesura.copia();
		this.nomEmbassament=nomEmbassament;
		this.poblacio=poblacio;
		this.provincia=provincia;
		this.nivell=nivell;
		this.percentatgeVolum=percent;
		this.volum=volum;
	}

	/** Metodo que comprueba si la poblacion dada por parametro
	 * es la misma que hay en dicha instancia.
	 * 
	 * @param poblacio
	 * @return
	 */
	public boolean esTrobaEnAquestaPoblacio(String poblacio) {
		return (poblacio.equalsIgnoreCase(this.poblacio));
	}
	
	/** Metodo que comprueba si la poblacion dada por parametro
	 * es la misma que hay en dicha instancia.
	 * 
	 * @param provincia
	 * @return
	 */
	public boolean esTrobaEnAquestaProvincia(String provincia) {
		return (provincia.equalsIgnoreCase(this.provincia));
	}

	/** Metodo que comprueba que la fecha de la medida esté
	 * entre las fechas dados por parametro
	 * @param d1
	 * @param d2
	 * @return
	 */
	public boolean esTrobaEnAquestPeriode(Data d1, Data d2) {
		return (d1.esDataInferiorOigual(dataMesura) && dataMesura.esDataInferiorOigual(d2));
	}
	
	/** Metodo que comprueba si un nombre dado por parametro
	 * es la misma que hay en dicha instancia.
	 * 
	 * @param nomEmbassament
	 * @return
	 */
	public boolean esAquestEmbassament(String nomEmbassament) {
        return this.nomEmbassament.equalsIgnoreCase(nomEmbassament);
   }

	/** Getter que obtiene el nombre de la presa
	 * 
	 * @return el nombre
	 */
	public String getNomEmbassament() { return nomEmbassament; }

	/** Getter que obtiene el volumen en porcentaje
	 * 
	 * @return el porcentaje volumetrico
	 */
	public double getPercentVolum() {
		return percentatgeVolum;
	}

	/** Metodo que obtiene el volumen
	 * 
	 * @return el volumen
	 */
	public double getVolum() { return volum; }

	@Override
	public String toString() {
		String aux="NivellAigua =>";
		aux=aux+"\n\tData mesura= " + dataMesura + ", nom embassament= "+nomEmbassament;
		aux=aux+"\n\tpoblacio= " + poblacio + ", provincia= "+provincia;
		aux=aux+"\n\tnivell= " + nivell + ", percentatgeVolum= "+percentatgeVolum+ ", volum= "+volum;
		return aux;
	}

	/** Metodo que copia en una instancia nueva
	 * los datos de la presa
	 * 
	 * @return nueva instancia con datos
	 */
	public NivellAigua copia() {
		NivellAigua mesura=new NivellAigua(dataMesura, nomEmbassament, poblacio, provincia, nivell, percentatgeVolum, volum);
		return mesura;
	}
}
