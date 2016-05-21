package minesweeper.DOM;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import minesweeper.Board;

/**
 * {@code Board} osztályok betöltésére, és kiírására használt osztály.
 * @author Gábor
 *
 */
public class BoardDOM {
	
	/**
	 * A kíírandó {@code Board}.
	 */
	private Board board;
	/**
	 * A fájl amibe kiírjuk, vagy amiből betöltjük a {@code Board} osztály adatait.
	 */
	private File saveFile = new File("save.xml");

	
	
	/**
	 * Létrehozza az objektumot a megadott értékek alapján.
	 * @param board a kíírandó {@code Board}
	 */
	public BoardDOM(Board board) {
		super();
		this.board = board;
	}



	/**
	 * Egy {@code Board} osztályt ír ki fájlba.
	 * @param time az ídő érték amit a {@code Board}-ban ki szeretnénk írni
	 */
	public void save(int time) {
			board.setTime(time);
		try {
			JAXBContext jc = JAXBContext.newInstance(Board.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.marshal(board, saveFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Egy {@code Board} osztályt olvasunk be fájlból.
	 * @return a beolvasott {@code Board} 
	 */
	public Board load() {
		if (saveFile.isFile()) {
			try {
				JAXBContext jc = JAXBContext.newInstance(Board.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				Board savedBoard = (Board) unmarshaller.unmarshal(saveFile);
				
				return savedBoard;
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return null;

	}
}
