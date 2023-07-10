package br.com.netdeal.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.netdeal.service.CollaboratorsService;

/*
//Number of Characters	Flat	+(n*4)
 * 
 * Additions
 * 
 * Number of Characters			Flat		+(n*4)
 * Uppercase Letters			Cond/Incr	+((len-n)*2)
 * Lowercase Letters			Cond/Incr	+((len-n)*2)
 * Numbers						Cond		+(n*4)
 * Symbols						Flat		+(n*6)
 * Middle Numbers or Symbols	Flat		+(n*2)
 * Requirements					Flat		+(n*2)
 * 
 * Deductions
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
public class Util {
	
	private static final Logger log = LogManager.getLogger(Util.class);
	
	private static final String VERY_STRONG = "Very Strong";
	private static final String STRONG = "Strong";
	private static final String GOOD = "Good";
	private static final String WEAK = "Weak";
	private static final String VERY_WEAK = "Very Weak";
	private static final String TOO_SHORT = "Too Short";
	
	public static Map<String, String> caluculateComplexityAndScore(String psswd) {
		
		Map<String, String> complexityAndScore = new HashMap<>();
		
		/** Additions */
		Double totalAdditions = calculateAdditions(psswd);
		Double totalDeductions = calculateDeductions(psswd);
		
		Double total = totalAdditions - totalDeductions;
		
		Map<Integer, String> complexity = calculateComplexity(psswd);
		Map<Integer, String> score = calculateScore(psswd);
		
		int psswdlength = psswd.length();
		
		complexityAndScore.put(complexity.get(psswdlength), score.get(psswdlength));
		
		return complexityAndScore;
	}
	
	private static Double calculateAdditions(String psswd) {
		
		Double totalAdditions = 0.0;
		
		totalAdditions = numberOfCharacters(psswd, totalAdditions);
		totalAdditions = uppercaseLetters(psswd, totalAdditions);
		totalAdditions = lowercaseLetters(psswd, totalAdditions);
		totalAdditions = numbers(psswd, totalAdditions);
		totalAdditions = symbols(psswd, totalAdditions);
		totalAdditions = middleNumbersOrSymbols(psswd, totalAdditions);
		totalAdditions = requirements(psswd, totalAdditions);
		
		return totalAdditions;
	}
	
	/** Additions */
	
	public static Double numberOfCharacters(String psswd, Double totalAdditions) {
		
		Double numberOfCharacters = 0.0;
		
		return numberOfCharacters;
	}
	
	public static Double uppercaseLetters(String psswd, Double totalAdditions) {
		
		Double uppercaseLetters = 0.0;
		
		return uppercaseLetters;
	}
	
	public static Double lowercaseLetters(String psswd, Double totalAdditions) {
		
		Double lowercaseLetters = 0.0;
		
		return lowercaseLetters;
	}
	
	public static Double numbers(String psswd, Double totalAdditions) {
		
		Double numbers = 0.0;
		
		return numbers;
	}
	
	public static Double symbols(String psswd, Double totalAdditions) {
		
		Double symbols = 0.0;
		
		return symbols;
	}
	
	public static Double middleNumbersOrSymbols(String psswd, Double totalAdditions) {
		
		Double middleNumbersOrSymbols = 0.0;
		
		return middleNumbersOrSymbols;
	}
	
	public static Double requirements(String psswd, Double totalAdditions) {
		
		Double requirements = 0.0;
		
		return requirements;
	}
	
	/*  Deductions **/


	private static Double calculateDeductions(String psswd) {
		
		Double totalDeductions = 0.0;
		
		totalDeductions = lettersOnly(psswd, totalDeductions);
		totalDeductions = numbersOnly(psswd, totalDeductions);
		totalDeductions = repeatCharactersCaseInsensitive(psswd, totalDeductions);
		totalDeductions = consecutiveUppercaseLetters(psswd, totalDeductions);
		totalDeductions = consecutiveLowercaseLetters(psswd, totalDeductions);
		totalDeductions = consecutiveNumbers(psswd, totalDeductions);
		totalDeductions = sequentialLetters3Plus(psswd, totalDeductions);
		totalDeductions = sequentialNumbers3Plus(psswd, totalDeductions);
		totalDeductions = sequentialSymbols3Plus(psswd, totalDeductions);
		
		return totalDeductions;
	}
	
	public static Double lettersOnly(String psswd, Double totalDeductions) {
		
		Double lettersOnly = 0.0;
		
		return lettersOnly;
	}
	
	public static Double numbersOnly(String psswd, Double totalDeductions) {
		
		Double numbersOnly = 0.0;
		
		return numbersOnly;
	}
	
	public static Double repeatCharactersCaseInsensitive(String psswd, Double totalDeductions) {
		
		Double repeatCharactersCaseInsensitive = 0.0;
		
		return repeatCharactersCaseInsensitive;
	}
	
	public static Double consecutiveUppercaseLetters(String psswd, Double totalDeductions) {
		
		Double consecutiveUppercaseLetters = 0.0;
		
		return consecutiveUppercaseLetters;
	}
	
	public static Double consecutiveLowercaseLetters(String psswd, Double totalDeductions) {
		
		Double consecutiveLowercaseLetters = 0.0;
		
		return consecutiveLowercaseLetters;
	}
	
	public static Double consecutiveNumbers(String psswd, Double totalDeductions) {
		
		Double consecutiveNumbers = 0.0;
		
		return consecutiveNumbers;
	}
	
	public static Double sequentialLetters3Plus(String psswd, Double totalDeductions) {
		
		Double sequentialLetters3Plus = 0.0;
		
		return sequentialLetters3Plus;
	}
	
	public static Double sequentialNumbers3Plus(String psswd, Double totalDeductions) {
		
		Double sequentialNumbers3Plus = 0.0;
		
		return sequentialNumbers3Plus;
	}
	
	public static Double sequentialSymbols3Plus(String psswd, Double totalDeductions) {
		
		Double sequentialSymbols3Plus = 0.0;
		
		return sequentialSymbols3Plus;
	}
	
	public static Map<Integer, String> calculateComplexity(String psswd) {
		log.info("calculateComplexity...");
		
		Map<Integer, String> complexity = new HashMap<>();
		int psswdlength = psswd.length();
		
		if(psswdlength >= 20)
			complexity.put(psswdlength, VERY_STRONG);		
		if(psswdlength >= 16 && psswdlength < 20)
			complexity.put(psswdlength, STRONG);
		if(psswdlength >= 10 && psswdlength < 16)
			complexity.put(psswdlength, GOOD);
		if(psswdlength >= 5 && psswdlength < 10)
			complexity.put(psswdlength, WEAK);
		if(psswdlength < 5 )
			complexity.put(psswdlength, VERY_WEAK);
		
		return complexity;
	}
	
	public static Map<Integer, String> calculateScore(String psswd) {
		
		log.info("calculateScore...");
		Map<Integer, String> score = new HashMap<>();
		int psswdlength = psswd.length();
		
		if(psswdlength >= 20)
			score.put(psswdlength, "100%");		
		if(psswdlength >= 16 && psswdlength < 20)
			score.put(psswdlength, "75%");
		if(psswdlength >= 10 && psswdlength < 16)
			score.put(psswdlength, "50%");
		if(psswdlength >= 5 && psswdlength < 10)
			score.put(psswdlength, "30%");
		if(psswdlength < 5 )
			score.put(psswdlength, "15%");
		
		return score;
	}

}
