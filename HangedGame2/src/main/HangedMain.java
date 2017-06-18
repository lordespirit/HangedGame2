package main;

/**
 *  Aplicación para jugar Ahorcado similar a la publicada en: 
	http://pasatiempos.elmundo.es/ahorcado/ahorcado.html 
	 
	En este caso la interfaz de usuario se realizará mediante línea de comandos
	
	 Caracteristicas :
	 
	-Iniciar partida
	-Al inicio de cada partida debe mostrarse una pista para la palabra secreta (e.j pais, nombre, provincia, etc) 
	-Durante el juego el usuario podrá reiniciar  la palabra secreta, pero la racha ganadora se reiniciará también. 
	-Cuenta regresiva de fallos
	-Racha ganadora actual y récord de racha ganadora ganadora (con nick si es el caso)
	-Mensaje Partida ganada
	-Mensaje Partida perdida
	-Si el jugador supera el récord de la racha ganadora, mensaje felicitación y opción de guardar su nick (archivo).
	-Salir del juego
	-Diccionario con minimo 20 palabras
	

 * @author campino
 *
 */

public class HangedMain {
	
	
	/**
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		
		int difficult = 10;
		
		HangedModel dictionary = new HangedModel("words.txt");
		HangedBoard board = new HangedBoard();
		String select = "jugar";
		boolean isAgain = false;
		do{
			if(!isAgain){
			UserInterface.showMenuInit(board.getStreak());
			select = UserInterface.scanMenuInicio();
			}
			switch(select){
			case "jugar":
			case "JUGAR":
				HangedModel.SecretWord secretWord = dictionary.getNextWord();
				board.startGame(secretWord.word,difficult);
				System.out.println("PISTA : " + secretWord.hint);
				char[] wordPlayer = board.getWordPlayer();
				String ch;
				boolean isWinner = false;
				boolean isGameOver = false;
				do{
				UserInterface.showMenuBoard(wordPlayer, secretWord.hint, board.getMaxFails()-board.getCurrentfails());
				ch = UserInterface.scanMenuBoard();
					switch(ch){
					case "salir":
						board.reset();
						isAgain = false;
						break;
					case "reiniciar":
						board.reset();
						isAgain = true;
						break;
					default:
						
						if(UserInterface.testWordPlayerContains(wordPlayer,ch.toCharArray()[0])){
							System.out.println("Error, esta letra ya ha salido...");
							break;
						}else{
							int[] result = board.addLetterToWordPlayer(ch.toCharArray()[0]);
							isWinner = board.isWinner();
							isGameOver = board.isGameOver();
								if(result.length==0){
									System.out.println("La letra no se encuentra, has perdido una vida");
								}else{
									System.out.println("Letra descubierta! Sigue as�.");
								}
							
							if(isWinner){
								board.setStreak(board.getStreak()+1);
								UserInterface.showMenuAgain(true);
								char againOption = UserInterface.scanMenuEndGame().toLowerCase().toCharArray()[0];
								if(againOption=='s'){
									isAgain = true;
								}else{
									isAgain = false;
									break;
								}
							}else if(isGameOver){
								UserInterface.showMenuAgain(false);
								char againOption = UserInterface.scanMenuEndGame().toLowerCase().toCharArray()[0];
								if(againOption=='s'){
									isAgain = true;
								}else{
									isAgain = false;
									break;
								}
							}
						}
						break;
					}
				}while(!(ch.equals("salir")||ch.equals("reiniciar")||isWinner==true||isGameOver==true));
				
				break;
				
			case "salir":
			case "SALIR":
				System.out.println("Gracias por jugar");
				if(board.getStreak()>0){
					System.out.println("Conseguiste una racha total de " + board.getStreak() + " FELICIDADES!");
				}
				break;	
				
			case "dificultad":
			case "DIFICULTAD":
				UserInterface.showMenuDifficult();
				difficult = UserInterface.scanMenudifficult();
				break;
				}
		}while(!((select.equals("salir"))||(select.equals("SALIR"))));
		 
	}
}
