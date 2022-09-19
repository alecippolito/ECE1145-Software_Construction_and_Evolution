package hotciv.standard;
import hotciv.framework.*;

public abstract class UnitImpl implements Unit {
    UnitDef unitDef;

    protected UnitImpl(String type, Player owner, int moveCount, int defense, int attack) {
        unitDef = new UnitDef(type, owner, moveCount, defense, attack);
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

    public void setUnitOwner(Player o)
    {
        unitDef.owner = o;
    }

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
    }
}