package hotciv.alternative;

import hotciv.framework.AgingStrategy;

public class AgingStrategyBeta implements AgingStrategy {
    @Override
    public int getAge(boolean endOfRound,int worldAge) {
        if (endOfRound == true) {
            // worldAge varies increments after every round
            if (worldAge < -100) {
                worldAge += 100;
            } else if (worldAge < -1) {
                worldAge += 99;
            } else if (worldAge == -1) {
                worldAge += 2;
            } else if (worldAge == 1) {
                worldAge += 49;
            } else if (worldAge < 1750) {
                worldAge += 50;
            } else if (worldAge < 1900) {
                worldAge += 25;
            } else if (worldAge < 1970) {
                worldAge += 5;
            } else {
                worldAge += 1;
            }
        }
        return worldAge;
    }
}
