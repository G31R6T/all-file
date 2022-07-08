package concurent.student.second;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Base {

    private static final int STARTER_PEASANT_NUMBER = 5;
    private static final int PEASANT_NUMBER_GOAL = 10;

    // lock to ensure only one unit can be trained at one time
    private final ReentrantLock trainingLock = new ReentrantLock();

    private final String name;
    private final Resources resources = new Resources();
    private final List<Peasant> peasants = Collections.synchronizedList(new LinkedList<>());
    private final List<Footman> footmen = Collections.synchronizedList(new LinkedList<>());
    private final List<Building> buildings = Collections.synchronizedList(new LinkedList<>());
    private final List<Personnel> army = Collections.synchronizedList(new LinkedList<>());

    public Base(String name){
        this.name = name;
        // TODO Create the initial 5 peasants - Use the STARTER_PEASANT_NUMBER constant
        // TODO 3 of them should mine gold
        // TODO 1 of them should cut tree
        // TODO 1 should do nothing
        // TODO Use the createPeasant() method
    }

    public void startPreparation(){
        // TODO Start the building and training preparations on separate threads
        // TODO Tip: use the hasEnoughBuilding method

        // TODO Build 3 farms - use getFreePeasant() method to see if there is a peasant without any work

        // TODO Create remaining 5 peasants - Use the PEASANT_NUMBER_GOAL constant
        // TODO 5 of them should mine gold
        // TODO 2 of them should cut tree
        // TODO 3 of them should do nothing
        // TODO Use the createPeasant() method

        // TODO Build a lumbermill - use getFreePeasant() method to see if there is a peasant without any work

        // TODO Build a blacksmith - use getFreePeasant() method to see if there is a peasant without any work

        // TODO Build a barracks - use getFreePeasant() method to see if there is a peasant without any work

        // TODO Wait for all the necessary preparations to finish

        // TODO Stop harvesting with the peasants once everything is ready
        System.out.println(this.name + " finished creating a base");
        System.out.println(this.name + " peasants: " + this.peasants.size());
        System.out.println(this.name + " footmen: " + this.footmen.size());
        for(Building b : buildings){
            System.out.println(this.name + " has a  " + b.getUnitType().toString());
        }
    }

    /**
     * Assemble the army - call the peasants and footmen to arms
     * @param latch
     */
    public void assembleArmy(CountDownLatch latch){
        // TODO Add the peasants and footmen to the army
        System.out.println(this.name + " is ready for war");
        // the latch is used to keep track of both factions
        latch.countDown();
    }

    /**
     * Starts a war between the two bases.
     *
     * @param enemy Enemy base's personnel
     * @param warLatch Latch to make sure they attack at the same time
     */
    public void goToWar(List<Personnel> enemy, CountDownLatch warLatch){
        // This is necessary to ensure that both armies attack at the same time
        warLatch.countDown();
        try {
            // Waiting for the other army to be ready for war
            warLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TODO Start attacking the enemy with every soldier on a separate thread
        // TODO Wait until the fight is resolved

        // If our army has no personnel, we failed
        if(army.isEmpty()){
            System.out.println(this.name + " has lost the fight");
        } else {
            System.out.println(this.name + " has won the fight");
        }
    }

    /**
     * Resolves the event when a personnel dies;
     * Remove it from the army and update the capacity.
     * @param p The fallen personnel
     */
    public void signalPersonnelDeath(Personnel p){
        // TODO Update resource capacity (this personnel no longer requires the food obviously)
        // TODO Remove from army (and any other container)
        System.out.println(this.name + " has lost a " + p.getUnitType().toString());

    }

    /**
     * Returns a peasants that is currently free.
     * Being free means that the peasant currently isn't harvesting or building.
     *
     * @return Peasant object, if found one, null if there isn't one
     */
    private Peasant getFreePeasant(){
        // TODO implement - use the peasant's isFree() method
        return null;
    }

    /**
     * Creates a peasant.
     * A peasant could only be trained if there are sufficient
     * gold, wood and food for him to train.
     *
     * At one time only one Peasant can be trained.
     *
     * @return The newly created peasant if it could be trained, null otherwise
     */
    private Peasant createPeasant(){
        Peasant result;
        if(resources.canTrain(UnitType.PEASANT.goldCost, UnitType.PEASANT.woodCost, UnitType.PEASANT.foodCost)){

            // TODO 1: Sleep as long as it takes to create a peasant - use sleepForMsec() method
            // TODO 2: Remove costs
            // TODO 3: Update capacity
            // TODO 4: Use the Peasant class' createPeasant method to create the new Peasant

            // TODO Remember that at one time only one peasant can be trained
            System.out.println(this.name + " created a peasant");
            // return result;
        }
        return null;
    }

    private Footman createFootman(){
        Footman result;
        if(resources.canTrain(UnitType.FOOTMAN.goldCost, UnitType.FOOTMAN.woodCost, UnitType.FOOTMAN.foodCost) &&
                false ){  // TODO Check if a barracks is built already

            // TODO 1: Sleep as long as it takes to create a footman - use sleepForMsec() method
            // TODO 2: Remove costs
            // TODO 3: Update capacity
            // TODO 4: Use the Footman class' createFootman method to create the new Footman

            // TODO Remember that at one time only one footman can be trained
            System.out.println(this.name + " created a footman");
            // return result;
        }
        return null;
    }

    public Resources getResources(){
        return this.resources;
    }

    public List<Personnel> getArmy(){
        return this.army;
    }

    public List<Building> getBuildings(){
        return this.buildings;
    }

    public String getName(){
        return this.name;
    }

    /**
     * Helper method to determine if a base has the required number of a certain building.
     *
     * @param unitType Type of the building
     * @param required Number of required amount
     * @return true, if required amount is reached (or surpassed), false otherwise
     */
    private boolean hasEnoughBuilding(UnitType unitType, int required){
        // TODO check in the buildings list if the type has reached the required amount
        return false;
    }

    private static void sleepForMsec(int sleepTime) {
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
        }
    }

}
