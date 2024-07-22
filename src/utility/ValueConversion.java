package utility;


import resources.constants.Constants_DefaultValues;


/**
 * Contains value converter methods.
 *
 * @author Michael Markov
 */
public class ValueConversion
{
    /**
     * Determines diagonal size from square length.
     *
     * @param squareLength
     * @return
     * @author Michael Markov
     */
    public static double getDiagonalSizeFromSquareLength (double squareLength)
    {
        return Math.sqrt(Math.pow(squareLength, Constants_DefaultValues.SQUARE_EXPONENT) * Constants_DefaultValues.TWO);
    }
}
