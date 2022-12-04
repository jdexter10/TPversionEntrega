
package tp1.p2.control;

/**
 * Represents the allowed levels in the game.
 *
 */
public enum Level {

	EASY(3, 0.1),
	HARD(5, 0.2),
	INSANE(10, 0.3);
	

	private int numberOfZombies;
	private double zombieFrequency;
	

	private Level(int numberOfZombies, double zombieFrequency) {
		this.numberOfZombies = numberOfZombies;
		this.zombieFrequency = zombieFrequency;
	}


	/**
	 * Parse an string and return any matching level
	 * 
	 * @param inputString string to parse
	 * @return the parsed {@link Level} or <code>null</code> if none match.
	 */
	public static Level valueOfIgnoreCase(String inputString) {
		for (Level level : Level.values()) {
			if (level.name().equalsIgnoreCase(inputString)) {
				return level;
			}
		}
		return null;
	}

	/**
	 * Returns a string representation of all the levels joined with <code>separator</code>
	 * 
	 * @param separator String used as separator
	 * 
	 * @return the string resulted from joining all levels using <code>separator</code>
	 */
	public static String all(String separator) {
		StringBuilder buffer = new StringBuilder();
		int levelCount = 0;
		for (Level level : Level.values()) {
			if (levelCount > 0) {
				buffer.append(separator);
			}
			buffer.append(level.name());
			levelCount++;
		}
		return buffer.toString();
	}
	/**
	 * Devuelve el número de zombies que aparecen dependiendo del nivel
	 * @return Número de zombies que aparecen.
	 */
	public int getNumberOfZombies() {
		return this.numberOfZombies; 
	}
	/**
	 * Devuelve la frecuencia con la que los zombies aparecen dependiendo del nivel
	 * @return  Frecuencia con la que los zombies que aparecen.
	 */
	public double getZombieFrequency() {
		return this.zombieFrequency;
	}

}