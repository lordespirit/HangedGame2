package test;

import org.junit.Assert;
import org.junit.Test;

import main.HangedBoard;

public class TestHangedBoard {
	
	@Test
	public void testAddLetterToWordPlayer(){
		
		HangedBoard board = new HangedBoard();
		board.startGame("cacatua", 2);
		int[] result = board.addLetterToWordPlayer('a');
		int[] expected  = new int[]{1,3,6};
		Assert.assertArrayEquals(expected, result);
		
		
		char[] actualWord = board.getWordPlayer();
		char[] expectedWord = "-a-a--a".toCharArray();
		Assert.assertArrayEquals(expectedWord,actualWord);
		
	}
	
	@Test
	public void testIsWinner(){
		
		HangedBoard board = new HangedBoard();
		board.startGame("cacatua", 2);
		board.addLetterToWordPlayer('a');
		board.addLetterToWordPlayer('c');
		board.addLetterToWordPlayer('t');
		board.addLetterToWordPlayer('u');
		
		Assert.assertTrue(board.isWinner());
		
	}
	
	@Test
	public void testIsGameOver(){
		
		HangedBoard board = new HangedBoard();
		board.startGame("cacatua", 2);
		board.addLetterToWordPlayer('a');
		board.addLetterToWordPlayer('c');
		board.addLetterToWordPlayer('z');
		board.addLetterToWordPlayer('f');
		
		Assert.assertTrue(board.isGameOver());
		
	}

}
