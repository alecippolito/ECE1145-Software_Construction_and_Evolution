package hotciv.alternative;

import hotciv.framework.AgingStrategy;

public class AgingStrategyAlpha implements AgingStrategy {

    int worldAge;

    AgingStrategyAlpha(){
        worldAge = -4000;
    }
    public int getAge(boolean endOfRound) {
        if (endOfRound == true) {
            worldAge += 100;
        }
        return worldAge;
    }
}
