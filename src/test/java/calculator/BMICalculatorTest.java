package calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import calculator.util.HeightUnit;
import calculator.util.WeightUnit;

public class BMICalculatorTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testCalculateResults(){
		
		float[] expectedBMI = {27.8f, 23.4f, 58.8f, 34.6f, 40f, 44.4f, 25.7f, 25.1f, 26.3f, 31.1f, 21.6f, 18.4f, 17.5f};
		int index = 0;
		float delta = 0.1f;
		assertEquals(expectedBMI[index++], BMICalculator.calculate(95f, WeightUnit.KILOGRAMS, 185f, HeightUnit.CENTIMETER), delta);
		assertEquals(expectedBMI[index++], BMICalculator.calculate(132.15f, WeightUnit.POUNDS, 160f, HeightUnit.CENTIMETER), delta);
		assertEquals(expectedBMI[index++], BMICalculator.calculate(850000f, WeightUnit.CARATS, 170f, HeightUnit.CENTIMETER), delta);
		assertEquals(expectedBMI[index++], BMICalculator.calculate(10000f, WeightUnit.DECAGRAMS, 170f, HeightUnit.CENTIMETER), delta);
		assertEquals(expectedBMI[index++], BMICalculator.calculate(3174.6f, WeightUnit.OUNCES, 150f, HeightUnit.CENTIMETER), delta);
		assertEquals(expectedBMI[index++], BMICalculator.calculate(0.1f, WeightUnit.TONS, 150f, HeightUnit.CENTIMETER), delta);
		assertEquals(expectedBMI[index++], BMICalculator.calculate(70000f, WeightUnit.GRAMS, 165f, HeightUnit.CENTIMETER), delta);

		assertEquals(expectedBMI[index++], BMICalculator.calculate(70f, WeightUnit.KILOGRAMS, 16.7f, HeightUnit.DECIMETER), delta);
		assertEquals(expectedBMI[index++], BMICalculator.calculate(70f, WeightUnit.KILOGRAMS, 5.348f, HeightUnit.FEET), delta);
		assertEquals(expectedBMI[index++], BMICalculator.calculate(70f, WeightUnit.KILOGRAMS, 59.055f, HeightUnit.INCH), delta);
		assertEquals(expectedBMI[index++], BMICalculator.calculate(70f, WeightUnit.KILOGRAMS, 0.0018f, HeightUnit.KILOMETER), delta);
		assertEquals(expectedBMI[index++], BMICalculator.calculate(70f, WeightUnit.KILOGRAMS, 1.95f, HeightUnit.METER), delta);
		assertEquals(expectedBMI[index++], BMICalculator.calculate(70f, WeightUnit.KILOGRAMS, 2000000000f, HeightUnit.NANOMETER), delta);
	}
	
	@Test
	public void testCalculatesExceptions(){
	    exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Are you a balloon with this weight? (-95.0)");
	    BMICalculator.calculate(-95f, WeightUnit.KILOGRAMS, 185f, HeightUnit.CENTIMETER);
	    exception.expectMessage("Are you a balloon with this weight? (0.0)");
	    BMICalculator.calculate(0f, WeightUnit.KILOGRAMS, 185f, HeightUnit.CENTIMETER);
	    exception.expectMessage("You're so negative with that height (or zero)? (-185.0)");
	    BMICalculator.calculate(95f, WeightUnit.KILOGRAMS, -185f, HeightUnit.CENTIMETER);
	    exception.expectMessage("You're so negative with that height (or zero)? (0.0)");
	    BMICalculator.calculate(95f, WeightUnit.KILOGRAMS, 0f, HeightUnit.CENTIMETER);
	}
	
	@Test
	public void testEvaluation(){
		assertEquals("Severe Thinness", BMICalculator.evaluateBMIIndex(5f));
		assertEquals("Moderate Thinness", BMICalculator.evaluateBMIIndex(16f));
		assertEquals("Mild Thinness", BMICalculator.evaluateBMIIndex(18f));
		assertEquals("Normal", BMICalculator.evaluateBMIIndex(20f));
		assertEquals("Overweight", BMICalculator.evaluateBMIIndex(26f));
		assertEquals("Obese Class I", BMICalculator.evaluateBMIIndex(32f));
		assertEquals("Obese Class II", BMICalculator.evaluateBMIIndex(37f));
		assertEquals("Obese Class III", BMICalculator.evaluateBMIIndex(50f));
	}
	
	@Test
	public void testEvaluationsExceptions(){
	    exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("So your BMI is 0.0... Sorry, I don't believe it");
	    BMICalculator.evaluateBMIIndex(0f);
	    exception.expectMessage("So your BMI is -5.0... Sorry, I don't believe it");
	    BMICalculator.evaluateBMIIndex(-5f);
	}
	
	@Test
	public void testCalculateBMIWithEvaluation(){
		assertEquals("BMI: 17.5\nEvaluatuion: Mild Thinness", BMICalculator.calculateBMIWithEvaluation(70f, WeightUnit.KILOGRAMS, 200f, HeightUnit.CENTIMETER));
	}
	
	@Test
	public void testCalculateBMIWithEvaluationsExceptions(){
	    exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Are you a balloon with this weight? (-95.0)");
	    BMICalculator.calculateBMIWithEvaluation(-95f, WeightUnit.KILOGRAMS, 185f, HeightUnit.CENTIMETER);
	    exception.expectMessage("Are you a balloon with this weight? (0.0)");
	    BMICalculator.calculateBMIWithEvaluation(0f, WeightUnit.KILOGRAMS, 185f, HeightUnit.CENTIMETER);
	    exception.expectMessage("You're so negative/zero with that height? (-185.0)");
	    BMICalculator.calculateBMIWithEvaluation(95f, WeightUnit.KILOGRAMS, -185f, HeightUnit.CENTIMETER);
	    exception.expectMessage("You're so negative/zero with that height? (0.0)");
	    BMICalculator.calculateBMIWithEvaluation(95f, WeightUnit.KILOGRAMS, 0f, HeightUnit.CENTIMETER);
	}
}
