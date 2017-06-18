package main;

import util.FileHelper;

/**
 * Esta clase funciona como diccionario de palabras. Las palabras se guardaran en un archivo, cada linea 
 * correspondera a un par palabra:pista
 * 
 * @author campino
 *
 */
public class HangedModel {
	final String fileDictionary; 
	
	private String[] newWord;
	
	@Deprecated
	private String[] oldWord;

	
	public HangedModel(String fileDictionary){
		this.fileDictionary = fileDictionary; 
		loadWords();
	}
	
	
	/**
	 * Carga todo el diccionario de palabras desde el fichero
	 */
	private void loadWords(){
		
		this.newWord =  FileHelper.readFile(fileDictionary);
		this.oldWord = new String[0];		
	}
	
	/**
	 * retorna una palabra seleccionada aleatoriamente del arreglo newWord
	 * si no hay mas palabras disponibles en newWord, reinicia los arreglos. 
	 * es decir carga nuevamente las palabras desde el fichero con loadWords()
	 * @return
	 */
	
	public SecretWord getNextWord(){
		
		if(newWord.length==0)
			loadWords();
		
			// Genero un número aleatorio del tamaño del arreglo de newWord
			int randomWord = (int) (Math.random() * this.newWord.length);
			String nextWord = this.newWord[randomWord];
			removeFromNew(randomWord);
			
			
			return new SecretWord(nextWord);
	}


	private void  removeFromNew(int index ){
		String[] copyNewWords = new String[newWord.length-1];
		int newCounter = 0;
		for(int i=0;i<newWord.length;i++){
			if(i!=(index)){
				copyNewWords[newCounter++]=newWord[i];
			}
		}
		newWord = copyNewWords;
	}
	
	
	



	public String[] getNewWord() {
		return newWord;
	}


	public void setNewWord(String[] newWord) {
		this.newWord = newWord;
	}



	public String getFileDictionary() {
		return fileDictionary;
	}
	
	
	public static class SecretWord{
		
		public final String word;
		public final String hint;
		
		private SecretWord(String fileLineWord){
			
			String value[] = fileLineWord.split(":");
			this.word = value[1];
			this.hint = value[0];
			
		}

		
	}
	
	
}
