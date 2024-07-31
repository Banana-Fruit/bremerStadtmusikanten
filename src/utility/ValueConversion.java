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
     * @param squareLength Length of The square.
     * @return Diagonal Size of the square.
     * @author Michael Markov
     */
    public static double getDiagonalSizeFromSquareLength (double squareLength)
    {
        // Takes the root of the square of squareLength multiplied by two
        return Math.sqrt(Math.pow(squareLength, Constants_DefaultValues.SQUARE_EXPONENT) * Constants_DefaultValues.TWO);
    }
}
