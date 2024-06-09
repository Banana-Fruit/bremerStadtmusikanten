package utility;


import resources.constants.Constants_DefaultValues;


public class ValueConversion
{
    public static double getDiagonalSizeFromSquareLength (double squareLength)
    {
        return Math.sqrt(Math.pow(squareLength, Constants_DefaultValues.SQUARE_EXPONENT) * 2);
    }
}
