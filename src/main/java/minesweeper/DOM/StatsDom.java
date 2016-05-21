package minesweeper.DOM;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import minesweeper.model.Stats;

/**
 * {@code Stats} osztályok betöltésére, és kiírására használt osztály.
 * @author Gábor
 *
 */
public class StatsDom {

	private File statFile = new File("stats.xml");
	
	/**
	 * Egy {@code Stats} osztályt ír ki fájlba. 
	 * @param stats a kiírandó {@code Stats}
	 */
	public void saveStats(Stats stats){
		try {
			JAXBContext jc = JAXBContext.newInstance(Stats.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.marshal(stats, statFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Egy {@code Stats} osztályt olvasunk be fájlból.
	 * @return a beolvasott {@code Stats}
	 */
	public Stats loadStats() {
		Stats stats=new Stats();
		if (statFile.isFile()) {
			try {
				JAXBContext jc = JAXBContext.newInstance(Stats.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				stats = (Stats) unmarshaller.unmarshal(statFile);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return stats;
	}
	
}
