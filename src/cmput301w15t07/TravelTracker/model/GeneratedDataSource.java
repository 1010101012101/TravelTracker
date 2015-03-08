package cmput301w15t07.TravelTracker.model;

import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import android.util.Log;
import cmput301w15t07.TravelTracker.serverinterface.ResultCallback;

public class GeneratedDataSource extends InMemoryDataSource {
	
	@Override
	public void addUser(ResultCallback<User> callback) {

		User user = new User(UUID.randomUUID());
		user.addObserver(this);
		
		users.put(user.getUUID(), user);

		Random r = new Random();
		
		// Want 100 random claims
		for (int i = 0; i < 100; ++i) {
			// Create claim and set data
			Claim c = new Claim(UUID.randomUUID());
			c.setName(getRandomString(r, 8, 16)); // Random name
			c.setStartDate(new Date()); // The current time
			c.setEndDate(new Date()); // The current time
			
			// Set status
			c.setStatus(
					Status.values()[r.nextInt(Status.values().length)]);
			
			// Could set tags here
			
			claims.put(c.getUUID(), c);
			
			// With 10 items each
			for (int j = 0; j < 10; ++j) {
				
				Item item = new Item(UUID.randomUUID());
				
				item.setClaim(c.getUUID()); // Set parent
				item.setAmount(r.nextFloat()*(10+r.nextInt(6))*r.nextInt(4)); // Set amount
				
				// Set currency
				Currency curr = null;
				for(int k = 0; k < 100; ++k) {
					try {
						 curr = Currency.getInstance(
								 Locale.getAvailableLocales()[r.nextInt(Locale.getAvailableLocales().length)]);
					}
					catch (IllegalArgumentException e) {
						// Some locales aren't supported, just try again
						continue;
					}
					
					// Success!
					break;
				}
				
				// If we make it through on iterations..
				if (curr == null)  {
					Log.w("GeneratedDataSource", "Couldn't get a good currency, defaulting to CAD");
					curr = Currency.getInstance(Locale.CANADA);
				}
				
				item.setCurrency(curr);
				
				item.setDate(new Date()); // The current time
				item.setDescription(getRandomString(r, 20, 76)); // Description 20-75
				
				// Set receipt, can't generate a receipt right now
				//item.setReceipt(receipt);
				items.put(item.getUUID(), item);
			}
		}
		
		callback.onResult(user);
	}

	
	/**
	 * Generate a random string of chars A-Z,a-z,0-9
	 * 
	 * http://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
	 * 
	 * @param minLength Min length of the string generated.
	 * @param maxLength Max length of the string generated
	 * @return Random string of length [minLength, maxLength)
	 */
	private String getRandomString(Random r, int minLength, int maxLength) {
		// Start chars. Two spaces for more probability of spaces. Does it matter really? No.
        final String CHARS = "  ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder str = new StringBuilder();
        int length = minLength + r.nextInt(maxLength-minLength);
        while (str.length() < length) {
            int index = r.nextInt(CHARS.length());
            str.append(CHARS.charAt(index));
        }
        
        return str.toString();
    }
}