package Source;

import java.awt.*;

public class TowerRegular extends Tower{
    //TODO

    /**
     *
     */
    public TowerRegular(){
        super.cost=20;
        super.range=150;
        super.damage=20;
        super.rateOfFire=20;
        super.towerType=1;
    }

    /**
     *
     * @return boolean
     */
    public boolean check(){
        if(this.cost > Game.getInstance().gold)
        {
            return false;
        }
        else
        {
            Game.getInstance().setGold(Game.getInstance().gold-this.cost);
            String gold = Display.getInstance().getInfoPanel().getCurrentGoldLabel().getText();
            Integer goldInt = Integer.parseInt(gold);
            goldInt= goldInt-this.cost;
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
        int xNew=super.x - 130;
        int yNew=super.y - 130;
        g.setColor(Color.YELLOW);
        g.fillOval(this.x, this.y, Commons.TowerSize, Commons.TowerSize);
        g.setColor(Color.RED);
        g.drawOval(xNew,yNew ,this.range*2,this.range*2);
    }
}
