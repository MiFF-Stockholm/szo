package util;

import gameObjects.Door;
import gameObjects.Floor;
import gameObjects.Wall;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import main.Game;

public class MapParser {

	private static String textureFileName = "32x32.gif";

	public static void parseTilesetXML(String fileName, Game game) {
		try {
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();

			// Setup a new eventReader
			InputStream in = new FileInputStream(fileName);
			XMLStreamReader sr = inputFactory.createXMLStreamReader(in);

			while (sr.hasNext()) {
				int eventType = sr.next();
				if (eventType == XMLStreamConstants.START_ELEMENT) {
					while (sr.hasNext()) {
						eventType = sr.next();
						String localName;
						if (eventType == XMLStreamConstants.START_ELEMENT) {
							localName = sr.getLocalName();
							if (localName.equals("tilelist")) {
								// no operation
							} else if (localName.equals("tile")) {
								try {
									String tileName = sr.getAttributeValue(
											null, "name");
									int id = Integer.parseInt(sr
											.getAttributeValue(null, "id"));
									String tileFilename = sr.getAttributeValue(
											null, "filename");
									String tileType = sr.getAttributeValue(
											null, "type");
									Tile tile = new Tile(tileName,
											tileFilename, tileType);
									if (tile.getType().equals("door")) {
										tile.setRoom(sr.getAttributeValue(null,
												"room"));
									}

									game.textureList.put(id, tile);

								} catch (NumberFormatException e) {
									e.printStackTrace();
								}
							}
							// else if (localName.equals("name_of_new_tag")) {
							// sr.next(); //if the tag contains a text element,
							// use this row
							// sr.getText(); //gets the text contained in the
							// text element
							// sr.getAttributeValue(null, "name_of_attribute");
							// gets value of attribute
							// }
						} else if (eventType == XMLStreamConstants.END_ELEMENT) {
							localName = sr.getLocalName();
							if (localName.equals("tilelist")) {
								try {
									sr.close();
									in.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
								return;
							}
						}
					}
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void parseMap2(String fileName, Game game) {
		try {
			BufferedReader fin = new BufferedReader(new FileReader(fileName));
			int maxRow = 0, maxCol = 0, layerId = 0;

			for (int i = 0; i < 3; i++) {
				String line = fin.readLine();
				StringTokenizer token = new StringTokenizer(line);
				maxRow = Integer.parseInt(token.nextToken());
				maxCol = Integer.parseInt(token.nextToken());
				layerId = Integer.parseInt(token.nextToken());

				for (int row = 0; row < maxRow; row++) {
					line = fin.readLine();
					StringTokenizer token2 = new StringTokenizer(line);
					for (int col = 0; col < maxCol; col++) {
						String nextToken = token2.nextToken();
						if (nextToken.equals("x")) {
							continue;
						}
						int id = Integer.parseInt(nextToken);
						makeTile(game, id, col, row, layerId);
					}
				}
			}

			fin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void makeTile(Game game, int id, int col, int row,
			int layerId) {
		// använd id't för att plocka fram tile
		Tile tile = game.textureList.get(id);

		if (tile.getType().equals("floor")) {
			game.layer0.add(new Floor(game, tile.getFilename(), col * 64,
					row * 64));
		} else if (tile.getType().equals("static")) {
			game.layer2.add(new Wall(game, tile.getFilename(), col * 64,
					row * 64));
		} else if (tile.getType().equals("movable")) {
			//TODO fulkod, riktigt experimentellt!
			game.layer2.add(new Wall(game, tile.getFilename(), col * 64,
					row * 64));
		} else if (tile.getType().equals("item")) {

		} else if (tile.getType().equals("door")) {
			game.layer2.add(new Door(game.getSprite(tile.getFilename()), tile
					.getRoom(), col * 64, row * 64));
		}
	}
}
