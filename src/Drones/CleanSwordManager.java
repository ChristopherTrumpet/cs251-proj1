package Drones;

import CommonUtils.BetterQueue;

import java.io.*;
import java.util.ArrayList;

/**
 * Manages everything regarding the cleaning of swords in our game.
 * Will be integrated with the other drone classes.
 *
 * You may only use java.util.List, java.util.ArrayList, and java.io.* from
 * the standard library.  Any other containers used must be ones you created.
 */
public class CleanSwordManager implements CleanSwordManagerInterface {
    /**
     * Gets the cleaning times per the specifications.
     *
     * @param filename file to read input from
     * @return the list of times requests were filled and times it took to fill them, as per the specifications
     */
    @Override
    public ArrayList<CleanSwordTimes> getCleaningTimes(String filename) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));

            //todo
            int numberOfSwords = 0;
            int swordRequests = 0;
            int swordDirtLevel = 0;

            BetterQueue<Integer> requests = new BetterQueue<>();
            BetterQueue<Integer> swordInventory = new BetterQueue<>();

            ArrayList<CleanSwordTimes> cleanSwordTimes = new ArrayList<>();

            String line;

            if ((line = bf.readLine()) != null) {
                String[] parts = line.split(" ");

                numberOfSwords = Integer.parseInt(parts[0]);
                swordRequests = Integer.parseInt(parts[1]);
                swordDirtLevel = Integer.parseInt(parts[2]);

            }

            for (int i = 0; i < numberOfSwords; i++) {
                swordInventory.add(Integer.parseInt(bf.readLine()));
            }

            for (int j = 0; j < swordRequests; j++) {
                requests.add(Integer.parseInt(bf.readLine()));
            }

            int totalTime = 0;

            while (!requests.isEmpty()) {

                totalTime += swordInventory.remove();
                swordInventory.add(swordDirtLevel);

                CleanSwordTimes newTime = new CleanSwordTimes(totalTime, totalTime - requests.remove());

                cleanSwordTimes.add(newTime);
            }

            return cleanSwordTimes;

        } catch (IOException e) {
            //This should never happen... uh oh o.o
            System.err.println("ATTENTION TAs: Couldn't find test file: \"" + filename + "\":: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }
}
