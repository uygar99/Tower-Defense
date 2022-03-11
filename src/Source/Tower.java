package Source;

import java.util.ArrayList;

import static java.lang.Math.sqrt;

public abstract class Tower extends Entity{
    public int range;
    public int rateOfFire;
    public int damage;
    public int cost;
    public int x;
    public int y;
    public int towerType;
    public int stepCounter;
    //TODO

    /**
     *
     */
    public Tower(){
        range=0;
        rateOfFire=0;
        damage=0;
        cost=0;
        x=0;
        y=0;
        towerType=0;
        stepCounter=0;
    }

    /**
     *
     * @return boolean
     */
    public abstract boolean check();

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     */
    public void fire(){
        if(!Game.getInstance().getMonsters().isEmpty())
        {
            double minDistance = 10000.0;
            int minIndex = 0;
            for(int i=0;i<Game.getInstance().getMonsters().size();i++)
            {
                int xCoor= Game.getInstance().getMonsters().get(i).getX()+Commons.MonsterSize/2;
                int yCoor = Game.getInstance().getMonsters().get(i).getY()+Commons.MonsterSize/2;
                double distance = sqrt((this.x-xCoor)*(this.x-xCoor) + (this.y-yCoor)*(this.y-yCoor));
                if(distance<minDistance)
                {
                    minDistance=distance;
                    minIndex=i;
                }
            }
            System.out.printf("Min index: %d\n",minIndex);

            Monster m = Game.getInstance().getMonsters().get(minIndex);
            if(minDistance<this.range) {
                m.setHealth(m.getHealth()-this.damage);
                if(this.towerType==1) m.setHit(1);
                if(this.towerType==2) m.setHit(2);
                if(this.towerType==3) m.setHit(3);
            }
            for(int i=0;i<Game.getInstance().getMonsterCount();i++)
            {
                if(Game.getInstance().getMonsters().get(i).getHealth()==0)
                {
                    String kill = Display.getInstance().getInfoPanel().getCurrentKillsLabel().getText();
                    Integer killInt = Integer.parseInt(kill);
                    killInt++;
                    kill = killInt.toString();
                    Display.getInstance().getInfoPanel().getCurrentKillsLabel().setText(kill);
                    String gold = Display.getInstance().getInfoPanel().getCurrentGoldLabel().getText();
                    Integer goldInt = Integer.parseInt(gold);
                    goldInt+=10;
                    gold = goldInt.toString();
                    Display.getInstance().getInfoPanel().getCurrentGoldLabel().setText(gold);
                    Game.getInstance().gold+=10;
                }
            }

        }

    }


    /**
     *
     * @return Vector2D
     */
    public Vector2D normalize(){
        if(x<=150 && x > 100) x = 105;
        if(x<=200 && x > 150) x = 155;
        if(x<=250 && x > 200) x = 205;
        if (x<=300 && x >250) x = 255;
        if(y<=150 && y > 100) y = 105;
        if(y<=200 && y > 150) y = 155;
        if(y<=250 && y > 200) y = 205;
        if (y<=300 && y >250) y = 255;
        Vector2D vector2D = new Vector2D(x,y);
        return vector2D;
    }
    public abstract void step();
    
}
