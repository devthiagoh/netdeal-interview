package br.com.netdeal.util;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.netdeal.dto.ComplexityAndScoreDTO;

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
	
	public static final String VERY_STRONG = "Very Strong";
	public static final String STRONG = "Strong";
	public static final String GOOD = "Good";
	public static final String WEAK = "Weak";
	public static final String VERY_WEAK = "Very Weak";
	public static final String TOO_SHORT = "Too Short";
	
	private static final String VERY_STRONG_PERCENT = "100%";
	private static final String STRONG_PERCENT = "75%";
	private static final String GOOD_PERCENT = "50%";
	private static final String WEAK_PERCENT = "30%";
	private static final String VERY_WEAK_PERCENT = "15%";
	private static final String TOO_SHORT_PERCENT = "0%";
	
	public static ComplexityAndScoreDTO calculateComplexityAndScore(String psswd) {
				
		Integer totalAdditions = calculateAdditions(psswd);
		Integer totalDeductions = calculateDeductions(psswd);
		
		Integer force = totalAdditions - totalDeductions;
		
		String complexity = calculateComplexity(force);
		String score = calculateScore(force);
		
		ComplexityAndScoreDTO complexityAndScore = new ComplexityAndScoreDTO();
		complexityAndScore.setComplexity(complexity);
		complexityAndScore.setScore(score);
		return complexityAndScore;
	}
	
	private static Integer calculateAdditions(String psswd) {
		
		Integer totalAdditions = 0;
		
		Integer numberOfCharacters = countNumberOfCharacters(psswd);
		Integer uppercaseLetters = countUppercaseLetters(psswd);
		Integer lowercaseLetters = countLowercaseLetters(psswd);
		Integer numbers = countNumbers(psswd);
		Integer symbols = countSymbols(psswd);
		Integer middleNumbersOrSymbols = countMiddleNumbersOrSymbols(psswd);
		Integer requirements = getRequirements(psswd);
		
		totalAdditions = numberOfCharacters + 
						 uppercaseLetters +
						 lowercaseLetters + 
						 numbers + 
						 symbols +
						 middleNumbersOrSymbols +
						 requirements;
		
		return totalAdditions;
	}
	
	/** Additions */
	
	private static Integer countNumberOfCharacters(String psswd) {
		
		Integer bonus = 0;
		Integer numberOfCharacters = psswd.length();
		Integer ratio = 4;	    
			    
	    bonus = numberOfCharacters * ratio;// +(n*4)
	    
		return bonus;
	}
	
	private static Integer countUppercaseLetters(String psswd) {
						
		Integer bonus = 0;
		Integer uppercaseLetters = 0;
		Integer psswdlength = psswd.length();
		Integer ratio = 2;	    
		
		char[] chars = psswd.toCharArray();

	    for (char c : chars)
	        if(Character.isUpperCase(c))
	        	uppercaseLetters += uppercaseLetters++;
	    
	    bonus = (psswdlength - uppercaseLetters) * ratio;// +((len-n)*2)
	    
		return bonus;
	}
	
	private static Integer countLowercaseLetters(String psswd) {
						
		Integer bonus = 0;
		Integer lowercaseLetters = 0;
		Integer psswdlength = psswd.length();
		Integer ratio = 2;	    
		
		char[] chars = psswd.toCharArray();

	    for (char c : chars)
	        if(Character.isLowerCase(c))
	        	lowercaseLetters += lowercaseLetters++;
	    
	    bonus = (psswdlength - lowercaseLetters) * ratio;// +((len-n)*2)
	    
		return bonus;
	}
	
	private static Integer countNumbers(String psswd) {
				
		Integer bonus = 0;
		Integer numbers = 0;
		Integer psswdlength = psswd.length();
		Integer ratio = 4;	    
		
		char[] chars = psswd.toCharArray();

	    for (char c : chars) 
	        if(Character.isDigit(c)) 
	        	numbers += numbers++;
	    
	    bonus = (psswdlength - numbers) * ratio;// +((len-n)*4)
	    
		return bonus;
	}
	
	private static Integer countSymbols(String psswd) {
		
		Integer bonus = 0;
		Integer symbols = 0;
		Integer psswdlength = psswd.length();
		Integer ratio = 4;	    
		
		char[] chars = psswd.toCharArray();

	    for (char c : chars) 
	        if(!Character.isDigit(c) && !Character.isLetter(c))
	        	symbols += symbols++;
	    
	    bonus = (psswdlength - symbols) * ratio;// +((len-n)*4)
	    
		return bonus;
	}
	
	private static Integer countMiddleNumbersOrSymbols(String psswd) {
		
		Integer middleNumbersOrSymbols = 0;
		
		return middleNumbersOrSymbols;
	}
	
	/**
		 	- Minimum 8 characters in length
			- Contains 3/4 of the following items:
				- Uppercase Letters
				- Lowercase Letters
				- Numbers
				- Symbols
		 
	 * */
	private static Integer getRequirements(String psswd) {
		
		Integer bonus = 0;
		Integer requirements = 0;
		Integer ratio = 2;
		
		boolean containMinimumCharacters = containMinimumCharacters(psswd);
		if(containMinimumCharacters)
			requirements++; 
		boolean containUpperCase = containUpperCase(psswd);
		if(containUpperCase)
			requirements++; 
		boolean containLowerCase = containLowerCase(psswd);
		if(containLowerCase)
			requirements++; 
		boolean containNumbers = containNumbers(psswd);
		if(containNumbers)
			requirements++; 
		boolean containSymbols = containSymbols(psswd);
		if(containSymbols)
			requirements++; 
		
		if(containMinimumCharacters && 
				(containUpperCase && containLowerCase && containNumbers || containSymbols ) ||
				(containLowerCase && containNumbers && containSymbols || containUpperCase ) ||
				(containUpperCase && containNumbers && containSymbols || containLowerCase ))
			bonus = requirements * ratio; //+(n*2)
		
		return bonus;
	}
	
	private static boolean containMinimumCharacters(String psswd) {
		
		boolean contain = false;
		
		if(psswd.isEmpty()) 
			return contain;
		if(psswd.length() >= 8)
			return contain;
		
		return contain;
	}
	
	private static boolean containUpperCase(String psswd) {
		
		boolean contain = false; 
		
		char[] chars = psswd.toCharArray();

	    for (char c : chars)
	        if(Character.isUpperCase(c))
	        	contain = true;	    
		
		return contain;
	}
	
	private static boolean containLowerCase(String psswd) {
		
		boolean contain = false; 
		
		char[] chars = psswd.toCharArray();

	    for (char c : chars)
	        if(Character.isLowerCase(c))
	        	contain = true;	    
		
		return contain;
	}
	
	private static boolean containNumbers(String psswd) {
		
		boolean contain = false; 
		
		char[] chars = psswd.toCharArray();

	    for (char c : chars)
	        if(Character.isDigit(c))
	        	contain = true;	    
		
		return contain;
	}
	
	private static boolean containSymbols(String psswd) {
		
		boolean contain = false; 
		
		char[] chars = psswd.toCharArray();

	    for (char c : chars)
	        if(!Character.isDigit(c) && !Character.isLetter(c))
	        	contain = true;	    
		
		return contain;
	}
	
	/*  Deductions **/


	private static Integer calculateDeductions(String psswd) {
		
		Integer totalDeductions = 0;
		
		Integer lettersOnly = countLettersOnly(psswd);
		Integer numbersOnly = countNumbersOnly(psswd);
		Integer repeatCharactersCaseInsensitive = countRepeatCharactersCaseInsensitive(psswd);
		Integer consecutiveUppercaseLetters = countConsecutiveUppercaseLetters(psswd);
		Integer consecutiveLowercaseLetters = countConsecutiveLowercaseLetters(psswd);
		Integer consecutiveNumbers = countConsecutiveNumbers(psswd);
		Integer sequentialLetters3Plus = countSequentialLetters3Plus(psswd);
		Integer sequentialNumbers3Plus = countSequentialNumbers3Plus(psswd);
		Integer sequentialSymbols3Plus = countSequentialSymbols3Plus(psswd);
		
		totalDeductions = lettersOnly + 
						  numbersOnly + 
						  repeatCharactersCaseInsensitive +
						  consecutiveUppercaseLetters + 
						  consecutiveLowercaseLetters + 
						  consecutiveNumbers + 
						  sequentialLetters3Plus + 
						  sequentialNumbers3Plus + 
						  sequentialSymbols3Plus;
		
		return totalDeductions;
	}
	
	private static Integer countLettersOnly(String psswd) {
				
		Integer onus = 0;
		Integer lettersOnly = 0;
		Integer psswdlength = psswd.length();
		Integer ratio = -1;	    
		
		char[] chars = psswd.toCharArray();

	    for (char c : chars)
	        if(!Character.isLetter(c))
	            lettersOnly += ++lettersOnly;
	    
	    if(lettersOnly == psswdlength)
	    	onus = psswdlength * ratio;//n - 1
	    
		return onus;
	}
	
	private static Integer countNumbersOnly(String psswd) {
		
		Integer onus = 0;
		Integer numbersOnly = 0;
		Integer psswdlength = psswd.length();
		Integer ratio = -1;	    
		
		char[] chars = psswd.toCharArray();

	    for (char c : chars)
	        if(Character.isDigit(c))
	        	numbersOnly += numbersOnly++;
	    
	    if(numbersOnly == psswdlength)
	    	onus = psswdlength * ratio;//n - 1
	    	    
		return onus;
	}
	
	private static Integer countRepeatCharactersCaseInsensitive(String psswd) {
		
		Integer onus = 0;
 
        return onus;
	}
	
	private static Integer countConsecutiveUppercaseLetters(String psswd) {
		
		Integer onus = 0;
		Integer repeat = 0;
		Integer ratio = 2;
        int psswdlength = psswd.length();
 
        char[] chars = psswd.toCharArray();
        
	    for (int i = 1; i < psswdlength; i++) 
            if (chars[i] - chars[i - 1] == 1)
            	if(Character.isUpperCase(chars[i]) && Character.isUpperCase(chars[i - 1]))
            		repeat++;
	    
	    onus = repeat * ratio;//-(n*2)
		
		return onus;
	}
	
	private static Integer countConsecutiveLowercaseLetters(String psswd) {
		
		Integer onus = 0;
		Integer repeat = 0;
		Integer ratio = 2;
        int psswdlength = psswd.length();
 
        char[] chars = psswd.toCharArray();
        
	    for (int i = 1; i < psswdlength; i++) 
            if (chars[i] - chars[i - 1] == 1)
            	if(Character.isLowerCase(chars[i]) && Character.isLowerCase(chars[i - 1]))
            		repeat++;
	    
	    onus = repeat * ratio;//-(n*2)
		
		return onus;
	}
	
	private static Integer countConsecutiveNumbers(String psswd) {
		
		Integer onus = 0;
		Integer repeat = 0;
		Integer ratio = 2;
        int psswdlength = psswd.length();
 
        char[] chars = psswd.toCharArray();
        
	    for (int i = 1; i < psswdlength; i++) 
            if (chars[i] - chars[i - 1] == 1)
            	if(Character.isDigit(chars[i]) && Character.isDigit(chars[i - 1]))
            		repeat++;
	    
	    onus = repeat * ratio;//-(n*2)
		
		return onus;
	}
	
	private static Integer countSequentialLetters3Plus(String psswd) {
		
		Integer sequentialLetters3Plus = 0;
		
		return sequentialLetters3Plus;
	}
	
	private static Integer countSequentialNumbers3Plus(String psswd) {
		
		Integer sequentialNumbers3Plus = 0;
		
		return sequentialNumbers3Plus;
	}
	
	private static Integer countSequentialSymbols3Plus(String psswd) {
		
		Integer sequentialSymbols3Plus = 0;
		
		return sequentialSymbols3Plus;
	}
	
	public static String calculateComplexity(Integer force) {
		
		log.info("calculateComplexity...");
		String complexity = TOO_SHORT;
		
		if(force >= 280)
			complexity = VERY_STRONG;		
		if(force >= 220 && force < 280)
			complexity = STRONG;
		if(force >= 140 && force < 220)
			complexity = GOOD;
		if(force >= 80 && force < 140)
			complexity = WEAK;
		if(force < 80 )
			complexity = VERY_WEAK;
		
		return complexity;
	}
	
	/** 
	 * 
	 	if(force >= 280)
			complexity = VERY_STRONG;		
		if(force >= 220 && force < 280)
			complexity = STRONG;
		if(force >= 140 && force < 220)
			complexity = GOOD;
		if(force >= 80 && force < 140)
			complexity = WEAK;
		if(force < 80 )
			complexity = VERY_WEAK;
			
			
		if(force >= 280)
			score = VERY_STRONG_PERCENT;		
		if(force >= 220 && force < 280)
			score = STRONG_PERCENT;
		if(force >= 140 && force < 220)
			score = GOOD_PERCENT;
		if(force >= 80 && force < 140)
			score = WEAK_PERCENT;
		if(force < 80 )
			score = VERY_WEAK_PERCENT;	
	 * 
	 * */
	
	public static String calculateScore(Integer force) {
		
		log.info("calculateScore...");
		String score = TOO_SHORT_PERCENT;
		
		if(force >= 280)
			score = VERY_STRONG_PERCENT;		
		if(force >= 220 && force < 280)
			score = STRONG_PERCENT;
		if(force >= 140 && force < 220)
			score = GOOD_PERCENT;
		if(force >= 80 && force < 140)
			score = WEAK_PERCENT;
		if(force < 80 )
			score = VERY_WEAK_PERCENT;	
		
		return score;
	}
	
}
