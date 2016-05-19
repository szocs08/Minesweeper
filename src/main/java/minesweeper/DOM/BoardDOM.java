package minesweeper.DOM;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import minesweeper.Board;

public class BoardDOM {
	
	private Board board;
	private File saveFile = new File("save.xml");

	
	
	public BoardDOM(Board board) {
		super();
		this.board = board;
	}



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
