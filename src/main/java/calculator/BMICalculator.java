package calculator;

import calculator.util.HeightUnit;
import calculator.util.WeightUnit;

/**
 * This BMI Calculator is a robust way to calculate a person's BMI index. But why stop there? You
 * can even calculate the BMI of Godzilla or a bug in a user friendly way by selecting the proper 
 * weight and height units.
 * 
 * @author Balázs Simon
 *
 */
public class BMICalculator {
	
	/**
	 * It calculates the body mass index of the person"s weight and height
	 * 
	 * @param weight		the weight of the person
	 * @param weightUnit	the unit of the weight from the options
	 * @param height		the height of the person
	 * @param heightUnit	the unit of the height from the options
	 * @return				calculated bmi
	 */
	public static float calculate(float weight, WeightUnit weightUnit, float height, HeightUnit heightUnit){
		if(weight <= 0)
			throw new IllegalArgumentException("Are you a balloon with this weight? (" + weight + ")");
		if(height <= 0)
			throw new IllegalArgumentException("You're so negative/zero with that height? (" + height + ")");
		float weightInKilos = convertWeightToKilograms(weight, weightUnit);
		float heightInMeters = convertHeightToMeters(height, heightUnit);
		return weightInKilos / (heightInMeters * heightInMeters);
	}
	
	/**
	 * It evaluates the given bmi and returns with the evaluation
	 * 
	 * @param bmi			body mass index
	 * @return				evaluation
	 */
	public static String evaluateBMIIndex(float bmi){
		if(bmi <= 0)
			throw new IllegalArgumentException("So your BMI is " + bmi + "... Sorry, I don't believe it");
		else if(bmi < 16 )
			return "Severe Thinness";
		else if(bmi < 17)
			return "Moderate Thinness";
		else if(bmi < 18.5f)
			return "Mild Thinness";
		else if(bmi < 25)
			return "Normal";
		else if(bmi < 30)
			return "Overweight";
		else if(bmi < 35)
			return "Obese Class I";
		else if(bmi < 40)
			return "Obese Class II";
		else
			return "Obese Class III";
	}
	
	/**
	 * This function calculate the BMI and return with the BMI and the evaluation
	 * 
	 * @param weight		the weight of the person
	 * @param weightUnit	the unit of the weight from the options
	 * @param height		the height of the person
	 * @param heightUnit	the unit of the height from the options
	 * @return				calculated bmi and this evaluation
	 */
	public static String calculateBMIWithEvaluation(float weight, WeightUnit weightUnit, float height, HeightUnit heightUnit){
		float bmiIndex = calculate(weight, weightUnit, height, heightUnit);
		return "BMI: " + bmiIndex + "\nEvaluatuion: " + evaluateBMIIndex(bmiIndex);
	}
	
	/**
	 * Converting all supported weight units to kilogram
	 * 
	 * @param weight		the weight of the person
	 * @param unit			the unit of the weight from the options
	 * @return				the given weight in kg
	 */
	private static float convertWeightToKilograms(float weight, WeightUnit unit){
		switch(unit){
			case KILOGRAMS:
				return weight;
			case DECAGRAMS:
				return weight / 100;
			case GRAMS:
				return weight / 1000;
			case POUNDS:
				return weight * 0.4536f;
			case OUNCES:
				return weight * 0.0283f;
			case CARATS:
				return weight * 0.0002f;
			case TONS:
				return weight * 1000;
			default:
				throw new IllegalArgumentException("Congratulations, you tried to use an unsupported weight unit! (" + unit + ")");
		}
	}
	
	/**
	 * Converting all supported height units to meter
	 * 
	 * @param height		the height of the person
	 * @param unit			the unit of the height from the options
	 * @returnthe 			given height in meter
	 */
	private static float convertHeightToMeters(float height, HeightUnit unit){
		switch(unit){
			case METER:
				return height;
			case DECIMETER:
				return height / 10;
			case CENTIMETER:
				return height / 100;
			case FEET:
				return height * 0.3048f;
			case INCH:
				return height * 0.0254f;
			case KILOMETER:
				return height * 1000;
			case NANOMETER:
				return height * 0.000000001f;
			default:
				throw new IllegalArgumentException("Congratulations, you tried to use an unsupported height unit! (" + unit + ")");
		}
	}
}
