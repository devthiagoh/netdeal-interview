package br.com.netdeal.util;

public class Util {
	
/*
//Number of Characters	Flat	+(n*4)
 * 
 * Adictional
 * 
 * Number of Characters			Flat		+(n*4)
 * Uppercase Letters			Cond/Incr	+((len-n)*2)
 * Lowercase Letters			Cond/Incr	+((len-n)*2)
 * Numbers						Cond		+(n*4)
 * Symbols						Flat		+(n*6)
 * Middle Numbers or Symbols	Flat		+(n*2)
 * Requirements					Flat		+(n*2)
 * 
 * Deduction
 * 
 * Letters Only								Flat	-n
 * Numbers Only								Flat	-n
 * Repeat Characters (Case Insensitive)		Comp	-
 * Consecutive Uppercase Letters			Flat	-(n*2)
 * Consecutive Lowercase Letters			Flat	-(n*2)
 * Consecutive Numbers						Flat	-(n*2)
 * Sequential Letters (3+)					Flat	-(n*3)
 * Sequential Numbers (3+)					Flat	-(n*3)
 * Sequential Symbols (3+)					Flat	-(n*3)
 * 
 * 
 * Notas de rodapé rápidas
• Flat: Taxas que adicionam/removem em incrementos inalteráveis.
• Incr: Taxas que adicionam/removem em incrementos de ajuste.
• Cond: Tarifas que adicionam/removem dependendo de fatores adicionais.
• Comp: Taxas muito complexas para resumir. Consulte o código-fonte para obter detalhes.
• n: Refere-se ao número total de ocorrências.
• len: Refere-se ao tamanho total da senha.
• Pontuações de bônus adicionais são dadas para aumentar a variedade de personagens.
• A pontuação final é um resultado cumulativo de todos os bônus menos as deduções.
• A pontuação final é limitada a um mínimo de 0 e um máximo de 100.
• As classificações de Pontuação e Complexidade não estão condicionadas ao cumprimento dos requisitos mínimos.
 */
	
	
	/** Adictional */
	
	public static String numberOfCharacters(String psswd) {
		
		String numberOfCharacters = "";
		
		return numberOfCharacters;
	}
	
	public static String uppercaseLetters(String psswd) {
		
		String uppercaseLetters = "";
		
		return uppercaseLetters;
	}
	
	public static String lowercaseLetters(String psswd) {
		
		String lowercaseLetters = "";
		
		return lowercaseLetters;
	}
	
	public static String numbers(String psswd) {
		
		String numbers = "";
		
		return numbers;
	}
	
	public static String symbols(String psswd) {
		
		String symbols = "";
		
		return symbols;
	}
	
	public static String middleNumbersOrSymbols(String psswd) {
		
		String middleNumbersOrSymbols = "";
		
		return middleNumbersOrSymbols;
	}
	
	public static String requirements(String psswd) {
		
		String requirements = "";
		
		return requirements;
	}
	
	/*  Deduction **/
	
	public static String lettersOnly(String psswd) {
		
		String lettersOnly = "";
		
		return lettersOnly;
	}
	
	public static String numbersOnly(String psswd) {
		
		String numbersOnly = "";
		
		return numbersOnly;
	}
	
	public static String repeatCharactersCaseInsensitive(String psswd) {
		
		String repeatCharactersCaseInsensitive = "";
		
		return repeatCharactersCaseInsensitive;
	}
	
	public static String consecutiveUppercaseLetters(String psswd) {
		
		String consecutiveUppercaseLetters = "";
		
		return consecutiveUppercaseLetters;
	}
	
	public static String consecutiveLowercaseLetters(String psswd) {
		
		String consecutiveLowercaseLetters = "";
		
		return consecutiveLowercaseLetters;
	}
	
	public static String consecutiveNumbers(String psswd) {
		
		String consecutiveNumbers = "";
		
		return consecutiveNumbers;
	}
	
	public static String sequentialLetters3Plus(String psswd) {
		
		String sequentialLetters3Plus = "";
		
		return sequentialLetters3Plus;
	}
	
	public static String sequentialNumbers3Plus(String psswd) {
		
		String sequentialNumbers3Plus = "";
		
		return sequentialNumbers3Plus;
	}
	
	public static String sequentialSymbols3Plus(String psswd) {
		
		String sequentialSymbols3Plus = "";
		
		return sequentialSymbols3Plus;
	}

}
