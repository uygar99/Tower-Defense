package Source;

import java.awt.*;

public class TowerPoison extends Tower{
    //TODO

    /**
     *
     */
    public TowerPoison(){
        super.cost=25;
        super.range=75;
        super.damage=5;
        super.rateOfFire=10;
        super.towerType=3;
    }

    /**
     *
     * @return boolean
     */
    public boolean check(){
        if(this.cost > Game.gold)
        {
            return false;
        }
        else
        {
            Game.setGold(Game.gold -this.cost);
            String gold = Display.getInstance().getInfoPanel().getCurrentGoldLabel().getText();
            Integer goldInt = Integer.parseInt(gold);
            goldInt = goldInt-this.cost;
            gold = goldInt.toString();
            Display.getInstance().getInfoPanel().getCurrentGoldLabel().setText(gold);
            return true;
        }
    }

    /**
     *
     */
    @Override
    public void step() {
        //TODO
        stepCounter++;
        if(stepCounter%this.rateOfFire == 0)
        {
            super.fire();
        }

    }

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        //TODO
        int xNew=super.x - Commons.TowerSize-15;
        int yNew=super.y - Commons.TowerSize-15;
        g.setColor(Color.GREEN);
        g.fillOval(this.x, this.y, Commons.TowerSize, Commons.TowerSize);
        g.setColor(Color.RED);
        g.drawOval(xNew,yNew ,this.range*2,this.range*2);
    }
}
