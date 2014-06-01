package com.teamcriminals.Character;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.TileMap.TileMap;

public class CharacterFactory {

	public Character setCharacter(int num, TileMap tm) {
		
		switch(num) {
		
		case 0 : 
			return new Caesar(tm);
		case 1 :
			return new Zero(tm);
		case 2 :
			return new Fyro(tm);
		case 3 :
			return new Draco(tm);
		default :
			return null;
		}
		
	}
	
}
