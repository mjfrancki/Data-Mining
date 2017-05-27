/**
   Calculate the probability of an outcome based on Naive Bayes
   We use the formula given on page 102 of our textbook, where 
   there are some numeric attributes.  This program is given the 
   discrete estimator counts and the normal distribution mean and 
   standard deviation.
 */
import javax.swing.* ;
import java.util.* ;

public class NBpredictor
{
    public static void main(String[] args)
    {

	int[] outlookYesCounts = new int[]{2, 4, 3} ;
	DiscreteEstimator outlookYes = 
	    new DiscreteEstimator(outlookYesCounts, "outlookYes") ;
	int[] outlookNoCounts = new int[]{4, 1, 3} ;
	DiscreteEstimator outlookNo = 
	    new DiscreteEstimator(outlookNoCounts, "outlookNo") ;
	double priorYes = 9.0 / 14 ;
	double priorNo = 5.0 / 14 ;
	NumericEstimator tempYes = 
	    new NumericEstimator(73, 6.2, "tempYes") ;
	NumericEstimator tempNo = 
	    new NumericEstimator(74.8364, 7.384, "tempNo") ;
	NumericEstimator humidYes = 
	    new NumericEstimator(79.1, 10.2, "humidYes") ;
	int[] windyYesCounts = new int[]{7, 4} ;
	DiscreteEstimator windyYes = 
	    new DiscreteEstimator(windyYesCounts, "windyYes") ;
	int[] windyNoCounts = new int[]{3, 4} ;
	DiscreteEstimator windyNo = 
	    new DiscreteEstimator(windyNoCounts, "windyNo") ;

	//Another new day
	//Outlook = sunny, T = 66, H = 90, Windy = true, Play = ?
	System.out.println("----------------------------") ;
	System.out.printf("p(Outlook=sunny | Play=yes) = %s = %.3f\n", 
			  outlookYes.getFraction(0), outlookYes.getP(0)) ;
	System.out.printf("p(T=66 | Play=yes) = %.3f\n", 
			  tempYes.getP(66)) ;
	System.out.printf("p(H=90 | Play=yes) = %.3f\n", 
			  humidYes.getP(90)) ;
	System.out.printf("p(windy=true | Play = yes) = %.3f\n", 
			  windyYes.getP(1)) ;
	System.out.printf("p(yes) = %.3f\n", priorYes) ;

	double likelihoodYes = outlookYes.getP(0) * tempYes.getP(66)
	    * humidYes.getP(90) * windyYes.getP(1) * priorYes ;
	System.out.printf("Likelihood of yes = %.6f\n", likelihoodYes) ;
	System.out.println("----------------------------") ;


	System.out.printf("p(Outlook=suuny | Play=no) = %.3f\n", 
			  outlookNo.getP(0)) ;

	System.out.printf("p(T=66 | Play=no) = %.3f\n", tempNo.getP(66)) ;

	NumericEstimator humidNo = 
	    new NumericEstimator(86.1111, 9.2424, "humidNo") ;
	System.out.printf("p(Humidity=90 | Play=no) = %.3f\n", 
			  humidNo.getP(90)) ;
	System.out.printf("p(Windy=true | Play=no) = %.3f\n", windyNo.getP(1)) ;
	System.out.printf("p(no) = %.3f\n", priorNo) ;

	double likelihoodNo = outlookNo.getP(0) * tempNo.getP(66)
	    * humidNo.getP(90) * windyNo.getP(1)
	    * priorNo ;
	System.out.printf("Likelihood of no = %.6f\n", likelihoodNo) ;

	System.out.println("----------------------------") ;
	double probabilityYes = likelihoodYes / (likelihoodYes + likelihoodNo) ;
	double probabilityNo = likelihoodNo / (likelihoodYes + likelihoodNo) ;
	System.out.printf("Probability of yes = %.2f\n", probabilityYes) ;
	System.out.printf("Probability of no = %.2f\n", probabilityNo) ;
	System.out.println("----------------------------") ;
    }
}
/**
   This class gives a probability P(E|H) for a discrete attribute
 */
class DiscreteEstimator
{
    private int[] counts ;
    private String attribute ;
    private double totalCounts ;
    /**
       Construct the estimator given prior and counts
       @param counts the array of counts for this attribute
       @param attribute the name of the attribute
     */
    public DiscreteEstimator(int[] counts, String attribute)
    {
	this.counts = counts ;
	this.attribute = attribute ;
	totalCounts = 0.0 ;
	for (int count : counts) {
	    totalCounts += count ;
	}
    }
    /**
       Calculates the probability P(E|H) for this attribute-value pair
       @param index the index value of the attribute chosen
     */
    public double getP(int index)
    {
	return counts[index] / totalCounts ;
    }
    /**
       Calculates the probability P(E|H) for this attribute-value pair
       @param index the index value of the attribute chosen
     */
    public String getFraction(int index)
    {
	return "" + counts[index] + "/" + totalCounts ;
    }
    
}
/**
   This class gives a probability P(E|H) for a numeric attribute
 */
class NumericEstimator
{
    private String attribute ;
    private double mean ;
    private double sd ;
    /**
       Construct the estimator given mean and sd
       @param mean the mean of the attribute values
       @param sd the standard deviation of the attribute values
     */
    public NumericEstimator(double mean, double sd, String attribute)
    {
	this.mean = mean ;
	this.sd = sd ;
	this.attribute = attribute ;
    }
    /**
       Calculates the probability P(E|H) for this attribute value
       @param value the value of the attribute
     */
    public double getP(double value)
    {
	double exponent = -(value - mean) * (value - mean) / (2 * sd * sd) ;
	double den = Math.sqrt(2 * Math.PI) * sd ;
	return Math.exp(exponent) / den ;
    }
    
}

