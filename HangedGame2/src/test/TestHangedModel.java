package test;


import org.junit.Assert;
import org.junit.Test;

import main.HangedModel;

public class TestHangedModel {

	@Test
	public void testGetNextWord(){
		
		HangedModel hanged = new HangedModel("words.txt");
		
		Assert.assertEquals(3, hanged.getNewWord().length);
		
		hanged.getNextWord();
		Assert.assertEquals(2, hanged.getNewWord().length);
		
		hanged.getNextWord();
		Assert.assertEquals(1, hanged.getNewWord().length);
		
		hanged.getNextWord();
		Assert.assertEquals(0, hanged.getNewWord().length);
		
		hanged.getNextWord();
		Assert.assertEquals(2, hanged.getNewWord().length);

	}
}
