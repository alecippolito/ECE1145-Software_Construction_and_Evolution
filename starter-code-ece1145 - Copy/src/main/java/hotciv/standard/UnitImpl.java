package hotciv.standard;
import hotciv.framework.*;

public abstract class UnitImpl implements Unit {
    UnitDef unitDef;
    Position position;

    protected UnitImpl(String type, Player owner, int moveCount, int defense, int attack) {
        unitDef = new UnitDef(type, owner, moveCount, defense, attack);
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
        return unitDef.type;
    }

    public Player getOwner() {
        return unitDef.owner;
    }

    public int getMoveCount() {
        return unitDef.moveCount;
    }

    public int getDefensiveStrength() {
        return unitDef.defense;
    }

    public int getAttackingStrength() {
        return unitDef.attack;
    }

    public void action() {}

    public boolean checkMove() {return unitDef.checkMove();}

    public void moved() {unitDef.moved();}

    public void setUnitOwner(Player o)
    {
        unitDef.owner = o;
    }

    public void resetMove() {unitDef.resetMove();}

    public Unit getUnitDef() {
        return unitDef;
    }

    public class UnitDef implements Unit {
        protected String type;
        protected Player owner;
        protected int moveCount;
        protected int defense;
        protected int attack;

        public UnitDef(String type, Player owner, int moveCount, int defense, int attack) {
            this.type = type;
            this.owner = owner;
            this.moveCount = moveCount;
            this.defense = defense;
            this.attack = attack;
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

        public Unit getUnitDef() {
            return null;
        }

        public void action() {}

        public boolean checkMove() {return moveCount > 0;}

        public void moved() {moveCount = 0;}

    }

    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (o.getClass() != TileImpl.class) { return false; }
        TileImpl other = (TileImpl) o;
        return position.equals(other.position) && unitDef.type.equals(other.type);
    }
}
