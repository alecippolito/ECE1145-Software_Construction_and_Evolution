package hotciv.standard;
import hotciv.framework.*;

public abstract class UnitImpl implements Unit {
    UnitStats unitStat;
    Position position;

    protected UnitImpl(String type, Player owner) {
        unitStat = new UnitStats(type, owner);
    }

    public static Unit produceUnit(String type, Player owner) {
        if(type.equals(GameConstants.ARCHER)) {
            return new UnitArcher(owner);
        } else if(type.equals(GameConstants.LEGION)) {
            return new UnitLegion(owner);
        } else if(type.equals(GameConstants.SETTLER)) {
            return new UnitSettler(owner);
        } else {
            return null;
        }
    }

    public String getTypeString() {
        return unitStat.type;
    }

    public Player getOwner() {
        return unitStat.owner;
    }

    public int getMoveCount() {
        return unitStat.moveCount;
    }

    public int getDefensiveStrength() {
        return unitStat.defense;
    }

    public int getAttackingStrength() {
        return unitStat.attack;
    }

    public void action() {}

    public boolean canMove() {return unitStat.canMove();}

    public void moved() {
        unitStat.moved();}

    public void setUnitOwner(Player o)
    {
        unitStat.owner = o;
    }

    public void resetMove() {
        unitStat.resetMove();}

    public Unit getUnitStat() {
        return unitStat;
    }

    public class UnitStats implements Unit {
        protected String type;
        protected Player owner;
        protected int moveCount;
        protected int defense;
        protected int attack;

        public UnitStats(String type, Player owner) {
            this.type = type;
            this.owner = owner;
            if (type == GameConstants.ARCHER) {
                this.moveCount = 1;
                this.defense = 3;
                this.attack = 2;
            }
            if (type == GameConstants.LEGION) {
                this.moveCount = 1;
                this.defense = 2;
                this.attack = 4;
            }
            if (type == GameConstants.SETTLER){
                this.moveCount = 1;
                this.defense = 3;
                this.attack = 0;
            }
        }

        public String getTypeString() {
            return type;
        }

        public Player getOwner() {
            return owner;
        }

        public int getMoveCount() {
            return moveCount;
        }

        public void setDefensiveStrength() {}

        public int getDefensiveStrength() {
            return defense;
        }

        public int getAttackingStrength() {
            return attack;
        }

        public void setUnitOwner(Player o)
        {
            owner = o;
        }

        public void resetMove() {moveCount = 1;}

        public Unit getUnitStat() {
            return null;
        }

        public void action() {}

        public boolean canMove() {return moveCount > 0;}

        public void moved() {moveCount = 0;}

    }

    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (o.getClass() != TileImpl.class) { return false; }
        TileImpl other = (TileImpl) o;
        return position.equals(other.position) && unitStat.type.equals(other.type);
    }
}
