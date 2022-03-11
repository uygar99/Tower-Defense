package Source;

import java.awt.*;

public class TowerIce extends Tower{
    //TODO

    /**
     *
     */
    public TowerIce(){
        super.cost=15;
        super.range=100;
        super.damage=10;
        super.rateOfFire=20;
        super.towerType=2;
    }

    /**
     *
     * @return boolean
     */
    @Override
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
    public void step() {
        //TODO
        stepCounter++;
      //  System.out.printf("stepCounter: %d\n",stepCounter);
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
        int xNew=super.x - Commons.TowerSize*2;
        int yNew=super.y - Commons.TowerSize*2;
        g.setColor(Color.BLUE);
        g.fillOval(this.x, this.y, Commons.TowerSize, Commons.TowerSize);
        g.setColor(Color.RED);
        g.drawOval(xNew,yNew ,this.range*2,this.range*2);
    }
}
