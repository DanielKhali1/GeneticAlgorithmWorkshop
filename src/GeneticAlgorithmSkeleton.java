import java.util.Random;

/**
 * A simple implementation of Genetic Algorithms that uses:
 * 		- Tournament Selection
 * 		- Single point Crossover
 * 
 * This class will attempt to break a given password using GA.
 * 
 * @author AI Club 2018
 */
public class GeneticAlgorithmSkeleton
{
	// Main entry point of the program. This is like the main method in C.
	public static void main(String[] args)
	{
		// Password Generator: https://passwordsgenerator.net/
		// Password Evaluator: https://www.grc.com/haystack.htm
		
		// Our password that will be cracked
		String password = "Hello world!";
		
		// The possible characters in the password
		String vocabulary = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()~?<>{}[],.-=:;'/`";

		// The mutation chance used in the genetic algorithm (1 = 100%, 0.5 = 50%, 0 = 0%)
		double mutationChance = 0.01; // 1%
		
		// The population size of the genetic algorithm
		int populationSize = 50;
		
		// Construct a new genetic algorithm to crack the password!
		GeneticAlgorithmSkeleton ga = new GeneticAlgorithmSkeleton(populationSize, mutationChance, password, vocabulary);
		
		// Keep evolving until the best chromosome is the password
		while(!ga.getBestChromosome().equals(password))
		{
			// Display the best chromosome to the console
			ga.displayBest();
			
			// Evolve!
			ga.evolve();
		}
		
		// After we are done, display the best chromosome to the console
		ga.displayBest();
	}
	
	/** The vocabulary of which to generate random characters from. */
	private final String vocabulary;
	
	/** The target password the GA is going to crack. */
	private final String password;
	
	/** The random number generator used in the GA. */
	private final Random rand;
	
	/** The mutation chance, a value from 0.0 to 1.0. */
	private double mutationChance;
	
	/** The generation number. Starts from 0. Is incremented after each evolve(). */
	private int generation;
	
	/** The current population that contains all the chromosomes. */
	private String[] population;
	
	/**
	 * Creates a new genetic algorithm that has the specified population size and mutation rate,
	 * that attempts to crack the specified password using the given vocabulary as its dictionary.
	 */
	public GeneticAlgorithmSkeleton(int populationCount, double mutationChance, String password, String vocabulary)
	{
		// Store the password / vocabulary for future reference
		this.vocabulary = vocabulary;
		this.password = password;
		
		// Initialize the random number generator
		rand = new Random();
		
		// store the mutation chance
		this.mutationChance = mutationChance;
		
		// start the generation at 0
		generation = 0;
		
		// Initialize Population using the given populatuin
		population = new String[populationCount];
		for(int i = 0; i < populationCount; i++)
		{
			population[i] = getRandomPassword();
		}
	}
	
	/**
	 * Evaluates the given chromosome against the target password.
	 * Your fitness function relies entirely on how you want to model the problem!
	 * 
	 * @return the fitness of the chromosome
	 */
	private int getFitness(String chromosome)
	{
		// TODO: Fill me!
		// Should return a numeric value that represents 'how good' 
		// the chromosome is when compared to the target password.
		// I.E, Bad password guesses should have a lower value than good guesses.
		
		return 0; // Temporary, remove when writing your own
	}
	
	/**
	 * Returns a chromosome from the current population.
	 * You may either use the Tournament or Roulette selection.
	 * 
	 * Tournament does the following:
	 * 		- Selects two chromosomes randomly.
	 * 		- Returns the chromosome with the highest fitness from the two.
	 * 
	 * Roulette does the following:
	 * 		- Evaluates each chromosome (getting its fitness value).
	 * 		- Map their fitnesses to probabilities such that
	 * 			- The higher the fitness, the more probable its chosen.
	 * 		- Select and return one chromosome from the weighted probabilities.
	 * 
	 * @return a chromosome
	 */
	private String selectParent(String[] population)
	{
		// TODO: Fill me!
		// Should return a single chromosome from the current population.
		// You may use either Tournament or Roulette selection.
		
		return null; // Temporary, remove when writing your own
	}
	
	/**
	 * Blends and recombines the genes of two parent chromosomes.
	 * This will return the offspring.
	 * You may use either single-point or two-point crossover.
	 * 
	 * @return the offspring
	 */
	private String crossover(String parent1, String parent2)
	{
		// TODO: Fill me!
		// Should crossover (blend the genes) of the two parent chromosomes and return the offspring.
		// You may use either single-point or two-point crossover.
		
		return null; // Temporary, remove when writing your own
	}
	
	/**
	 * Mutates a chromosome using the a mutation chance.
	 * 
	 * @return the mutated chromosome
	 */
	private String mutate(String chromosome, double mutationChance)
	{
		// TODO: Fill me!
		// Should mutate the given chromosome and return the mutated version.
		
		return null; // Temporary, remove when writing your own
	}
	
	/**
	 * Does a single iteration of the evolution cycle. It consists of evaluating the current population,
	 * then selecting the parents for crossover, which produce an offspring that will undergo mutation.
	 * This process is repeated until the next population has the same population count as the previous
	 * generation.
	 */
	public void evolve()
	{
		String[] nextPopulation = new String[population.length];
		
		// Repeat to fill the next generation
		for(int i = 0; i < population.length; i++)
		{
			// Selection: Select two parents by either tournament or roulette (we use tournament here)
			String parent1 = selectParent(population);
			String parent2 = selectParent(population);
			
			// Crossover: Crossover the two parents by either single or two points (we use single here)
			String offspring = crossover(parent1, parent2);
			
			// Mutation: mutate the offspring by some low rate
			String mutatedOffspring = mutate(offspring, mutationChance);
			
			// Add offspring to next population
			nextPopulation[i] = mutatedOffspring;
		}
		
		population = nextPopulation;
		generation++;
	}
	
	/**
	 * Generates a random password using characters in the provided vocabulary with the same length as the target password.
	 * 
	 * @return generated random password
	 */
	private String getRandomPassword()
	{
		// Initialize a string builder, which is an efficient way to build strings
		StringBuilder sb = new StringBuilder();
		
		// Add random characters from the vocabulary until we get a password with the same length as the target password
		for(int i = 0; i < password.length(); i++)
		{
			sb.append(getRandomCharacter());
		}
		
		// Return the string representation in the string builder
		return sb.toString();
	}
	
	/**
	 * Generates a random character from the vocabulary.
	 * 
	 * @return a random character from the vocabulary
	 */
	private char getRandomCharacter()
	{
		// Get a random number from 0 inclusive to the vocabulary's length exclusive.
		int randomIndex = rand.nextInt(vocabulary.length());
		
		// Return the character at the random index
		return vocabulary.charAt(randomIndex);
	}
	
	/**
	 * Displays the current generation with the best chromosome and its fitness to the console.
	 */
	public void displayBest()
	{
		// Get the best chromosome of the current population
		String bestChromosome = getBestChromosome();
		
		// Get the fitness of the best chromosome
		int highestFitness = getFitness(bestChromosome);
		
		// Get the maximum fitness possible
		int maxFitnessPossible = password.length();
		
		// Display information to the console
		System.out.println("Generation " + generation);
		System.out.println("\tHighest Fitness = " + highestFitness + " / " + maxFitnessPossible);
		System.out.println("\tBest Chromosome = " + bestChromosome);
		System.out.println();
	}
	
	/**
	 * Finds the best chromosome with the highest fitness in the current population.
	 * 
	 * @return the best chromosome
	 */
	public String getBestChromosome()
	{
		// Initialize the best to be the first in the population
		String bestChromosome = population[0];
		int highestFitness = getFitness(bestChromosome);
		
		// Loop through the entire population to see if anyone is better, and if it is,
		// update the bestChromosome/highestFitness variables
		for(int i = 1; i < population.length; i++)
		{
			// Get the fitness of the current chromosome in the population
			int fitness = getFitness(population[i]);
			
			// Compare its fitness against the stored best,
			// and if it is better, set it to be the best
			if(fitness > highestFitness)
			{
				highestFitness = fitness;
				bestChromosome = population[i];
			}
		}
		
		// Return the best chromosome in the current population
		return bestChromosome;
	}
}